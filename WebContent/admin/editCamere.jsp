
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Camera"%>
    <%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
    Collection<Camera> camere = (Collection<Camera>) request.getAttribute("editCamere");
    if (camere == null) {
        response.sendRedirect(request.getContextPath()+"/admin/EditCamere");
        return;
    }
	%> 
	
	<section class="editServizi">
 
 
 	
 	<section class="eliminaServizi">
        <%
            if (camere != null && camere.size() != 0) {
                Iterator<?> it = camere.iterator();
                while (it.hasNext()) {
                    Camera bean = (Camera) it.next();
        %>
        <div class="card">
        <img alt="Immagine servizio 1" src="./GetPicture?beanType=camera&id=<%=bean.getNumero()%>&numberImg=1">
          <div class="alignItem">
            
            <span>Numero:<%= " "+bean.getNumero() %>   <a href="<%= request.getContextPath() %>/admin/EditServizi?action=delete&nome=<%= bean.getNumero() %>">
            <span>Costo: <%= bean.getCosto() %> €</span>
            <span>Numero massimo ospiti: <%= bean.getNumeroMaxOspiti() %></span>
            <span>tipo: <%= bean.getTipo() %></span>
            <span>quadratura: <%= bean.getQuadratura() %></span>
        <button type="button"></button>
        </a></span>
            <span>Descrizione:</span>
            <p><%= bean.getDescrizione() %></p>
            
    
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