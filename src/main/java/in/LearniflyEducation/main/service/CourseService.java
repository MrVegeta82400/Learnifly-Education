package in.LearniflyEducation.main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.LearniflyEducation.main.entity.Courses;
import in.LearniflyEducation.main.repository.CourseRepository;




@Service
public class CourseService {
	
	private String UPLOAD_DIR="src/main/resources/static/images/uploads/";
	private String IMAGE_URL="http://localhost:8080/images/uploads/";
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Courses> getAllCourseDetails(){
		return courseRepository.findAll();
	}
	
	public Page<Courses> getAllCourseDetailsByPagination(Pageable pageable){
		return courseRepository.findAll(pageable);
	}
	
	public Courses getCourseDetails(String courseName){
		return courseRepository.findByName(courseName);
	}
	
	public void addCourse(Courses courses , MultipartFile courseImg) throws IOException {
		String imgName = courseImg.getOriginalFilename();
		Path imgPath = Paths.get(UPLOAD_DIR+imgName);
		Files.write(imgPath, courseImg.getBytes());
		
		String imgUrl = IMAGE_URL+imgName;
		courses.setImageUrlString(imgUrl);
		courseRepository.save(courses);
	}
	
	public void updateCourseByDetail(Courses course) {
		courseRepository.save(course);
	}
	
	public void deleteCourseDetails(String courseName)
	{
		Courses course = courseRepository.findByName(courseName);
		if(course != null)
		{
 			courseRepository.delete(course);
		}
		else
		{
			throw new RuntimeException("Course not found with name : "+courseName);
		}
	}
	
	
	
	public List<String> getAllCourseName(){
		List<Courses> courseList = courseRepository.findAll();
		List<String> courseNameList = new ArrayList<>();
		
		for(Courses courses : courseList) {
			String courseName = courses.getName();
			courseNameList.add(courseName);
		}
		return courseNameList;
	}
	
}
