package com.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * The starter of the Web Server
 * 
 * @author aleksandar.kovachev
 *
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class UniversityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityWebApplication.class, args);
	}
}
