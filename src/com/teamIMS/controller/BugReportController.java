package com.teamIMS.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamIMS.dao.BugReportDAO;
import com.teamIMS.entity.BugReport;
import com.teamIMS.entity.BugReportComment;
import com.teamIMS.service.CustomerService;

@Repository
@Controller
@RequestMapping("/bugReport")
public class BugReportController {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping("/list")
	@Transactional
	public String listBugReport(Model theModel) {
		
		//get bug reports from the service
		//List<BugReport> bugReports = bugReportDAO.getBugReports();
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		Query<BugReport> theQuery = 
				currentSession.createQuery("from BugReport order by id", 
											BugReport.class);
		
		//execute query and get result list
		List<BugReport> bugReports = theQuery.getResultList();
		
		//add the bug reports to the model
		theModel.addAttribute("bugReport", bugReports);
		
		return "bug-reports";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		BugReport theBugReport = new BugReport();
		
		theModel.addAttribute("bugReport", theBugReport);
		
		return "bug-form";
	}
	
	@PostMapping("/saveBugReport")
	@Transactional
	public String saveBugReport(@ModelAttribute("bugReport") BugReport theBug) {
		
		//save the bug using our DAO
		//bugReportDAO.saveBugReport(theBug);
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save or update the customer by checking if primary key(id) already exists
		currentSession.saveOrUpdate(theBug);
		
		return "redirect:/bugReport/list";
	}
	
	@GetMapping("/showFormForUpdate")
	@Transactional
	public String showFormForUpdate(@RequestParam("bugReportId") int theId,
									Model theModel) {
		
		// get the bug report
		//BugReport theBug = bugReportDAO.getBugReport(theId);
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve or read from database using the primary key
		BugReport theBug = currentSession.get(BugReport.class,  theId);
		
		//get the report's comments
		//List<BugReportComment> bugReportComments = bugReportDAO.getComments(theId);
		
		Query<BugReportComment> theQuery = 
				currentSession.createQuery("from BugReportComment brc where brc.bugReport.id =:reportId", 
											BugReportComment.class);
		
		theQuery.setParameter("reportId", theId);
		
		//execute query and get result list
		List<BugReportComment> bugReportComments = theQuery.getResultList();
		
		//set bug report as a model attribute to pre-populate the form
		theModel.addAttribute("bugReport", theBug);
		
		//set the bug report's comments
		theModel.addAttribute("bugReportComment", bugReportComments);
		
		//send over to our form
		return "bug-form";
	}
	
	@GetMapping("/delete")
	@Transactional
	public String deleteBugReport(@RequestParam("bugReportId") int theId) {
		
		//delete the bug report
		//bugReportDAO.deleteBugReport(theId);
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete from the database using the primary key(id)
		Query theQuery = 
				currentSession.createQuery("delete from BugReport where id=:bugReportId");
		theQuery.setParameter("bugReportId", theId);
		
		theQuery.executeUpdate();
		
		return "redirect:/bugReport/list";
	}
	
	/*
	@GetMapping("/search")
	public String searchBugReport(@RequestParam("search") String search,
									Model theModel) {
		
		//search bug reports from the DAO
		List<BugReport> bugReportResult = bugReportDAO.searchBugReport(search);
		
		//add search result to bug report
		theModel.addAttribute("bugReport", bugReportResult);
		
		return "bug-reports";
	} */
	
	@GetMapping("/search")
	@Transactional
	public String searchBugReport(@RequestParam("search") String search,
									@RequestParam(required=false) String filter,
									Model theModel) {
		
		List<BugReport> bugReportResult = null;
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		if(filter != null) {
		
			//search bug reports from the DAO
			//bugReportResult = bugReportDAO.searchBugReport(search, filter);
			
		
			
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
			bugReportResult = query.getResultList();
			
			
			
		} else { //filter is null
			
			//search bug reports from the DAO
			//bugReportResult = bugReportDAO.searchBugReport(search);
			
			//set to default null
			Query query = null;
			
			//Only search if search is not empty
			if(search != null && search.trim().length() > 0) {
				
				//query from the database with HQL
				query =
					currentSession.createQuery("from BugReport where lower(title) like :search"
												+ " or lower(description) like :search", BugReport.class);
				
				//set query's param
				query.setParameter("search", search);
				
			} else {
				
				//search is empty, so we will retrieve all Customers
				query = 
					currentSession.createQuery("from BugReport", BugReport.class);
			}
			
			//execute query and get result list
			bugReportResult = query.getResultList();
		}
		
		//add search result to bug report
		theModel.addAttribute("bugReport", bugReportResult);
		
		return "bug-reports";
	} 
	
	@PostMapping("/postComment")
	@Transactional
	public String postComment(@RequestParam("bugReportId") int theId,
								@RequestParam("comment") String comment,
								Model theModel) {
		
		//Call the DAO object to save the comment
		//bugReportDAO.saveComment(id, comment);
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Retrieve the bug report
		BugReport tempBugReport = currentSession.get(BugReport.class, theId);
		
		//Init the bug report comment obj
		BugReportComment tempBugReportComment = new BugReportComment(comment);
		
		//Add to the specified bug report
		tempBugReport.add(tempBugReportComment);
		
		currentSession.save(tempBugReportComment);
		
		
		/** Repeat codes from showUpdateForm to render back the same report Id **/
		  // get the bug report from DAO
		//BugReport theBug = bugReportDAO.getBugReport(id);
		
		//get the report's comments
		//List<BugReportComment> bugReportComments = bugReportDAO.getComments(id);
		
		//retrieve or read from database using the primary key
		BugReport theBug = currentSession.get(BugReport.class,  theId);
		
		//get the report's comments
		//List<BugReportComment> bugReportComments = bugReportDAO.getComments(theId);
		
		Query<BugReportComment> theQuery = 
				currentSession.createQuery("from BugReportComment brc where brc.bugReport.id =:reportId", 
											BugReportComment.class);
		
		theQuery.setParameter("reportId", theId);
		
		//execute query and get result list
		List<BugReportComment> bugReportComments = theQuery.getResultList();

		
		//set bug report as a model attribute to pre-populate the form
		theModel.addAttribute("bugReport", theBug);
		
		//set the bug report's comments
		theModel.addAttribute("bugReportComment", bugReportComments);
		
		return "bug-form";
	}

	
}
