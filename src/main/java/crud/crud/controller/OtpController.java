package crud.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.crud.service.OtpService;

@CrossOrigin("*")
@RequestMapping("/otp")
@RestController
public class OtpController {

	@Autowired
	OtpService otpServ;
	
	// send OTP
	@GetMapping("/sendOtp/{email}")
	public String sendOtpController (@PathVariable String email) {
		 return otpServ.sendOTP(email);
	}
	
	// Verify OTP
	@GetMapping("/verifyOtp/{email}/{otp}")
	public ResponseEntity<?> verifyOtpController(@PathVariable String email, @PathVariable Integer otp) {
		
		return otpServ.verifyOtp(email, otp);
		
	}
}
