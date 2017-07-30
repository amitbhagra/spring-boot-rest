package com.sample.myproj.advices;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sample.myproj.exceptions.DuplicateUserException;

@ControllerAdvice
public class ExceptionHandlingAdvice {

	@ExceptionHandler(DuplicateUserException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> handleDuplicateUserException(
			DuplicateUserException exception) {

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("error", "Mobile number " + exception.getUser().getContact() + " is already registered!");

		return map;
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> handleBadCredentialsException(
			BadCredentialsException exception) {

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("error", "Invalid Username or password!");

		return map;
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> handleInvalidUserException(
			UsernameNotFoundException exception) {

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("error", exception.getMessage());

		return map;
	}

}
