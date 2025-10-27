package in.LearniflyEducation.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.Orders;

@Repository
public interface OrdersChartsRepository extends JpaRepository<Orders, Long>{

	String SQL_OUERY1 = "SELECT SUBSTRING_INDEX(date_of_purchase,',',1) AS purchased_date, COUNT(*) AS number_of_course_sold FROM orders GROUP BY purchased_date ORDER BY purchased_date";
	@Query(value = SQL_OUERY1,nativeQuery = true)
	List<Object[]> findCoursesSoldPerDay();
	
	String SQL_OUERY2 = "SELECT course_name , COUNT(*) AS total_sold FROM orders GROUP BY course_name";
	@Query(value = SQL_OUERY2,nativeQuery = true)
	List<Object[]> findCoursesTotalSale();
	
	String SQL_OUERY3 = "select substring_index(date_of_purchase,',',1) as purchased_date, sum(amount) as total_sales_amount from orders group by date_of_purchase order by date_of_purchase";
	@Query(value = SQL_OUERY3,nativeQuery = true)
	List<Object[]> findCoursesSale();
}
