package com.fairanswers.KubeUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages="com.fairanswers.KubeUser")
public class KubeUserApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(KubeUserApplication.class, args);
	}

}
