package com.teamIMS.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamIMS.springdemo.dao.BugReportDAO;
import com.teamIMS.springdemo.entity.BugReport;
import com.teamIMS.springdemo.service.CustomerService;

@Controller
@RequestMapping("/bugReport")
public class BugReportController {

	//need to inject bugReport DAO
	@Autowired
	private BugReportDAO bugReportDAO;
	
	@RequestMapping("/list")
	public String listBugReport(Model theModel) {
		
		//get customers from the service
		List<BugReport> bugReports = bugReportDAO.getBugReports();
		
		//add the customers to the model
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
	public String saveBugReport(@ModelAttribute("bugReport") BugReport theBug) {
		
		//save the bug using our DAO
		bugReportDAO.saveBugReport(theBug);
		
		return "redirect:/bugReport/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bugReportId") int theId,
									Model theModel) {
		
		// get the bug report from DAO
		BugReport theBug = bugReportDAO.getBugReport(theId);
		
		//set bug report as a model attribute to pre-populate the form
		theModel.addAttribute("bugReport", theBug);
		
		//send over to our form
		return "bug-form";
	}
	
	@GetMapping("/delete")
	public String deleteBugReport(@RequestParam("bugReportId") int theId) {
		
		//delete the bug report
		bugReportDAO.deleteBugReport(theId);
		
		return "redirect:/bugReport/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("search") String search,
									Model theModel) {
		
		//search bug reoprts from the DAO
		List<BugReport> bugReportResult = bugReportDAO.searchBugReport(search);
		
		//add search result to Customer
		theModel.addAttribute("bugReport", bugReportResult);
		
		return "bug-reports";
	}
	
}
