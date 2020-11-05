package com.teamIMS.entity;

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
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private String status;
	
	@Column(name="reportedBy")
	private String reportedBy;
	
	@Column(name="assignee")
	private String assignee;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
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
		return "BugReport [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", reportedBy=" + reportedBy + ", assignee=" + assignee + ", bugReportComments=" + bugReportComments
				+ "]";
	}
	
}
