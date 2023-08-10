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
    <form:form action="handle-form" modelAttribute="student">
        <label for="firstName">First name: </label>
        <form:input path="firstName" id="firstName" />
        <br /><br />
        <label for="lastName">Last name: </label>
        <form:input path="lastName" id="lastName" />
        <br /><br />
        <%-- hard coded ...
        <form:select path="country">
            <form:option value="Brazil" label="Brazil" />
            <form:option value="France" label="France" />
            <form:option value="Germany" label="Germany" />
            <form:option value="India" label="India" />
            <form:option value="Spain" label="Spain" />
        </form:select>
        -->
        <%-- using dynanic values        --%>
        <form:select path="country">
            <form:options items="${student.countryOptions}" />
        </form:select>
        <br /><br />
        <label>Favorite programming language</label>
        <br /><br />
        <%-- hard coded
        Java   <form:radiobutton path="favoriteLanguage" value="Java" />
        PHP    <form:radiobutton path="favoriteLanguage" value="PHP" />
        C#     <form:radiobutton path="favoriteLanguage" value="C#" />
        Python <form:radiobutton path="favoriteLanguage" value="Python" />
        --%>
        <%-- using dynanic values --%>
        <form:radiobuttons
                path="favoriteLanguage"
                items="${student.favoriteLanguageOptions}"  />
        <br /><br />
        <label>Operating systems: </label>
        Linux   <form:checkbox path="operatingSystems" value="Linux" />
        Mac OS  <form:checkbox path="operatingSystems" value="MacOs" />
        Windows <form:checkbox path="operatingSystems" value="Windows" />
        <br /><br />

        <input type="submit" value="submit"/>
    </form:form>
</body>
</html>