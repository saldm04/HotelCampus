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
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/styles/fontFamily.css">
</head>
<body>
	<% 
        String edit = request.getParameter("edit");
        if (edit == null) {
            edit = "dati";
        }
    %>
    <%@ include file="/navigationBar.jsp"%>  
    <section class="areaRiservata">
    <div class="menu">
        <ul>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=82785&format=png&color=000000"/><a href="<%=request.getContextPath()%>/common/areaRiservata.jsp?edit=dati">Dati account</a></li>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=4027&format=png&color=000000"/><a href="<%=request.getContextPath()%>/common/areaRiservata.jsp?edit=prenotazioni">Visualizza prenotazioni</a></li>
        </ul>
    </div>
    <div class="editArea">
 		<% if ("dati".equals(edit)) { %>
            <%@ include file="/common/editDatiAccount.jsp" %>
        <% } else if ("prenotazioni".equals(edit)) { %>
            <%@ include file="/common/visualizzaPrenotazioni.jsp" %>
        <% } %>
    </div>
</section>

<%@ include file="../footer.jsp" %>
<script type="text/javascript" src="scripts/areaRiservata.js"></script>
</body>
</html>
