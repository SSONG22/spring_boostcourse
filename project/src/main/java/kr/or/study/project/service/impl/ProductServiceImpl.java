package kr.or.study.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.study.project.dao.ProductDao;
import kr.or.study.project.dto.Comment;
import kr.or.study.project.dto.DisplayInfo;
import kr.or.study.project.dto.DisplayInfoImage;
import kr.or.study.project.dto.Product;
import kr.or.study.project.dto.ProductImage;
import kr.or.study.project.dto.ProductPrice;
import kr.or.study.project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	@Transactional(readOnly=false)
	public int getCount(Integer categoryId) {
		if(categoryId == 0) return productDao.selectCount();
		return productDao.selectCountByCategory(categoryId);
	}

	@Override
	@Transactional(readOnly=false)
	public List<Product> getProducts(Integer categoryId, Integer start) {
		if (categoryId == 0)
			return productDao.selectAll(start);
		else
			return productDao.selectByCategory(categoryId, start);
	}

	@Override
	@Transactional(readOnly=false)
	public List<Comment> getComments(Integer displayInfoId) {
		return productDao.selectComment(displayInfoId);
	}

	@Override
	@Transactional(readOnly=false)
	public List<DisplayInfo> getDisplayInfo(Integer displayInfoId) {
		return productDao.selectByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly=false)
	public List<ProductPrice> getPrices(Integer displayInfoId) {
		return productDao.selectPrice(displayInfoId);
	}

	@Override
	@Transactional(readOnly=false)
	public List<ProductImage> getImages(Integer displayInfoId) {
		return productDao.selectProductImage(displayInfoId);
	}

	@Override
	@Transactional(readOnly=false)
	public List<DisplayInfoImage> getDisplayInfoImages(Integer displayInfoId) {
		return productDao.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	@Transactional(readOnly=false)
	public Double getScoreAvg(Integer displayInfoId) {
		return productDao.selectAvg(displayInfoId);
	}

	@Override
	@Transactional(readOnly=false)
	public List<ProductImage> getProductImageEt(Integer displayInfoId) {
		return productDao.selectImageTypeEt(displayInfoId);
	}

}
