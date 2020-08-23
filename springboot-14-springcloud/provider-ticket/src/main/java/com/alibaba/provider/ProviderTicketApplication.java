package com.alibaba.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ProviderTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderTicketApplication.class, args);
	}

}