<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.enseeiht.biblio.entity.Reservation" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Reservations</title>
</head>
<body>
    <h2>My Reservations</h2>
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
            if (reservations != null) {
                for (Reservation reservation : reservations) {
                    out.println("<tr>");
                    out.println("<td>" + reservation.getExemplaire().getBook().getTitle() + "</td>");
                    out.println("<td>" + reservation.getExemplaire().getBook().getPublication_year() + "</td>");
                    out.println("<td>" + reservation.getExemplaire().getBook().getAuthor().getName() + "</td>");
                    out.println("<td>" + reservation.getReservedAt() + "</td>");
                    out.println("<td>" + reservation.getStatus() + "</td>");
                    out.println("<td><form action='StudentServlet' method='post'>");
                    out.println("<input type='hidden' name='op' value='cancel'>");
                    out.println("<input type='hidden' name='reservationId' value='" + reservation.getId() + "'>");
                    out.println("<button type='submit'>Cancel</button>");
                    out.println("</form></td>");
                    out.println("</tr>");
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
