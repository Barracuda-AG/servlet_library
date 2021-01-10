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
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/show_order"><fmt:message key="order.show"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<br/>
<div class="container">

    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/user/find" method="POST">
        <input class="form-control mr-sm-2" type="search" name="text" placeholder="<fmt:message key="title.author"/>" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="search"/></button>
    </form>
</div>
<br/>

<div class="container">
<h2><fmt:message key="books.all"/></h2>
<div>
    <form action="${pageContext.request.contextPath}/user/make_order" method="POST">
        <table class="table table-striped">
        <thead>
        <tr>
            <th><a href="/user/view_books?page=${currentPage}&sort=title&sortDir=${sortDir eq 'asc' ? 'desc' : 'asc'}"><fmt:message key="title"/></a></th>
            <th><a href="/user/view_books?page=${currentPage}&sort=author&sortDir=${sortDir eq 'asc' ? 'desc' : 'asc'}"><fmt:message key="author"/></a></th>
            <th><a href="/user/view_books?page=${currentPage}&sort=publisher&sortDir=${sortDir eq 'asc' ? 'desc' : 'asc'}"><fmt:message key="publisher"/></a></th>
            <th><a href="/user/view_books?page=${currentPage}&sort=date&sortDir=${sortDir eq 'asc' ? 'desc' : 'asc'}"><fmt:message key="publish.date"/></a></th>
            <th><fmt:message key="quantity"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
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
        <c:if test="${currentPage != 1}">
            <td><a href="/user/view_books?page=${currentPage - 1}&sort=${sort}&sortDir=${sortDir}"><fmt:message key="previous"/></a></td>
        </c:if>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="view_books?page=${i}&sort=${sort}&sortDir=${sortDir}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="/user/view_books?page=${currentPage + 1}&sort=${sort}&sortDir=${sortDir}"><fmt:message key="next"/></a></td>
        </c:if>
    <br/>
        <br/>
        <button type="submit" class="btn btn-primary" >
            <fmt:message key="to.order"/>
        </button>
    </form>
</div>
</div>
</body>
</html>

