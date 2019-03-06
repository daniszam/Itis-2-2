<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 2019-02-28
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>

    <title>Title</title>
    <link href="<c:url value="/resources/css/form.css" />" rel="stylesheet">
</head>
<body>
    <%--<form onclick="${s:mvcUrl('C#calculus').build()}">--%>
    <form action="${s:mvcUrl('C#calc').build()}">
        <label for="a">First num</label>
        <input type="text" id="a" name="a">
        <br>
        <label for="b">Second Num</label>
        <input type="text" id="b" name="b">
        <label for="command">Command</label>
        <input type="text" id="command" name="command">
        <h6>${result}</h6>
        <button type="submit"> Посчитать </button>
    </form>
</body>
</html>
