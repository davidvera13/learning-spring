<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Save customer</title>
	<spring:url value="/resources/assets/css" var="style" />
	<link rel="stylesheet" type="text/css"
		  href="${style}/style.css">
	<link rel="stylesheet" type="text/css"
		  href="${style}/add-customer-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
		<div id="container">
			<form:form 
				action="saveCustomer" 
				modelAttribute="customer"
				method="POST">
				
				<!--  need to associate the data with customerId -->
				<form:hidden path="id"/>
				
				<table>
					<tbody>
						<tr>
							<td><label>First name: </label></td>
							<td><form:input path="firstName"/></td>
						</tr>
						<tr>
							<td><label>Last name: </label></td>
							<td><form:input path="lastName"/></td>
						</tr>
						<tr>
							<td><label>Email: </label></td>
							<td><form:input path="email"/></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="save" class="save" /></td>
						</tr>
					</tbody>
				</table>
				
			</form:form>
			
			<div style="clear: both"></div>
			<p>
				<a href="${pageContext.request.contextPath}/customer/list">
					Back to list
				</a>
			</p>
			
		
		</div>
	
	</div>

</body>
</html>