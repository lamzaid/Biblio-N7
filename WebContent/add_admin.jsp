<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Admin</title>
</head>
<body>
	<h2>Add New Admin</h2>
	<form action="AddAdminServlet" method="post">
		First Name: <input type="text" name="firstName"><br>
		Last Name: <input type="text" name="lastName"><br>
		Email: <input type="email" name="email"><br>
		Password: <input type="password" name="password"><br>
        <input type="submit" value="Submit">
	</form>
</body>
</html>