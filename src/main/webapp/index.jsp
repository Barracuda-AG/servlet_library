<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="locale" scope="session"/>

<html>
<head>
    <title>Index</title>
</head>
<body>
<div>

        <ul>
            <li>
                <a href="?locale=en">
                    <img src="${pageContext.request.contextPath}/image/english.png" width="30"/><fmt:message key="button.english"/>
                </a>
            </li>

            <li>
                <a href="?locale=uk_UA">
                    <img src="${pageContext.request.contextPath}/image/ukraine.png" width="30"/><fmt:message key="button.ukrainian"/>
                </a>
            </li>
        </ul>
</div>

<h2><fmt:message key="greetings"/></h2>
<a href="login.jsp"><fmt:message key="login"/></a>
</body>
</html>







<%--<fmt:setBundle basename="messages"/>--%>
<%--<fmt:setLocale value="locale" />--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Index</title>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--</head>--%>
<%--<body>--%>
<%--<h2>--%>
<%--    <fmt:message key="greetings"/>--%>
<%--</h2>--%>

<%--<br/>--%>
<%--<a href="${pageContext.request.contextPath}api/login"><fmt:message key="login"/></a>--%>
<%--<br/>--%>


<%--</body>--%>
<%--</html>--%>

