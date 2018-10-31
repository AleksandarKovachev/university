package com.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Account;
import com.university.entity.Role;
import com.university.repository.AccountRepository;

/**
 * Service for {@link Account} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllTeaechers() {
		return accountRepository.findAllAccountsByRoleId(Role.TEACHER);
	}

	public List<Account> getAllStudents() {
		return accountRepository.findAllAccountsByRoleId(Role.STUDENT);
	}

}
