<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 2019-03-07
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<a href="<c:url value="/" />">Homepage</a><br><br>
<c:if test="${not empty message}">
    <h1>${message}</h1>
</c:if>
<form:form method="POST" modelAttribute="user">
    <form:label path="userName">User Name</form:label>
    <form:input path="userName"/>
    <form:errors path="userName" /><br>

    <form:label path="email">Email</form:label>
    <form:textarea path="email"/>
    <form:errors path="email" /><br>

    <form:checkbox path="emailSubscription"/>
    <form:errors path="emailSubscription" cssClass="error" />

    <form:radiobutton path="gender" value="${male}"/>
    <form:label path="gender" for="${male}">Male</form:label>
    <form:radiobutton path="gender" value="${female}"/>
    <form:label path="gender" for="${female}">FEMale</form:label>
    <form:errors path="gender" cssClass="error" />


    <form:select path="country" items="${countryItems}" />
    <form:errors path="country" cssClass="error" />


    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
