package com.fairanswers.KubeUser;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.openapi.models.V1ServiceAccount;
import io.kubernetes.client.openapi.models.V1ServiceAccountList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepository {
	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	private static final Integer TIMEOUT_SEC = 60;

	ApiClient client;
	CoreV1Api api;
	String kubeConfigPath = "kubeconfig.yaml";
	boolean isConnected=false;
	
	public UserRepository() {
		this.connect();
	}
	
	public boolean connect() {
		try {
			client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
		} catch (Exception e) {
			throw new Error("Could not connect to User Repository.", e);
		}
		Configuration.setDefaultApiClient(client);
		api = new CoreV1Api();
		isConnected=true;
		return true;
	}

	public User[] list() throws Exception {
		User [] users=null;
		try {
			int i=0;
			V1ServiceAccountList list = api.listServiceAccountForAllNamespaces(false, null, null, null, 1000000, null, null, TIMEOUT_SEC, false);
			users = new User[list.getItems().size() ];
			for (V1ServiceAccount item : list.getItems()) {
				users[i++]=getUserFromServiceAccount(item);  //Note: incremented
			}
		}
		catch(Exception ex) {
			System.out.println("Exception ="+ex);
		}
		return users;
	}

	private User getUserFromServiceAccount(V1ServiceAccount item) throws ApiException {
		String name = getNameFromServiceAccount(item);
		String namespace = item.getMetadata().getNamespace();
		String secretName = item.getSecrets().get(0).getName();
		V1Secret secret = getSecret(namespace, secretName);
		return getUserFromService(name, namespace, secret);
	}

	private String getCAFromSecret(V1Secret secret) {
		return new String(secret.getData().get("ca.crt") );
	}

	private String getTokenFromSecret(V1Secret secret) {
		return new String(secret.getData().get("token") );
	}

	private V1Secret getSecret(String namespace, String name) throws ApiException {
		V1Secret secret = api.readNamespacedSecret(name, namespace, null, true, true);
		return secret;
	}

	private String getNameFromServiceAccount(V1ServiceAccount item) {
		return item.getMetadata().getName();
	}

	public User read(String name, String namespace) throws ApiException{
		V1ServiceAccount user = api.readNamespacedServiceAccount(name, namespace, null, true, false);
		String secretName = user.getSecrets().get(0).getName();
		V1Secret secret = getSecret(namespace, secretName);
		return getUserFromService(name, namespace, secret);
	}

	private User getUserFromService(String name, String namespace, V1Secret secret) {
		return new User(name, 
				namespace,
				getTokenFromSecret(secret), 
				getCAFromSecret(secret), 
				//getKubeconfigFromServiceAccount(item, api)
				null
				);
	}

}
