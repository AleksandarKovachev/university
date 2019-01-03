package com.university.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representation of account table
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Account implements Serializable {

	private static final long serialVersionUID = 8943226163876686705L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String keycloakId;

	@Column
	private String username;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String email;

	@Column
	private Integer roleId;

	@Column
	private String image;

}
