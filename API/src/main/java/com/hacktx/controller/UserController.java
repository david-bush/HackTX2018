package com.hacktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hacktx.model.User;
import com.hacktx.repository.UserDataRepository;

@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	UserDataRepository userDataRepository;
	
	@RequestMapping(path = "/getAllUsers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userDataRepository.findAllUsers();
	}
	
}
