package com.university.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.university.dto.Account;
import com.university.dto.Course;
import com.university.response.AccountResponse;
import com.university.response.CourseResponse;

/**
 * Defining feign client for communicating with the gateway
 * 
 * @author aleksandar.kovachev
 *
 */
@FeignClient(value = GatewayClient.TARGET)
public interface GatewayClient {

	public static final String AUTH_TOKEN = "Authorization";

	public static final String TARGET = "api-gateway";

	@GetMapping("/{username}")
	Account account(@PathVariable String username);

	@GetMapping("/course/account/{id}")
	List<Course> accountCourses(@RequestHeader(AUTH_TOKEN) String token, @PathVariable("id") Long id,
			@RequestParam Integer roleId);

	@GetMapping("/students/id")
	AccountResponse students(@RequestParam List<Long> studentId);

	@GetMapping("/teachers/id")
	AccountResponse teachers(@RequestParam List<Long> teacherId);

	@GetMapping("/course/{id}")
	Course course(@RequestHeader(AUTH_TOKEN) String token, @PathVariable("id") String id);

	@GetMapping("/course/get")
	CourseResponse getCoursesByFilter(@RequestHeader(AUTH_TOKEN) String token, @RequestParam String courseName,
			@RequestParam Long teacherId, @RequestParam Integer attendance, @RequestParam int pageNumber,
			@RequestParam int pageSize);

}
