package com.example.ToDoList.service.login;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ToDoList.DAO.RoleRepository;
import com.example.ToDoList.DAO.UserRepository;
import com.example.ToDoList.entity.Role;
import com.example.ToDoList.entity.User;

@Service
public class UserService {
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		userRepo = userRepository;
		roleRepo = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User findUserByUserName(String name) {
		return userRepo.findByUserName(name);
	}
	
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepo.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepo.save(user);
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		else {
			username = principal.toString();
		}

		return findUserByUserName(username);
	}
}
