package com.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapp.model.User;
import com.springbootapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User login(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	public void registerUser(User user) {
		userRepository.save(user);
	}
}
