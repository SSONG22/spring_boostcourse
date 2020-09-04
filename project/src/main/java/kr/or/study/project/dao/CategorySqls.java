package kr.or.study.project.dao;

public class CategorySqls {
	public static final String SELECT_ALL = "SELECT name, c.id, count(*) as count FROM product p left join category c on p.category_id = c.id group by c.id";
	public static final String SELECT_COUNT = "SELECT count(*) FROM category";

}
