package com.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestConstant;
import com.university.constant.ViewConstant;

/**
 * Controller for working with course methods
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class CourseController {

	@GetMapping(RequestConstant.COURSES_GET)
	public ModelAndView getCourse() {
		return new ModelAndView(ViewConstant.COURSE_GET);
	}

}
