package in.LearniflyEducation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.LearniflyEducation.main.repository.OrdersChartsRepository;

@Service
public class OrdersChatsService {

	@Autowired
	private OrdersChartsRepository ordersChartsRepository;
	
	public List<Object[]> findCoursesSoldPerDay(){
		return ordersChartsRepository.findCoursesSoldPerDay();
	}
	
	public List<Object[]> findCoursesTotalSale(){
		return ordersChartsRepository.findCoursesTotalSale();
	}
	
	public List<Object[]> findCoursesSale(){
		return ordersChartsRepository.findCoursesSale();
	}
	
	
}
