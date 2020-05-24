package com.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsernameAndPassword(String username, String password);
}
