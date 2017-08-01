package com.sample.myproj.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.myproj.dto.UserDto;
import com.sample.myproj.entities.User;
import com.sample.myproj.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	Logger log = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public @ResponseBody Page<User> getUsers(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "direction", required = false) String direction) {

		Integer pg = page == null ? new Integer(0) : page;
		Integer sz = size == null ? new Integer(10) : size;
		String srt = sort == null ? "updated_on" : sort;
		Direction dir = "desc".equals(direction) ? Direction.DESC
				: Direction.ASC;
		log.info("inside getUsers. Page {}, Size {}, Sort {}, Direction {}",
				pg, sz, srt, dir);
		return userService.getUsersByRole("ROLE_USER", new PageRequest(pg,
				sz, dir, srt));
	}
	
    
	@RequestMapping(value = "/api/usersNameContaining", method = RequestMethod.GET)
	public @ResponseBody Page<User> getUsersByNameContaining(
			@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("userName") String userName, @RequestParam("sort") String sort
			, @RequestParam(value = "direction", required = false) String direction) {
		//TODO
		log.info("inside getUsersByNameContaining");
		Direction dir = "desc".equals(direction) ? Direction.DESC : Direction.ASC;
		return userService.getUsersByRoleAndNameContaining("ROLE_USER", userName, new PageRequest(
				page, size, dir, sort));
	}
	
	@RequestMapping(value = "/api/signup", method = RequestMethod.POST)
	public @ResponseBody User signup(@RequestBody UserDto userDto) {
		log.info("Inside signup with username {}, password {}", userDto.getUserName(), userDto.getPassword());
		User user = userService.registerNewUserAccount(userDto);
		return user;
	}
	
	
	@RequestMapping(value = "/api/sociallogin", method = RequestMethod.POST)
	public @ResponseBody User sociallogin(@RequestBody UserDto userDto) {
		//TODO
		log.info("Inside social login with username {}", userDto.getUserName());
		User user = userService.socialLogin(userDto);
		return user;
	}
	
	
	@RequestMapping(value="/api/downloadUsersCSV", method = RequestMethod.GET)
	public String viewCSV(Model model) throws IOException {
		//TODO
		log.info("inside download");
		Iterable<User> listUsers = userService.getUsers();
	 
	    String[] header = { "Email", "Contact", "UserName", "FirstName",
	            "LastName" };
	 
	    model.addAttribute("users", listUsers);
	    model.addAttribute("headers", header);
	    return "";
	}
	
	
}
