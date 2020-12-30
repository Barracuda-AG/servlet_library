<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Admin Cabinet</title>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/cabinet"><fmt:message key="cabinet"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/addbook"><fmt:message key="book.add"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/view_books"><fmt:message key="books.all"/></a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<div class="container">
<h2><fmt:message key="user.all"/></h2>
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="role"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="subscription"/></th>
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
            <td><br><c:out value="${user.role}"/></td>
                <td style="color:${user.accountNonLocked eq 'true' ? 'green' : 'red'}">
                    <c:if test="${user.accountNonLocked eq 'true'}">
                    <fmt:message key="unlocked"/>
                </c:if>
                    <c:if test="${user.accountNonLocked eq 'false'}">
                        <fmt:message key="locked"/>
                    </c:if>
                </td>
                <td><br>

                <c:out value="${empty user.order ? '' : user.order.id}" />
            </td>
                <td>
                    <form  action="${pageContext.request.contextPath}/admin/edit_user" method="POST">
                        <input type="hidden" name="id" value="${user.id}" />
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="edit"/>
                        </button>
                    </form>
                </td>
                <td><form action="${pageContext.request.contextPath}/admin/delete_user" method="POST">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" class="btn btn-danger">
                        <fmt:message key="delete"/>
                    </button>

                </form>
                </td>
        </c:forEach>
        </tr>
        </tbody>
    </table>

</div>
</div>
</body>
</html>

