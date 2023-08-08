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
<h3>Handling form...</h3>
    Student name: ${param.studentName }

</body>
</html>