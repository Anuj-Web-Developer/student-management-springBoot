package crud.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.crud.dto.LoginRequest;
import crud.crud.entity.User;
import crud.crud.repository.UserRepository;
import crud.crud.security.JwtUtil;
import crud.crud.service.AuthService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepo;
	@Autowired
	AuthService authServ;

	// Register new user
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		if (userRepo.findByEmail(user.getEmail()).isPresent()) {
			return "User already exists";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "User register successfully";
	}

	// Login Registered user
	@PostMapping("/login")
	    public ResponseEntity<?> login(
	            @RequestBody LoginRequest request) {

	        try {
	        	authenticationManager.authenticate(
		                new UsernamePasswordAuthenticationToken(
		                        request.getEmail(),
		                        request.getPassword()));

	        	User user = userRepo.findByEmail(request.getEmail()).get();
	        	if(user.getStatus().equalsIgnoreCase("Inactive")) {
	        		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your account is deactivate, Activate your account then login");
	        	}
	        	
		        String token = jwtUtil.generateToken(
		                request.getEmail());
		        return ResponseEntity.ok(token);
	        }catch(UsernameNotFoundException e) {
	        	return ResponseEntity.status(404).body("User not found");
	        }catch(Exception e) {
	        	return ResponseEntity.status(401).body("Invalid email or password");
	        }
	    }

	// Forget Password
	@PutMapping("/forget")
	public String forgetPassword(@RequestBody User user) {

		if (userRepo.findByEmail(user.getEmail()).isPresent()) {
			User olduser = userRepo.findByEmail(user.getEmail()).get();
			olduser.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(olduser);
			return "Password Updated Successfully";
		} else {
			return "User is not registered";
		}
	}

	// DeActivate User
	@PutMapping("/deActiveUser/{email}")
	public ResponseEntity<?> deActiveUserController(@PathVariable String email) {
		return authServ.deActiveUserService(email);
	}
	
	// Activate User
	@PutMapping("/activateUser/{email}")
	public ResponseEntity<?> activateUserController(@PathVariable String email) {
		return authServ.activateUserService(email);
	}
}
