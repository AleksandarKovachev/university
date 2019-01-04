package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.university.entity.Account;

/**
 * Repository with custom methods for the {@link Account} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Component
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findAllAccountsByRoleId(int roleId);

	List<Account> findAllAccountsByIdInAndRoleId(List<Long> id, int roleId);

	Account findAccountByUsername(String username);

	Account findAccountByKeycloakId(String keycloakId);

}
