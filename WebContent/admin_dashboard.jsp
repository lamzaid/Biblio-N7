<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
    <h2>Admin Dashboard</h2>
    <ul>
        <li><a href="BookServlet?op=list">List Books</a></li>
        <li><a href="BookServlet?op=add">Add Book</a></li>
        <li><a href="AuthorServlet?op=add">Add Author</a></li>
        <li><a href="ReservationServlet?op=list">View Reservations</a></li>
    </ul>
</body>
</html>
