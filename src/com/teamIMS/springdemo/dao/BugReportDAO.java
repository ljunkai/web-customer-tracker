package com.teamIMS.springdemo.dao;

import java.util.List;

import com.teamIMS.springdemo.entity.BugReport;
import com.teamIMS.springdemo.entity.BugReportComment;

public interface BugReportDAO {

	public List<BugReport> getBugReports();

	public void saveBugReport(BugReport theCustomer);

	public BugReport getBugReport(int theId);

	public void deleteBugReport(int theId);
	
	public List<BugReport> searchBugReport(String search);
	
	public List<BugReport> searchBugReport(String search, String filter);
	
	public List<BugReportComment> getComments(String reportId);
}
