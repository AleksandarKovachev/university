package com.university.controller;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.constant.RequestConstant;
import com.university.constant.ViewConstant;

/**
 * Admin controller
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class AdminController {

	@Autowired
	private KeycloakRestTemplate restTemplate;

	@GetMapping(RequestConstant.ADMIN)
	public ModelAndView getCourse() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ViewConstant.ADMIN, modelMap);
	}

}
