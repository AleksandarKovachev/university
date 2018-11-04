package com.university.controller;

import java.util.List;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestAttribute;
import com.university.constant.RequestConstant;
import com.university.constant.Role;
import com.university.constant.ViewConstant;
import com.university.dto.Account;
import com.university.dto.Course;
import com.university.response.AccountResponse;

/**
 * Controller for working with account methods
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class AccountController {

	@Autowired
	private KeycloakRestTemplate restTemplate;

	@GetMapping(RequestConstant.ACCOUNT)
	public ModelAndView getCourse(@PathVariable String username) {
		ModelMap modelMap = new ModelMap();

		StringBuilder accountUrl = new StringBuilder("http://api-gateway/").append(username);
		ResponseEntity<Account> accountResponse = restTemplate.getForEntity(accountUrl.toString(), Account.class);

		if (accountResponse.hasBody()) {
			Account account = accountResponse.getBody();
			StringBuilder courseUrl = new StringBuilder("http://api-gateway/course/account/").append(account.getId())
					.append("?roleId=").append(account.getRoleId());
			ResponseEntity<List<Course>> coursesResponse = restTemplate.exchange(courseUrl.toString(), HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Course>>() {
					});

			modelMap.addAttribute(RequestAttribute.ACCOUNT, account);

			if (coursesResponse.hasBody()) {
				List<Course> courses = coursesResponse.getBody();
				if (account.getRoleId().equals(Role.TEACHER.getId())) {
					StringBuilder studentUrl = new StringBuilder("http://api-gateway/students/id");

					courses.stream().forEach(course -> {
						course.setTeacher(account);
						course.getCourseStudents().stream().forEach(courseStudents -> addQueryParameter(studentUrl,
								"studentId", courseStudents.getStudentId()));
					});

					ResponseEntity<AccountResponse> studentResponse = restTemplate.getForEntity(studentUrl.toString(),
							AccountResponse.class);

					if (studentResponse.hasBody()) {
						List<Account> accounts = studentResponse.getBody().getAccounts();
						courses.stream()
								.forEach(course -> course.getCourseStudents().stream()
										.forEach(courseStudent -> courseStudent.setStudent(accounts.stream()
												.filter(a -> a.getId().equals(courseStudent.getStudentId())).findFirst()
												.orElse(null))));
					}
					modelMap.addAttribute(RequestAttribute.COURSES, courses);
					return new ModelAndView(ViewConstant.ACCOUNT_STUDENT, modelMap);
				} else {
					StringBuilder teachersUrl = new StringBuilder("http://api-gateway/teachers/id");

					courses.stream()
							.forEach(course -> addQueryParameter(teachersUrl, "teacherId", course.getTeacherId()));

					ResponseEntity<AccountResponse> teachersResponse = restTemplate.getForEntity(teachersUrl.toString(),
							AccountResponse.class);

					if (teachersResponse.hasBody()) {
						List<Account> accounts = teachersResponse.getBody().getAccounts();

						courses.stream().forEach(course -> course.setTeacher(accounts.stream()
								.filter(a -> a.getId().equals(course.getTeacherId())).findFirst().orElse(null)));
					}
					modelMap.addAttribute(RequestAttribute.COURSES, courses);
					return new ModelAndView(ViewConstant.ACCOUNT_STUDENT, modelMap);
				}
			}
		}
		return null;
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
