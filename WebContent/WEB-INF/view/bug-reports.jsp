<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
	<title>Bug Reports</title>
	
	<!-- reference our style sheet -->
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
	
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>Bug Reports</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Bug report  -->
		
			<input type="button" value="Add Bug Report"
				   onClick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
			
			<!-- add our search form here -->
			 <form:form action="search" method="GET">
                Search Bug Report: <input type="text" name="search" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
			
		
			<!-- add our html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
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
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
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