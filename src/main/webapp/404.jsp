<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>404</title>
</head>
<body>
<div class ="container">
    <div>
        <nav class="navbar navbar-expand bg-light" >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><fmt:message key="login"/></a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<h2><fmt:message key="error.404"/></h2>

</body>
</html>