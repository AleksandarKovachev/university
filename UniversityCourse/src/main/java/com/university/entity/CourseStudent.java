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
public class CourseStudent implements Serializable {

	private static final long serialVersionUID = -6836942826391457742L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Long courseId;

	@Column
	private Long studentId;

	@Column
	private Integer gradeId;

}
