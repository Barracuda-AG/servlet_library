<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Index</title>
</head>
<body>
<div>
        <ul>
            <li>
    <a href="${pageContext.request.contextPath}?locale=en">
        <img src="<c:url value="english.png"/>" width="30" alt="<fmt:message key="button.english"/>">
                </a>
            </li>

            <li>
   <a href="${pageContext.request.contextPath}?locale=uk_UA">
       <img src="<c:url value="ukraine.png"/>" width="30" alt="<fmt:message key="button.ukrainian"/>">
   </a>
            </li>
        </ul>
</div>

<h2><fmt:message key="greetings"/></h2>
<a href="login.jsp"><fmt:message key="login"/></a>
<a href="registration.jsp"><fmt:message key="registration"/></a>

</body>
</html>


