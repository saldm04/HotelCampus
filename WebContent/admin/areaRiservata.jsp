<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=request.getContextPath()%>/" />
    <meta charset="UTF-8">
    <title>Hotel Campus</title>
    <meta name="viewport" content="initial-scale=1, width=device-width">
    <link rel="stylesheet" href="styles/areaRiservata.css">
</head>
<body>
    <% 
        String edit = request.getParameter("edit");
        if (edit == null) {
            edit = "camere";
        }
    %>
    <%@ include file="/navigationBar.jsp"%>  
    <section class="areaRiservata">
    <div class="menu">
        <ul>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=qvzfPde1fHtV&format=png&color=0000000"/><a href="<%=request.getContextPath()%>/admin/areaRiservata.jsp?edit=camere">Modifica Camere</a></li>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=113629&format=png&color=0000000"/><a href="<%=request.getContextPath()%>/admin/areaRiservata.jsp?edit=servizi">Modifica Servizi</a></li>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=vfFvmPdD49B8&format=png&color=0000000"/><a href="<%=request.getContextPath()%>/admin/areaRiservata.jsp?edit=prenotazioni">Gestione Prenotazioni</a></li>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=cykh8BZMTKkb&format=png&color=0000000"/><a href="<%=request.getContextPath()%>/admin/areaRiservata.jsp?edit=clienti">Visualizza Utenti</a></li>
        </ul>
    </div>
    <div class="editArea">
        <% if ("camere".equals(edit)) { %>
            <%@ include file="/admin/editCamere.jsp" %>
        <% } else if ("servizi".equals(edit)) { %>
            <%@ include file="/admin/editServizi.jsp" %>
        <% } else if ("prenotazioni".equals(edit)) { %>
            <%@ include file="/admin/gestionePrenotazioni.jsp" %>
        <% } else if ("clienti".equals(edit)) { %>
            <%@ include file="/admin/visualizzaUtenti.jsp" %>
        <% } %>
    </div>
</section>

<%@ include file="../footer.jsp" %>
<script type="text/javascript" src="scripts/areaRiservataAdmin.js"></script>
</body>
</html>
