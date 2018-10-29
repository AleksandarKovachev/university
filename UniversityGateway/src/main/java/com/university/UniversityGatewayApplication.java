package com.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * The starter of the Gateway Server
 * 
 * @author aleksandar.kovachev
 *
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class UniversityGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityGatewayApplication.class, args);
	}
}
