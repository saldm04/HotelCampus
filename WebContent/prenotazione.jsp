<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat, model.Camera, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="styles/prenotazione.css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
	<script src="scripts/checkDatePrenotazione.js"></script>
	<title>Prenota ora</title>
</head>
<body>
	<%@ include file="navigationBar.jsp"%>
	
	<% 
		// Ottieni la data corrente
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        
        List<Camera> camere = (List<Camera>) request.getAttribute("camereDisponibili");
        if(camere==null){
        	response.sendRedirect("CamereDisponibiliPrenotazione");
			return;
        }        
	%>
	
	<div class="formContainer">
		<form method="post" id="dateForm" action="">
			<label for="checkindate">Check in: <input type="date" id="checkindate" name="checkindate" min=<%=todayStr%> oninput="checkSelectedDate(this)" required/></label>
			<label for="checkoutdate">Check out: <input type="date" id="checkoutdate" name="checkoutdate" required disabled/></label>
			<label for="numOspiti">Numero di ospiti: <input type="number" min="1" max="4" required/></label>
			<input type="submit" value="Verifica disponibilità">
		</form>
	</div>
	
	<%
		if(camere!=null && camere.size()>0){
			Iterator<Camera> it = camere.iterator();
			while(it.hasNext()){
				Camera camera = it.next();
	%>
	
	<div class="cameraContainer">
		<div class="immagini">
			<img alt="Immagine camera 1" src="./GetPicture?beanType=camera&id=<%=camera.getNumero()%>&numberImg=1">
		</div>
		<div class="info">
			<h1>Tipologia camera: </h1><span><%=camera.getTipo()%></span>
			<h1>Quadratura: </h1><span><%=camera.getQuadratura()%></span>
			<h1>Numero massimo di ospiti: </h1><span><%=camera.getNumeroMaxOspiti()%></span>
		</div>
		<div class="costo">
			<h1>Costo per notte: </h1><span><%=camera.getCosto()%> €</span>
		</div>
	</div>
	
	<%
			}
		}
	%>
	<%@ include file="footer.jsp"%>
</body>
</html>