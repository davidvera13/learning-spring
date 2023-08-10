<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reading html form data</title>
    <spring:url value="/resources/assets/css" var="style" />
    <link rel="stylesheet" type="text/css"
          href="${style}/style.css">
</head>
<body>
    <spring:url value="/resources/assets/img" var="images" />
    <img src="${images}/img.png"  alt="logo" />
    <h3>Show form...</h3>
    <i>Fill out the form. Asterisk means (*) required</i>
    <form:form action="handle-form" modelAttribute="customer">
        <label for="firstName">First name: </label>
        <form:input path="firstName" id="firstName" />
        <br /><br />

        <%-- validation: mandatory value, not null  --%>
        <label for="lastName">Last name (*): </label>
        <form:input path="lastName" id="lastName" />
        <form:errors path="lastName" cssClass="error" />
        <br /><br />

        <%-- validation: number range between 0 and 10  --%>
        <%--
            Failed to convert property value of type java.lang.String
            to required type int for property freePasses;
            For input string: "waow"
        --%>
        <label for="freePasses">Free passes: </label>
        <form:input path="freePasses" id="freePasses" />
        <form:errors path="freePasses" cssClass="error" />
        <br /><br />

        <%-- validation: using regex / pattern --%>
        <label for="postalCode">Postal code : </label>
        <form:input path="postalCode" id="postalCode" />
        <form:errors path="postalCode" cssClass="error" />
        <br /><br />


        <%-- validation: using custom annotation --%>
        <label for="courseCode">Postal code : </label>
        <form:input path="courseCode" id="courseCode" />
        <form:errors path="courseCode" cssClass="error" />
        <br /><br />

        <input type="submit" value="submit"/>
    </form:form>
</body>
</html>