package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.university.entity.CourseStudent;

/**
 * Repository with custom methods for the {@link CourseStudent} entity
 * 
 * @author aleksandar.kovachev
 *
 */
@Component
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

	@Modifying
	@Transactional
	@Query("update CourseStudent c set c.grade.id = :gradeId where c.courseId = :courseId and c.studentId = :studentId")
	void updateCourseStudentByCourseIdandStudentId(@Param("gradeId") int gradeId, @Param("courseId") long courseId,
			@Param("studentId") long studentId);

}
