package in.LearniflyEducation.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String courseName;
	@Column
	private String userEmail;
	@Column
	private String dateOfPurchase;
	@Column
	private String rzpPaymentId;
	@Column
	private String amount;
	@Column
	private String orderIdString;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrderIdString() {
		return orderIdString;
	}
	public void setOrderIdString(String orderIdString) {
		this.orderIdString = orderIdString;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getRzpPaymentId() {
		return rzpPaymentId;
	}
	public void setRzpPaymentId(String rzpPaymentId) {
		this.rzpPaymentId = rzpPaymentId;
	}
	
	
	
}
