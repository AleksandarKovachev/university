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
 * Entity representation of course table
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Course implements Serializable {

	private static final long serialVersionUID = -1025714778286004387L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	@Column
	private String teacherId;

	@Column
	private int attendance;

}
