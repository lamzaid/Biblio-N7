<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.enseeiht.biblio.entity.Exemplaire" %>
<%@ page import="fr.enseeiht.biblio.entity.Book" %>
<%@ page import="fr.enseeiht.biblio.entity.Reservation" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Exemplaires</title>
</head>
<body>
    <h2>Exemplaires of <%= ((Book) request.getAttribute("book")).getTitle() %></h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Disponibilité</th>
            <th>Réservé par</th>
            <th>Action</th>
        </tr>
        <%
            List<Exemplaire> exemplaires = (List<Exemplaire>) request.getAttribute("exemplaires");
            if (exemplaires != null) {
                for (Exemplaire exemplaire : exemplaires) {
                    Reservation reservation = exemplaire.getCurrentReservation();
        %>
        <tr>
            <td><%= exemplaire.getId() %></td>
            <td><%= exemplaire.getDisponible() ? "Disponible" : (reservation.isValidated() ? "Validée" : "Réservé") %></td>
            <td><%= reservation != null && !exemplaire.getDisponible() ? reservation.getStudent().getFirstName() + " " + reservation.getStudent().getLastName() : "N/A" %></td>
            <td>
                <form action="AdminServlet" method="post">
                    <input type="hidden" name="op" value="return">
                    <input type="hidden" name="exemplaireId" value="<%= exemplaire.getId() %>">
                    <button type="submit" <%= (reservation == null || !reservation.isValidated()) ? "disabled" : "" %>>Return</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">No exemplaires available</td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="./BookServlet?op=list">Back to book list</a>
</body>
</html>
