package com.example.ToDoList.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.example.ToDoList.entity.Task;

@Repository
public class TaskDAOImp implements TaskDAO {
	
	private EntityManager manager;
	
	public TaskDAOImp(EntityManager theEntityManager) {
		manager = theEntityManager;
	}

	@Override
	public List<Task> findAll() {
		Session session = manager.unwrap(Session.class);
		
		Query<Task> theQuery = session.createQuery("from Task", Task.class);
		
		List<Task> tasks = theQuery.getResultList();
		
		return tasks;
	}

	@Override
	public Task findById(int id) {
		Session session = manager.unwrap(Session.class);
		
		Task theTask = session.find(Task.class, id);
		
		return theTask;
	}

	@Override
	public void save(Task task) {
		Session session = manager.unwrap(Session.class);
		
		session.saveOrUpdate(task);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
