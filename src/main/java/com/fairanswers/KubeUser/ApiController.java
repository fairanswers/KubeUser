package com.fairanswers.KubeUser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ComponentScan("com.fairanswers.KubeUser")
@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private UserRepository userRepo;
	
	ApiController(){
	}
	
	@GetMapping(path= {"/", "/{namespace}/{name}"} )
	public User[] list(@PathVariable("namespace") Optional<String> namespace, @PathVariable("name") Optional<String> name) throws Exception {
		if(!namespace.isPresent() && !name.isPresent()) {
			return userRepo.list();
		}
		if(namespace.isPresent() && name.isPresent()) {
			return new User[] {userRepo.read(namespace.get(), name.get() )};
		}
		throw new Exception("User and Namespace must be both present, or both blank");
		
	}

	@GetMapping("/test")
	public String sayHello() {
		return "SFSG";
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

}
