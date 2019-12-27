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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple example of how to use the Java API from an application outside a kubernetes cluster
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.KubeConfigFileClientExample"
 *
 * <p>From inside $REPO_DIR/examples
 */



public class UserRepository {

	private static final Integer TIMEOUT_SEC = 60;

	public boolean connect() {
		
		return true;
	}

	public User[] list() {
		String kubeConfigPath = "kubeconfig.yaml";
		User [] users = new User[100];

		try {
			// loading the out-of-cluster config, a kubeconfig from file-system
			ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
	
			// set the global default api-client to the in-cluster one from above
			Configuration.setDefaultApiClient(client);
	
			// the CoreV1Api loads default api-client from global configuration.
			CoreV1Api api = new CoreV1Api();
	
			int i=0;
			// invokes the CoreV1Api client
			//V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
			V1ServiceAccountList list = api.listServiceAccountForAllNamespaces(false, null, null, null, 1000000, null, null, TIMEOUT_SEC, false);
			for (V1ServiceAccount item : list.getItems()) {
				users[i]=getUserFromServiceAccount(api, item);
				if(i==99) {
					break;
				}
				i++;
			}
		}
		catch(Exception ex) {
			System.out.println("Exception ="+ex);
		}
		return users;
	}

	private User getUserFromServiceAccount(CoreV1Api api, V1ServiceAccount item) throws ApiException {
		String name = getNameFromServiceAccount(item);
		String namespace = item.getMetadata().getNamespace();
		String secretName = item.getSecrets().get(0).getName();
		V1Secret secret = getSecret(namespace, secretName, api);
		return new User(name, 
				getTokenFromSecret(secret), 
				getCAFromSecret(secret), 
				//getKubeconfigFromServiceAccount(item, api)
				null
				);
	}

	private String getCAFromSecret(V1Secret secret) {
		return new String(secret.getData().get("ca.crt") );
	}

	private String getTokenFromSecret(V1Secret secret) {
		return new String(secret.getData().get("token") );
	}

	private V1Secret getSecret(String namespace, String name, CoreV1Api api) throws ApiException {
		V1Secret secret = api.readNamespacedSecret(name, namespace, null, true, true);
		return secret;
	}

	private String getNameFromServiceAccount(V1ServiceAccount item) {
		return item.getMetadata().getName();
	}

	public User[] read(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
