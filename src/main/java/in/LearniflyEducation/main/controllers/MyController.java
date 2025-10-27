package in.LearniflyEducation.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import in.LearniflyEducation.main.dto.PurchasedCourse;
import in.LearniflyEducation.main.entity.Courses;
import in.LearniflyEducation.main.entity.User;
import in.LearniflyEducation.main.repository.OrderRepository;
import in.LearniflyEducation.main.repository.UserRepository;
import in.LearniflyEducation.main.service.CourseService;
import in.LearniflyEducation.main.service.UserService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("sessionUser")
public class MyController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping({ "/", "/index" })
	public String indexPage(Model model , @SessionAttribute(name="sessionUser", required=false) User sessionUser) {
		List<Courses> courseList= courseService.getAllCourseDetails();
		model.addAttribute("courseList",courseList);
		if(sessionUser != null) {
			List<Object[]> purchaList= orderRepository.findPurchasedCoursesByEmail(sessionUser.getEmail());
			List <String> purchasedCoursesNameList = new ArrayList<>();
			for (Object[] objects : purchaList) {
				String courseName = (String) objects[3]; 
				purchasedCoursesNameList.add(courseName);
			}
			System.out.println(purchasedCoursesNameList);
			model.addAttribute("purchasedCoursesNameList",purchasedCoursesNameList);
		}
		return "index";
	}

	/* ------Registration details------- */
	@PostMapping("/regform")
	public String regfoamHanbling(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "register";
		} else {
			try {
				user.setBanStatus(false);
				userService.registerUserService(user);
				model.addAttribute("succMsg", "Register Succesfully.");
				return "register";
			} catch (Exception e) {
				model.addAttribute("errorMsg", "Something Went Wrong.");
				e.printStackTrace();
				return "errorpage";
			}
		}
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	/* ------Registration details------- */

	/* ------Login details------- */
	@GetMapping("/login")
	public String loginPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@PostMapping("/loginForm")
	public String handleLoginForm(@ModelAttribute User user, Model model)
	{
		boolean isAuthenticated = userService.loginUserService(user.getEmail(), user.getPassword());
		if(isAuthenticated)
		{
			User authenticatedUser = userRepository.findByEmail(user.getEmail());
			if(authenticatedUser.isBanStatus())
			{
				model.addAttribute("errorMsg", "Sorry, your account is banned, please contact admin, thank you...!!");
				return "login";
			}
			model.addAttribute("sessionUser", authenticatedUser);
			
			return "profilePage";
		}
		else
		{
			model.addAttribute("errorMsg", "Incorrect Email id or Password");
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(SessionStatus sessionStatus)
	{
		sessionStatus.setComplete();
		return "login";
	}
	
	@GetMapping("/profilePage")
	public String openUserProfile()
	{
		return "profilePage";
	}
	
	@Autowired
	private OrderRepository ordersRepository;
	
	@GetMapping("/MyCourse")
	public String myCoursesPage(@SessionAttribute("sessionUser") User sessionUser, Model model)
	{
		List<Object[]> pcDbList = ordersRepository.findPurchasedCoursesByEmail(sessionUser.getEmail());
	
		List<PurchasedCourse> purchasedCoursesList = new ArrayList<>();
	
		for(Object[] course : pcDbList)
		{
//			System.out.println("===============================");
//			System.out.println(course[0]);
//			System.out.println(course[1]);
//			System.out.println(course[2]);
//			System.out.println(course[3]);
//			System.out.println(course[4]);
//			System.out.println("===============================");
			PurchasedCourse purchasedCourse = new PurchasedCourse();
			
			purchasedCourse.setPurchasedOn((String)course[0]);
			purchasedCourse.setDescription((String)course[1]);
			purchasedCourse.setImageUrl((String)course[2]);
			purchasedCourse.setCourseName((String)course[3]);
			purchasedCourse.setUpdatedOn((String)course[4]);
		
			purchasedCoursesList.add(purchasedCourse);
		}
		
		model.addAttribute("purchasedCoursesList", purchasedCoursesList);
		
		return "MyCourse";
	}
	
	@GetMapping("/buy")
	public String openBuyPage() {
		return "buy";
	}
	
	@PostMapping("/handleBuyPage")
	public String handleBuyPage() {
		return "";
	}
	
	

}


