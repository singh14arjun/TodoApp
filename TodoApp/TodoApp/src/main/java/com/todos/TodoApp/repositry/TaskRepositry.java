package com.todos.TodoApp.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todos.TodoApp.model.Task;

@Repository
public interface TaskRepositry extends JpaRepository<Task, Integer>{
	 List<Task> findByCompleted(boolean completed);}
