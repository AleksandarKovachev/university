package com.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.constant.RequestConstant;
import com.university.constant.ResponseStatus;
import com.university.response.AccountsResponse;
import com.university.service.AccountService;

/**
 * Defining account controller
 * 
 * @author aleksandar.kovachev
 *
 */
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping(RequestConstant.TEACHERS)
	public AccountsResponse getTeachers() {
		AccountsResponse response = new AccountsResponse();
		response.setAccounts(accountService.getAllTeaechers());
		response.setStatus(ResponseStatus.SUCCESSFUL.getStatus());
		return response;
	}

	@GetMapping(RequestConstant.STUDENTS)
	public AccountsResponse getStudents() {
		AccountsResponse response = new AccountsResponse();
		response.setAccounts(accountService.getAllStudents());
		response.setStatus(ResponseStatus.SUCCESSFUL.getStatus());
		return response;
	}

}
