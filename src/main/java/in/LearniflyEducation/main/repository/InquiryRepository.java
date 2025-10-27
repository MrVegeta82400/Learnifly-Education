package in.LearniflyEducation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.Inqueiry;
import java.util.List;


@Repository
public interface InquiryRepository extends JpaRepository<Inqueiry,Long> {
	 List<Inqueiry> findByPhoneno(String phoneno);
}
