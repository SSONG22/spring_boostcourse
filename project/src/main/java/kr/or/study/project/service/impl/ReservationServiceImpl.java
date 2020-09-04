package kr.or.study.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.study.project.dao.ReservationDao;
import kr.or.study.project.dto.CommentImage;
import kr.or.study.project.dto.Reservation;
import kr.or.study.project.dto.ReservationComment;
import kr.or.study.project.dto.ReservationParam;
import kr.or.study.project.dto.ReservationPrice;
import kr.or.study.project.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;

	@Override
	@Transactional(readOnly=false)
	public List<Reservation> getReservations(String reservationEmail) {
		return reservationDao.selectReserv(reservationEmail);
	}

	@Override
	@Transactional(readOnly=false)
	public int getCount(String reservationEmail) {
		return reservationDao.selectResvCnt(reservationEmail);
	}

	@Override
	@Transactional(readOnly=false)
	public ReservationParam addReservation(ReservationParam reservation) {
		int reservationInfoId = reservationDao.insertReservation(reservation);
		List<ReservationPrice> prices = reservationDao.insertReservationPrice(reservation.getPrices(), reservationInfoId);
		ReservationParam insertReservation = reservationDao.insertReservationResult(reservationInfoId);
		insertReservation.setPrices(prices);
		return insertReservation;
	}

	@Override
	@Transactional(readOnly=false)
	public ReservationParam cancelReservation(Integer reservationInfoId) {
		return reservationDao.updateReservation(reservationInfoId);
	}

	@Override
	@Transactional(readOnly=false)
	public ReservationComment addReservationComment(String comment, int productId, int reservationInfoId, int score, MultipartFile file) {
		int commentId = reservationDao.insertComment(comment, productId, reservationInfoId, score);
		CommentImage commentImage = null;
		if(file != null) {
			int fileId = reservationDao.insertFileInfo(file);
			int commentImageId = reservationDao.insertCommentImage(commentId, reservationInfoId, fileId);
			commentImage = reservationDao.selectCommentImage(commentImageId);
		}
		ReservationComment reservationComment = reservationDao.selectComment(commentId);
		reservationComment.setCommentImage(commentImage);

		return reservationComment;
	}
	

}
