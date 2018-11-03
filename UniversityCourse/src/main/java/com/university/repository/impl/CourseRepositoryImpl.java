package com.university.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.university.entity.Course;
import com.university.filter.CourseFilter;
import com.university.repository.CourseRepositoryCustom;

/**
 * Course Repository implementation
 * 
 * @author aleksandar.kovachev
 *
 */
@Repository
public class CourseRepositoryImpl implements CourseRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Course> findCoursesByFilter(CourseFilter filter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

		Root<Course> course = criteriaQuery.from(Course.class);
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getTeacherId() != null) {
			predicates.add(criteriaBuilder.equal(course.get("teacherId"), filter.getTeacherId()));
		}

		if (StringUtils.hasText(filter.getCourseName())) {
			predicates.add(criteriaBuilder.like(course.get("name"), filter.getCourseName()));
		}

		if (filter.getAttendance() != null) {
			predicates.add(criteriaBuilder.equal(course.get("attendance"), filter.getAttendance()));
		}

		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(filter.getPageNumber() * filter.getPageSize());
		query.setMaxResults(filter.getPageSize());
		return query.getResultList();
	}

}
