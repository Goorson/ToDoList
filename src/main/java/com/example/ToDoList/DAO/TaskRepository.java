package com.example.ToDoList.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDoList.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
