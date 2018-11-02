package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.university.entity.Grade;

/**
 * Repository with custom methods for the {@link Grade} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Component
public interface GradeRepository extends JpaRepository<Grade, Integer> {

}
