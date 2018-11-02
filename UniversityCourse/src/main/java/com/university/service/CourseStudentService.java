package com.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
