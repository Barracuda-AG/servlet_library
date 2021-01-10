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
<header>
    <div>
        <nav class="navbar navbar-expand bg-light" >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <form  method="POST" action="/logout">
                        <button class="btn btn-primary" type="submit"><fmt:message key="logout"/></button>
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/cabinet.jsp"><fmt:message key="cabinet"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/view_books"><fmt:message key="books.all"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<div class="container">
    <h2><fmt:message key="books.all"/></h2>
    <div>
        <form action="${pageContext.request.contextPath}/user/make_order" method="POST">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="title"/></th>
                    <th><fmt:message key="author"/></th>
                    <th><fmt:message key="publisher"/></th>
                    <th><fmt:message key="publish.date"/></th>
                    <th><fmt:message key="quantity"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${bookList}">
                <tr>
                    <td><br><c:out value="${book.title}"/></td>
                    <td><br><c:out value="${book.author}"/></td>
                    <td><br><c:out value="${book.publisher}"/></td>
                    <td><br><c:out value="${book.publishDate}"/></td>
                    <td><br><c:out value="${book.quantity}"/></td>
                    <td> <input type="checkbox" name="bookId" value="${book.id}"></td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary" >
                <fmt:message key="to.order"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>

