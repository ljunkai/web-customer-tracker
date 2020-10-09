package com.teamIMS.springdemo.dao;

import java.util.List;

import com.teamIMS.springdemo.entity.BugReport;

public interface BugReportDAO {

	public List<BugReport> getBugReports();

	public void saveBugReport(BugReport theCustomer);

	public BugReport getBugReport(int theId);

	public void deleteBugReport(int theId);
	
	public List<BugReport> searchBugReport(String search);
}
