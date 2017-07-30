package com.sample.myproj.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.sample.myproj.entities.User;
import com.sample.myproj.exceptions.DuplicateUserException;
import com.sample.myproj.repository.UserRepository;
@Component
@RepositoryEventHandler
public class UserEventHandler {

	@Autowired
	private UserRepository repository;
	
	Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

//	@HandleBeforeCreate
//	public void handleUserCreate(User user) {
//		log.info("[Before Create] Checking Duplicate User for " + user.getUserName());
//		log.info("" + repository.findByUserName(user.getUserName()));
//		if (repository.findByUserName(user.getUserName()) != null) {
//			throw new DuplicateUserException(user);
//		}
//	}
}
