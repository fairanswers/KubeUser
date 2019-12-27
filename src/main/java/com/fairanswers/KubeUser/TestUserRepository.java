package com.fairanswers.KubeUser;

public class TestUserRepository {

	User[] allUsers =new User[] {new User("1", "1", "1","1", "1"), new User("2", "2", "2","2", "2")};
	public User[] list() {
		return allUsers;
	}

	public User[] read(String id) {
		if(id.equals("1") ) {
			return new User[] {allUsers[0]};
		}
		if(id.equals("2") ) {
			return new User[] {allUsers[1]};
		}
		return new User[] {};
	}

}
