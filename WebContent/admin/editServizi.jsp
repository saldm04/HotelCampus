<%@page import="model.Servizio"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Utente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <base href="<%=request.getContextPath()%>/" />
 <link rel="stylesheet" href="styles/visualizzaUtenti.css">
</head>
<body>

<%
    Collection<Utente> servizi = (Collection<Utente>) request.getAttribute("servizi");
    if (servizi == null) {
        response.sendRedirect(request.getContextPath()+"/Servizi");
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
