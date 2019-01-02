package com.university.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.constant.RequestConstant;
import com.university.constant.ResponseStatus;
import com.university.constant.Role;
import com.university.entity.Course;
import com.university.entity.CourseAttendance;
import com.university.entity.CourseStudent;
import com.university.entity.Grade;
import com.university.filter.CourseFilter;
import com.university.response.CourseResponse;
import com.university.service.CourseAttendanceService;
import com.university.service.CourseService;
import com.university.service.CourseStudentService;
import com.university.service.GradeService;

/**
 * Rest controller for working with courses
 * 
 * @author aleksandar.kovachev
 *
 */
@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private CourseStudentService courseStudentService;

	@Autowired
	private CourseAttendanceService courseAttendanceService;

	@GetMapping(RequestConstant.COURSE_GET)
	public CourseResponse getCourses(@ModelAttribute CourseFilter filter) {
		CourseResponse response = new CourseResponse();
		response.setTotalCount(courseService.getCountCoursesByFilter(filter));
		response.setCourses(courseService.getCoursesByFilter(filter));
		response.setStatus(ResponseStatus.SUCCESSFUL.getStatus());
		return response;
	}

	@GetMapping(RequestConstant.COURSE)
	public Course course(@PathVariable("id") String id) {
		if (!StringUtils.isNumeric(id)) {
			return null;
		}
		return courseService.getCourse(Long.parseLong(id));
	}

	@GetMapping(RequestConstant.COURSES)
	public List<Course> courses() {
		return courseService.findAllCourses();
	}

	@GetMapping(RequestConstant.COURSES_BY_ACCOUNT_ID)
	public List<Course> course(@PathVariable("id") String id, @RequestParam("roleId") String roleId) {
		if (!StringUtils.isNumeric(id) && !StringUtils.isNumeric(roleId)) {
			return null;
		}

		if (Integer.parseInt(roleId) == Role.STUDENT.getId()) {
			return courseService.findAllCoursesByStudentId(Long.parseLong(id));
		} else {
			return courseService.findAllCoursesByTeacherId(Long.parseLong(id));
		}
	}

	@GetMapping(RequestConstant.GRADES)
	public List<Grade> course() {
		return gradeService.getAllGrades();
	}

	@PostMapping(RequestConstant.COURSESTUDENT_UPDATE)
	public void changeStudentGrade(@RequestParam int gradeId, @RequestParam long courseId,
			@RequestParam long studentId) {
		courseStudentService.updateCourseStudentByCourseIdandStudentId(gradeId, courseId, studentId);
	}

	@PostMapping(RequestConstant.COURSESTUDENT_INSERT)
	public boolean insertCourseStudent(@RequestParam String courseId, @RequestParam String studentId) {
		if (!StringUtils.isNumeric(courseId) || !StringUtils.isNumeric(studentId)) {
			return false;
		}
		CourseStudent courseStudent = new CourseStudent();
		courseStudent.setCourseId(Long.parseLong(courseId));
		courseStudent.setStudentId(Long.parseLong(studentId));
		courseStudentService.addCourseStudent(courseStudent);
		return true;
	}

	@PostMapping(RequestConstant.COURSE_ATTENDANCE_INSERT)
	public boolean insertCourseAttendance(@RequestParam String courseId, @RequestParam String studentId,
			@RequestParam String attendance) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			simpleDateFormat.parse(attendance);
		} catch (ParseException e) {
			return false;
		}
		if (!StringUtils.isNumeric(courseId) || !StringUtils.isNumeric(studentId)) {
			return false;
		}
		CourseAttendance courseAttendance = new CourseAttendance();
		courseAttendance.setCourseId(Long.parseLong(courseId));
		courseAttendance.setStudentId(Long.parseLong(studentId));
		courseAttendance.setAttendance(simpleDateFormat.parse(attendance));
		courseAttendanceService.addCourseAttendance(courseAttendance);
		return true;
	}

}
