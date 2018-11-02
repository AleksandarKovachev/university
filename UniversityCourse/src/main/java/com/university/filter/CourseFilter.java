package com.university.filter;

import lombok.Data;

/**
 * Course filter
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
public class CourseFilter {

	private String courseName;

	private Long teacherId;

	private Integer attendance;

}
