package com.teamIMS.springdemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="bugReport")
public class BugReport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy="bugReport",
			cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
				  CascadeType.REFRESH, CascadeType.REMOVE})
	private List<BugReportComment> bugReportComments;
	
	public BugReport() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BugReportComment> getBugReportComments() {
		return bugReportComments;
	}

	public void setBugReportComments(List<BugReportComment> bugReportComments) {
		this.bugReportComments = bugReportComments;
	}
	
	//add convenience methods for bi-directional relationship
	public void add(BugReportComment tempComments) {
		
		if(bugReportComments == null) {
			bugReportComments = new ArrayList<>();
		}
		
		bugReportComments.add(tempComments);
		tempComments.setBugReport(this);
	}
	

	@Override
	public String toString() {
		return "BugReport [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", status=" + status + "]";
	}
	
}
