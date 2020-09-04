package kr.or.study.project.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.study.project.config.ApplicationConfig;
import kr.or.study.project.dto.Reservation;
import kr.or.study.project.service.ProductService;
import kr.or.study.project.service.ReservationService;

public class ProductServiceTest {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ProductService productService = ac.getBean(ProductService.class);
		ReservationService service = ac.getBean(ReservationService.class);
		
		List<Reservation>result = service.getReservations("kimjinsu@connect.co.kr");
		int s = service.getCount("kimjinsu@connect.co.kr");
		System.out.println(s);
	}
}
