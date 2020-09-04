package kr.or.study.project.dao;

import static kr.or.study.project.dao.ProductSqls.SELECT_BY_DISPLAY_INFO_ID;
import static kr.or.study.project.dao.ReservationSqls.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.or.study.project.dto.CommentImage;
import kr.or.study.project.dto.DisplayInfo;
import kr.or.study.project.dto.Reservation;
import kr.or.study.project.dto.ReservationComment;
import kr.or.study.project.dto.ReservationParam;
import kr.or.study.project.dto.ReservationPrice;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<Reservation> reservationMapper = BeanPropertyRowMapper.newInstance(Reservation.class);
	private RowMapper<DisplayInfo> displayInfoMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<ReservationParam> rervParamMapper = BeanPropertyRowMapper.newInstance(ReservationParam.class);
	private RowMapper<ReservationPrice> rervPriceMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);
	private RowMapper<CommentImage> commentImgMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<ReservationComment> commentMapper = BeanPropertyRowMapper.newInstance(ReservationComment.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Reservation> selectReserv(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservation_email", reservationEmail);
		List<Reservation> list = jdbc.query(SELECT_RESERV, params, reservationMapper);
		for (Reservation r : list) {
			DisplayInfo displayInfo = selectByDisplayInfoId(r.getDisplayInfoId());
			r.setDisplayInfo(displayInfo);
		}

		return list;
	}

	public DisplayInfo selectByDisplayInfoId(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_BY_DISPLAY_INFO_ID, params, displayInfoMapper).get(0);
	}

	public int selectResvCnt(String reservationEmail) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("reservation_email", reservationEmail);
		return jdbc.queryForObject(SELECT_RESERV_CNT, namedParameters, Integer.class);
	}

	public int insertReservation(ReservationParam reservation) {
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("product_id", reservation.getProductId());
		namedParameters.addValue("display_info_id", reservation.getDisplayInfoId());
		namedParameters.addValue("reservation_name", reservation.getReservationName());
		namedParameters.addValue("reservation_tel", reservation.getReservationTelephone());
		namedParameters.addValue("reservation_email", reservation.getReservationEmail());
		namedParameters.addValue("reservation_date", reservation.getReservationYearMonthDay());
		jdbc.update(INSERT_RESERV, namedParameters,generatedKeyHolder);
		int reservationInfoId = generatedKeyHolder.getKey().intValue();		
		//insertReservationPrice(reservation.getPrices(), reservationInfoId);
		return reservationInfoId;
	}

	public List<ReservationPrice> insertReservationPrice(List<ReservationPrice> price, Integer reservationInfoId) {
		Map<String, Object> params;
		for(ReservationPrice p : price) {
			params = new HashMap<>();
			params.put("count", p.getCount());
			params.put("product_price_id", p.getProductPriceId());
			params.put("reservation_info_id", reservationInfoId);
			jdbc.update(INSERT_RESERV_PRICE, params);
			p.setReservationInfoId(reservationInfoId);
		}
		return price;
	}
	
	public ReservationParam insertReservationResult(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservation_info_id", reservationInfoId);
		return jdbc.query(SELECT_RESERV_BY_ID, params, rervParamMapper).get(0);
	}
	
	public ReservationParam updateReservation(int reservationInfoId) {
		Map<String,Integer> params = new HashMap<>();
		params.put("reservation_info_id", reservationInfoId);
		jdbc.update(UPDATE_RESERV, params);
		ReservationParam reservation = jdbc.query(SELECT_RESERV_PARAM, params,rervParamMapper).get(0);
		List<ReservationPrice> prices = jdbc.query(SELECT_RESERV_PRICE, params,rervPriceMapper);
		reservation.setPrices(prices);
		return reservation;
	}
	
	//comment
	public int insertFileInfo(MultipartFile file) {
		String osName = System.getProperty("os.name");
		LocalDate nowDate = LocalDate.now();
		String path = "";
		if (osName.matches(".*Windows.*"))
			path = "c:/tmp/";
		else
			path = "/Users/songi/Documents/boostcourse/tmp/";
		try (FileOutputStream fos = new FileOutputStream(path + nowDate.toString() + file.getOriginalFilename());
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte buffer[] = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("file_name", file.getOriginalFilename());
		namedParameters.addValue("save_file_name", nowDate.toString()+file.getOriginalFilename());
		namedParameters.addValue("content_type", file.getContentType());
		
		jdbc.update(INSERT_FILE_INFO, namedParameters,generatedKeyHolder);
		return generatedKeyHolder.getKey().intValue(); // return insertFileId
	}
	public int insertComment(String comment, int productId, int reservationInfoId, int score) {
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("product_id", productId);
		namedParameters.addValue("reservation_info_id", reservationInfoId);
		namedParameters.addValue("score", score);
		namedParameters.addValue("comment", comment);
		jdbc.update(INSERT_COMMENT, namedParameters,generatedKeyHolder);
		return generatedKeyHolder.getKey().intValue(); //return insertCommentId
	}
	public int insertCommentImage(int commentId, int reservationInfoId, int fileId) {
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("reservation_user_comment_id", commentId);
		namedParameters.addValue("reservation_info_id", reservationInfoId);
		namedParameters.addValue("file_id", fileId);
		jdbc.update(INSERT_COMMENT_IMG, namedParameters,generatedKeyHolder);
		return generatedKeyHolder.getKey().intValue(); //return insertCommentImageId
	}
	public CommentImage selectCommentImage(int imageId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("imageId", imageId);
		return jdbc.query(SELECT_INSERT_COMMENT_IMG, params, commentImgMapper).get(0);
	}
	public ReservationComment selectComment(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("comment_id", commentId);
		return jdbc.query(SELECT_INSERT_COMMENT, params, commentMapper).get(0);
	}
}

