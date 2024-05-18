<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <c:if test="${not empty error}">
            <p class="error">Invalid email or password.</p>
        </c:if>
        <form action="LoginServlet" method="post" class="form-style">
            <div class="form-group">
                <label for="email">Mail:</label>
                <input type="text" name="email" id="email" required placeholder="Enter your email">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required placeholder="Enter your password">
            </div>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
