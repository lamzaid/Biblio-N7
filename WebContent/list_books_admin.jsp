<%@ page import="java.util.List" %>
<%@ page import="fr.enseeiht.biblio.entity.Book" %>
<%@ page import="fr.enseeiht.biblio.entity.Reservation" %>
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
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (books != null && reservations != null) {
                    for (Book book : books) {
                        boolean hasReservation = false;
                        boolean isTaken = false;
                        Reservation currentReservation = null;
                        for (Reservation reservation : reservations) {
                            if (reservation.getBook().getId() == book.getId()) {
                                hasReservation = true;
                                currentReservation = reservation;
                                if (reservation.isValidated()) {
                                    isTaken = true;
                                }
                                break;
                            }
                        }
            %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getPublication_year() %></td>
                    <td><%= book.getAuthor().getName() %></td>
                    <td><%= isTaken ? "Taken by student" : hasReservation ? "Reserved" : "Available" %></td>
                    <td>
                        <form action="./BookServlet" method="get">
                            <input type="hidden" name="bookId" value="<%= book.getId() %>">
                            <input type="submit" name="op" value="delete" <%= hasReservation ? "disabled" : "" %> >
                        </form>
                        <% if (hasReservation) { %>
                        <form action="./BookServlet" method="get">
                            <input type="hidden" name="reservationId" value="<%= currentReservation.getId() %>">
                            <input type="submit" name="op" value="return">
                        </form>
                        <% } %>
                    </td>
                </tr>
            <%
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
