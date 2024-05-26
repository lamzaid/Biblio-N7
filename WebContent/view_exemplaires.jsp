<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.enseeiht.biblio.entity.Exemplaire" %>
<%@ page import="fr.enseeiht.biblio.entity.Book" %>
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
        </tr>
        <%
            List<Exemplaire> exemplaires = (List<Exemplaire>) request.getAttribute("exemplaires");
            if (exemplaires != null) {
                for (Exemplaire exemplaire : exemplaires) {
        %>
        <tr>
            <td><%= exemplaire.getId() %></td>
            <td><%= exemplaire.getDisponible() ? "Disponible" : "Réservé" %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="2">No exemplaires available</td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="./BookServlet?op=list">Back to book list</a>
</body>
</html>
