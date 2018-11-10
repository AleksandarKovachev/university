package com.university.constant;

/**
 * Defining request paths as constants
 * 
 * @author aleksandar.kovachev
 *
 */
public class RequestConstant {

	public static final String COURSE_GET = "/course/get";

	public static final String COURSE = "/course/{id}";

	public static final String COURSES_BY_ACCOUNT_ID = "/course/account/{id}";

	public static final String GRADES = "/course/grades";

	public static final String COURSESTUDENT_UPDATE = "/course/courseStudent/update";

	public static final String COURSES = "/course/courses";

	public static final String COURSESTUDENT_INSERT = "/course/courseStudent/insert";

	public static final String COURSE_ATTENDANCE_INSERT = "/course/courseAttendance/insert";

}
