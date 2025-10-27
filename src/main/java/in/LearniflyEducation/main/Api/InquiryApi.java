package in.LearniflyEducation.main.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.LearniflyEducation.main.entity.Inqueiry;
import in.LearniflyEducation.main.service.InquiryService;

@RestController
@RequestMapping("/api")
public class InquiryApi {
	
	@Autowired
	private InquiryService inquiryService;
	
	@GetMapping("/searchInquiry")
	public List<Inqueiry> searchInquiry(@RequestParam("phoneNo") String phoneNo) {
		return inquiryService.findInquiryUsingPhoneNo(phoneNo);
	}
}
