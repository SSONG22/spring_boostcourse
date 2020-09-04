package kr.or.study.project.dao;

public class ReservationSqls {
	public static final String SELECT_RESERV = "select reservation_info.id as reservationInfoId, product_id as productId, display_info_id as displayInfoId,\n"
			+ "reservation_name as reservationName, reservation_email as reservationEmail, reservation_tel as reservationTelephone,\n"
			+ "reservation_date as reservationDate, cancel_flag as cancelYn, create_date as createDate, modify_date as modifyDate,\n"
			+ "totalPrice from (SELECT * FROM reservation_info where reservation_email = :reservation_email ) reservation_info\n"
			+ "left join (select sum(price*(1-discount_rate/100)*count) as totalPrice, p.reservation_info_id from\n"
			+ "(SELECT reservation_info_id, product_price_id, count,\n"
			+ "price, discount_rate FROM reservation_info_price\n"
			+ "left join product_price on product_price.id = product_price_id\n"
			+ ")p group by p.reservation_info_id) resvP on resvP.reservation_info_id = reservation_info.id";
	
	public static final String SELECT_RESERV_CNT = "SELECT count(*) FROM reservation_info where reservation_email = :reservation_email";
	
	public static final String INSERT_RESERV = "insert into reservation_info "
			+ "(product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, create_date, modify_date )"
			+ "values ( :product_id, :display_info_id, :reservation_name, :reservation_tel, :reservation_email, :reservation_date, now(), now());";
	public static final String INSERT_RESERV_PRICE = "insert into reservation_info_price (reservation_info_id, product_price_id, count)"
			+ " values (:reservation_info_id, :product_price_id, :count);";
	public static final String SELECT_RESERV_BY_ID = "SELECT id as reservationInfoId, product_id as productId, reservation_name as reservationName,\n"
			+ "reservation_tel as reservationTelephone, reservation_email as reservationEmail,\n"
			+ "reservation_date as reservationDate, cancel_flag as cancelYn, create_date as createDate,\n"
			+ "modify_date as modifyDate FROM reservation_info where id=:reservation_info_id;";
	public static final String UPDATE_RESERV = "update reservation_info set cancel_flag=1 where id = :reservation_info_id";
	public static final String SELECT_RESERV_PRICE = "SELECT id as reservationInfoPriceId, reservation_info_id as reservationInfoId,\n"
			+ "count, product_price_id as productPriceId\n"
			+ "FROM reservation_info_price where reservation_info_id = :reservation_info_id;";
	public static final String SELECT_RESERV_PARAM = "select * from (select reservation_info.id as reservationInfoId, product_id as productId, reservation_name as reservationName,\n"
			+ "reservation_tel as reservationTelephone, reservation_email as reservationEmail,\n"
			+ "reservation_date as reservationDate, cancel_flag as cancelYn, create_date as createDate, modify_date as modifyDate\n"
			+ "from reservation_info right join\n" + "reservation_info_price on\n"
			+ "reservation_info_price.reservation_info_id = reservation_info.id) reserv\n"
			+ "where reserv.reservationInfoId = :reservation_info_id;";

	public static final String INSERT_FILE_INFO = "insert into file_info (file_name, save_file_name, content_type, delete_flag, create_date, modify_date)"
			+ " values ( :file_name, :save_file_name, :content_type, 0, now(), now());";
	public static final String INSERT_COMMENT = "insert into reservation_user_comment( product_id, reservation_info_id, score, comment, create_date, modify_date)"
			+ "values ( :product_id, :reservation_info_id, :score, :comment, now(), now());";
	public static final String INSERT_COMMENT_IMG = "insert into reservation_user_comment_image"
			+ "(reservation_info_id, reservation_user_comment_id, file_id) values (:reservation_info_id, :reservation_user_comment_id, :file_id);";
	public static final String SELECT_INSERT_COMMENT = "SELECT product_id as productId,reservation_info_id as reservaionInfoId,\n"
			+ "score, comment, create_date as createDate, modify_date as modifyDate,\n" + "id as commentId\n"
			+ "FROM reservation_user_comment where id = :comment_id";
	public static final String SELECT_INSERT_COMMENT_IMG = "select * from (SELECT reservation_user_comment_image.id as imageId, file_name as fileName, file_id as fileId, content_type as contentType, create_date as createDate\n" + 
			"			, modify_date as modifyDate, delete_flag as deleteFlag, reservation_info_id as reservationInfoId,\n" + 
			"			reservation_user_comment_id as reservationUserCommentId,\n" + 
			"			save_file_name as saveFileName FROM reservation_user_comment_image left join\n" + 
			"		file_info on file_info.id = file_id)t where imageId = :imageId;";
}
