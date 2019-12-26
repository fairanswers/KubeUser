package com.fairanswers.KubeUser;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	private UserRepository userRepo = new UserRepository();
	
	@GetMapping(path= {"/", "/{id}"} )
	public User[] list(@PathVariable("id") Optional<String> id) {
		if(!id.isPresent()) {
			return userRepo.list();
		}else {
			return userRepo.read(id.get());
		}
		
	}

	@GetMapping("/test")
	public String sayHello() {
		return "SFSG";
	}

}
