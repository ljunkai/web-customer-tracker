package com.teamIMS.springdemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bugReportComment")
public class BugReportComment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="comment")
	private String comment;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
		     CascadeType.REFRESH})
	@JoinColumn(name="bugReportId")
	private int bugReportId;

	
	public BugReportComment() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getBugReportId() {
		return bugReportId;
	}

	public void setBugReportId(int bugReportId) {
		this.bugReportId = bugReportId;
	}

	@Override
	public String toString() {
		return "bugReportComment [id=" + id + ", comment=" + comment + ", bugReportId=" + bugReportId + "]";
	}
	
}
