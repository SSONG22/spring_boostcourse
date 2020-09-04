package kr.or.study.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.study.project.dto.Promotion;
import kr.or.study.project.service.PromotionService;

@RestController
@RequestMapping(path="/api/promotions")
public class PromotionApiController {

	@Autowired
	PromotionService promotionService;
	
	@GetMapping
	public Map<String, Object> list(){
		List<Promotion> promotions = promotionService.getPromotions();
		Map<String, Object> map = new HashMap<>();
		map.put("items", promotions);
		return map;
	}
}
