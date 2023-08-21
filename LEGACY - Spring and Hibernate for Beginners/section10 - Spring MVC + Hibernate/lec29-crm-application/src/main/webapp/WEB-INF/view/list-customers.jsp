<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ page import="spring.learning.springframework.app.helpers.SortUtils" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List customers</title>
	<spring:url value="/resources/assets/css" var="style" />
	<link rel="stylesheet" type="text/css"
		  href="${style}/style.css">
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h1>List customers</h1>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="Add Customer" 
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button">

			<!--  add a search box -->
            <form:form action="search" method="GET">
				<label for="name">Search customer: </label><input type="text" id="name" name="searchedName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
            
			<table>
				<thead>
					<tr>
						<th>
							<!-- construct a sort link for first name -->
							<c:url var="sortLinkFirstName" value="/customer/list">
								<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
							</c:url>
							<a href="${sortLinkFirstName}">First Name</a>
						</th>
						<th>
							<c:url var="sortLinkLastName" value="/customer/list">
								<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
							</c:url>
							<a href="${sortLinkLastName}">Last Name</a>
						</th>
						<th>
							<!-- construct a sort link for email -->
							<c:url var="sortLinkEmail" value="/customer/list">
								<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
							</c:url>
							<a href="${sortLinkEmail}">Email</a>
						</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!-- loop over and print customers from list -->
					<c:forEach var="customer" items="${customers}">
						<c:url var="updateLink" value="/customer/showFormForUpdate" >
							<c:param name="customerId" value="${customer.id }" />
						</c:url>
						<c:url var="deleteLink" value="/customer/delete" >
							<c:param name="customerId" value="${customer.id }" />
						</c:url>
						
						<tr>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
							<td>${customer.email}</td>
							<td>
								<a href="${updateLink}">Update</a> | 
								<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete ?'))) return false" >Delete</a>
							</td>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
		</div>
	</div>

</div>
</body>
</html>