package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

	@GetMapping("/login")
	public String login() {
		return "loginpage";
	}
	
	@GetMapping("/req/signup")
	public String signup() {
		return "signuppage";
	}
	
	@GetMapping("/index")
	public String home() {
		return "indexpage";
	}
}
