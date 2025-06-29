package com.socialmedia.eureka.registry.SocialMediaAppRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SocialMediaAppRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaAppRegistryApplication.class, args);
	}

}
