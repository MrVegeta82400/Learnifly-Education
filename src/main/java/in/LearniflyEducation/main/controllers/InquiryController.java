package in.LearniflyEducation.main.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import in.LearniflyEducation.main.entity.Employee;
import in.LearniflyEducation.main.entity.FollowUps;
import in.LearniflyEducation.main.entity.Inqueiry;
import in.LearniflyEducation.main.service.FollowUpsService;
import in.LearniflyEducation.main.service.InquiryService;

@Controller
@SessionAttributes("sessionEmp")
public class InquiryController {
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private FollowUpsService followUpsService;
	
	@GetMapping("/newInquiry")
	public String openInquiryPage(Model model) {
		model.addAttribute("inquiry", new Inqueiry());
		return "new-inquery";
	}
	
	@PostMapping("/submitInquiryForm")
	public String submitInquiryForm(@ModelAttribute("inquiry") Inqueiry inqueiry,
										@SessionAttribute("sessionEmp") Employee sessionEmp,
											Model model,
										@RequestParam(name="followUpDate",required = false)String followUpDate,
										@RequestParam(name="sourcePage",required = false) String sourcePage)
	{
		inqueiry.setEmpEmail(sessionEmp.getEmail());
		
		LocalDate ld = LocalDate.now();
		String date1 = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		LocalTime lt = LocalTime.now();
		String time1 = lt.format(DateTimeFormatter.ofPattern("HH:mm:ss")); 
		
		inqueiry.setDateOfInquiry(date1);
		inqueiry.setDateOfTime(time1);
		
		
		try {
			
			inquiryService.addNewInquiry(inqueiry);
			
			String status =inqueiry.getStatus() ;
			if(status.equals("Interested - (follow up)") && followUpDate != null) {
				FollowUps followUps = new FollowUps();
				
				followUps.setPhoneNo(inqueiry.getPhoneno());
				followUps.setEmpEmail(sessionEmp.getEmail());
				followUps.setFollowUps(followUpDate);
				
				followUpsService.addFollowUps(followUps);
			}else {
				followUpsService.deleteByPhoneNo(inqueiry.getPhoneno());
			}
			model.addAttribute("successMsg","New Inquiery Added Successfully.");
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg","Some error occured, plz try again after some time.");
		}
		if("followUpsPage".equals(sourcePage))
		{
			model.addAttribute("succMsg","Inquiry Handled Successfully.");
			return "followups";
		}else {
			return "inquiry-management";
		}
	}

}
