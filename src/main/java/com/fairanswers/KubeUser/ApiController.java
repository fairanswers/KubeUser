package com.fairanswers.KubeUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiController {
	@GetMapping("/test")
	public String sayHello() {
		return "SFSG";
	}

}
