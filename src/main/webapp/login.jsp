<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html lang="${locale}">
<head>
    <title>Login</title>
</head>
<body>
<fmt:message key="greetings"/>

<form method="POST" action="/api/login">
<p><fmt:message key="email"/> </p>
<input type="email" placeholder="<fmt:message key="email"/>" name="email" />
<br/>
<p><fmt:message key="password"/> </p>
<input type="password" placeholder="<fmt:message key="email"/>" name="password" >
<br/>
<button type="submit" class="btn btn-primary"><fmt:message key="login"/> </button>
</form>
<a href="index.jsp"><fmt:message key="login.title"/></a>
<a href="registration.jsp"><fmt:message key="registration"/></a>
</body>
</html>
