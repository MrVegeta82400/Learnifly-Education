package in.LearniflyEducation.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.FollowUps;

@Repository
public interface FollowUpsRepo extends JpaRepository<FollowUps, Long>
{
	Optional<FollowUps> findByPhoneNo(String phoneNo);
	
	List<FollowUps> findByEmpEmailAndFollowUps(String empEmail, String followUps);
	
}
