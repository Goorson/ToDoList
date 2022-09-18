package com.example.ToDoList.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ToDoList.DAO.TaskDAO;
import com.example.ToDoList.DAO.TaskDAOImp;
import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.entity.Task;


@Service
public class TaskServiceImpl implements TaskService {
	
//	private TaskDAO taskDAO;
	private TaskRepository taskDAO;
	
	@Autowired
	public TaskServiceImpl(TaskRepository repo) {
		taskDAO = repo;
	}

	@Override
	@Transactional
	public List<Task> findAll() {
		return taskDAO.findAll();
	}

	@Override
	@Transactional
	public Task findById(int theId) {
//		Task result = taskDAO.findById(theId);
		Optional<Task> result = taskDAO.findById(theId);
		Task theTask = null;
		
		if(result.isPresent()) {
			theTask = result.get();
		}
		else {
			throw new RuntimeException("Could not find by id" + theId);
		}
		return theTask;
	}

	@Override
	@Transactional
	public void save(Task task) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		task.setDateOfCreation(LocalDateTime.now().format(formatter));
		System.out.print("service");
		taskDAO.save(task);

	}

	@Override
	@Transactional
	public void deleteTaskById(int theId) {
//		taskDAO.delete(theId);
		taskDAO.deleteById(theId);
	}
	
	

}
