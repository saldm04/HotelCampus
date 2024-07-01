<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Hotel Campus - Error</title>
<base href="<%=request.getContextPath()%>/" />
<link rel="stylesheet" href="styles/camera.css" type="text/css">
<link rel="stylesheet" href="styles/errorPage.css" type="text/css">
<meta charset="UTF-8">
</head>
<body>
    <%@ include file="navigationBar.jsp"%>

    <div class="error-container">
        <div class="error-code">
            Ops qualcosa è andato storto: Errore <%= response.getStatus() %>
        </div>
        <div class="error-message">
            Se l'errore persiste, contattare l'admin tramite l'email <a href="mailto:hotelcampus@admin.com">hotelcampus@admin.com</a>.
        </div>
    </div>

    <%@ include file="footer.jsp"%>
</body>
</html>