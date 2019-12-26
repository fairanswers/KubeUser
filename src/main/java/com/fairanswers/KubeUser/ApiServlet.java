package com.fairanswers.KubeUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ApiServlet {
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello";
	}

}
