package com.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestAttribute;
import com.university.constant.RequestConstant;
import com.university.constant.ViewConstant;
import com.university.filter.CourseFilter;

/**
 * Controller for working with course methods
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class CourseController {

	@GetMapping(RequestConstant.COURSES_GET)
	public ModelAndView getCourse(@ModelAttribute CourseFilter filter) {
		ModelMap modelMap = new ModelMap();
		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

	@PostMapping(RequestConstant.COURSES_GET)
	public ModelAndView postCourse(@ModelAttribute CourseFilter filter) {
		ModelMap modelMap = new ModelMap();
		modelMap.put(RequestAttribute.FILTER, filter);
		return new ModelAndView(ViewConstant.COURSE_GET, modelMap);
	}

}
