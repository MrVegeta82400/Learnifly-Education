package in.LearniflyEducation.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.LearniflyEducation.main.entity.Orders;
import in.LearniflyEducation.main.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public void storeUserOrder(Orders orders) {
		orderRepository.save(orders);
	}
}
