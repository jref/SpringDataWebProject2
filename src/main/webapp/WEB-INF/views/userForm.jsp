<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New User&nbsp;</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>">
    <fmt:setLocale value="en_US" scope="request"/>
</head>
<body>
<div class="container">
    <table>

        <form action="<c:url value='/users'/>" method="post" enctype="multipart/form-data">
            <tr>
                <td>
                    <input name="username" type="text" placeholder="Username.."/>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="email" type="email" placeholder="Email.."/>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="password" type="password" placeholder="Password.."/>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="photo" type="file">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Save">
                </td>
            </tr>

        </form>
    </table>

</div>

</body>
</html>
