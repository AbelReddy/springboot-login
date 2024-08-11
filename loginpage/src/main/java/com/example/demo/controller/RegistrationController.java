package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MyAppUser;
import com.example.demo.model.MyAppUserRepository;

@RestController
public class RegistrationController {
	
	
private	MyAppUserRepository userRepo;

private PasswordEncoder passwordEncoder;


	
	
	
	RegistrationController(MyAppUserRepository userRepo , PasswordEncoder passwordEncoder){
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping( value = "/req/signup" , consumes="application/json")
	public MyAppUser createUser(@RequestBody MyAppUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	

}
