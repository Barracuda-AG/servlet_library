<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>User Cabinet</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
<div class ="container">
    <div>
        <nav class="navbar navbar-expand bg-light" >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <form  method="POST" action="/api/logout">
                        <button class="btn btn-primary" type="submit"><fmt:message key="logout"/></button>
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/cabinet.jsp"><fmt:message key="cabinet"/></a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="user"/></th>
        <th scope="col"><fmt:message key="surname"/></th>
        <th scope="col"><fmt:message key="name"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
    <td><c:out value="${user.email}"/></td>
    <td><c:out value="${user.firstName}"/></td>
    <td><c:out value="${user.lastName}"/></td>
    </tr>
    </tbody>
</table>
<h3><fmt:message key="order.make"/> </h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="subscription"/></th>
        <th scope="col"><fmt:message key="date.return"/></th>
        <th scope="col"><fmt:message key="penalty"/></th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.returnDate}"/></td>
            <td style="color:${order.penalty gt 0 ? 'red' : ''}"><c:out value="${order.penalty}"/></td>
        </tr>
    </tbody>
</table>
<h3><fmt:message key="books"/> </h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="title"/></th>
        <th scope="col"><fmt:message key="author"/></th>
        <th scope="col"><fmt:message key="publisher"/></th>
        <th scope="col"><fmt:message key="publish.date"/></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}" varStatus = "loopStatus">
        <tr>
            <td><c:out value="${book.title}"/></td>
            <td><c:out value="${book.author}"/></td>
            <td><c:out value="${book.publisher}"/></td>
            <td><c:out value="${book.publishDate}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/user/cancel_order" method="POST">
    <input type="hidden" name="id" value="${order.id}">
    <c:if test="${order ne null}">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="books.return"/>
        </button>
    </c:if>

</form>



</body>
</html>

