<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <form action="handle-form-model" method="GET">
        <label for="studentName">Name: </label>
        <input type="text"
               name="studentName"
               id="studentName"
               placeholder="What is your name" />
        <input type="submit" />
    </form>
</body>
</html>