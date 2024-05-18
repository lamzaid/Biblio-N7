<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Set Student ID</title>
</head>
<body>
    <form action="StudentServlet" method="post">
        <label for="studentId">Enter your Student ID:</label>
        <input type="text" id="studentId" name="studentId" required>
        <button type="submit">Submit</button>
        <input type="hidden" name="op" value="set_student_id">
    </form>
</body>
</html>
