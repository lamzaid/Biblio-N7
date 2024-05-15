<%@ page import="java.util.List" %>
<%@ page import="fr.enseeiht.biblio.entity.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Books</title>
</head>
<body>
    <h1>List of Books</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Title</th>
                <th>Publication Year</th>
                <th>Author</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                if (books != null) {
                    for (Book book : books) {
            %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getPublication_year() %></td>
                    <td><%= book.getAuthor().getName() %></td>
                    <td>
                        <form action="./BookServlet" method="get">
                            <input type="hidden" name="bookId" value="<%= book.getId() %>">
                            <input type="submit" name="op" value="delete">
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="3">No books available</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
