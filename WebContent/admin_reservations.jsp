<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Reservations</title>
</head>
<body>
    <h2>Admin Reservations</h2>
    <table>
        <tr>
            <th>Title</th>
            <th>Publication Year</th>
            <th>Author</th>
            <th>Student</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach var="reservation" items="${reservations}">
            <tr>
                <td>${reservation.book.title}</td>
                <td>${reservation.book.publication_year}</td>
                <td>${reservation.book.author.name}</td>
                <td>${reservation.student.email}</td>
                <td><c:choose>
                        <c:when test="${reservation.validated}">Validated</c:when>
                        <c:otherwise>Pending</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${!reservation.validated}">
                        <form action="AdminServlet" method="post">
                            <input type="hidden" name="op" value="validate">
                            <input type="hidden" name="reservationId" value="${reservation.id}">
                            <button type="submit">Validate</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
