<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>">
    <fmt:setLocale value="en_US" scope="request"/>
</head>
<body style="text-align: center">
<h1>User list</h1>

<table style="margin-left: auto;margin-right: auto" cellspacing="30px">
    <c:forEach items="${userList}" var="user" varStatus="counter">
        <tr style="vertical-align: top">
            <td><img src="${user.photoUrl}" style="width: 100px"/></td>
            <td>${user.username}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>


</table>
<a href="<c:url value='/users/add'/>">Add</a>
</body>
</html>
