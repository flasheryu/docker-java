package com.github.dockerjava.test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;

public class TestClient {
	
	public static void main(String[] args){
		DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://localhost:2375").build();
		Info info = dockerClient.infoCmd().exec();
		System.out.print(info);
		System.out.println("Hello docker!");
	}
}
