<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Login</title>
</head>
<body>
<fmt:message key="greetings"/>
<form class="w3-container" method="POST" action="">
    <div class="w3-section w3-left-align">
        <label><b><fmt:message key="login"/></b></label>
        <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="login"/>" name="login" required>

        <label><b><fmt:message key="password"/></b></label>
        <input class="w3-input w3-border" type="password" placeholder="<fmt:message key="password"/>" name="password" required>

        <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" type="submit">
            <fmt:message key="login"/>
        </button>
    </div>
</form>
</body>
</html>
