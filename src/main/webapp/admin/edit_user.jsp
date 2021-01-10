<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Book edit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
<header>
    <div>
        <nav class="navbar navbar-expand bg-light" >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <form  method="POST" action="/logout">
                        <button class="btn btn-primary" type="submit"><fmt:message key="logout"/></button>
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/cabinet"><fmt:message key="cabinet"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>

<div class="container">

        <h4><fmt:message key="user"/></h4>
        <div><c:out value="${user.email}"/></div>
        <hr>
        <h4><fmt:message key="name"/></h4>
        <div><c:out value="${user.firstName}"/></div>
        <hr>
        <h4><fmt:message key="surname"/></h4>
        <div><c:out value="${user.lastName}"/></div>
        <hr>
        <h4><fmt:message key="role"/></h4>
        <div><c:out value="${user.role}"/></div>
        <hr>
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="role" value="${user.role}">
    <form action="${pageContext.request.contextPath}/admin/change_role" method="POST">
        <button type="submit" class="btn btn-primary">
            <c:if test="${user.role eq 'ROLE_USER'}">
                <fmt:message key="to.librarian"/>
            </c:if>
            <c:if test="${user.role eq 'ROLE_LIBRARIAN'}">
                <fmt:message key="to.user"/>
            </c:if>

        </button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/block_user" method="POST">
        <input type="hidden" name="id" value="${user.id}">

            <c:if test="${user.accountNonLocked eq false}">
        <button type="submit" class="btn btn-success">
                <fmt:message key="unlock"/>
        </button>
            </c:if>
            <c:if test="${user.accountNonLocked eq true}">
        <button type="submit" class="btn btn-danger">
        <fmt:message key="lock"/>
        </button>
            </c:if>
    </form>

</div>

</body>
</html>
