package com.github.dockerjava.test;

import java.util.List;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.PullImageResultCallback;
import com.github.dockerjava.jaxrs.DockerCmdExecFactoryImpl;

public class TestClient {
	
	public static void main(String[] args){
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost("tcp://localhost:2375")
                .withRegistryUrl("https://index.docker.io/v1/").build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config)
		  .build();
		
		Volume volume1 = new Volume("/tmp"); 
		
//		DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://localhost:2375").build();
		
//		List<SearchItem> dockerSearch = dockerClient.searchImagesCmd("busybox").exec();
//		System.out.println("Search returned" + dockerSearch.toString());
		String testImage = "flasheryu/jmeter";
        dockerClient.pullImageCmd(testImage).exec(new PullImageResultCallback()).awaitSuccess();

		CreateContainerResponse container = dockerClient.createContainerCmd(testImage)
				.withVolumes(volume1)
				.withBinds(new Bind("/log",volume1))
//				   .withCmd("docker run --rm --name jmetertest -v $(pwd):/tmp flasheryu/jmeter /opt/jmeter/bin/jmeter -n -t /jmeter/hello-baidu.jmx -l /tmp/result_hello-baidu.jtl", "/test")
				   .exec();

				dockerClient.startContainerCmd(container.getId()).exec();
//				dockerClient.stopContainerCmd(container.getId()).exec();
//				dockerClient.waitContainerCmd(container.getId()).exec();
		
//		Info info = dockerClient.infoCmd().exec();
//		System.out.print(info);
//		System.out.println("Hello docker!");
	}
}
