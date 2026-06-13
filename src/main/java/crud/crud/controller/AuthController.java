package crud.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.crud.dto.LoginRequest;
import crud.crud.entity.User;
import crud.crud.repository.UserRepository;
import crud.crud.security.JwtUtil;
import crud.crud.entity.User;

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
	
	// Register new user
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		if(userRepo.findByEmail(user.getEmail()).isPresent()) {
			return "User already exists";
		}
		user.setPassword(
				passwordEncoder.encode(user.getPassword())
				);
		userRepo.save(user);
		return "User register successfully";
	}
	
	// Login Registered user
	 @PostMapping("/login")
	    public String login(
	            @RequestBody LoginRequest request) {

	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getEmail(),
	                        request.getPassword()));

	        return jwtUtil.generateToken(
	                request.getEmail());
	    }
	 
	 // Forget Password
	 @PutMapping("/forget")
	 public String forgetPassword(@RequestBody User user) {
		 
		if(userRepo.findByEmail(user.getEmail()).isPresent()) {
		 	User olduser =  userRepo.findByEmail(user.getEmail()).get();
		 	olduser.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(olduser);
			return "Password Updated Successfully";
		}
		else {
			return "User is not registered";
		}
	 }
}
