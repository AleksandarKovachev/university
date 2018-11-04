package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	List<Course> findAllCoursesByTeacherId(Long teacherId);

	@Query("SELECT c FROM Course c, CourseStudent cs WHERE cs.studentId = :studentId")
	List<Course> findAllCoursesByStudentId(@Param("studentId") Long studentId);

}
