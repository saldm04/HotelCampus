<%@page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Camera, java.util.List, java.math.BigInteger"%>
    <%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <base href="<%=request.getContextPath()%>/" />
 <link rel="stylesheet" href="styles/editCamere.css" type="text/css">
</head>
<body>

	<%! 
		private SecureRandom y = new SecureRandom(); 
		private String csrf2 = new BigInteger(130, y).toString(32);
	%>
	
	<%
    	Collection<Camera> camere = (Collection<Camera>) request.getAttribute("editCamere");
    	if (camere == null) {
        	response.sendRedirect(request.getContextPath()+"/admin/EditCamere");
        	return;
    	}
    	
    	List<String> errors = (List<String>) request.getSession().getAttribute("problemDetectd");
        
        request.getSession().setAttribute("csrfToken", csrf2);
	%> 
	
	<section class="editCamere">
		<div class="aggiungiCamera" id="aggiungiCamera">
			<div class="information" onclick="showMenuCamere()"><p>Clicca qui per aggiungere una nuova camera</p> <img src="https://img.icons8.com/?size=100&id=1501&format=png&color=0272FF"></div>
			<div class= "camereForm">
			 <div class="close"><p>Inserisci dati camera:</p> <img src="https://img.icons8.com/?size=100&id=3062&format=png&color=FF0202" onclick="showMenuCamere()"></div>
			 <form action="<%=request.getContextPath()%>/admin/EditCamere" enctype="multipart/form-data" method="post">
 	 			<input type="hidden" name="action" value="addCamera">
 	 			<input type="hidden" name="csrfToken" value="<%=csrf2%>">
 	 		    <span><label for="foto1">Prima immagine: </label><input type="file" name="foto1"  required="required"></span>
				<span><label for="foto2">Seconda immagine (facoltativa): </label><input type="file" name="foto2" accept="image/png, image/jpeg"></span>

				<input type="number" min="1" name="numeroCamera" value="" required="required" placeholder="Numero camera">
				<input type="number" min="1" name="numeroMaxOspiti" value="" required="required" placeholder="Numero Ospiti">
				<input type="number" min="1" name="quadratura" value="" required="required" placeholder="Quadratura">
				<input type="number" min="1" name="costo" value="" required="required" placeholder="Costo">
				<select name="tipologia">
					<option value="deluxe">Deluxe</option>
					<option value="exclusive">Exclusive</ooption>
					<option value="standard">Standard</option>
					<option value="luxury">Luxury</option>
				</select>
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
						<% }%>
						<P>
      				</span>
      			</div>
      		<% request.getSession().removeAttribute("problemDetectd");} %>
		</div> 
		
		<div class="eliminaCamere">
			<%
            	if (camere != null && camere.size() != 0) {
                	Iterator<?> it = camere.iterator();
                	while (it.hasNext()) {
                    	Camera bean = (Camera) it.next();
        	%>
        				<div class="card">
        					<img src="./GetPicture?beanType=camera&id=<%=bean.getNumero()%>&numberImg=1">
        					   <div class="alignItem">
            					<span class="alignButton">Numero:<%= " "+bean.getNumero() %> <a href="<%= request.getContextPath() %>/admin/EditCamere?action=delete&numero=<%= bean.getNumero() %>&csrfToken=<%= csrf2 %>"><button type="button"></button></a> </span>
            					<span>Costo: <%= bean.getCosto() %> €</span>
            					<span>Numero massimo ospiti: <%= bean.getNumeroMaxOspiti() %></span>
            					<span>Tipo: <%= bean.getTipo() %></span>
            					<span>Quadratura: <%= bean.getQuadratura() %></span>
            				  </div>	
        				</div>
       
        	<%	 
                	}
            	}
        	%>
		</div>
		
	</section>
 	
 	
</body>
</html>