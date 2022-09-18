package com.example.ToDoList.service;

import java.util.List;

import com.example.ToDoList.entity.Task;

public interface TaskService {
	public List<Task> findAll();
	public Task findById(int theId);
	public void save(Task task);
	public void deleteTaskById(int theId) ;
}
