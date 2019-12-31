package com.fairanswers.KubeUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class KubeUserApplication extends SpringBootServletInitializer{
	private static final Logger LOGGER=LoggerFactory.getLogger(KubeUserApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(KubeUserApplication.class, args);
	}

}
