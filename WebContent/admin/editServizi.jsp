<%@page import="java.security.SecureRandom"%>
<%@page import="model.Servizio"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Utente, java.util.List, java.math.BigInteger"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <base href="<%=request.getContextPath()%>/" />
 <link rel="stylesheet" href="styles/editServizi.css" type="text/css">
</head>
<body>

<%! private SecureRandom x = new SecureRandom(); 
	private String csrf = new BigInteger(130, x).toString(32);
%>

<%
    Collection<Servizio> servizi = (Collection<Servizio>) request.getAttribute("editServizi");
    if (servizi == null) {
        response.sendRedirect(request.getContextPath()+"/admin/EditServizi");
        return;
    }
    List<String> errors = (List<String>) request.getSession().getAttribute("problemDetectd");
    
    request.getSession().setAttribute("csrfToken", csrf);
%> 
 <section class="editServizi">
 
 	<div class=" aggiungiServizio " id="addServizio">
 	 <div class="information" onclick="showMenuServizi()"><p>Clicca qui per aggiungere un nuovo servizio</p> <img src="https://img.icons8.com/?size=100&id=1501&format=png&color=0272FF"></div>
 	 <div class="serviziForm">
 	 	<div class="close"><p>Inserisci dati Servizio:</p> <img src="https://img.icons8.com/?size=100&id=3062&format=png&color=FF0202" onclick="showMenuServizi()"></div>
 	 	<form action="<%=request.getContextPath()%>/admin/EditServizi" enctype="multipart/form-data" method="post">
 	 		<input type="hidden" name="action" value="addServizio">
 	 		<input type="hidden" name="csrfToken" value="<%=csrf%>">
			<input type="file" name="foto1" value="" required="required">	
			<input type="text" name="nome" value="" required="required" placeholder="nome servizio">
			<textarea name="descrizione" value="" maxlength="600" required="required" rows="5" > </textarea>
			<input type="number" min="1" name="costo" value="" required="required" placeholder="costo">
			<input type="submit" value="Conferma"><input type="reset" value="Ripristina">
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
				<% }%><P>
      		</span>
      	</div>
      <% request.getSession().removeAttribute("problemDetectd");} %>
 	</div>
 	
 	<section class="eliminaServizi">
        <%
            if (servizi != null && servizi.size() != 0) {
                Iterator<?> it = servizi.iterator();
                while (it.hasNext()) {
                    Servizio bean = (Servizio) it.next();
        %>
        <div class="card">
        <img alt="Immagine servizio 1" src="./GetPicture?beanType=servizio&id=<%=bean.getNome()%>&numberImg=1">
          <div class="alignItem">
            
            <span>Nome:<%= " "+bean.getNome() %>   <a href="<%= request.getContextPath() %>/admin/EditServizi?action=delete&nome=<%= bean.getNome() %>&csrfToken=<%= csrf %>">
        <button type="button"></button>
        </a></span>
            <span>Descrizione:</span>
            <p><%= bean.getDescrizione() %></p>
            <span>Costo per persona: <%= bean.getCosto() %> €</span>
    
            </div>
        </div>
       
        <% 
                }
            }
        %>
	 </section>	
 </section>

</body>
</html>
