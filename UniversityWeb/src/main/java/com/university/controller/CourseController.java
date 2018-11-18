package com.university.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestAttribute;
import com.university.constant.RequestConstant;
import com.university.constant.ViewConstant;
import com.university.dto.Account;
import com.university.dto.Course;
import com.university.feignclient.GatewayClient;
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
	private GatewayClient gatewayClient;

	@GetMapping(RequestConstant.COURSES_MY_COURSES)
	public ModelAndView getCourse(KeycloakAuthenticationToken keycloakPrincipal) {
		ModelMap modelMap = new ModelMap();

		String token = ((KeycloakSecurityContext) keycloakPrincipal.getCredentials()).getTokenString();

		Account account = gatewayClient.account(keycloakPrincipal.getName());
		if (account != null) {
			List<Course> courses = gatewayClient.accountCourses("Bearer " + token, account.getId(),
					account.getRoleId());
			if (!CollectionUtils.isEmpty(courses)) {
				modelMap.addAttribute(RequestAttribute.COURSES, courses);
			}
		}

		return new ModelAndView(ViewConstant.COURSE_COURSES, modelMap);
	}

	@GetMapping(RequestConstant.COURSES)
	public ModelAndView getCourses(@ModelAttribute CourseFilter filter, KeycloakAuthenticationToken keycloakPrincipal) {
		ModelMap modelMap = new ModelMap();
		filter.setPageSize(10);
		setCoursesData(filter, modelMap, keycloakPrincipal);
		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	@PostMapping(RequestConstant.COURSES)
	public ModelAndView postCourses(@ModelAttribute CourseFilter filter,
			KeycloakAuthenticationToken keycloakPrincipal) {
		ModelMap modelMap = new ModelMap();
		setCoursesData(filter, modelMap, keycloakPrincipal);
		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	@GetMapping(RequestConstant.COURSES_PREVIEW)
	public ModelAndView previewCourse(@PathVariable("id") String id, KeycloakAuthenticationToken keycloakPrincipal) {
		ModelMap modelMap = new ModelMap();
		String token = ((KeycloakSecurityContext) keycloakPrincipal.getCredentials()).getTokenString();
		if (org.apache.commons.lang.StringUtils.isNumeric(id)) {

			Course course = gatewayClient.course("Bearer " + token, id);
			if (course != null) {
				AccountResponse accountsResponse = gatewayClient.students(
						course.getCourseStudents().stream().map(c -> c.getStudentId()).collect(Collectors.toList()));

				if (accountsResponse != null) {
					List<Account> accounts = accountsResponse.getAccounts();
					course.getCourseStudents().stream()
							.forEach(courseStudent -> courseStudent.setStudent(accounts.stream()
									.filter(account -> account.getId().equals(courseStudent.getStudentId())).findFirst()
									.orElse(null)));
				}

				AccountResponse teachersResponse = gatewayClient.teachers(Arrays.asList(course.getTeacherId()));
				if (teachersResponse != null) {
					List<Account> accounts = teachersResponse.getAccounts();
					course.setTeacher(accounts.stream().filter(account -> account.getId().equals(course.getTeacherId()))
							.findFirst().orElse(null));
				}

				modelMap.put(RequestAttribute.COURSE, course);
			}
		}
		return new ModelAndView(ViewConstant.COURSE_PREVIEW, modelMap);
	}

	private void setCoursesData(CourseFilter filter, ModelMap modelMap, KeycloakAuthenticationToken keycloakPrincipal) {
		String token = ((KeycloakSecurityContext) keycloakPrincipal.getCredentials()).getTokenString();

		CourseResponse courseResponse = gatewayClient.getCoursesByFilter("Bearer " + token, filter.getCourseName(), filter.getTeacherId(), filter.getAttendance(), filter.getPageNumber(), filter.getPageSize());
		if (courseResponse != null && !CollectionUtils.isEmpty(courseResponse.getCourses())) {
			List<Course> courses = courseResponse.getCourses();

			AccountResponse teachersResponse = gatewayClient
					.teachers(courses.stream().map(c -> c.getTeacherId()).collect(Collectors.toList()));
			if (teachersResponse != null) {
				List<Account> accounts = teachersResponse.getAccounts();
				courses.stream().forEach(course -> course.setTeacher(accounts.stream()
						.filter(account -> account.getId().equals(course.getTeacherId())).findFirst().orElse(null)));
			}
			modelMap.put(RequestAttribute.COURSES, courses);
			modelMap.put(RequestAttribute.TOTAL_COUNT, courseResponse.getTotalCount());
		}
	}

}
