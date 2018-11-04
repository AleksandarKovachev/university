package com.university.constant;

/**
 * Defining user roles
 * 
 * @author aleksandar.kovachev
 *
 */
public enum Role {

	TEACHER(1), STUDENT(2);

	private int id;

	private Role(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
