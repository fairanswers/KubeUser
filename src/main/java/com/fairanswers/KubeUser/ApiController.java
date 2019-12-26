package com.fairanswers.KubeUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	private UserRepository userRepo = new UserRepository();
	
	@GetMapping("/")
	public User[] list() {
		return userRepo.list();
	}

	@GetMapping("/test")
	public String sayHello() {
		return "SFSG";
	}

}
