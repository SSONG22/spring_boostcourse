package kr.or.study.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.study.project.dao.PromotionDao;
import kr.or.study.project.dto.Promotion;
import kr.or.study.project.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	@Autowired
	PromotionDao promotionDao;

	@Override
	@Transactional(readOnly=false)
	public List<Promotion> getPromotions() {
		return promotionDao.selectAll();
	}

}
