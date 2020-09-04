package kr.or.study.project.service;

import java.util.List;

import kr.or.study.project.dto.Comment;
import kr.or.study.project.dto.DisplayInfo;
import kr.or.study.project.dto.DisplayInfoImage;
import kr.or.study.project.dto.Product;
import kr.or.study.project.dto.ProductImage;
import kr.or.study.project.dto.ProductPrice;

public interface ProductService {

	public List<Product> getProducts(Integer categoryId, Integer start);
	public int getCount(Integer categoryId);
	public List<Comment> getComments(Integer displayInfoId);
	public List<DisplayInfo> getDisplayInfo(Integer displayInfoId);
	public List<ProductPrice> getPrices(Integer displayInfoId);
	public List<ProductImage> getImages(Integer displayInfoId);
	public List<DisplayInfoImage> getDisplayInfoImages(Integer displayInfoId);
	public Double getScoreAvg(Integer displayInfoId);
	public List<ProductImage> getProductImageEt(Integer displayInfoId);
}
