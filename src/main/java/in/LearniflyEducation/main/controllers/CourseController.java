package in.LearniflyEducation.main.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.LearniflyEducation.main.entity.Courses;
import in.LearniflyEducation.main.service.CourseService;

@Controller
public class CourseController {

	private String UPLOAD_DIR = "src/main/resources/static/images/uploads/";
	private String IMAGE_URL = "http://localhost:8080/images/uploads/";

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/coursemanagement")
	public String openCousrseManagment(Model model ,
												@RequestParam(name="page",defaultValue = "0") int page,
												@RequestParam(name="size",defaultValue = "3") int size) 
	{
		Pageable pageable =PageRequest.of(page, size);
		
		Page<Courses> coursePage = courseService.getAllCourseDetailsByPagination(pageable);
		model.addAttribute("coursePage", coursePage);
		return "Course-Managment";
	}
	
	
	
	// -------Add Course Start----------------
		@GetMapping("/add-course")
		public String openAddCourse(Model model) {
			Courses courses = new Courses();
			model.addAttribute("course", courses);
			return "add-course";
		}

		@PostMapping("/addCourseForm")
		public String handleCourseForm(@ModelAttribute("courses") Courses course,
				@RequestParam("courseImage") MultipartFile courseImage, Model model) {
			try {
				courseService.addCourse(course, courseImage);
				model.addAttribute("succMsg", "Course Added Successfully.");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", "Course Not Added Due To Some Error.");
			}
			return "add-course";
		}
		// -------Add Course End----------------

		
		// -------Edit Course Start----------------
		@GetMapping("/editCourse")
		public String openEditCoursePage(@RequestParam("courseName") String courseName,Model model) {

			Courses course = courseService.getCourseDetails(courseName);
			model.addAttribute("course", course);
			model.addAttribute("newCourse", course);
			return "edit-courses";
		}

		@PostMapping("/updateCourseDetailsForm")
		public String openUpdateCoursePage(@ModelAttribute("newCourse") Courses newCourse,
				@RequestParam("courseImage") MultipartFile courseImage,RedirectAttributes redirectAttributes) {

			Courses oldCourseObj = courseService.getCourseDetails(newCourse.getName());
			newCourse.setId(oldCourseObj.getId());
			try {
				if (!courseImage.isEmpty()) {
					String imgName = courseImage.getOriginalFilename();
					Path imgPath = Paths.get(UPLOAD_DIR + imgName);
					Files.write(imgPath, courseImage.getBytes());
					redirectAttributes.addFlashAttribute("succMsg", "Course Updated Successfully.");

					String imgUrl = IMAGE_URL + imgName;
					newCourse.setImageUrlString(imgUrl);
				} else {
					redirectAttributes.addFlashAttribute("succMsg", "Course Updated Successfully.");
					newCourse.setImageUrlString(oldCourseObj.getImageUrlString());
				}
				courseService.updateCourseByDetail(newCourse);
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("errMsg", "Course Not Update.");
				e.printStackTrace();
			}
			return "redirect:/coursemanagement";
		}
		// -------Edit Course End----------------
		
		
		// -------Delete Course Start----------------
		@GetMapping("/deleteCourseDetails")
		public String deleteCourseDetails(@RequestParam("courseName") String courseName, RedirectAttributes redirectAttributes)
		{
			System.out.println("Course name : "+courseName);
			try
			{
				courseService.deleteCourseDetails(courseName);
				redirectAttributes.addFlashAttribute("successMsg", "Course deleted successfully");
			}
			catch(Exception e)
			{
				redirectAttributes.addFlashAttribute("errorMsg", "Course not deleted due to some error");
				e.printStackTrace();
			}
			return "redirect:/coursemanagement";
		}
		// -------Delete Course End----------------

}
