package com.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootapp.model.Todo;
import com.springbootapp.model.User;
import com.springbootapp.service.TodoService;
import com.springbootapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/")
	public String showLoginPage(User user, Model model) {
		User userLogin = new User();
		model.addAttribute("user", userLogin);
		return "login";
	}
	
	@PostMapping("/login-user")
	public String loginUser(User user, ModelMap model) {
		if(userService.login(user.getUsername(), user.getPassword()) != null){
			model.put("user", user);
			model.addAttribute("todos", todoService.findAllTodosByUsername(user.getUsername()));
			return "welcome-page";
		} else {
			model.addAttribute("message", "Invalid username or password!");
			return "login";
		}
	}
	
	@GetMapping("/homepage/{username}")
	public String showHomepage(@PathVariable String username, Model model) {
		User user = new User();
		user.setUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("todos", todoService.findAllTodosByUsername(user.getUsername()));
		return "homepage";
	}
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register-form";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@ModelAttribute User user, Model model) {
		userService.registerUser(user);
		model.addAttribute("registerSuccess", "User Registration Successful! Please Login!");
		return "login";
	}
	
	@GetMapping("/add-todo/{username}")
	public String showTodoForm(@PathVariable String username, Model model) {
		Todo todo = new Todo();
		todo.setUsername(username);
		System.out.println(username);
		model.addAttribute("todo", todo);
		return "todo-form";
	}
	
	@PostMapping("/save-todo/{username}")
	public String saveTodo(@ModelAttribute Todo todo, @PathVariable String username, Model model) {
		User user = new User();
		user.setUsername(username);
		todoService.saveTodo(todo);
		model.addAttribute("user", user);
		model.addAttribute("todos", todoService.findAllTodosByUsername(username));
		return "homepage";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("logout", "Logout Successful!");
		return "login";
	}
	
	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable int id, Model model) {
		model.addAttribute("todo", todoService.getTodoById(id));
		return "update-form";
	}
	
	@GetMapping("/delete-todo/{id}")
	public String deleteTodo(@PathVariable int id, ModelMap model) {
		Todo todo = new Todo();
		todo = todoService.findTodoById(id);
		todoService.deleteTodo(id);
		model.addAttribute("user", todo);
		model.addAttribute("todos", todoService.findAllTodosByUsername(todo.getUsername()));
		return "homepage";
	}
	
}
