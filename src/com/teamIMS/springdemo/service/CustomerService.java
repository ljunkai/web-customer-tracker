package com.teamIMS.springdemo.service;

import java.util.List;

import com.teamIMS.springdemo.entity.BugReport;

public interface CustomerService {

	public List<BugReport> getCustomers();

	public void saveCustomer(BugReport theCustomer);

	public BugReport getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<BugReport> searchCustomer(String search);
}
