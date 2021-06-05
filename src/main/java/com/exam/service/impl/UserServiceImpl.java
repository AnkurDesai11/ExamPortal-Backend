package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		// TODO Auto-generated method stub
		//return null;
		
		User localUser = this.userRepository.findByUsername(user.getUsername());
		
		if(localUser!=null) {
			System.out.println("User already present");
			throw new Exception("User already present");
		}
		else {
			//create user
			for(UserRole urole: userRoles ) {
				roleRepository.save(urole.getRole());//adding roles to database
			}
			user.getUserRoles().addAll(userRoles);//assigning roles to user
			localUser = this.userRepository.save(user);
		}
		return localUser;
	}
	
	//getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	//delete user by userId
	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

	//update user by username
	@Override
	public User updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
		User localUser = this.userRepository.findByUsername(user.getUsername());
		
		if(localUser==null) {
			System.out.println("User absent");
			throw new Exception("User absent");
		}
		else {
			user.setId(localUser.getId());
		}
		return this.userRepository.save(user);
	}
	
}
