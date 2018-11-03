package com.university.repository;

import java.util.List;

import com.university.entity.Course;
import com.university.filter.CourseFilter;

/**
 * Custom repository for {@link Course} entity
 * 
 * @author aleksandar.kovachev
 *
 */
public interface CourseRepositoryCustom {

	List<Course> findCoursesByFilter(CourseFilter filter);

	Number findCountCoursesByFilter(CourseFilter filter);

}
