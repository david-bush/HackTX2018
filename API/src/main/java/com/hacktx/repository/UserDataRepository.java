package com.hacktx.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hacktx.model.User;

@Repository
public interface UserDataRepository {

	List<User> findAllUsers();
	
}
