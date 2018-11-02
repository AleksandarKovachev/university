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
 * Entity representation of course_student table
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Grade implements Serializable {

	private static final long serialVersionUID = 7609553410973430278L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String name;

	@Column
	private int value;

}
