package com.university.controller;

import java.io.UnsupportedEncodingException;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestAttribute;
import com.university.constant.RequestConstant;
import com.university.constant.ViewConstant;
import com.university.filter.CourseFilter;
import com.university.response.CourseResponse;

/**
 * Controller for working with course methods
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class CourseController {

	@Autowired
	private KeycloakRestTemplate restTemplate;

	@GetMapping(RequestConstant.COURSES_GET)
	public ModelAndView getCourse(@ModelAttribute CourseFilter filter) {
		ModelMap modelMap = new ModelMap();
		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	@PostMapping(RequestConstant.COURSES_GET)
	public ModelAndView postCourse(@ModelAttribute CourseFilter filter) throws UnsupportedEncodingException {
		ModelMap modelMap = new ModelMap();

		StringBuilder url = new StringBuilder("http://api-gateway/course/get");
		addQueryParameters(filter, url);

		ResponseEntity<CourseResponse> response = restTemplate.exchange(url.toString(), HttpMethod.GET,
				new HttpEntity<>(new HttpHeaders()), CourseResponse.class);

		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	private void addQueryParameters(CourseFilter filter, StringBuilder url) {
		if (StringUtils.hasText(filter.getCourseName())) {
			appendPrefix(url);
			url.append("courseName=").append(filter.getCourseName());
		}
		if (filter.getTeacherId() != null) {
			appendPrefix(url);
			url.append("teacherId=").append(filter.getTeacherId());
		}
		if (filter.getAttendance() != null) {
			appendPrefix(url);
			url.append("attendance=").append(filter.getAttendance());
		}
	}

	private void appendPrefix(StringBuilder url) {
		if (url.toString().contains("?")) {
			url.append("&");
		} else {
			url.append("?");
		}
	}

}
