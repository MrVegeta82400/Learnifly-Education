package in.LearniflyEducation.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.LearniflyEducation.main.service.EmployeeSalesService;

@Controller
public class EmployeeSalesInfoController {
	
	@Autowired
	private EmployeeSalesService employeeSalesService;
	
	@GetMapping("/sales")
	public String openSalesPage(Model model){
		String totalSales = employeeSalesService.findTotalSalesByAllEmployee();
		model.addAttribute("totalSales",totalSales);
		System.out.println(totalSales);
		
		List<Object[]> salesList = employeeSalesService.findTotalSalesByEachEmployee();
		model.addAttribute("salesList",salesList);
		System.out.println(salesList);
		
		return "sales";
	}

}
