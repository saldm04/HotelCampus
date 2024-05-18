<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Servizio"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Servizi</title>
	<link rel="stylesheet" href="styles/servizi.css" type="text/css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
</head>
<body>
	<%@ include file="navigationBar.jsp"%>
	
	<section class="testoIniziale">
      <h1>
        Benvenuti all'Hotel Campus, dove l'ospitalità incontra il comfort e
        l'eleganza. <br />
        <br />Qui offriamo una gamma completa di servizi per rendere il vostro
        soggiorno indimenticabile:
      </h1>
    </section>
	
	<% 
		Collection<?> servizi = (Collection<?>) request.getAttribute("servizi");
		if(servizi == null){
			response.sendRedirect("Servizi");
			return;
		}
	%>
	<section class="servizi">
	<%
		if(servizi != null && servizi.size()!=0){
			Iterator<?> it = servizi.iterator();
			while(it.hasNext()){
				Servizio bean = (Servizio) it.next();
	%>
	<div class="card">
		<img alt="Immagine servizio 1" src="./GetPicture?beanType=servizio&id=<%=bean.getNome()%>&numberImg=1">
		<h1><%= bean.getNome()%></h1>
		<p><%= bean.getDescrizione()%></p>
		<span>Costo per persona: <%= bean.getCosto()%> €</span>
	</div>	
	
	
		<%}%>
	<%}%>
	</section>
	
</body>
</html>