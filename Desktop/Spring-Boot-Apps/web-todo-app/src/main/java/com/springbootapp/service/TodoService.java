package com.springbootapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapp.model.Todo;
import com.springbootapp.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> findAllTodosByUsername(String username){
		return (List<Todo>) todoRepository.findAllTodosByUsername(username);
	}
	
	public void saveTodo(Todo todo) {
		todoRepository.save(todo);
	}
	
	public Todo getTodoById(int id) {
		return todoRepository.findById(id).get();
	}
	
	public void deleteTodo(int id) {
		todoRepository.deleteById(id);
	}
	
	public Todo findTodoById(int id) {
		return todoRepository.findTodoById(id);
	}
}
