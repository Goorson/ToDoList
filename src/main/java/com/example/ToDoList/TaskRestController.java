package com.example.ToDoList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.ToDoList.entity.Task;
import com.example.ToDoList.service.TaskService;


@RestController
@RequestMapping("/TaskChecker")
public class TaskRestController {
	private TaskService service;
	
	@Autowired
	public TaskRestController(TaskService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public List<Task> findAll() {
		return service.findAll();
	}
	
	@PostMapping("/")
	public Task addTask(@RequestBody Task task) {
		task.setId(0);
		service.save(task);
		return task;
	}
	
	@RequestMapping("/{theId}")
	public Task findById(@PathVariable int theId) {
		Task theTask = service.findById(theId);
		
		if(theTask == null) {
			throw new RuntimeException("Employee id not found - " + theId);
		}
		return theTask;
	}
	
	@DeleteMapping("/{theId}")
	public String deleteTask(@PathVariable int theId) {
		Task theTask = service.findById(theId);
		
		if(theTask == null) {
			throw new RuntimeException("There is no task with that id");
		}
		
		service.deleteTaskById(theId);
		return "Deleted task with id " + theId;
	}
	
	@PutMapping("/")
	public Task updateEmployee(@RequestBody Task theTask) {
		service.save(theTask);
		
		return theTask;
	}
}
