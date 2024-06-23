<%@page import="java.util.Iterator"%>
<%@page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Utente, java.util.List, java.math.BigInteger"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <base href="<%=request.getContextPath()%>/" />
 <link rel="stylesheet" href="styles/visualizzaUtenti.css">
</head>
<body>

<%! 
		private SecureRandom z = new SecureRandom(); 
		private String csrf3 = new BigInteger(130, z).toString(32);
%>

<%
    Collection<Utente> utenti = (Collection<Utente>) request.getAttribute("utenti");
    if (utenti == null) {
        response.sendRedirect(request.getContextPath()+"/admin/Utenti");
        return;
    }
    
    request.getSession().setAttribute("csrfToken", csrf3);
%>

<section class="vUtenti">

<ul class="responsive-table">
	<li class="table-header">
      <div class="col col-1">Email</div>
      <div class="col col-2">Nome</div>
      <div class="col col-3">Cognome</div>
      <div class="col col-4">Nazionalità</div>
      <div class="col col-5">Data di Nascita</div>
      <div class="col col-6">Admin</div>
      <div class="col col-7"> </div>
    </li>
        <%
            for (Utente utente : utenti) {
        %>
		<li class="table-row">
			<div class="col col-1" data-label="Email"><%= utente.getEmail() %></div>
			<div class="col col-2" data-label="Nome"><%= utente.getNome() %></div>
			<div class="col col-3" data-label="Cognome"><%= utente.getCognome() %></div>
			<div class="col col-4" data-label="Nazionalità"><%= utente.getNazionalità() %></div>
			<div class="col col-5" data-label="Data di nascita"><%= utente.getDataNascita().toString() %></div>
        	<div class="col col-6" data-label="Admin">  
        		<form action="<%=request.getContextPath()%>/admin/Utenti" method="post">
                   			<input type="hidden" name="csrfToken" value="<%=csrf3%>">
                            <input type="hidden" name="action" value="updateAdminStatus">
                            <input type="hidden" name="email" value="<%= utente.getEmail()%>">
                            <input type="checkbox" name="isAdmin" value="true" <% if (utente.isAdmin()) { %>checked<% } %> onchange="this.form.submit()">
                 </form> 
            </div>
            <div class="col col-7" data-label="Elimina"> 
            	 <form action="<%=request.getContextPath()%>/admin/Utenti" method="post">
                   			<input type="hidden" name="csrfToken" value="<%=csrf3%>">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="email" value="<%= utente.getEmail()%>">
                            <input class="delete" type="button" onclick = "this.form.submit()">
                   </form>
            </div>
        </li>
        <%
            }
        %>
    
</ul>

</section>

</body>
</html>
