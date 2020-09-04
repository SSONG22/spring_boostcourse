package kr.or.study.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.study.project.dao.CategoryDao;
import kr.or.study.project.dto.Category;
import kr.or.study.project.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getCategories() {
		List<Category> list = categoryDao.selectAll();
		return list;
	}

	@Override
	public int getCount() {
		return categoryDao.selectCount();
	}
	
	
}
