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
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                if (books != null) {
                    for (Book book : books) {
                        boolean hasReservedExemplaire = false;
                        for (Exemplaire exemplaire : book.getExemplaires()) {
                            if (!exemplaire.getDisponible()) {
                                hasReservedExemplaire = true;
                                break;
                            }
                        }
            %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getPublication_year() %></td>
                    <td><%= book.getAuthor().getName() %></td>
                    <td>
                        <form action="./BookServlet" method="get" style="display:inline;">
                            <input type="hidden" name="bookId" value="<%= book.getId() %>">
                            <button type="submit" name="op" value="view_exemplaires">View Exemplaires</button>
                        </form>
                        <form action="./BookServlet" method="get" style="display:inline;">
                            <input type="hidden" name="bookId" value="<%= book.getId() %>">
                            <button type="submit" name="op" value="delete" <% if (hasReservedExemplaire) { %>disabled<% } %>>Delete</button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">No books available</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
