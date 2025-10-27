package in.LearniflyEducation.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.EmployeeOrder;

@Repository
public interface EmployeeSalesRepository extends JpaRepository<EmployeeOrder, Long>{
	String SQL_QUERRY1="select sum(amount) as total_sum_of_course_amount from orders where order_id_string not like 'order_%'";
	@Query(value = SQL_QUERRY1,nativeQuery = true)
	String findTotalSalesByAllEmployee();
	
	String SQL_QUERRY2="select e.name as employee_name,e.email as employee_email,e.phoneno as employee_phoneNo,sum(o.amount) as total_sales from employee e join employee_order eo on e.email=eo.employee_email join orders o on eo.order_id = o.order_id_string group by e.name,e.email,e.phoneno;";
	@Query(value = SQL_QUERRY2,nativeQuery = true)
	List<Object[]> findTotalSalesByEachEmployee();
}
