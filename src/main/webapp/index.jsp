<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:set var="language" scope="session" value="${locale}" />--%>
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
<%--<a href="?locale=en"  type="submit">--%>
    <a href="${pageContext.request.contextPath}?locale=en">
                    <img src="${pageContext.request.contextPath}/image/english.png" width="30"/>
                </a>
            </li>

            <li>
<%--<a href="?locale=uk_UA" type="submit">--%>
   <a href="${pageContext.request.contextPath}?locale=uk_UA">
                   <img src="${pageContext.request.contextPath}/image/ukraine.png" width="30"/>
                </a>
            </li>
        </ul>
</div>

<h2><fmt:message key="greetings"/></h2>
<a href="login.jsp"><fmt:message key="login"/></a>
</body>
</html>


