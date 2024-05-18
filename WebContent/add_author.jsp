<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Author</title>
</head>
<body>
    <h2>Add a New Author</h2>
    <form action="AuthorServlet" method="post">
        <input type="hidden" name="op" value="add">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <button type="submit">Add Author</button>
    </form>
</body>
</html>
