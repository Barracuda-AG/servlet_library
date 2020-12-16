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
<form  method="POST" action="/api/login">
    <div>
        <label><b><fmt:message key="login"/></b></label>
        <input type="text" placeholder="<fmt:message key="login"/>" name="email" required>

        <label><b><fmt:message key="password"/></b></label>
        <input type="password" placeholder="<fmt:message key="password"/>" name="password" required>

        <button type="submit">
            <fmt:message key="login"/>
        </button>
    </div>
    <a href="index.jsp"><fmt:message key="login.title"/></a>

</form>
</body>
</html>
