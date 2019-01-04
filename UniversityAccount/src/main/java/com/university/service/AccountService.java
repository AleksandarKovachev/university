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

	public List<Account> findAllAccountsByIdInAndRoleId(List<Long> id, int roleId) {
		return accountRepository.findAllAccountsByIdInAndRoleId(id, roleId);
	}

	public Account findAccountByUsername(String username) {
		return accountRepository.findAccountByUsername(username);
	}

	public Account findAccountByKeycloakId(String keycloakId) {
		return accountRepository.findAccountByKeycloakId(keycloakId);
	}

	public Account save(Account account) {
		return accountRepository.save(account);
	}

}
