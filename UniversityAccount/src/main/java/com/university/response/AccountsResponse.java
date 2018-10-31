package com.university.response;

import java.util.List;

import com.university.entity.Account;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Response with list of {@link Account}
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountsResponse extends BaseResponse {

	private List<Account> accounts;

}
