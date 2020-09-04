package kr.or.study.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.study.project.dto.Comment;
import kr.or.study.project.dto.DisplayInfo;
import kr.or.study.project.dto.DisplayInfoImage;
import kr.or.study.project.dto.Product;
import kr.or.study.project.dto.ProductImage;
import kr.or.study.project.dto.ProductPrice;
import kr.or.study.project.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> list(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<Product> items = productService.getProducts(categoryId, start);

		int totalCount = productService.getCount(categoryId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("items", items);
		map.put("totalCount", totalCount);
		return map;
	}

	@GetMapping("/{displayInfoId}")
	public Map<String, Object> getInfo(@PathVariable(name = "displayInfoId") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Double averageScore = productService.getScoreAvg(id);
		if(averageScore == null) averageScore = (double) 0;
		List<Comment> comments = productService.getComments(id);
		DisplayInfo displayInfo = productService.getDisplayInfo(id).get(0);
		DisplayInfoImage displayInfoImage = productService.getDisplayInfoImages(id).get(0);
		List<ProductImage> productImages = productService.getImages(id);
		List<ProductPrice> productPrices = productService.getPrices(id);
		
		map.put("averageScore",averageScore);
		map.put("comments",comments);
		map.put("displayInfo",displayInfo);
		map.put("displayInfoImage",displayInfoImage);
		map.put("productImages",productImages);
		map.put("productPrices",productPrices);
		System.out.println(map);
		return map;
	}
}
