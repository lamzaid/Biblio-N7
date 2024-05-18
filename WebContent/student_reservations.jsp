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
            for (Reservation reservation : reservations) {
                out.println("<tr>");
                out.println("<td>" + reservation.getBook().getTitle() + "</td>");
                out.println("<td>" + reservation.getBook().getPublication_year() + "</td>");
                out.println("<td>" + reservation.getBook().getAuthor().getName() + "</td>");
                out.println("<td>" + reservation.getReservedAt() + "</td>");
                out.println("<td>" + (reservation.isValidated() ? "Reservation Validated" : "Pending") + "</td>");
                out.println("<td><form action='StudentServlet' method='post'>");
                out.println("<input type='hidden' name='op' value='cancel'>");
                out.println("<input type='hidden' name='studentId' value='" + reservation.getStudent().getId() + "'>");
                out.println("<input type='hidden' name='reservationId' value='" + reservation.getId() + "'>");
                out.println("<button type='submit'>Cancel</button>");
                out.println("</form></td>");
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>
