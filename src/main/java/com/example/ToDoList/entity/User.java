package com.example.ToDoList.entity;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@Column(name="user_name")
	@Length(min= 5, message="User name must be longer than 5 characters")
	@NotEmpty(message = "*Please provide a user name")
	private String userName;
	
	@Column(name="password")
	@Length(min= 5, message="Password must be longer than 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private @Setter String password;
	
	@Column(name="first_name")
	@NotEmpty(message = "*Please provide your name")
	private String firstName;
	
	@Column(name="email")
	@Email(message = "Please provide a valid email")
	@NotEmpty(message = "Please provide email")
	private String email;
	
	@Column(name="last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@ManyToMany
	@JoinTable(name = "user_tasks", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns= @JoinColumn(name = "task_id"))
	private Set<Task> tasks; 
	
	@Column(name="active")
	private Boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(int id,
			@Length(min = 5, message = "User name must be longer than 5 characters") @NotEmpty(message = "*Please provide a user name") String userName,
			@Length(min = 5, message = "Password must be longer than 5 characters") @NotEmpty(message = "*Please provide your password") String password,
			@NotEmpty(message = "*Please provide your name") String firstName,
			@NotEmpty(message = "*Please provide your last name") String lastName, Set<Role> roles, Boolean isActive, String email, Set<Task> tasks) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
		this.active = isActive;
		this.email = email;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", roles=" + roles + ", is active: " + active + ", email: " + email + "]";
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
}
