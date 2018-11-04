package com.university.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Course DTO
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Course {

	private Long id;

	private String name;

	private Long teacherId;

	private int attendance;

	private List<CourseStudent> courseStudents;

	private List<CourseAttendance> courseAttendance;

	private Account teacher;

}
