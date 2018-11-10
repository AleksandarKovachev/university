package com.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.entity.CourseAttendance;
import com.university.repository.CourseAttendanceRepository;

/**
 * Service for {@link CourseAttendance} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
public class CourseAttendanceService {

	@Autowired
	private CourseAttendanceRepository courseAttendanceRepository;

	@Modifying
	@Transactional
	public void addCourseAttendance(CourseAttendance courseAttendance) {
		courseAttendanceRepository.saveAndFlush(courseAttendance);
	}

}
