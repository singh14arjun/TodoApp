package com.todos.TodoApp.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todos.TodoApp.model.Task;

@Repository
public interface TaskRepositry extends JpaRepository<Task, Integer>{

}
