package com.todos.TodoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todos.TodoApp.model.Task;
import com.todos.TodoApp.repositry.TaskRepositry;

@Service
public class TaskService {

   
    @Autowired
    private TaskRepositry taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskByStatus(boolean isCompleted)
    {
    	return taskRepository.findByCompleted(isCompleted);
    }
    
    public Task createTask(Task task) 
    {
        return taskRepository.save(task);
    }

    public Task updateTask(int id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
    
    public Task getTaskById(int id)
    {
    	Task task=taskRepository.findById(id).get();
    	return task;
    }
}
