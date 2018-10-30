package com.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
