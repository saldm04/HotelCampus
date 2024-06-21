<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat, model.Camera, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="styles/prenotazione.css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
	<script src="scripts/checkDatePrenotazione.js"></script>
	<title>Prenota ora</title>
</head>
<body>
	<% request.setAttribute("in", "prenotaOra"); %>
	<%@ include file="navigationBar.jsp"%>
	
	<% 
		// Ottieni la data corrente
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
        
        List<Camera> camere = (List<Camera>) request.getAttribute("camereDisponibili");
        Boolean ricercaEffettuata = (Boolean) request.getAttribute("ricercaEffettuata");
        String maxAttributeValueOspiti = (String) request.getAttribute("maxAttributeValueOspiti");
        if(camere==null || maxAttributeValueOspiti==null || ricercaEffettuata==null){
        	response.sendRedirect("CamereDisponibiliPrenotazione");
			return;
        }
	%>
	
	<div class="formContainer">
		<form method="post" id="dateForm" action="CamereDisponibiliPrenotazione">
			<%   
	        	ArrayList<Camera> camereCarrello = (ArrayList<Camera>) request.getSession().getAttribute("CarrelloCamere");
	        	if(camereCarrello!=null && !camereCarrello.isEmpty()){
				%>
					<label for="checkindate">Check in: <input type="date" value="<%=request.getSession().getAttribute("dataInizioPrenotazione")%>" id="checkindate"  min=<%=todayStr%> oninput="checkSelectedDate(this)" required disabled/></label>
					<label for="checkoutdate">Check out: <input type="date" value="<%=request.getSession().getAttribute("dataFinePrenotazione")%>" id="checkoutdate" required disabled/></label>
					<input type="hidden" name="checkindate" value="<%=request.getSession().getAttribute("dataInizioPrenotazione")%>">
					<input type="hidden" name="checkoutdate" value="<%=request.getSession().getAttribute("dataFinePrenotazione")%>">
				<%
				}else{
				%>
					<label for="checkindate">Check in: <input type="date" id="checkindate" name="checkindate" min=<%=todayStr%> oninput="checkSelectedDate(this)" required/></label>
					<label for="checkoutdate">Check out: <input type="date" id="checkoutdate" name="checkoutdate" required disabled/></label>
				<%} %>
			<label for="numOspiti">Numero di ospiti: <input type="number" name="numOspiti" min="1" max=<%=maxAttributeValueOspiti%> required/></label>
			<input type="submit" value="Verifica disponibilità">
		</form>
	</div>
		<%
			if(camere!=null && !camere.isEmpty()){
				%>
					<div class="camereMainContainter">
				<%
				Iterator<Camera> it = camere.iterator();
				while(it.hasNext()){
					Camera camera = it.next();
		%>
			<div class="cameraContainer">
				<div class="immagini">
					<img alt="Immagine camera 1" src="./GetPicture?beanType=camera&id=<%=camera.getNumero()%>&numberImg=1">
				</div>
				<div class="info">
					<h1>Tipologia camera:</h1><span><%=camera.getTipo()%></span>
					<h1>Quadratura:</h1><span><%=camera.getQuadratura()%> mq</span>
					<h1>Numero massimo di ospiti:</h1><span><%=camera.getNumeroMaxOspiti()%></span>
				</div>
				<div class="costo">
					<h1>N°:</h1><span><%=camera.getNumero()%></span>
					<h1>Costo per notte:</h1><span><%=camera.getCosto()%></span>
					<%if(ricercaEffettuata){%>
						<form method="post" action="GestisciCarrello" id="addCartForm">
							<input type="hidden" name="numeroCamera" value="<%=camera.getNumero()%>"/>
							<input type="submit" value="Aggiungi al carrello"/>
						</form>
					<%}%>
				</div>
			</div>
		
		<%
				}
		%>
	</div>
	<%
		}else if(camere.isEmpty()){
			%>	<div class="avvisoCamereContainer">
					<h1 class="avvisoCamere">La vostra ricerca non ha prodotto risultati.</h1>
					<h1 class="avvisoCamere">Non sono disponibili camere che soddisfano i vostri criteri di ricerca.</h1>
				</div>
			<%
		}
	%>
	
	<%@ include file="footer.jsp"%>
</body>
</html>