package com.stackroute.movieapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class MovieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAppApplication.class, args);
	}
}
