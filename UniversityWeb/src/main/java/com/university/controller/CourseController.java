package com.university.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.university.dto.Account;
import com.university.dto.Course;
import com.university.dto.CourseStudent;
import com.university.filter.CourseFilter;
import com.university.response.AccountResponse;
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

		StringBuilder courseUrl = new StringBuilder("http://api-gateway/course/get");
		addCourseFilterParameters(filter, courseUrl);

		ResponseEntity<CourseResponse> courseResponse = restTemplate.getForEntity(courseUrl.toString(),
				CourseResponse.class);

		if (courseResponse.hasBody()) {
			List<Course> courses = courseResponse.getBody().getCourses();

			StringBuilder studentUrl = new StringBuilder("http://api-gateway/students/id");

			for (Course course : courses) {
				for (CourseStudent courseStudent : course.getCourseStudents()) {
					addQueryParameter(studentUrl, "studentId", courseStudent.getStudentId());
				}
			}

			ResponseEntity<AccountResponse> studentResponse = restTemplate.getForEntity(studentUrl.toString(),
					AccountResponse.class);

			if (studentResponse.hasBody()) {
				List<Account> accounts = studentResponse.getBody().getAccounts();
				courses.stream()
						.forEach(course -> course.getCourseStudents().stream()
								.forEach(courseStudent -> courseStudent.setStudent(accounts.stream()
										.filter(account -> account.getId().equals(courseStudent.getStudentId()))
										.findFirst().orElse(null))));
			}
		}

		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	private void addCourseFilterParameters(CourseFilter filter, StringBuilder url) {
		if (StringUtils.hasText(filter.getCourseName())) {
			addQueryParameter(url, "courseName", filter.getCourseName());
		}
		if (filter.getTeacherId() != null) {
			addQueryParameter(url, "teacherId", filter.getTeacherId());
		}
		if (filter.getAttendance() != null) {
			addQueryParameter(url, "attendance", filter.getAttendance());
		}

		addQueryParameter(url, "pageNumber", filter.getPageNumber());
		addQueryParameter(url, "pageSize", 10);
	}

	private void addQueryParameter(StringBuilder url, String name, Object value) {
		appendPrefix(url);
		url.append(name).append("=").append(value);
	}

	private void appendPrefix(StringBuilder url) {
		if (url.toString().contains("?")) {
			url.append("&");
		} else {
			url.append("?");
		}
	}

}
