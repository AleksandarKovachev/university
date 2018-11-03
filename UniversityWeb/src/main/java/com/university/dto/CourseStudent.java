package com.university.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * CourseStudent DTO
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class CourseStudent {

	private Long id;

	private Long courseId;

	private Long studentId;

	private Integer gradeId;

	private Account student;

}
