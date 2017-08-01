package com.sample.myproj.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sample.myproj.dto.UserDto;
import com.sample.myproj.entities.Authority;
import com.sample.myproj.entities.User;
import com.sample.myproj.exceptions.DuplicateUserException;
import com.sample.myproj.repository.AuthorityRepository;
import com.sample.myproj.repository.UserRepository;
import com.sample.myproj.security.JwtUserFactory;

@Component(value = "userDetailService")
public class UserServiceImpl  implements UserService, UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthorityRepository authRepository;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public User registerNewUserAccount(UserDto accountDto)
			throws DuplicateUserException {
		if (contactExists(accountDto.getContact())) {
	        throw new DuplicateUserException(accountDto);
	    }
		
		User user = new User();
		
		user.setFirstName(accountDto.getFirstName());
	    user.setLastName(accountDto.getLastName());
	    
	    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
	     
	    user.setEmail(accountDto.getEmail());
	    user.setContact(accountDto.getContact());
	    user.setState(accountDto.getState());
	    user.setUserName(accountDto.getUserName());
	    user.setLoginMode(accountDto.getMode());
	    user.setEnabled(true);
	    List<Authority> list = new ArrayList<Authority>();
	    Authority auth = authRepository.findOne(1L);
	    list.add(auth);
	    user.setAuthorities(list);
	    
		return repository.save(user);
		
	}
	
	private boolean usernameExists(String userName) {
		User user = repository.findByUserName(userName);
		return user != null ? true : false;
	}
	
	private boolean contactExists(String contact) {
		User user = repository.findByContact(contact);
		return user != null ? true : false;
	}

//	@Override
//	public User login(UserDto userDto) throws UsernameNotFoundException {
//		User user = repository.findByContact(userDto.getContact());
//		if (user == null) throw new UsernameNotFoundException("Mobile Number does not exist");
//		
//		if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
//			return user; 
//		} else {
//			throw new UsernameNotFoundException("Invalid password");
//		}
//		
//	}
	
	@Override
	public User socialLogin(UserDto accountDto)
			throws DuplicateUserException {
			//TODO
		return null;
	}

	@Override
	public Iterable<User> getUsers() {
		return repository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.info("inside loadUserByUsername " + username);
    	User user = repository.findByUserName(username);
    	log.info("found User");
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
	}

	@Override
	public Page<User> getUsersByRole(String role, Pageable p) {
		log.info("inside getUsersByRole " + role);
		Page<User> page = repository.getUsersByRole(role, p);
		log.info("Exiting getUsersByRole");
		return page;
	}

	@Override
	public Page<User> getUsersByRoleAndNameContaining(String role,
			String userName, Pageable p) {
		log.info("inside getUsersByRoleAndNameContaining with role {} and userName {}", role, userName);
		return repository.getUsersByRoleAndNameContaining(role, userName, p);
	}
}
