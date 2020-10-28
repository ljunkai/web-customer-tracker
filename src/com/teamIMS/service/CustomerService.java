package com.teamIMS.service;

import java.util.List;

import com.teamIMS.entity.BugReport;

public interface CustomerService {

	public List<BugReport> getCustomers();

	public void saveCustomer(BugReport theCustomer);

	public BugReport getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<BugReport> searchCustomer(String search);
}
