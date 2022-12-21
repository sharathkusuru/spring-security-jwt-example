package com.sharath.security.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharath.security.entity.AuthRequest;
import com.sharath.security.entity.User;
import com.sharath.security.service.UserService;
import com.sharath.security.util.JwtUtil;

@RestController
public class WelcomeController {
	@Autowired
	UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/hii")
	public String welcome() {
		return "Welcome to javatechie !!";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
//		} catch (Exception ex) {
//			throw new Exception("inavalid username/password");
//		}
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		return jwtUtil.generateToken(authRequest.getEmail());
	}

	@GetMapping("/user")
	private List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@PostMapping("/create")
	private int saveUser(@RequestBody User user) {
		userService.saveOrUpdate(user);
		return user.getUserid();
	}
}
