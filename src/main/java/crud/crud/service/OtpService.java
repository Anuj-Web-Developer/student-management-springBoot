package crud.crud.service;

import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crud.crud.entity.Otp;
import crud.crud.entity.User;
import crud.crud.repository.OtpRepository;
import crud.crud.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class OtpService {

	@Autowired
	private OtpRepository otpRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepo;

	// Generate OTP
	public Integer generateOtp() {
		Random random = new Random();
		return 100000 + random.nextInt(900000);
	}

	// Save OTP
	public void saveOtp(String email) {

		// delete old OTPs first
		otpRepo.deleteAllByEmail(email);

		Integer otpNum = generateOtp();

		Otp otp = new Otp();
		otp.setEmail(email);
		otp.setOtp(otpNum);
		otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

		otpRepo.save(otp);
	}

	// Verify OTP
	public ResponseEntity<?> verifyOtp(String email, Integer otp) {

		Optional<Otp> otpOpt = otpRepo.findByEmail(email);

		if (otpOpt.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            .body("OTP not found");
		}
		
		Otp oldOtp = otpOpt.get();

		if (LocalDateTime.now().isAfter(oldOtp.getExpiryTime())) {
			return ResponseEntity.status(HttpStatus.GONE).body("OTP has expired");
		}

		if (!oldOtp.getOtp().equals(otp)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong OTP");
		}

		otpRepo.delete(oldOtp);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("OTP verified successfully");
	}

	// Send OTP
	public String sendOTP(String email) {

		Optional<User> userOpt = userRepo.findByEmail(email);

		if (userOpt.isEmpty()) {
			return "User not found";
		}

		otpRepo.deleteAllByEmail(email);
		Integer otpNum = generateOtp();

		Otp otp = new Otp();
		otp.setEmail(email);
		otp.setOtp(otpNum);
		otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

		otpRepo.save(otp);

		return emailService.sendOtp(email, otpNum);
	}
}