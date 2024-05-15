<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
    .error {
        color: red;
    }
</style>
</head>
<body>
    <% if ("true".equals(request.getAttribute("error"))) { %>
        <p class="error">Invalid e-mail or password.</p>
    <% } %>
    <form action="LoginServlet" method="post">
	    Mail: <input type="text" name="email" required><br>
	    Password: <input type="password" name="password" required><br>
	    <input type="submit" value="Login">
    </form>
</body>
</html>
