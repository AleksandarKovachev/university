package com.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.constant.RequestConstant;
import com.university.constant.ResponseStatus;
import com.university.entity.Account;
import com.university.entity.Role;
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

	@GetMapping(RequestConstant.STUDENTS_BY_ID)
	public AccountsResponse getStudentsById(@RequestParam("studentId") List<Long> studentId) {
		AccountsResponse response = new AccountsResponse();
		response.setAccounts(accountService.findAllAccountsByIdInAndRoleId(studentId, Role.STUDENT));
		response.setStatus(ResponseStatus.SUCCESSFUL.getStatus());
		return response;
	}

	@GetMapping(RequestConstant.TEACHERS_BY_ID)
	public AccountsResponse getTeachersById(@RequestParam("teacherId") List<Long> teacherId) {
		AccountsResponse response = new AccountsResponse();
		response.setAccounts(accountService.findAllAccountsByIdInAndRoleId(teacherId, Role.TEACHER));
		response.setStatus(ResponseStatus.SUCCESSFUL.getStatus());
		return response;
	}

	@GetMapping(RequestConstant.ACCOUNT)
	public Account getAccount(@PathVariable String username) {
		if (!StringUtils.hasText(username)) {
			return null;
		}
		return accountService.findAccountByUsername(username);
	}

}
