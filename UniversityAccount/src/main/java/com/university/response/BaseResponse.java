package com.university.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base response
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@EqualsAndHashCode
public class BaseResponse {

	private String message;

	private int status;

}
