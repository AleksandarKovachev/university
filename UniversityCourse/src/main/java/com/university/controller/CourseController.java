package com.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.university.constant.RequestConstant;
import com.university.constant.ResponseStatus;
import com.university.entity.Course;
import com.university.filter.CourseFilter;
import com.university.response.CourseResponse;
import com.university.service.CourseService;

/**
 * Rest controller for working with courses
 * 
 * @author aleksandar.kovachev
 *
 */
@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping(RequestConstant.COURSE_GET)
	public CourseResponse getCourses(@ModelAttribute CourseFilter filter) {
		CourseResponse response = new CourseResponse();
		List<Course> courses = courseService.getCoursesByFilter(filter);
		response.setCourses(courses);
		response.setStatus(ResponseStatus.SUCCESSFUL.getStatus());
		return response;
	}

}
