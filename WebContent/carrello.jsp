<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList, model.Camera, model.Servizio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/styles/carrello.css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
	<script src="scripts/carrelloNumeroServizi.js"></script>
	<title>Carrello</title>
</head>
<body>
	<%@ include file="navigationBar.jsp"%>
		<%
			HttpSession sessione = request.getSession();
			ArrayList<Camera> camere = (ArrayList<Camera>) sessione.getAttribute("CarrelloCamere");
			ArrayList<Servizio> servizi = (ArrayList<Servizio>) session.getAttribute("Servizi");
			Integer numeroMassimoTotaleOspiti = 0;
			Integer totaleCamere = 0;
			Integer totaleServizi = 0;
			
			if(camere==null || camere.isEmpty()){
				if(servizi!=null){
					for(Servizio servizio : servizi){
						sessione.setAttribute("att"+servizio.getNome().replaceAll("\\s+", ""), "0");
					}
				}
				%>
				<div class="carrelloVuoto">
					<h1>Il carrello è vuoto</h1>
					<p>Seleziona una o più camere nelle date che preferisci, 
						successivamente potrai scegliere se voler acquistare i
						servizi da noi offerti, per migliorare la tua esperienza.
					</p>
				</div>
				<div class="errorCarrello">
				<%if(request.getAttribute("erroreAcquisto")!=null){%>
						<h1>La prenotazione non è andata a buon fine</h1>
						<p><%=request.getAttribute("erroreAcquisto")%></p>
						<%request.removeAttribute("erroreAcquisto");%>
				<%}%>
				</div>
			<%
			}else if(!camere.isEmpty()){
			%>
				<form method="post" action="<%=request.getContextPath()%>/common/Acquista" id="formAcquista">
				<div id="containerTabella">
					<p>Periodo di soggiorno: da 
					<%=sessione.getAttribute("dataInizioPrenotazione")%> a 
					<%=sessione.getAttribute("dataFinePrenotazione")%>
					</p>
					<table id="tabellaCamere">
						<tr class="rigaIntestazione">
							<th>Numero di camera</th>
							<th>Numero massimo di ospiti</th>
							<th>Quadratura</th>
							<th>Tipologia</th>
							<th>Costo per notte</th>
							<th>Rimuovi dal carrello</th>
						</tr>
						<% 
						for(Camera camera : camere){
							numeroMassimoTotaleOspiti += camera.getNumeroMaxOspiti();
							totaleCamere += camera.getCosto();
						%>
						<tr>
							<td><%=camera.getNumero()%></td>
							<td><%=camera.getNumeroMaxOspiti()%></td>	
							<td><%=camera.getQuadratura()%> mq</td>	
							<td><%=camera.getTipo()%></td>	
							<td><%=camera.getCosto()%> €</td>
							<td><div class="containerImgTabella">
								<a href="GestisciCarrello?camera=<%=camera.getNumero()%>">
								<img alt="Rimuovi dal carrello" src="images/simboloelimina.png"/>
								</a>
							</div></td>	
						</tr>
						<%
						}
						%>
					</table>
			<%
			}if(servizi!=null && !servizi.isEmpty()){
				sessione.setAttribute("numeroMassimoTotaleOspiti", numeroMassimoTotaleOspiti);
			%>		
					<p>Prenotazione servizi</p>
					<table id="tabellaServizi">
						<tr class="rigaIntestazione">
							<th>Nome</th>
							<th>Costo a persona</th>
							<th>Numero di persone che usufruiscono del servizio</th>
						</tr>
						<% 
						for(Servizio servizio : servizi){
							String valueInSessione = (String) sessione.getAttribute("att"+servizio.getNome().replaceAll("\\s+", ""));
							if(valueInSessione==null ){
								valueInSessione="0";
								sessione.setAttribute("att"+servizio.getNome().replaceAll("\\s+", ""), "0");
							}else if(Integer.parseInt(valueInSessione)>numeroMassimoTotaleOspiti){
								valueInSessione = numeroMassimoTotaleOspiti.toString();
							}
							totaleServizi += Integer.parseInt(valueInSessione)*servizio.getCosto();
						%>
						<tr>
							<td><%=servizio.getNome()%></td>	
							<td><%=servizio.getCosto()%> €</td>
							<td>
								<div class="containerImgTabella">
								<a href="GestisciCarrello?op=aggiungi&servizio=att<%=servizio.getNome().replaceAll("\\s+", "")%>"><img alt="Aggiungi servizio" src="images/simbolopiu.png"></a>
								<input type="number" value="<%=valueInSessione%>" name="<%=servizio.getNome().replaceAll("\\s+", "")%>" readonly>
								<a href="GestisciCarrello?op=rimuovi&servizio=att<%=servizio.getNome().replaceAll("\\s+", "")%>"><img alt="Rimuovi servizio" src="images/simbolomeno.png"></a>
								</div>
							</td>
						</tr>
						<%
						}
						%>
					</table>
			<%
			}
			Integer totaleCarrello = totaleCamere+totaleServizi;
			if(camere!=null && !camere.isEmpty()){
			%>
			<h1>Totale carrello: <%=totaleCarrello%> €</h1>
			<input type="hidden" value="<%=totaleCarrello%>" name="totaleCarrello" />
			<input type="submit" value="Acquista" />
			</div>
			</form>
			<%}%>
			
	<%@ include file="footer.jsp"%>
</body>
</html>