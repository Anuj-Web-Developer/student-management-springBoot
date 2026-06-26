package crud.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Email(message = "Please enter valid email address")
	private String email;
	
	@NotBlank
	private String password;
	
	private String status;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setStatus(String status) {
		this.status= status;
	}
	public String getStatus() {
		return status;
	}
	
	public User(String email, String password, String status) {
		super();
		this.email = email;
		this.password = password;
		this.status = status;
	}
	public User() {
		super();
	}
}
