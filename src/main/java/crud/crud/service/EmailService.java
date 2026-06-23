package crud.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public String sendOtp(String email, Integer otp) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(email);
		message.setSubject("Email verification otp");
		message.setText("Your OTP is:"+ otp);
		
		mailSender.send(message);
		return "OTP send successfully";
	}
}
