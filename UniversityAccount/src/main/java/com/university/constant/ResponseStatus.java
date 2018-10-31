package com.university.constant;

/**
 * Defining response statuses
 * 
 * @author aleksandar.kovachev
 *
 */
public enum ResponseStatus {

	SUCCESSFUL(0), FAIL(1);

	private int status;

	private ResponseStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
