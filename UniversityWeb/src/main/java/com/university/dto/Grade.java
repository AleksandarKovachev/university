package com.university.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Grade DTO
 * 
 * @author aleksandar.kovachev
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Grade {

	private Integer id;

	private String name;

	private int value;

}
