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
    <%@ include file="/navigationBar.jsp"%>  
    <section class="areaRiservata">
    <div class="menu">
        <ul>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=qvzfPde1fHtV&format=png&color=0000000"/><a href="<%=request.getContextPath()%>/admin/areaRiservata.jsp?edit=camere">Modifica Camere</a></li>
            <li><img alt="" src="https://img.icons8.com/?size=100&id=113629&format=png&color=0000000"/><a href="<%=request.getContextPath()%>/admin/areaRiservata.jsp?edit=servizi">Modifica Servizi</a></li>
        </ul>
    </div>
    <div class="editArea">
 		
    </div>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>
