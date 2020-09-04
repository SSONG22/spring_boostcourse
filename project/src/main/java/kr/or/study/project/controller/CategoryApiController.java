package kr.or.study.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.study.project.dto.Category;
import kr.or.study.project.service.CategoryService;

@RestController
@RequestMapping(path="/api/categories")
public class CategoryApiController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public Map<String, Object> list(){
		List<Category> categories = categoryService.getCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("categories", categories);
		return map;
	}
}
