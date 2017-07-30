package com.sample.myproj.exceptions;

import com.sample.myproj.dto.UserDto;

public class DuplicateUserException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDto user;

	public DuplicateUserException(UserDto user) {
		super();
		this.user = user;
	}
	
	public UserDto getUser() {
		return user;
	}

}
