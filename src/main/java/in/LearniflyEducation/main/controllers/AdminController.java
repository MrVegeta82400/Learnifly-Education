package in.LearniflyEducation.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.LearniflyEducation.main.entity.Courses;
import in.LearniflyEducation.main.entity.Feedback;
import in.LearniflyEducation.main.service.CourseService;
import in.LearniflyEducation.main.service.FeedbackService;

@Controller
public class AdminController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/Admin-login")
	public String openAdminLogin() {
		return "Admin-login";
	}
	
	@GetMapping("/courseManagement")
	public String openCourseManagementPage(Model model,
					@RequestParam(name="page", defaultValue = "0") int page,
					@RequestParam(name="size", defaultValue = "4") int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Courses> coursesPage = courseService.getAllCourseDetailsByPagination(pageable);
		
		model.addAttribute("coursePage", coursesPage);
		
		return "Course-Managment";
	}

	@PostMapping("/adminloginForm")
	public String adminloginForm(@RequestParam("Admin Email") String aemail,
			@RequestParam("Admin Password") String apassword, Model model) {

		if (aemail.equals("admin@gmail.com") && apassword.equals("admin1234")) {
			return "Admin-Profile";
		} else {
			model.addAttribute("errorMsg", "Invalid user name or password");
			return "Admin-login";
		}
	}
	
	//-------------admin logout------------------------
		@GetMapping("/adminLogout")
		public String adminLogout(SessionStatus sessionStatus)
		{
			sessionStatus.setComplete();
			return "admin-login";
		}
		
		//-------------feedback----------------------------
		@GetMapping("/adminFeedback")
		public String openFeedbackPage(Model model,
				@RequestParam(name="page", defaultValue = "0") int page,
				@RequestParam(name="size", defaultValue = "4") int size)
		{
			Pageable pageable = PageRequest.of(page, size);
			
			Page<Feedback> feedbackPage = feedbackService.getAllFeedbacksByPagination(pageable);
			
			model.addAttribute("feedbackPage", feedbackPage);
			
			return "view-feedback";
		}
		
	    @PostMapping("/updateFeedbackStatus")
	    public String updateFeedbackStatus(@RequestParam("id") Long id, @RequestParam("status") String status, RedirectAttributes redirectAttributes)
	    {
	        boolean success = feedbackService.updateFeedbackStatus(id, status);
	        if (success) {
	            redirectAttributes.addFlashAttribute("successMsg", "Feedback updated successfully.");
	        } else {
	            redirectAttributes.addFlashAttribute("errorMsg", "Failed to update feedback status.");
	        }
	        return "redirect:/adminFeedback"; // Redirect to the page where feedbacks are listed
	    }
}
