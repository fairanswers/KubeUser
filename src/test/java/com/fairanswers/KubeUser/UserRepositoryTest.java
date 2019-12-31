package com.fairanswers.KubeUser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {
	
	UserRepository up= null;

	@BeforeEach
	void setUp() throws Exception {
		up = new UserRepository();
		up.connect();
	}

	@Test
	void testConnection() {
	    assertTrue(up.connect() );
	    up.kubeConfigPath=null;
	    try {up.connect();fail("Should have caught exeception for null kubeconfig.");}catch(Error ex){} 
	}

	@Test
	void testList() throws Exception {
		User[] users = up.list();
		assertTrue(users.length > 0); 
	}

	@Test
	void testRead() throws Exception {
		User[] users = up.list();
		User user = users[0];
		up.read(user.getName(), user.getNamespace());
		assertTrue(user != null, "Should have found first user"); 
		user = users[users.length-1];
		up.read(user.getName(), user.getNamespace());
		assertTrue(user != null, "Should have found last user"); 
	}
}
