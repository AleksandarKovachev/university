package com.university.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Account DTO
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Account {

	private Long id;

	private String keycloakId;

	private String username;

	private String firstName;

	private String lastName;

	private String email;

	private Integer roleId;

}
