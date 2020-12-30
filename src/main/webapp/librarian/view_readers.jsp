<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Librarian Cabinet</title>
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
                    <form  method="POST" action="/logout">
                        <button class="btn btn-primary" type="submit"><fmt:message key="logout"/></button>
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/librarian/cabinet"><fmt:message key="cabinet"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/librarian/view_books"><fmt:message key="books.all"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/librarian/view_subscriptions"><fmt:message key="subscriptions"/></a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<div class="container">
<h2><fmt:message key="readers"/></h2>
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="subscription"/></th>
            <th><fmt:message key="books"/></th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}" varStatus = "loopStatus">
        <tr>
            <td><br><c:out value="${user.firstName}"/></td>
            <td><br><c:out value="${user.lastName}"/></td>
            <td><br><c:out value="${user.email}"/></td>
            <td><br><c:out value="${user.order.id}" /></td>
            <td><table>
                <c:forEach var="book" items="${user.order.books}" varStatus="loopStatus">
                    <tr><td><c:out value="${book.title}"/></td>
                    </tr>
                </c:forEach>
            </table></td>

            <td><form action="${pageContext.request.contextPath}/librarian/cancel_order" method="POST">
                <input type="hidden" name="orderId" value="${user.order.id}">
                <button type="submit" class="btn btn-danger">
                    <fmt:message key="cancel.subscription"/>
                </button>
            </form>
            </td>
            </c:forEach>

        </tbody>
    </table>

</div>
</div>
</body>
</html>

