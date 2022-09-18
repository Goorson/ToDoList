package com.example.ToDoList.service.login;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ToDoList.DAO.RoleRepository;
import com.example.ToDoList.DAO.UserRepository;
import com.example.ToDoList.entity.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceTest {

	@Mock
	private UserRepository mockUserRepository;
	
	@Mock
	private RoleRepository mockRoleRepository;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	private UserService userServiceUnderTest;
	
	private User user;
	
	@Before(value = "")
	public void setUp() {
		openMocks(this);
		userServiceUnderTest = new UserService(mockUserRepository, 
				mockRoleRepository, 
				passwordEncoder);
		
		user = new User();
		user.setId(1);
		user.setFirstName("Adam");
		user.setLastName("Gorecki");
		user.setEmail("test@test.com");
  				
				Mockito.when(mockUserRepository.save(any()))
				.thenReturn(user);
				Mockito.when(mockUserRepository.findByUserName(anyString()))
				.thenReturn(user);
				
	}
	
	@Test
	void testFindUserByEmail() {
		// setup
		final String email = "test@test.com";
		
		final User result = userServiceUnderTest.findUserByEmail(email);
		
		assertEquals(email, result.getEmail());
	}
	
	@Test
	public void testSaveUser() {
		final String email = "test@test.com";
		
		User result = userServiceUnderTest.saveUser(new User());
		
		assertEquals(email, result.getEmail());
	}

}
