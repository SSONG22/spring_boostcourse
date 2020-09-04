package kr.or.study.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.study.project.dto.Reservation;
import kr.or.study.project.dto.ReservationComment;
import kr.or.study.project.dto.ReservationParam;

public interface ReservationService {
	public List<Reservation> getReservations(String reservationEmail);
	public int getCount(String reservationEmail);
	public ReservationParam addReservation(ReservationParam reservation);
	public ReservationParam cancelReservation(Integer reservationInfoId);
	public ReservationComment addReservationComment(String comment, int productId, int reservationInfoId, int score, MultipartFile file);
}
