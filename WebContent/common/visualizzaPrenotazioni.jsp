<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Prenotazione, java.util.List, java.util.Map, model.CameraPrenotata, model.ServizioPrenotato"%>
    <%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" href="styles/areaRiservataPrenotazioni.css" type="text/css">
</head>
<body>
	<%
		Collection<Prenotazione> prenotazioni = (Collection<Prenotazione>) request.getAttribute("prenotazioni");
    	Map<Integer, Collection<CameraPrenotata>> camereMap = (Map<Integer, Collection<CameraPrenotata>>) request.getAttribute("camereMap");
    	Map<Integer, Collection<ServizioPrenotato>> serviziMap = (Map<Integer, Collection<ServizioPrenotato>>) request.getAttribute("serviziMap");
		
    	List<String> errors = (List<String>) request.getSession().getAttribute("problemDetectd");
        
	%> 
	
	<section class="prenotazioni">
	
	 	<div class="filtro"> 
	 		<form action="<%=request.getContextPath()%>/common/Prenotazioni" method="post" onsubmit="return validateForm()">
    			<div>
        		<label for="checkindate">Check in: <input type="date" id="checkindate" name="checkindate" oninput="checkSelectedDate(this)" value=""/></label>
        		<label for="checkoutdate">Check out: <input type="date" id="checkoutdate" name="checkoutdate" oninput="checkSelectedDate(this)" value=""/></label>
    			</div>
    			<input type="submit" value="Filtra">
			</form>
	 	</div>
	 	
	 	<% if(errors != null){  %>
      	<div class="warning">
      	 	<img src="images/warning.png" alt="" />
      		<span>
      			<h4>Attenzione</h4>
      			<p>
     				<% for (String error: errors){ %>
     			    <%=error %> <br>		
					<% }%>
				</p>
      		</span>
      	</div>
    <% request.getSession().removeAttribute("problemDetectd");} %>
	
	

	
	<div class="visualizzaPrenotazioni">
		
			<%if(prenotazioni != null && !prenotazioni.isEmpty()){
				Iterator<?> it = prenotazioni.iterator(); %>
				<div class="cardBox">
				<%
				while (it.hasNext()) {
					Prenotazione bean = (Prenotazione) it.next();	
			%>
			
				<div class="card">
        			<ul>
        				<li class="align1"><span><b>Data prenotazione:</b> <%=bean.getDataPrenotazione()%></span><span><b>Utente:</b> <%=bean.getUtente()%></span></li>
        				<li class="align1"><span><b>Inizio pernottamento:</b> <%=bean.getDataInizio() %></span> <span><b>Fine pernottamento:</b> <%= bean.getDataFine() %></span></li>
        				<li class="align1"><span><b>Importo totale speso:</b> <%=bean.getImporto()%></span></li>
        				<li class="align1"><b>Camere:</b>
                            <ul>
                                <%
                                    Collection<CameraPrenotata> camere = camereMap.get(bean.getId());
                                    if (camere != null) {
                                        for (CameraPrenotata camera : camere) {
                                %>
                                            <li>Numero Camera: <%= camera.getCamera() %>, Costo: <%= camera.getCosto() %></li>
                                <%
                                        }
                                    }
                                %>
                            </ul>
                        </li>
        				<li class="align1"><b>Servizi:</b>
                            <ul>
                                <%
                                    Collection<ServizioPrenotato> servizi = serviziMap.get(bean.getId());
                                    if (servizi != null && !servizi.isEmpty()) {
                                        for (ServizioPrenotato servizio : servizi) {
                                %>
                                            <li>Servizio: <%=servizio.getServizio() %>, Costo: <%=servizio.getCosto() %></li>
                                <%
                                        }
                                    }else{%>
                                    	<li>Nessun servizio acquistato</li>
                                    <%}%>
                            </ul>
                        </li>
        			</ul>	
        		</div>
			
			<%  } %>
			  </div>
			<%}else{%>
				<span class="information">Per iniziare a visualizzare le prenotazioni attiva almeno un filtro</span>
			<%}%>
	</div>
    </section>
</body>
</html>