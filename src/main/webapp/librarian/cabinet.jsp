<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Librarian Cabinet</title>
</head>
<body>
<form  method="POST" action="/api/logout">
    <button type="submit"><fmt:message key="logout"/></button>
</form>
<h2><fmt:message key="login.success"/></h2>
<h2><fmt:message key="librarian"/></h2>
</body>
</html>
