package com.springbootrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrest.entity.User;
import com.springbootrest.exception.ResourceNotFoundException;
import com.springbootrest.repository.UserRepository;



@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	// Get all users
	@GetMapping
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	// Get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with the id: " + userId));
	}
	
	// Create a user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	

	// Update a user by id
	@PutMapping("{id}")
	public User updateUser(@RequestBody User user, @PathVariable (value="id") long userId) {
		User existingUser = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with the id: " + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return userRepository.save(existingUser);
	}
	
	
	// Delete user by id
	@DeleteMapping("{id}")
	public ResponseEntity<User> deleteUser(@PathVariable (value="id") long userId){
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with the id: " + userId));
		userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
}
