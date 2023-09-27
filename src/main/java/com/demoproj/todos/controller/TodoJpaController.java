package com.demoproj.todos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demoproj.todos.Constant;
import com.demoproj.todos.entity.Todo;
import com.demoproj.todos.repository.TodoJpaRepository;

@RestController
@CrossOrigin(Constant.CROSS_ORIGIN_URL)
@RequestMapping("/jpa")
public class TodoJpaController {
	
	@Autowired
	TodoJpaRepository todoJpaRepository;

	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoJpaRepository.findByUsername(username);
	}

	@GetMapping(path = "/users/{username}/todo/{id}")
	public Todo getTodosById(@PathVariable String username, @PathVariable long id) {
		return todoJpaRepository.findById(id).get();
	}

	@PostMapping(path = "/users/{username}/todo")
	public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo createdTodo = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(createdTodo, HttpStatus.OK);
	}

	@PutMapping(path = "/users/{username}/todo")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo updatedTodo = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}

	@DeleteMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
            @PathVariable long id) {
		todoJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
