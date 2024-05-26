<%@ page import="java.util.List" %>
<%@ page import="fr.enseeiht.biblio.entity.Book" %>
<%@ page import="fr.enseeiht.biblio.entity.Exemplaire" %>
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
                <th>Available Copies</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                if (books != null) {
                    for (Book book : books) {
                        long availableCopies = book.getExemplaires().stream().filter(Exemplaire::getDisponible).count();
                        if (availableCopies > 0) {
            %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getPublication_year() %></td>
                    <td><%= book.getAuthor().getName() %></td>
                    <td><%= availableCopies %></td>
                    <td>
                        <form action="StudentServlet" method="post">
                            <input type="hidden" name="op" value="reserve">
                            <input type="hidden" name="bookId" value="<%= book.getId() %>">
                            <button type="submit">Reserve</button>
                        </form>
                    </td>
                </tr>
            <%
                        }
                    }
                } else {
            %>
                <tr>
                    <td colspan="5">No books available</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
