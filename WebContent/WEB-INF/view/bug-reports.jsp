<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>
	
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- reference our style sheet -->
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	<title>Bug Reports</title>
	
</head>


<body>

	<div id="container" class="w3-container w3-pale-green wrapper w3-bottombar w3-border-red">
		
		<h2 style="margin:20px 10px">Bug Reports</h2>
		
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Bug report  -->
		
			<input type="button" value="Add Bug Report"
				   onClick="window.location.href='showFormForAdd'; return false;"
				   class="w3-button w3-khaki w3-round-large"
			/>
			
			<!-- display username and login info -->
			
			<p>
				User: <security:authentication property="principal.username" />
				<br />
				Role: <security:authentication property="principal.authorities"/>
			</p>
			
			
			<!-- logout button -->
			<form:form action="${pageContext.request.contextPath}/logout"
						method="POST">
						
				<input type="submit" value="Logout" />
				
			</form:form>
			
			<!-- add our search form here -->
			 <form:form action="search" method="GET">
                Search Bug Report: <input type="text" name="search" />
                
                <input type="submit" value="Search" class="btn btn-primary" style="margin:5px 3px" />
                
                Status <input type="radio" id="status" name="filter" value="status">
                First Name <input type="radio" id="firstName" name="filter" value="firstName">
                
            </form:form>
			
		
			<!-- add our html table here -->
			<table class="w3-table w3-hoverable w3-bordered">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Status</th>
					<th>Action</th>	
				</tr>
				
				<!-- loop over and print our bug reports -->
				<c:forEach var="tempBugReports" items="${bugReport}">
				
					<!-- construct an "update" link with bug id -->
					<c:url var="updateLink" value="/bugReport/showFormForUpdate">
						<c:param name="bugReportId" value="${tempBugReports.id}" />
					</c:url>	
						
					<!-- construct a "delete" link with bug id -->
					<c:url var="deleteLink" value="/bugReport/delete">
						<c:param name="bugReportId" value="${tempBugReports.id}" />
					</c:url>
					
					<tr>
						<td>${tempBugReports.firstName}</td>
						<td>${tempBugReports.lastName}</td>
						<td>${tempBugReports.email}</td>
						<td>${tempBugReports.status}</td>
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">View</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this bug report?'))) return false">Delete</a>
						</td>
					</tr>
				
				</c:forEach>
			</table>
		</div>
	
	</div>

</body>


</html>