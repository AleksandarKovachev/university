package com.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The starter of the course-service
 * 
 * @author aleksandar.kovachev
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UniversityCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityCourseApplication.class, args);
	}
}
