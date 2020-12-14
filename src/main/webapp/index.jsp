<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" />

<html lang="language">
<head>
    <title>Index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>
    <fmt:message key="greetings"/>
</h2>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="button.english"/></option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}><fmt:message key="button.ukrainian"/></option>
    </select>
</form>
<br/>
<a href="${pageContext.request.contextPath}/login"><fmt:message key="login"/></a>
<br/>
<a href="${pageContext.request.contextPath}/registration"><fmt:message key="registration"/></a>

</body>
</html>

