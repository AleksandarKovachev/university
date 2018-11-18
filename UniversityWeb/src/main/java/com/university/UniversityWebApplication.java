package com.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The starter of the Web Server
 * 
 * @author aleksandar.kovachev
 *
 */
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UniversityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityWebApplication.class, args);
	}
}
