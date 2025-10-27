package in.LearniflyEducation.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.LearniflyEducation.main.service.OrdersChatsService;

@Controller
public class OrderChartsController {
	
	@Autowired
	private OrdersChatsService ordersChatsService;
	
	@GetMapping("/adminProfile")
	public String openAdminProfile(Model model) {
		
		//-----------Graph1------------------------ 
		List<Object[]> listofcoursesoldperday = ordersChatsService.findCoursesSoldPerDay();
		
		List<String> dates1 = new ArrayList<>();
		List<Long> counts1 = new ArrayList<>();
		
		for(Object[] obj : listofcoursesoldperday) {
			dates1.add((String)obj[0]);
			counts1.add((Long)obj[1]);
		}
		
		model.addAttribute("date1",dates1);
		model.addAttribute("counts1",counts1);
		
		//-----------Graph2------------------------
		
		List<Object[]> listofmaxcoursesold = ordersChatsService.findCoursesTotalSale();
		
		List<String> dates2 = new ArrayList<>();
		List<Long> counts2 = new ArrayList<>();
		
		for(Object[] obj : listofmaxcoursesold) {
			dates2.add((String)obj[0]);
			counts2.add((Long)obj[1]);
		}
		
		model.addAttribute("date2",dates2);
		model.addAttribute("counts2",counts2);
		
		//-----------Graph3------------------------
		
	List<Object[]> findMaxCoursesSale = ordersChatsService.findCoursesSale();
		
		List<String> dates3 = new ArrayList<>();
		List<Double> counts3 = new ArrayList<>();
		
		for(Object[] obj : findMaxCoursesSale) {
			dates3.add((String)obj[0]);
			counts3.add((Double)obj[1]);
		}
	
		model.addAttribute("date3",dates3);
		model.addAttribute("counts3",counts3);
		
		
		return "Admin-Profile";
	}

}
