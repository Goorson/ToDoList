package com.example.ToDoList.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDoList.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String name);
	User findByEmail(String email);
}
