<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <spring:url value="/resources/assets/css" var="style" />
    <link rel="stylesheet" type="text/css"
          href="${style}/style.css">
</head>
<body>
<spring:url value="/resources/assets/img" var="images" />
<img src="${images}/img.png"  alt="logo" />
    <h3>Learning Spring MVC</h3>
    <hr />
    <ol>
        <li>
            <a href="./show-form">Reading form data</a>
        </li>
        <li>
            <a href="./show-form-model">Reading form data using model</a>
        </li>
        <li>
            <a href="./show-form-request-params">Reading form data using request param binding</a>
        </li>
        <li>
            <a href="./solving/ambiguous/mapping/show-form">Solving ambiguous mapping</a>
        </li>

    </ol>
</body>
</html>