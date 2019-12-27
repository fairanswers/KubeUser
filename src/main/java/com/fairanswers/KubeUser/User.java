package com.fairanswers.KubeUser;

public class User {
	String name;
	String namespace;
	String token;
	String cert;
	String config;
	
	public User() {}
	
	public User(String id, String namespace, String token, String cert, String config) {
		super();
		this.name = id;
		this.namespace=namespace;
		this.token = token;
		this.cert = cert;
		this.config = config;
	}
	public String getName() {
		return name;
	}
	public void setName(String id) {
		this.name = id;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
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
