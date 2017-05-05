<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.username}&nbsp;</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>">
    <fmt:setLocale value="en_US" scope="request"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>${user.username}
            <%--<small><fmt:formatDate type="date" value="${user.birthday}"/></small>--%>
        </h1>
    </div>


    <img class="avatar" src="${user.photoUrl}" alt="${user.username}'s photo">


</div>

</body>
</html>
