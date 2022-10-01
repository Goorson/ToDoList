package com.example.ToDoList.service;

import java.util.List;
import java.util.Set;

import com.example.ToDoList.entity.Task;
import com.example.ToDoList.entity.User;

public interface TaskService {
	public List<Task> findAll();
	public Task findById(int theId);
	public void save(Task task);
	public void deleteTaskById(int theId);
	public Set<Task> findUserTasks(User user);
}
