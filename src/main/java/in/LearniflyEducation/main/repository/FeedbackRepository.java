package in.LearniflyEducation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>
{

}
