package kr.or.study.project.dao;

public class PromotionSqls {
	public static final String SELECT_ALL = "SELECT id, a.product_id, productImageUrl FROM (select promotion.id, promotion.product_id from promotion left join product on product.id=promotion.product_id) a\n" + 
			"			left join (select product_id, productImageUrl from (SELECT type,product_id, save_file_name as productImageUrl FROM file_info right join product_image on file_info.id = product_image.file_id)image where type ='th') b\n" + 
			"			on a.product_id = b.product_id";
}
