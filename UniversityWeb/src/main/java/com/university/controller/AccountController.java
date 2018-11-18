package com.university.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestAttribute;
import com.university.constant.RequestConstant;
import com.university.constant.Role;
import com.university.constant.ViewConstant;
import com.university.dto.Account;
import com.university.dto.Course;
import com.university.feignclient.GatewayClient;
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
	private GatewayClient gatewayClient;

	@GetMapping(RequestConstant.ACCOUNT)
	public ModelAndView account(@PathVariable String username, KeycloakAuthenticationToken keycloakPrincipal) {
		ModelMap modelMap = new ModelMap();

		String token = ((KeycloakSecurityContext) keycloakPrincipal.getCredentials()).getTokenString();

		Account account = gatewayClient.account(username);
		if (account != null) {
			modelMap.addAttribute(RequestAttribute.ACCOUNT, account);

			List<Course> courses = gatewayClient.accountCourses("Bearer " + token, account.getId(),
					account.getRoleId());
			if (!CollectionUtils.isEmpty(courses)) {
				if (account.getRoleId().equals(Role.TEACHER.getId())) {
					List<Long> listOfStudentId = new ArrayList<>();
					courses.stream().forEach(course -> {
						course.setTeacher(account);
						course.getCourseStudents().stream()
								.forEach(courseStudents -> listOfStudentId.add(courseStudents.getStudentId()));
					});

					AccountResponse students = gatewayClient.students(listOfStudentId);
					if (students != null) {
						List<Account> accounts = students.getAccounts();
						courses.stream()
								.forEach(course -> course.getCourseStudents().stream()
										.forEach(courseStudent -> courseStudent.setStudent(accounts.stream()
												.filter(a -> a.getId().equals(courseStudent.getStudentId())).findFirst()
												.orElse(null))));
					}
					modelMap.addAttribute(RequestAttribute.COURSES, courses);
					return new ModelAndView(ViewConstant.ACCOUNT_TEACHER, modelMap);
				} else {
					List<Long> listOfTeacherId = courses.stream().map(course -> course.getTeacherId())
							.collect(Collectors.toList());
					AccountResponse teachersResponse = gatewayClient.teachers(listOfTeacherId);
					if (teachersResponse != null) {
						List<Account> accounts = teachersResponse.getAccounts();

						courses.stream().forEach(course -> course.setTeacher(accounts.stream()
								.filter(a -> a.getId().equals(course.getTeacherId())).findFirst().orElse(null)));
					}
					modelMap.addAttribute(RequestAttribute.COURSES, courses);
					return new ModelAndView(ViewConstant.ACCOUNT_STUDENT, modelMap);
				}
			}
		}
		return new ModelAndView(ViewConstant.ACCOUNT_STUDENT, modelMap);
	}

}
