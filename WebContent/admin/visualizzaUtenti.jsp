<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Utente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <base href="<%=request.getContextPath()%>/" />
 <link rel="stylesheet" href="styles/visualizzaUtenti.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<%
    Collection<Utente> utenti = (Collection<Utente>) request.getAttribute("utenti");
    if (utenti == null) {
        response.sendRedirect(request.getContextPath()+"/admin/Utenti");
        return;
    }
%>

<section class="vUtenti">

<table>
   <thead>
    <tr>
        <th class="sortable">Email</th>
        <th class="sortable">Nome</th>
        <th class="sortable">Cognome</th>
        <th>Nazionalità</th>
        <th>Data di Nascita</th>
        <th>Admin</th>
    </tr>
</thead>
    <tbody>
        <%
            for (Utente utente : utenti) {
        %>
            <tr>
                <td><%= utente.getEmail() %></td>
                <td><%= utente.getNome() %></td>
                <td><%= utente.getCognome() %></td>
                <td><%= utente.getNazionalità()%></td>
                <td><%= utente.getDataNascita().toString() %>
                <td>
                   <form action="<%=request.getContextPath()%>/admin/Utenti" method="post">
                            <input type="hidden" name="action" value="updateAdminStatus">
                            <input type="hidden" name="email" value="<%= utente.getEmail()%>">
                            <input type="checkbox" name="isAdmin" value="true" <% if (utente.isAdmin()) { %>checked<% } %> onchange="this.form.submit()">
                   </form>
                </td>
                <td>
                   <form action="<%=request.getContextPath()%>/admin/Utenti" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="email" value="<%= utente.getEmail()%>">
                            <input class="delete" type="button" onclick = "this.form.submit()">
                   </form>
                </td>
            </tr>
        <%
            }
        %>
    </tbody>
</table>

</section>

</body>
</html>
