package com.fairanswers.KubeUser;

import io.kubernetes.client.openapi.ApiException;

public interface IUserRepository {

	boolean connect();

	User[] list() throws Exception;

	User read(String name, String namespace) throws ApiException;

}