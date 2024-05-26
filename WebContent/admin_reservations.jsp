<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.enseeiht.biblio.entity.Reservation" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Reservations</title>
</head>
<body>
    <h2>All Reservations</h2>
    <table>
        <tr>
            <th>Title</th>
            <th>Publication Year</th>
            <th>Author</th>
            <th>Reserved At</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
            if (reservations != null && !reservations.isEmpty()) {
                for (Reservation reservation : reservations) {
        %>
        <tr>
            <td><%= reservation.getExemplaire().getBook().getTitle() %></td>
            <td><%= reservation.getExemplaire().getBook().getPublication_year() %></td>
            <td><%= reservation.getExemplaire().getBook().getAuthor().getName() %></td>
            <td><%= reservation.getReservedAt() %></td>
            <td><%= reservation.isValidated() ? "Validated" : "Pending" %></td>
            <td>
                <form action="AdminServlet" method="post">
                    <input type="hidden" name="op" value="validate">
                    <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                    <button type="submit" <%= reservation.isValidated() ? "disabled" : "" %>>Validate</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6">No reservations found</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
