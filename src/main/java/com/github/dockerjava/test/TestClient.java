package com.github.dockerjava.test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

public class TestClient {
	
	public static void main(String[] args){
//		DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://localhost:2375").build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config()).build();
		Info info = dockerClient.infoCmd().exec();
		System.out.print(info);
		System.out.println("Hello docker!");
	}
	
    private static DefaultDockerClientConfig config() {
        return config(null);
    }
    
    protected static DefaultDockerClientConfig config(String password) {
        DefaultDockerClientConfig.Builder builder = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withRegistryUrl("https://index.docker.io/v1/");
        if (password != null) {
            builder = builder.withRegistryPassword(password);
        }

        return builder.build();
    }
}
