package crud.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import crud.crud.entity.User;
import crud.crud.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	UserRepository userRepo;
	
	// DeActivate user
	public ResponseEntity<?> deActiveUserService(String email) {
		
		Optional<User> user = userRepo.findByEmail(email);
		
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		User usr = user.get();
		usr.setStatus("Inactive");
		userRepo.save(usr);
		return ResponseEntity.ok("Your account deActivate successfully");
		
	}
	
	// Activate user
	public ResponseEntity<?> activateUserService(String email) {
		
		Optional<User> opt_user = userRepo.findByEmail(email);
		if(opt_user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		User user = opt_user.get();
		user.setStatus("Active");
		
		userRepo.save(user);
		return ResponseEntity.ok("Congratulations! Your account activated, Now you can login");
	}
}
