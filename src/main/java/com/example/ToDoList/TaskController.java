package com.example.ToDoList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ToDoList.entity.Task;
import com.example.ToDoList.service.TaskService;

@Controller
@RequestMapping("/list")
public class TaskController {

	private TaskService service;
	
	@Autowired
	public TaskController(TaskService service) {
		this.service = service;
	}
	
	@GetMapping("/list")
	public String listTasks(Model theModel) {
		List<Task> tasks = service.findAll();
		
		theModel.addAttribute("tasks", tasks);
		
		return "task-list";
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAddTask(Model theModel) {
			Task theTask = new Task();
			theTask.setDescription("");
			theModel.addAttribute("task", theTask);
			
			return "add-task-form";
	}
	
	@PostMapping("/save")
	public String saveTask(@ModelAttribute("task") Task theTask) {

		service.save(theTask);
		
		return "redirect:/list/list";
	}
	
	@GetMapping("/update")
	public String updateTask(@RequestParam ("taskId") int theId, Model theModel) {
		
		Task theTask = service.findById(theId);
		
		theModel.addAttribute("task", theTask);
		
		return "add-task-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("taskId") int theId) {
		
		service.deleteTaskById(theId);
		
		return "redirect:/list/list";
	}
}
