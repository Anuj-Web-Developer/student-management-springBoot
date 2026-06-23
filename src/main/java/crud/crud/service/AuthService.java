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
	
	public ResponseEntity<?> deleteUserService(String email) {
		
		Optional<User> user = userRepo.findByEmail(email);
		
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		userRepo.delete(user.get());
		return ResponseEntity.ok("Your account deleted successfully");
		
	}
}
