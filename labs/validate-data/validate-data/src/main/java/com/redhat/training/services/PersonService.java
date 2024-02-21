package com.redhat.training.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.redhat.training.model.Person;

@Stateless
public class PersonService {
    //TODO: obtain an EntityManager instance using @PersistenceContext 
	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;

	// Simple non-RESTy method for JSF bean invocation
	public String hello(String name) {
		try {
				// let's grab the current date and time on the server
				LocalDateTime today = LocalDateTime.now();

				// format it nicely for on-screen display
				DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a");
				String fdate = today.format(format);

				// Create a new Person object and persist to database
				Person p = new Person();
				p.setName(name);
				// call persist() method of entity manager to save the data																
				entityManager.persist(p);

				// respond back with Hello and convert the name to UPPERCASE. Also, send the
				// current time on the server.
				return "Hello " + name.toUpperCase() + "!. " + "Time on the server is: " + fdate;
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	public String getPerson(Long id) {
           
		return entityManager.find(Person.class, id).getPersonName();
	}

}
