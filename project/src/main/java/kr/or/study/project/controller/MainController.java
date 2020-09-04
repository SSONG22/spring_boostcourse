package kr.or.study.project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.study.project.dto.Comment;
import kr.or.study.project.dto.DisplayInfo;
import kr.or.study.project.dto.DisplayInfoImage;
import kr.or.study.project.dto.Product;
import kr.or.study.project.dto.ProductImage;
import kr.or.study.project.dto.ProductPrice;
import kr.or.study.project.dto.Reservation;
import kr.or.study.project.service.ProductService;
import kr.or.study.project.service.ReservationService;

@Controller
public class MainController {
	@Autowired
	ProductService productService;
	@Autowired
	ReservationService reservationService;

	@GetMapping(path = "/main")
	public String list(@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start, ModelMap model,
			HttpSession session, HttpServletResponse response) {

		List<Product> items = productService.getProducts(categoryId, start);
		int totalCount = productService.getCount(categoryId);
		int listCount = items.size();
		model.addAttribute("items", items);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("listCount", listCount);
		return "main";
	}

	@GetMapping(path = "/bookinglogin")
	public String loginPage() {
		return "login";
	}

	@PostMapping(path = "/myreservation")
	public String login(@RequestParam(value = "email", required = false) String email, HttpSession session) {
		int size = reservationService.getCount(email);
		if (size == 0)
			return "main";

		session.setAttribute("email", email);

		System.out.println(session.getAttribute("email"));
		return "myreservation";
//		else return "main";
	}

	@GetMapping(path = "/myreservation")
	public String myreservation(@RequestParam(value = "email", required = false) String email, HttpSession session,RedirectAttributes redirectAttr) {
		System.out.println(email);
		if(email==null) {
			redirectAttr.addFlashAttribute("errorMessage", "예매 내역이 없습니다.");
			return "redirect:/main";
		}
		int size = reservationService.getCount(email);
		if (size == 0)
		{
			redirectAttr.addFlashAttribute("errorMessage", "예매 내역이 없습니다.");
			return "redirect:/main";
		}
		session.setAttribute("email", email); // 예매정보가 있다면
		return "myreservation";
	}

	@GetMapping(path = "/detail")
	public String detail() {
//		List<ProductImage> productImagesEt = productService.getProductImageEt(displayInfoId);
//		model.addAttribute("etImages", productImagesEt);
		return "detail";
	}

	@GetMapping(path = "/reserve")
	public String reserve(@RequestParam(value = "displayInfoId", required = false) int displayInfoId, ModelMap model) throws JsonProcessingException {
		LocalDate startDate = LocalDate.now();
		long start = startDate.toEpochDay();
		LocalDate endDate = startDate.plusDays(5);
		long end = endDate.toEpochDay();
		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		LocalDate reservationDate = LocalDate.ofEpochDay(randomEpochDay);
		System.out.println(reservationDate); // random date between the range

		DisplayInfo displayInfo = productService.getDisplayInfo(displayInfoId).get(0);
		ProductImage productImages = productService.getImages(displayInfoId).get(0);
		List<ProductPrice> productPrices = productService.getPrices(displayInfoId);
		model.addAttribute("reservationDate", reservationDate);
		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("productId",displayInfo.getProductId());
		
		model.addAttribute("productContent", displayInfo.getProductContent());
		model.addAttribute("openingHours", displayInfo.getOpeningHours());
		model.addAttribute("telephone", displayInfo.getTelephone());
		model.addAttribute("placeName", displayInfo.getPlaceName());
		model.addAttribute("displayImage", productImages.getSaveFileName());
		model.addAttribute("startDate", startDate.minusMonths(1).toString());
		model.addAttribute("endDate", startDate.plusMonths(1).toString());
		//model.addAttribute("productPrices", productPrices);
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(productPrices);
		model.addAttribute("productPrices", jsonStr);

		return "reserve";
	}

	@GetMapping(path = "/review")
	public String review(@RequestParam(value = "displayInfoId", required = false) Integer displayInfoId,
			ModelMap model) {
		Double averageScore = productService.getScoreAvg(displayInfoId);
		if (averageScore == null)
			averageScore = (double) 0;
		List<Comment> comments = productService.getComments(displayInfoId);
		DisplayInfo displayInfo = productService.getDisplayInfo(displayInfoId).get(0);

		model.addAttribute("items", comments);
		model.addAttribute("averageScore", averageScore);
		model.addAttribute("productName", displayInfo.getProductDescription());
		return "review";
	}

	@GetMapping(path = "/reviewWrite")
	public String reviewWrite(@RequestParam(value = "displayInfoId", required = false) Integer displayInfoId,
			@RequestParam(value = "reservationInfoId", required = false) Integer reservationInfoId, ModelMap model) {
		DisplayInfo displayInfo = productService.getDisplayInfo(displayInfoId).get(0);

		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("reservationInfoId", reservationInfoId);
		model.addAttribute("productId",displayInfo.getProductId());
		model.addAttribute("displayInfoName", displayInfo.getProductDescription());
		return "reviewWrite";
	}

	@GetMapping(path = "/image")
	public void imageController(@RequestParam(name = "fileName") String fileName) {
		System.out.println(fileName);

		String osName = System.getProperty("os.name");
		String path = "";
		if (osName.matches(".*Windows.*"))
			path = "c:/tmp/";
		else
			path = "/Users/songi/Documents/boostcourse/tmp/";
		// return path+fileName;
	}

}
