package com.company.raghirserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RaghiRServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaghiRServiceRegistryApplication.class, args);
	}

}
