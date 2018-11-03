package com.university.response;

import java.util.List;

import com.university.dto.Account;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Account response
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountResponse extends BaseResponse {

	private List<Account> accounts;

}
