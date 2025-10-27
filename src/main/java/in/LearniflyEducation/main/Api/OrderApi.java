 package in.LearniflyEducation.main.Api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import in.LearniflyEducation.main.entity.Orders;
import in.LearniflyEducation.main.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderApi {
	@Autowired
	private OrderService orderService;

//	@PostMapping("/storeOrderDetails")
//	public ResponseEntity<String> storeUserOrderDetails(@RequestBody Orders orders) {
//		
//		orderService.storeUserOrder(orders);
//		return ResponseEntity.ok("Order details store sucessfully.");
//	}
	
	@PostMapping("/storeOrderDetails")
	public ResponseEntity<String> storeUserOrderDetails(@RequestBody Orders orders) throws RazorpayException {
	
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_RGGOfmIFdS8a4R", "SmBS2XaFHlY8VN3DqPHvPZvd");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",orders.getAmount()); // Amount is in currency subunits. 
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "receiptId" + System.currentTimeMillis());

		Order order = razorpayClient.orders.create(orderRequest);
		System.out.println(order);
		
		String orderIdString =order.get("id");
		orders.setOrderIdString(orderIdString);
		
		orderService.storeUserOrder(orders);
		return ResponseEntity.ok("Order details store sucessfully.");
	}	
}
