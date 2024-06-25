<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Hotel Campus - Error</title>
<base href="<%=request.getContextPath()%>/" />
<link rel="stylesheet" href="styles/camera.css" type="text/css">
<meta charset="UTF-8">
<style>
    .error-container {
        text-align: center;
        width: 100vw;
        height:100vh;
        display:flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    .error-code {
    	width: 90%;
        font-size: clamp(15px, 3vw, 200px);
        color: red;
    }
    .error-message {
    	width: 90%;
        font-size: clamp(14px, 2.5vw, 200px);
        margin-top: 20px;
    }
</style>
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