package com.sample.myproj.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sample.myproj.dto.UserDto;
import com.sample.myproj.entities.User;
import com.sample.myproj.exceptions.DuplicateUserException;

public interface UserService {
	public User registerNewUserAccount(UserDto accountDto)
			throws DuplicateUserException;

	public User socialLogin(UserDto userDto);

	public Iterable<User> getUsers();
	
	public Page<User> getUsersByRole(String role, Pageable p);
	
	public Page<User> getUsersByRoleAndNameContaining(String role, String userName, Pageable p);
}
