package com.insta.instagram.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.modal.User;
import com.insta.instagram.repository.UserRepository;
import com.insta.instagram.service.UserService;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/signup")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException{
		User createdUser = userService.registerUser(user);
		return new ResponseEntity<User>(createdUser,HttpStatus.OK);
	}
	@GetMapping("/signin")
	public ResponseEntity<User> signinHandler(Authentication auth) throws BadCredentialsException{
		
		Optional<User> opt = userRepository.findByEmail(auth.getName());
		
		if(opt.isPresent()) {
			return new ResponseEntity<User>(opt.get(),HttpStatus.ACCEPTED);
			
		}
		
		throw new BadCredentialsException("Invalid username or password");		
	}

}
