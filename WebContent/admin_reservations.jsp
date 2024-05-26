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
            <th>Reserved By</th>
            <th>Action</th>
        </tr>
        <%
            List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
            if (reservations != null && !reservations.isEmpty()) {
                for (Reservation reservation : reservations) {
                    // Ne pas afficher les réservations dont l'exemplaire est disponible (c'est-à-dire retourné)
                    if (!reservation.getExemplaire().getDisponible()) {
        %>
        <tr>
            <td><%= reservation.getExemplaire().getBook().getTitle() %></td>
            <td><%= reservation.getExemplaire().getBook().getPublication_year() %></td>
            <td><%= reservation.getExemplaire().getBook().getAuthor().getName() %></td>
            <td><%= reservation.getReservedAt() %></td>
            <td><%= reservation.isValidated() ? "Validated" : "Pending" %></td>
            <td><%= reservation.getStudent().getFirstName() + " " + reservation.getStudent().getLastName() %></td>
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
                }
            } else {
        %>
        <tr>
            <td colspan="7">No reservations found</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
