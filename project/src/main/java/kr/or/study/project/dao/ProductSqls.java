package kr.or.study.project.dao;

public class ProductSqls {
	public static final String SELECT_ALL = "select displayInfoId, placeName, productContent, productDescription, productId, productImageUrl\n"
			+ "from(\n" + "SELECT display_info.id as displayInfoId, place_name as placeName, product_id as productId,\n"
			+ "product.content as productContent, product.description as productDescription\n"
			+ "from display_info left join product on display_info.product_id = product.id\n" + ") a left join \n"
			+ "(\n" + "select product_image.product_id, file_info.save_file_name as productImageUrl from \n"
			+ "(SELECT * FROM product_image where type=\"th\") product_image left join file_info\n"
			+ "on file_info.id = product_image.file_id\n" + ") b on a.productId = b.product_id \n"
			+ "order by displayInfoId limit :start, 4";
	public static final String SELECT_BY_CATEGORY = "select displayInfoId, placeName, productContent, productDescription, productId, productImageUrl\n"
			+ "from(\n" + "SELECT display_info.id as displayInfoId, place_name as placeName, product_id as productId,\n"
			+ "product.content as productContent, product.description as productDescription\n"
			+ "from display_info right join (select * from product where category_id = :category_id) product \n"
			+ "on display_info.product_id = product.id) a left join \n" + "(\n"
			+ "select product_image.product_id, file_info.save_file_name as productImageUrl from \n"
			+ "(SELECT * FROM product_image where type=\"th\") product_image left join file_info\n"
			+ "on file_info.id = product_image.file_id\n" + ") b on a.productId = b.product_id \n"
			+ "order by displayInfoId limit :start, 4";

	public static final String SELECT_COUNT = "SELECT count(*) FROM display_info";
	public static final String SELECT_COUNT_BY_CATEGORY = "SELECT count(*) FROM display_info\n"
			+ "left join product on product_id = product.id where category_id = :category_id";

	public static final String SELECT_BY_DISPLAY_INFO_ID = "SELECT categoryId,categoryName, productContent, productDescription,\n" + 
			"productEvent, productId, display_info.id as displayInfoId, email, homepage,\n" + 
			"opening_hours as openingHours, place_name as placeName, place_lot as placeLot,\n" + 
			"place_street as placeStreet, tel as telephone, display_info.create_date as createDate,\n" + 
			"display_info.modify_date as modifyDate  FROM display_info\n" + 
			"left join (SELECT product.id as productId, category.name as categoryName,category.id as categoryId,\n" + 
			"product.content as productContent, product.description as productDescription, product.event as productEvent\n" + 
			"FROM product left join category on category.id = product.category_id) product\n" + 
			"on productId = display_info.product_id where display_info.id = :displayInfoId";
	
	public static final String SELECT_PRICE = "SELECT product_price.id as productPriceId, price_type_name as priceTypeName,\n" + 
			"price, discount_rate as discountRate, productId, create_date as createDate, modify_date as modifyDate\n" + 
			"FROM product_price right join \n" + 
			"(SELECT display_info.id as displayInfoId, product.id as productId FROM display_info\n" + 
			"left join product on product.id = display_info.product_id where display_info.id = :displayInfoId) product on\n" + 
			"product.productId = product_price.product_id order by productPriceId desc ";
	
