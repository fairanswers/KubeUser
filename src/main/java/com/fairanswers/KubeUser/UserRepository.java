package com.fairanswers.KubeUser;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
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
			V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
			for (V1Pod item : list.getItems()) {
				users[i]=new User(item.toString() , "", "", "");
				if(i==99) {
					break;
				}
			}
		}
		catch(Exception ex) {
			System.out.println("Exception ="+ex);
		}
		return users;
	}

	public User[] read(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
