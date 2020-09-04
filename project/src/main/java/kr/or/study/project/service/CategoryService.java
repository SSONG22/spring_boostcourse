package kr.or.study.project.service;

import java.util.List;

import kr.or.study.project.dto.Category;

public interface CategoryService {
	public List<Category> getCategories();
	public int getCount();

}
