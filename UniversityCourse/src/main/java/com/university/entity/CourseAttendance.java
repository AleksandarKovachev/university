package com.university.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representation of course_attendance table
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class CourseAttendance implements Serializable {

	private static final long serialVersionUID = 6298864324981259883L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private long courseId;

	@Column
	private long studentId;

	@Column
	private Date attendance;

}
