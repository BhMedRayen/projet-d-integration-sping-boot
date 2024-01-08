package com.example.freelancer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FreelancerServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreelancerServiceApplication.class, args);
	}

}
