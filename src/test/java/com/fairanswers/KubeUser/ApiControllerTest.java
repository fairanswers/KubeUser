package com.fairanswers.KubeUser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiControllerTest {

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
		controller = new ApiController();
		User[] users = controller.getUserRepo().list();
		assertTrue(users.length > 0);
	}
	
	@Test
	void testGetFirst() throws Exception {
		controller = new ApiController();
		User[] users = controller.getUserRepo().list();
		User user = users[0];
		user = controller.getUserRepo().read(user.getName(), user.getNamespace() );
		assertNotNull(user);
	}

}
