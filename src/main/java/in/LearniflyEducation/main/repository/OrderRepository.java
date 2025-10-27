package in.LearniflyEducation.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.LearniflyEducation.main.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	String SELECT_QUERY = "SELECT o.date_of_purchase ,c.description,c.image_url_string,c.name,updated_on FROM orders o JOIN courses c ON o.course_name=c.name WHERE o.user_email=:email";
	@Query(value = SELECT_QUERY, nativeQuery = true)
	List<Object[]> findPurchasedCoursesByEmail(@Param("email") String email);
	
	String SELECT_QUERY2 = "SELECT c.image_url_string, o.course_name, o.amount, o.date_of_purchase, o.id, o.rzp_payment_id FROM orders o JOIN courses c ON o.course_name=c.name WHERE o.user_email=:email";
	@Query(value = SELECT_QUERY2, nativeQuery = true)
	List<Object[]> findCustomerCoursesByEmail(@Param("email") String email);
}
