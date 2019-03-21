<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 2019-03-14
  Time: 22:01
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
<form:form method="POST" modelAttribute="author">
    <form:label path="name">Name</form:label>
    <form:input path="name"/>
    <form:errors path="name" /><br>

    <form:label path="avatarUrl">Avatar url</form:label>
    <form:input path="avatarUrl" />
    <form:errors path="avatarUrl" /><br><!-- Why no errors?  -->

    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
