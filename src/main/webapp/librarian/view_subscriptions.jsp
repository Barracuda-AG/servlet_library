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
                    <a class="nav-link" href="${pageContext.request.contextPath}/librarian/cabinet"><fmt:message key="cabinet"/></a>
                </li>
            </ul>
        </nav>
    </div>
</div>

<h3><fmt:message key="order.make"/> </h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="subscription"/></th>
        <th scope="col"><fmt:message key="returned"/></th>
        <th scope="col"><fmt:message key="date.issue"/></th>
        <th scope="col"><fmt:message key="date.return"/></th>
        <th scope="col"><fmt:message key="penalty"/></th>
        <th scope="col"><fmt:message key="books"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}" varStatus = "loopStatus">
    <tr>
        <td><c:out value="${order.id}"/></td>
        <td><c:out value="${order.returned}"/></td>
        <td><c:out value="${order.issueDate}"/></td>
        <td><c:out value="${order.returnDate}"/></td>
        <td><c:out value="${order.penalty}"/></td>
        <td><table>
            <c:forEach var="book" items="${order.books}" varStatus="loopStatus">
                <tr><td><c:out value="${book.title}"/></td>
                </tr>
            </c:forEach>
        </table></td>
    </tr>
    </c:forEach>
    </tbody>
</table>



</body>
</html>

