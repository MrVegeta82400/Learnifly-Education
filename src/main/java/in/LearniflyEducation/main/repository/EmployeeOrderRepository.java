package in.LearniflyEducation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.EmployeeOrder;

@Repository
public interface EmployeeOrderRepository extends JpaRepository<EmployeeOrder,Long> {

	
}
