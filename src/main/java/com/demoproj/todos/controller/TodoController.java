package com.demoproj.todos.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demoproj.todos.Constant;
import com.demoproj.todos.entity.Todo;
import com.demoproj.todos.services.TodoService;

@RestController
@CrossOrigin(Constant.CROSS_ORIGIN_URL)
public class TodoController {
	@Autowired
	TodoService todoService;

	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return TodoService.getAllTodos();
	}

	@GetMapping(path = "/users/{username}/todo/{id}")
	public Todo getTodosById(@PathVariable String username, @PathVariable long id) {
		return TodoService.findTodoById(id);
	}

	@PostMapping(path = "/users/{username}/todo")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo createdTodo = TodoService.saveTodo(todo);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping(path = "/users/{username}/todo")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo updatedTodo = TodoService.saveTodo(todo);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);

	}

	@DeleteMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
            @PathVariable long id) {
		Todo todo = TodoService.removeTodoById(id);
		return todo != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
