<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="styles/footer.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/styles/fontFamily.css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
</head>
<body>
	<footer>
	    <div class="container">
	        <div class="contatti">
	            <h3>Contattaci</h3>
	            <p>Email: <a href="mailto:hotelcampus@info.it">hotelcampus@info.it</a></p>
	            <p>Telefono: <a href="tel:+39089961111">(+39) 089 961111</a></p>
	            <p>P.IVA: 01234567890</p>
	        </div>
	        <div class="indirizzo">
	            <h3>Indirizzo</h3>
	            <p>Via Giovanni Paolo II, 132</p>
	            <p>84084 Fisciano SA</p>
	        </div>
	    </div>
	    <div class="bottom">
	        <p>&copy; <%=pageContext.getServletContext().getInitParameter("footer.year")%> Hotel Campus. Tutti i diritti riservati.</p>
	    </div>
	</footer>
</body>
</html>