<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Admin Cabinet</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
<div class ="container">
    <div>
        <nav class="navbar navbar-expand bg-light" >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <form  method="POST" action="/api/logout">
                        <button class="btn btn-primary" type="submit"><fmt:message key="logout"/></button>
                    </form>
                </li>
            </ul>
        </nav>
    </div>
</div>
<h2><fmt:message key="user.all"/></h2>
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="role"/></th>
            <th><fmt:message key="subscription"/></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}" varStatus = "loopStatus">
            <tr>
            <td><br><c:out value="${user.firstName}"/></td>
            <td><br><c:out value="${user.lastName}"/></td>
            <td><br><c:out value="${user.email}"/></td>
            <td><br><c:out value="${user.role}"/></td>
            <td><br><c:out value="${user.order}"/></td>
                <td> <button class="btn btn-primary" >
                    <fmt:message key="delete"/>
                </button></td>
        </c:forEach>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>

