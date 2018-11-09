package com.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.entity.CourseStudent;
import com.university.repository.CourseStudentRepository;

/**
 * Service for {@link CourseStudent} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
public class CourseStudentService {

	@Autowired
	private CourseStudentRepository courseStudentRepository;

	public void updateCourseStudentByCourseIdandStudentId(int gradeId, long courseId, long studentId) {
		courseStudentRepository.updateCourseStudentByCourseIdandStudentId(gradeId, courseId, studentId);
	}

	@Modifying
	@Transactional
	public void addCourseStudent(CourseStudent courseStudent) {
		courseStudentRepository.saveAndFlush(courseStudent);
	}

}
