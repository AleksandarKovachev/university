package com.university.controller;

import java.util.List;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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

	@GetMapping(RequestConstant.COURSES_MY_COURSES)
	public ModelAndView getCourse() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ViewConstant.COURSE_COURSES, modelMap);
	}

	@GetMapping(RequestConstant.COURSES)
	public ModelAndView getCourses(@ModelAttribute CourseFilter filter) {
		ModelMap modelMap = new ModelMap();
		filter.setPageSize(10);
		setCoursesData(filter, modelMap);
		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	@PostMapping(RequestConstant.COURSES)
	public ModelAndView postCourses(@ModelAttribute CourseFilter filter) {
		ModelMap modelMap = new ModelMap();
		setCoursesData(filter, modelMap);
		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	@GetMapping(RequestConstant.COURSES_PREVIEW)
	public ModelAndView previewCourse(@PathVariable("id") String id) {
		ModelMap modelMap = new ModelMap();
		if (org.apache.commons.lang.StringUtils.isNumeric(id)) {
			StringBuilder courseUrl = new StringBuilder("http://api-gateway/course/").append(id);
			ResponseEntity<Course> courseResponse = restTemplate.getForEntity(courseUrl.toString(), Course.class);

			if (courseResponse.hasBody()) {
				Course course = courseResponse.getBody();

				StringBuilder studentUrl = new StringBuilder("http://api-gateway/students/id");
				for (CourseStudent courseStudent : course.getCourseStudents()) {
					addQueryParameter(studentUrl, "studentId", courseStudent.getStudentId());
				}

				ResponseEntity<AccountResponse> studentResponse = restTemplate.getForEntity(studentUrl.toString(),
						AccountResponse.class);

				if (studentResponse.hasBody()) {
					List<Account> accounts = studentResponse.getBody().getAccounts();
					course.getCourseStudents().stream()
							.forEach(courseStudent -> courseStudent.setStudent(accounts.stream()
									.filter(account -> account.getId().equals(courseStudent.getStudentId())).findFirst()
									.orElse(null)));
				}

				StringBuilder teachersUrl = new StringBuilder("http://api-gateway/teachers/id");
				addQueryParameter(teachersUrl, "teacherId", course.getTeacherId());

				ResponseEntity<AccountResponse> teachersResponse = restTemplate.getForEntity(teachersUrl.toString(),
						AccountResponse.class);
				if (teachersResponse.hasBody()) {
					List<Account> accounts = teachersResponse.getBody().getAccounts();
					course.setTeacher(accounts.stream().filter(account -> account.getId().equals(course.getTeacherId()))
							.findFirst().orElse(null));
				}

				modelMap.put(RequestAttribute.COURSE, course);
			}
		}
		return new ModelAndView(ViewConstant.COURSE_PREVIEW, modelMap);
	}

	private void setCoursesData(CourseFilter filter, ModelMap modelMap) {
		StringBuilder courseUrl = new StringBuilder("http://api-gateway/course/get");
		addCourseFilterParameters(filter, courseUrl);

		ResponseEntity<CourseResponse> courseResponse = restTemplate.getForEntity(courseUrl.toString(),
				CourseResponse.class);
		if (courseResponse.hasBody() && !CollectionUtils.isEmpty(courseResponse.getBody().getCourses())) {
			List<Course> courses = courseResponse.getBody().getCourses();

			StringBuilder teachersUrl = new StringBuilder("http://api-gateway/teachers/id");
			for (Course course : courses) {
				addQueryParameter(teachersUrl, "teacherId", course.getTeacherId());
			}

			ResponseEntity<AccountResponse> teachersResponse = restTemplate.getForEntity(teachersUrl.toString(),
					AccountResponse.class);
			if (teachersResponse.hasBody()) {
				List<Account> accounts = teachersResponse.getBody().getAccounts();
				courses.stream().forEach(course -> course.setTeacher(accounts.stream()
						.filter(account -> account.getId().equals(course.getTeacherId())).findFirst().orElse(null)));
			}
			modelMap.put(RequestAttribute.COURSES, courses);
			modelMap.put(RequestAttribute.TOTAL_COUNT, courseResponse.getBody().getTotalCount());
		}
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
		addQueryParameter(url, "pageSize", filter.getPageSize());
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
