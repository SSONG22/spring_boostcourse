package kr.or.study.project.dao;

import static kr.or.study.project.dao.ProductSqls.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.study.project.dto.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> productMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<DisplayInfo> displayInfoMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<ProductPrice> priceMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	private RowMapper<ProductImage> imageMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<DisplayInfoImage> displayInfoImageMapper = BeanPropertyRowMapper
			.newInstance(DisplayInfoImage.class);
	private RowMapper<CommentImage> commentImageMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<Comment> commentMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);

	}

	public List<Product> selectByCategory(Integer categoryId, Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category_id", categoryId);
		params.put("start", start);
		return jdbc.query(SELECT_BY_CATEGORY, params, productMapper);
	}

	public int selectCountByCategory(Integer categoryId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("category_id", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY, namedParameters, Integer.class);
	}

	public List<Product> selectAll(Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		return jdbc.query(SELECT_ALL, params, productMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	public List<DisplayInfo> selectByDisplayInfoId(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_BY_DISPLAY_INFO_ID, params, displayInfoMapper);
	}

	public List<ProductPrice> selectPrice(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRICE, params, priceMapper);
	}

	public List<ProductImage> selectProductImage(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_IMAGE, params, imageMapper);
	}

	public List<DisplayInfoImage> selectDisplayInfoImage(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_DISPLAY_INFO_ID_IMAGE, params, displayInfoImageMapper);
	}

	public List<Comment> selectComment(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		BeanPropertyRowMapper<Comment> mapper = new BeanPropertyRowMapper<>(Comment.class);
		mapper.setPrimitivesDefaultedForNullValue(true);
		List<Comment> comments = jdbc.query(SELECT_COMMENT, params, mapper);
		boolean isComment = comments.get(0).getCreateDate()==null ? false: true;

		System.out.println(comments+"\n"+isComment);
		
		if(isComment) {
			for (Comment c : comments) {
				List<CommentImage> commentImage = selectCommentImage(c.getCommentId());
				c.setCommentImage(commentImage);
			}
			return comments;
		}
		comments.remove(0);
		return comments;
	}

	public List<CommentImage> selectCommentImage(Integer commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);
		return jdbc.query(SELECT_COMMENT_IMAGE, params, commentImageMapper);
	}

	public Double selectAvg(Integer displayInfoId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_COMMENT_AVG, namedParameters, Double.class);
	}

	public List<ProductImage> selectImageTypeEt(Integer displayInfoId){
		Map<String, Integer> params = new HashMap<>();
		params.put("display_info_id",displayInfoId);
		return jdbc.query(SELECT_PRODUCT_IMAGE_ET, params, imageMapper);
	}
}
