package com.example.ToDoList;

import java.util.List;
import java.util.Set;

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
import com.example.ToDoList.entity.User;
import com.example.ToDoList.service.TaskService;
import com.example.ToDoList.service.login.UserService;

@Controller
@RequestMapping("/list")
public class TaskController {

	private TaskService taskService;
	private UserService userService;
	
	@Autowired
	public TaskController(TaskService service, UserService userService) {
		this.taskService = service;
		this.userService = userService;
		
	}
	
	@GetMapping("/list")
	public String listTasks(Model theModel) {
		User loggedUser = retrieveLoggedUser();
		Set<Task> tasks = taskService.findUserTasks(loggedUser);
		
		theModel.addAttribute("tasks", tasks);
		
		return "task-list";
		
	}

	private User retrieveLoggedUser() {
		User loggedUser = userService.getLoggedUser();
		return loggedUser;
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

		taskService.save(theTask);
		retrieveLoggedUser().addTask(theTask);
		userService.updateUser(retrieveLoggedUser());
		
		return "redirect:/list/list";
	}
	
	@GetMapping("/update")
	public String updateTask(@RequestParam ("taskId") int theId, Model theModel) {
		
		Task theTask = taskService.findById(theId);
		
		theModel.addAttribute("task", theTask);
		
		return "add-task-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("taskId") int theId) {
		
		taskService.deleteTaskById(theId);
		
		return "redirect:/list/list";
	}
}
