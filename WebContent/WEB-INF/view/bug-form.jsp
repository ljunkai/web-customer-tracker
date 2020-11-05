<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<title>Bug Report</title>
	
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Bug Report</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Details of Bug Report</h3>
		
		<form:form action="saveBugReport" modelAttribute="bugReport" method="POST">
		
		<!-- need to associate this data with bug id -->
		<form:hidden path="id" />
		
			<table style="background:white">
				<tbody>
					<tr>
						<td><label>Title:</label></td>
						<td><form:input path="title" /></td>
					</tr>
					<tr>
						<td><label>Description:</label></td>
						<td><form:textarea path="description" rows="5" columns="40" /></td>
					</tr>
					<tr>
						<td><label>Reported By:</label></td>
						<td><form:input path="reportedBy" /></td>
					</tr>
					<tr>
						<td><label>Status:</label></td>
						<td>
							<form:select path="status">  
       						<form:option value="Open" label="Open"/>  
       						<c:if test="${bugReport.title != NULL}">  
					        <form:option value="Pending Verification" label="Pending Verification"/>  
					        <form:option value="Passed" label="Passed"/> 
					        <form:option value="Closed" label="Closed"/>
					        </c:if>
					        </form:select> 
						</td>
					</tr>				
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"/></td>
					</tr>
				</tbody>
			</table>
		
		</form:form>
		
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/bugReport/list">Back to List</a>
		</p>
		
		<c:if test="${bugReport.title != NULL}"> 
		<!-- Comment Systems -->
		<hr> 
		<div>
			<h3>Comments</h3>
		
			<!-- Display comments -->
			
				<c:forEach var="tempComment" items="${bugReportComment}">
						<p class="w3-panel w3-yellow w3-topbar w3-bottombar w3-border-amber w3-round-xlarge" style="width:50%">${tempComment.comment}</p>
				</c:forEach>	
			
			<!-- Add comments -->
			<form action="postComment" method="POST">
			
				<input type="hidden" name="bugReportId" value="${bugReport.id}">
				<textarea name="comment" rows="10" cols="40">Enter comment here...</textarea>
				
				<input type="submit" value="Post Comment" />
			</form>
			
		</div>
		</c:if>
	</div>

</body>

</html>