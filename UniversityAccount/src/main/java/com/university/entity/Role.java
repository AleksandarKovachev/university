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
public class Role implements Serializable {

	private static final long serialVersionUID = -8297956632164351925L;

	public static final Integer TEACHER = 1;
	public static final Integer STUDENT = 2;
	public static final Integer ADMIN = 3;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String name;

}
