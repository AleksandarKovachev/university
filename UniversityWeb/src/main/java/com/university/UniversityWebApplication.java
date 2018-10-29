package com.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The starter of the Web Server
 * 
 * @author aleksandar.kovachev
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UniversityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityWebApplication.class, args);
	}
}
