package in.LearniflyEducation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.LearniflyEducation.main.repository.EmployeeSalesRepository;

@Service
public class EmployeeSalesService {
	
	@Autowired
	private EmployeeSalesRepository employeeSalesRepository;
	
	public String findTotalSalesByAllEmployee() {
		return employeeSalesRepository.findTotalSalesByAllEmployee();
	}
	
	public List<Object[]> findTotalSalesByEachEmployee() {
		return employeeSalesRepository.findTotalSalesByEachEmployee();
	}

}
