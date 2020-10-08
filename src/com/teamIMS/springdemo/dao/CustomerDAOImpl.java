package com.teamIMS.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.teamIMS.springdemo.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", 
											Customer.class);
		
		//execute query and get result list
		List<Customer> customer = theQuery.getResultList();
		
		//return the results
		return customer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save or update the customer by checking if primary key(id) already exists
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve or read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class,  theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete from the database using the primary key(id)
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}
	
	@Override
	public List<Customer> searchCustomer(String search) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//set to default null
		Query query = null;
		
		//Only search if search is not empty
		if(search != null && search.trim().length() > 0) {
			
			//query from the database with HQL
			query =
				currentSession.createQuery("from Customer where lower(lastName) like :theName"
											+ " or lower(firstName) like :theName", Customer.class);
			
			//set query's param
			query.setParameter("theName", search);
			
		} else {
			
			//search is empty, so we will retrieve all Customers
			query = 
				currentSession.createQuery("from Customer", Customer.class);
		}
		
		//execute query and get result list
		List<Customer> customerSearchResult = query.getResultList();
		
		return customerSearchResult;
		
	}

}
