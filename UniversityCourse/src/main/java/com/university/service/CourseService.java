package com.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Course;
import com.university.filter.CourseFilter;
import com.university.repository.CourseRepository;

/**
 * Service for {@link Course} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getCoursesByFilter(CourseFilter filter) {
		return courseRepository.findCoursesByFilter(filter);
	}

	public Integer getCountCoursesByFilter(CourseFilter filter) {
		return courseRepository.findCountCoursesByFilter(filter).intValue();
	}

	public Course getCourse(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public List<Course> findAllCoursesByTeacherId(Long teacherId) {
		return courseRepository.findAllCoursesByTeacherId(teacherId);
	}

	public List<Course> findAllCoursesByStudentId(Long studentId) {
		return courseRepository.findAllCoursesByStudentId(studentId);
	}

	public List<Course> findAllCourses() {
		return courseRepository.findAll();
	}

}
