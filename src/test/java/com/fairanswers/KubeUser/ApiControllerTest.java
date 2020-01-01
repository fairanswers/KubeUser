package com.fairanswers.KubeUser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiControllerTest {

	@Autowired
	ApiController controller;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCreateandConnect() {
		controller = new ApiController();
	}
	
	@Test
	void testGetList() throws Exception {
		User[] users = controller.getUserRepo().list();
		assertTrue(users.length > 0);
	}
	
	@Test
	void testGetFirst() throws Exception {
		User[] users = controller.getUserRepo().list();
		User user = users[0];
		user = controller.getUserRepo().read(user.getName(), user.getNamespace() );
		assertNotNull(user);
	}

}
