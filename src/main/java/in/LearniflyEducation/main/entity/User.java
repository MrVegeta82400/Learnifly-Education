package in.LearniflyEducation.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9._%+]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$",message = "Invalid email of a user.")
	private String email;
	@Column
	@Pattern(regexp = "^[a-z,A-Z ]{5,25}$",message = "Invalid name of a user.")
	private String name;
	@Column
	@Pattern(regexp = "^[0-9]{10}$",message = "Invalid phone number of a user.")
	private String phoneno;
	@Column
	@Pattern(regexp = "^[a-zA-Z]{3,25}$",message = "Invalid city of a user.")
	private String city;
	@Column
	@Pattern(regexp = "^[a-z,A-Z,0-9,@]{5,25}$",message = "Invalid password of a user.")
	private String password;
	@Column
	private Boolean banStatus;
	
	public Boolean isBanStatus() {
		return banStatus;
	}
	public void setBanStatus(boolean banStatus) {
		this.banStatus = banStatus;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
