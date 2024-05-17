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
	
	<% 
		Collection<?> servizi = (Collection<?>) request.getAttribute("servizi");
		if(servizi == null){
			response.sendRedirect("Servizi");
			return;
		}
	%>
	<h1>I nostri servizi</h1>
	<%
		if(servizi != null && servizi.size()!=0){
			Iterator<?> it = servizi.iterator();
			while(it.hasNext()){
				Servizio bean = (Servizio) it.next();
	%>
	<section class="service">
		<h2><%= bean.getNome()%></h2>
		<p><%= bean.getDescrizione()%></p>
		<p>Costo per persona: <%= bean.getCosto()%> €</p>
		<div class="images">
			<img alt="Immagine servizio 1" src="./GetPicture?beanType=servizio&id=<%=bean.getNome()%>&numberImg=1">
			<img alt="Immagine servizio 2" src="./GetPicture?beanType=servizio&id=<%=bean.getNome()%>&numberImg=2">
		</div>
	</section>
	
		<%}%>
	<%}%>
</body>
</html>