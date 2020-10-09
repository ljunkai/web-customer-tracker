package com.teamIMS.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamIMS.springdemo.dao.BugReportDAO;
import com.teamIMS.springdemo.entity.BugReport;

@Service
public class CustomerServiceImpl implements CustomerService {

	//need to inject customer dao
	@Autowired
	private BugReportDAO bugReportDAO;
	
	
	@Override
	@Transactional
	public List<BugReport> getCustomers() {
	
		return bugReportDAO.getBugReports();
	}

	@Override
	@Transactional
	public void saveCustomer(BugReport theCustomer) {
		
		bugReportDAO.saveBugReport(theCustomer);
	}

	@Override
	@Transactional
	public BugReport getCustomer(int theId) {
	
		return bugReportDAO.getBugReport(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		bugReportDAO.deleteBugReport(theId);
		
	}
	
	@Override
	@Transactional
	public List<BugReport> searchCustomer(String search) {
		
		return bugReportDAO.searchBugReport(search);
	}

}
