<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Books</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Available Books</h2>
    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Publication Year</th>
            <th>Action</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author.name}</td>
                <td>${book.publication_year}</td>
                <td>
                    <form action="StudentServlet" method="post">
                        <input type="hidden" name="op" value="reserve">
                        <input type="hidden" name="bookId" value="${book.id}">
                        <button type="submit">Reserve</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
