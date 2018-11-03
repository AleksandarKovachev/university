package com.university.response;

import java.util.List;

import com.university.entity.Course;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Course response
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseResponse extends BaseResponse {

	private List<Course> courses;

	private int totalCount;

}
