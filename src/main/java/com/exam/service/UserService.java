package com.exam.service;

import java.util.*;

import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	
	//create user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

	public User getUser(String username);

	public void deleteUser(Long userId);
	
	public User updateUser(User user) throws Exception;
	
}
