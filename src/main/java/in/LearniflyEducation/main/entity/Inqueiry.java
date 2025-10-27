package in.LearniflyEducation.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inqueiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String phoneno ;
	@Column
	private String name;
	@Column
	private String intersetedCourse;
	@Column
	private String discussion;
	@Column
	private String inquiryType;
	@Column
	private String callType;
	@Column
	private String status;
	@Column
	private String empEmail;
	@Column
	private String  dateOfInquiry;
	@Column
	private String dateOfTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntersetedCourse() {
		return intersetedCourse;
	}
	public void setIntersetedCourse(String intersetedCourse) {
		this.intersetedCourse = intersetedCourse;
	}
	public String getDiscussion() {
		return discussion;
	}
	public void setDiscussion(String discussion) {
		this.discussion = discussion;
	}
	public String getInquiryType() {
		return inquiryType;
	}
	public void setInquiryType(String inquiryType) {
		this.inquiryType = inquiryType;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getDateOfInquiry() {
		return dateOfInquiry;
	}
	public void setDateOfInquiry(String dateOfInquiry) {
		this.dateOfInquiry = dateOfInquiry;
	}
	public String getDateOfTime() {
		return dateOfTime;
	}
	public void setDateOfTime(String dateOfTime) {
		this.dateOfTime = dateOfTime;
	}
	
	
}
