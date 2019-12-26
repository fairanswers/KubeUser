package com.fairanswers.KubeUser;

public class User {
	String id;
	String token;
	String cert;
	String config;
	
	public User() {}
	
	public User(String id, String token, String cert, String config) {
		super();
		this.id = id;
		this.token = token;
		this.cert = cert;
		this.config = config;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

	
}
