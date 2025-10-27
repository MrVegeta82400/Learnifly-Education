package in.LearniflyEducation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.Courses;



@Repository
public interface CourseRepository extends JpaRepository<Courses, Long>{
	
	Courses findByName(String courseName);

}
