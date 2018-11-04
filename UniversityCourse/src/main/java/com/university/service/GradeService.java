package com.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Grade;
import com.university.repository.GradeRepository;

/**
 * Service for {@link Grade} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	public List<Grade> getAllGrades() {
		return gradeRepository.findAll();
	}

}
