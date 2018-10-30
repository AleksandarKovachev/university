package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.university.entity.CourseAttendance;

/**
 * Repository with custom methods for the {@link CourseAttendance} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Component
public interface CourseAttendanceRepository extends JpaRepository<CourseAttendance, Long> {

}
