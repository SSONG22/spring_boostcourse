package kr.or.study.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.study.project.dto.Reservation;
import kr.or.study.project.dto.ReservationComment;
import kr.or.study.project.dto.ReservationParam;
import kr.or.study.project.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	ReservationService reservationService;

	@GetMapping
	public Map<String, Object> myresv(
			@RequestParam(value = "reservationEmail", required = false) String reservationEmail) {
		// reservations?reservationEmail=kimjinsu%40connect.co.kr
		List<Reservation> reservations = reservationService.getReservations(reservationEmail);
		int size = reservationService.getCount(reservationEmail);
		Map<String, Object> map = new HashMap<>();
		map.put("reservations", reservations);
		map.put("size", size);
		return map;
	}
	@PostMapping
	public ReservationParam postReservation(@RequestBody ReservationParam reservationParam) {
		ReservationParam insertResult = reservationService.addReservation(reservationParam);
//		System.out.println(reservationParam);
//		ReservationParam insertResult = new ReservationParam();
		return insertResult;
	}

	@PutMapping(path = "/{reservationId}")
	public ReservationParam putReservation(@PathVariable(name = "reservationId") Integer reservationId) {
		return reservationService.cancelReservation(reservationId);
	}

	@PostMapping(path = "/{reservationInfoId}/comments")
	public ReservationComment postComment(@PathVariable(required = true) int reservationInfoId,
			@RequestParam(name = "comment", required = true) String comment,
			@RequestParam(name = "productId", required = true) Integer productId,
			@RequestParam(name = "score", required = true) Integer score,
			@RequestParam(value = "attachedImage", required = false) MultipartFile file) {
		return reservationService.addReservationComment(comment, productId, reservationInfoId, score, file);
	}

}
