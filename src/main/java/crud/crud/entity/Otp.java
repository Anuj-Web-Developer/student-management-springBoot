package crud.crud.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private Integer otp;
	private LocalDateTime expiryTime;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	public Integer getId() {
		return id;
	}
	public Otp(String email, Integer otp, LocalDateTime expiryTime) {
		super();
		this.email = email;
		this.otp = otp;
		this.expiryTime = expiryTime;
	}
	public Otp(Integer id, String email, Integer otp, LocalDateTime expiryTime) {
		super();
		this.id = id;
		this.email = email;
		this.otp = otp;
		this.expiryTime = expiryTime;
	}
	public Otp() {
		super();
	}
	
}

