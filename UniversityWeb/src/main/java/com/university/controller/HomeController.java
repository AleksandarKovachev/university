package com.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestConstant;
import com.university.constant.ViewConstant;

/**
 * Defining methods for processing the index requests
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class HomeController {

	@GetMapping(RequestConstant.INDEX)
	public ModelAndView index() {
		return new ModelAndView(ViewConstant.INDEX);
	}

}
