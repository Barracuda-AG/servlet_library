<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html lang="${locale}">
<head>
    <title>Registration</title>
</head>
<body>
<fmt:message key="greetings"/>

    <form action="/api/registration" method="POST">
        <p><fmt:message key="name.first"/></p>
        <input type="text" placeholder="<fmt:message key="name.first"/>" name="firstName" />
        <br/>
        <p><fmt:message key="name.last"/></p>
        <input type="text" placeholder="<fmt:message key="name.last"/>"  name="lastName" />
        <br/>
        <p><fmt:message key="email"/> </p>
        <input type="email" placeholder="<fmt:message key="email"/>" name="email" />
        <br/>
        <p><fmt:message key="password"/> </p>
        <input type="password" placeholder="<fmt:message key="email"/>" name="password" >
        <br/>
        <button type="submit" class="btn btn-primary"><fmt:message key="registration"/> </button>
    </form>
<a href="login.jsp"><fmt:message key="login"/></a>

</body>
</html>

