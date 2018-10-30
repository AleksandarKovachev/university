package com.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Course;
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

}
