package com.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findAllTodosByUsername(String username);
}
