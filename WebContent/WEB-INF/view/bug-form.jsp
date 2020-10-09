<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
	<title>Bug Report</title>
	
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
		<h3>Save Customer</h3>
		
		<form:form action="saveBugReport" modelAttribute="bugReport" method="POST">
		
		<!-- need to associate this data with bug id -->
		<form:hidden path="id" />
		
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><form:input path="firstName" /></td>
					</tr>
					<tr>
						<td><label>First Name:</label></td>
						<td><form:input path="lastName" /></td>
					</tr>
					<tr>
						<td><label>First Name:</label></td>
						<td><form:input path="email" /></td>
					</tr>
					<tr>
						<td><label>Status:</label></td>
						<td>
							<form:select path="status">  
       						<form:option value="Open" label="Open"/>  
					        <form:option value="Pending Verification" label="Pending Verification"/>  
					        <form:option value="Passed" label="Passed"/>  
					        <form:option value="Closed" label="Closed"/>
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
		
	</div>

</body>

</html>