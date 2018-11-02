package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.university.entity.Course;

/**
 * Repository with custom methods for the {@link Course} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Component
public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {

}
