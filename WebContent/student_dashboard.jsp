<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
</head>
<body>
    <h2>Student Dashboard</h2>
    <ul>
        <c:if test="${not empty sessionScope.studentId}">
            <li><a href="StudentServlet?op=list_books">List Books</a></li>
            <li><a href="StudentServlet?op=reservations">My Reservations</a></li>
        </c:if>
    </ul>
</body>
</html>
