package com.university.filter;

import java.util.Date;

import lombok.Data;

/**
 * Course filter
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
public class CourseFilter {

	private String name;

	private Long teacherId;

	private Date attendance;

}
