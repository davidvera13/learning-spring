<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h3>Handling form...</h3>
    <p>
        Student information: ${student.firstName} ${student.lastName}<br />
        Student country: ${student.country} <br />
        Student favorite language: ${student.favoriteLanguage}<br />
        Operating systems:
        <ul>
            <c:forEach var="os" items="${student.operatingSystems}">
            <li>${os}</li>
            </c:forEach>
        </ul>
    </p>
</body>
</html>