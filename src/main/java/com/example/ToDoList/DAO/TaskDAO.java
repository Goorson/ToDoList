package com.example.ToDoList.DAO;

import java.util.List;

import com.example.ToDoList.entity.Task;

public interface TaskDAO {
	public void save(Task task);
	public void delete(int id);
	public List<Task> findAll();
	public Task findById(int id);
}
