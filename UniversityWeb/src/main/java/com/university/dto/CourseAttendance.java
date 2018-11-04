package com.university.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * CourseAttendance DTO
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class CourseAttendance {

	private Long id;

	private long courseId;

	private long studentId;

	private Date attendance;

}
