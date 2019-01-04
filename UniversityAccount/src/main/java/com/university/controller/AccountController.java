package com.university.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping(RequestConstant.ACCOUNT_IMAGE)
	public ResponseEntity<Void> accountImage(@PathVariable String accountId,
			@ModelAttribute("accountImage") MultipartFile accountImage) {
		if (accountImage == null || accountImage.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		try {
			Account account = accountService.findAccountByKeycloakId(accountId);
			account.setImage(Base64Utils.encodeToString(accountImage.getBytes()));
			accountService.save(account);
		} catch (IOException e) {
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(RequestConstant.ACCOUNT_IMAGE)
	public ResponseEntity<Void> accountImage(@PathVariable String accountId) {
		Account account = accountService.findAccountByKeycloakId(accountId);
		account.setImage(null);
		accountService.save(account);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
