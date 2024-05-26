<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.enseeiht.biblio.entity.Author" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
</head>
<body>
    <h2>Add a New Book</h2>
    <form action="BookServlet" method="post">
        <input type="hidden" name="op" value="add">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>
        <label for="year">Publication Year:</label>
        <input type="number" id="year" name="publication_year" required><br><br>
        <label for="author">Author:</label>
        <select id="author" name="author_id">
            <% 
                List<Author> authors = (List<Author>) request.getAttribute("authors");
                for (Author author : authors) {
                    out.println("<option value=\"" + author.getId() + "\">" + author.getName() + "</option>");
                }
            %>
        </select><br><br>
        <label for="copies">Number of Copies:</label>
        <input type="number" id="copies" name="copies" required><br><br>
        <button type="submit">Add Book</button>
    </form>
</body>
</html>
