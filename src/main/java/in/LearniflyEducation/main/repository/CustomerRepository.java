package in.LearniflyEducation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.LearniflyEducation.main.entity.User;

public interface CustomerRepository extends JpaRepository<User, Long> 
{
	User findByEmail(String email);
}
