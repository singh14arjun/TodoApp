package com.todos.TodoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todos.TodoApp.model.Task;
import com.todos.TodoApp.service.TaskService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/")
	public String viewTasks(@RequestParam(value = "status",required = false)String status , ModelMap model) {
		List<Task> tasks ;
		
		if(status == null || status.equals("status")) {
		  tasks=taskService.getAllTasks();
		}
		else if(status.equals("completed")) {
			tasks=taskService.getTaskByStatus(true);
		}
		else {
			tasks=taskService.getTaskByStatus(false);
		}
	    model.addAttribute("tasks", tasks);

		model.addAttribute("newTask", new Task());
		return "welcome";
	}

	@GetMapping("/addtask")
	public String adTask() {
		return "add-task";
	}

	@PostMapping("/add")
	public String createTask(@ModelAttribute("newTask") Task task) {
		taskService.createTask(task);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String editTask(@PathVariable int id, ModelMap model) {

		Task taskById = taskService.getTaskById(id);
		model.addAttribute("task",taskById);
		return "edit-task";
	}

	@PostMapping("/edit/{id}")
	public String updateTask(@PathVariable int id, @ModelAttribute("task") Task taskDetails) {
		taskService.updateTask(id, taskDetails);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable int id) {
		taskService.deleteTask(id);
		return "redirect:/";
	}

}
