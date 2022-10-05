package com.cooksys.socialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6002261747167460826L;
	
	private String message;
}
