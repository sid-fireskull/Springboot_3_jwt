package com.demoproj.todos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demoproj.todos.entity.Todo;

@Service
public class TodoService {
	static List<Todo> todos = new ArrayList<>();
	static Long counter = 0l;

	static {
		todos.add(new Todo(++counter, "John", "Learn Advance Javascript", new Date(), false));
		todos.add(new Todo(++counter, "John", "Learn Hibernate", new Date(), false));
		todos.add(new Todo(++counter, "John", "Learn Angular", new Date(), false));
	};

	public static List<Todo> getAllTodos() {
		return todos;

	}


	public static Todo removeTodoById(long id) {
		Todo temp = findTodoById(id);
		if (temp != null) {
			if(todos.remove(temp))
				return temp;
			else
				return null;
		}
		return temp;
	}
	
	public static  Todo saveTodo(Todo todo)
	{
		if(todo.getId()==-1)
		{
			todo.setId(++counter);
			todos.add(todo);
		}
		else {
			removeTodoById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}

	public static Todo findTodoById(long id) {
		for (Todo item : todos)
			if (item.getId() == id)
				return item;
		return null;
	}
}
