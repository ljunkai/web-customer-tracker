package com.teamIMS.springdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamIMS.springdemo.entity.BugReport;
import com.teamIMS.springdemo.entity.BugReportComment;


@Repository
public class BugReportDAOImpl implements BugReportDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<BugReport> getBugReports() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		Query<BugReport> theQuery = 
				currentSession.createQuery("from BugReport order by lastName", 
											BugReport.class);
		
		//execute query and get result list
		List<BugReport> bugReport = theQuery.getResultList();
		
		//return the results
		return bugReport;
	}

	@Override
	@Transactional
	public void saveBugReport(BugReport theBugReport) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save or update the customer by checking if primary key(id) already exists
		currentSession.saveOrUpdate(theBugReport);
	}

	@Override
	@Transactional
	public BugReport getBugReport(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve or read from database using the primary key
		BugReport theCustomer = currentSession.get(BugReport.class,  theId);
		
		return theCustomer;
	}

	@Override
	@Transactional
	public void deleteBugReport(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete from the database using the primary key(id)
		Query theQuery = 
				currentSession.createQuery("delete from BugReport where id=:bugReportId");
		theQuery.setParameter("bugReportId", theId);
		
		theQuery.executeUpdate();
		
	}
	
	@Override
	@Transactional
	public List<BugReport> searchBugReport(String search) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//set to default null
		Query query = null;
		
		//Only search if search is not empty
		if(search != null && search.trim().length() > 0) {
			
			//query from the database with HQL
			query =
				currentSession.createQuery("from BugReport where lower(lastName) like :theName"
											+ " or lower(firstName) like :theName", BugReport.class);
			
			//set query's param
			query.setParameter("theName", search);
			
		} else {
			
			//search is empty, so we will retrieve all Customers
			query = 
				currentSession.createQuery("from BugReport", BugReport.class);
		}
		
		//execute query and get result list
		List<BugReport> bugReportSearchResult = query.getResultList();
		
		return bugReportSearchResult;
		
	}
	
	@Override
	@Transactional
	public List<BugReport> searchBugReport(String search, String filter) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//set to default null
		Query query = null;
		
		//Only search if search is not empty
		if(search != null && search.trim().length() > 0) {
			
			//query from the database with HQL
			query =
				currentSession.createQuery("from BugReport where lower(" + filter + ") = :theSearch"
											, BugReport.class);
			
			//set query's param
			query.setParameter("theSearch", search);
			
		} else {
			
			//search is empty, so we will retrieve all Customers
			query = 
				currentSession.createQuery("from BugReport", BugReport.class);
		}
		
		//execute query and get result list
		List<BugReport> bugReportSearchResult = query.getResultList();
		
		return bugReportSearchResult;
		
	}

	@Override
	@Transactional
	public List<BugReportComment> getComments(int id) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		/*
		//get the instructor from db
		int theId = id;
		BugReport tempBugReport = currentSession.get(BugReport.class, theId);
		
		List<Comment> comments = new ArrayList<Comment>();
		
		//get course for the instructor
		List<BugReportComment> commentList = tempBugReport.getBugReportComments();
		for (int i=0; i < commentList.size(); i++) {
			
			comments.add(commentList.get(i));
		} */
		
		//create a query for the report's comments
		Query<BugReportComment> theQuery = 
				currentSession.createQuery("from BugReportComment brc where brc.bugReport.id =:reportId", 
											BugReportComment.class);
		
		theQuery.setParameter("reportId", id);
		
		//execute query and get result list
		List<BugReportComment> commentList = theQuery.getResultList();
		
		
		return commentList;
	} 

}
