package com.lucaspires.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	
	// Bean for RestTemplate (used to make client REST calls)
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}