	public static final String SELECT_PRODUCT_IMAGE = "SELECT file_info.id as fileInfoId, file_name as fileName,\n" + 
			"save_file_name as saveFileName, content_type as contentType,\n" + 
			"delete_flag as deleteFalg, create_date as createDate, modify_date as modifyDate,\n" + 
			"productImageId, type, productId \n" + 
			"FROM file_info right join (SELECT product_image.id as productImageId, product_image.product_id as productId,\n" + 
			"type, file_id FROM product_image\n" + 
			"right join (SELECT display_info.id, display_info.product_id FROM display_info left join product on\n" + 
			"product.id = display_info.product_id where display_info.id = :displayInfoId) p\n" + 
			"on p.product_id = product_image.product_id) image\n" + 
			"on image.file_id = file_info.id";
	public static final String SELECT_DISPLAY_INFO_ID_IMAGE ="SELECT display_info_id as displayInfoId, display_info_image.id as displayInfoImageId,\n" + 
			"file_info.id as fileId, create_date as createDate, modify_date as modifyDate, content_type as contentType,\n" + 
			"delete_flag as deleteFlag, file_name as fileName, save_file_name as saveFileName\n" + 
			"FROM (SELECT * FROM display_info_image where display_info_id = :displayInfoId) display_info_image \n" + 
			"left join file_info on file_info.id = display_info_image.file_id";
	public static final String SELECT_COMMENT ="select commentId, comment, productId, score, createDate, modifyDate, reservationInfoId,\n" + 
			"reservation_name as reservationName, reservation_tel as reservationTelephone,\n" + 
			"reservation_email as reservationEmail, reservation_date as reservationDate from\n" + 
			"(SELECT reservation_user_comment.id as commentId, comment,  \n" + 
			"reservation_user_comment.product_id as productId, score,   \n" + 
			"create_date as createDate, modify_date as modifyDate,\n" + 
			"reservation_info_id as reservationInfoId from reservation_user_comment\n" + 
			"right join (select display_info.id as displayInfoId, product_id \n" + 
			"from (select * FROM display_info where id = :displayInfoId) display_info left join product\n" + 
			"on product.id = display_info.product_id) display on\n" + 
			"display.product_id = reservation_user_comment.product_id) comments\n" + 
			"left join reservation_info on reservation_info.id = comments.reservationInfoId order by commentId desc";
	
	public static final String SELECT_COMMENT_IMAGE="SELECT imageId, file_info.id as fileId, create_date as createDate, modify_date as modifyDate,\n" + 
			"delete_flag as deleteFlag, file_name as fileName, content_type as contentType,\n" + 
			"save_file_name as saveFileName, reservationInfoId,reservationUserCommentId\n" + 
			"FROM (SELECT reservation_user_comment.reservation_info_id as reservationInfoId,\n" + 
			"reservation_user_comment.id as reservationUserCommentId, file_id, reservation_user_comment_image.id as imageId\n" + 
			"FROM reservation_user_comment_image inner join \n" + 
			"(SELECT * FROM reservation_user_comment where id = :commentId) reservation_user_comment \n" + 
			"on reservation_user_comment.id =reservation_user_comment_image.reservation_user_comment_id) reservation_user_comment_image\n" + 
			"left join file_info \n" + 
			"on file_info.id = reservation_user_comment_image.file_id;";
	
	public static final String SELECT_COMMENT_AVG ="select avg(score) from reservation_user_comment right join\n" + 
			"(select display.id as displayInfoId,product.id as productId from \n" + 
			"(SELECT * FROM display_info where id = :displayInfoId) display left join product \n" + 
			"on display.product_id = product.id) product on\n" + 
			"product.productId = reservation_user_comment.product_id";
	
	public static final String SELECT_PRODUCT_IMAGE_ET ="SELECT file_info.id as fileInfoId, file_name as fileName,\n" + 
			"save_file_name as saveFileName, content_type as contentType,\n" + 
			"delete_flag as deleteFalg, create_date as createDate, modify_date as modifyDate,\n" + 
			"productImageId, type, productId \n" + 
			"FROM file_info right join (SELECT product_image.id as productImageId, product_image.product_id as productId,\n" + 
			"type, file_id FROM (select * from product_image where type=\"et\") product_image\n" + 
			"right join (SELECT display_info.id, display_info.product_id FROM display_info left join product on\n" + 
			"product.id = display_info.product_id where display_info.id = :display_info_id) p\n" + 
			"on p.product_id = product_image.product_id) image\n" + 
			"on image.file_id = file_info.id";
}
