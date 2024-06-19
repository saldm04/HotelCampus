<%@page import="model.Servizio"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.Utente"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <base href="<%=request.getContextPath()%>/" />
 <link rel="stylesheet" href="styles/editServizi.css" type="text/css">
</head>
<body>

<%
    Collection<Servizio> servizi = (Collection<Servizio>) request.getAttribute("editServizi");
    if (servizi == null) {
        response.sendRedirect(request.getContextPath()+"/admin/EditServizi");
        return;
    }
%> 
 <section class="editServizi">
 
 	<div class=" aggiungiServizio " id="addServizio">
 	 <div class="information" onclick="showMenu()"><p>Clicca qui per aggiungere un nuovo servizio</p> <img src="https://img.icons8.com/?size=100&id=1501&format=png&color=0272FF"></div>
 	 <div class="serviziForm">
 	 	<form action="<%=request.getContextPath()%>/admin/EditServizi" enctype="multipart/form-data" method="post">
 	 		<input type="hidden" name="action" value="addServizio">
			<input type="file" name="foto1" value=""  accept="image/png, image/jpeg" required="required">	
			<input type="text" name="nome" value="" required="required" placeholder="nome servizio">
			<textarea name="descrizione" value="" required="required" rows="5" > </textarea>
			<input type="number" min="1" name="costo" value="" required="required" placeholder="costo">
			<input type="submit" value="Conferma"><input type="reset" value="Ripristina">
		</form>
 	 </div>
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
            
            <span>Nome:<%= " "+bean.getNome() %>   <a href="<%= request.getContextPath() %>/admin/EditServizi?action=delete&nome=<%= bean.getNome() %>">
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


<script type="text/javascript">

let addMenuIsClick = false;


function showMenu(){
	
	const addMenu = document.getElementById("addServizio");
	
	if(burgerMenuIsClick){
		addMenuIsClick = false;
		addMenu.className = "formAnimation aggiungiServizio";
			
			
	
	}else{
		burgerMenuIsClick = true;
		addMenu.className = "aggiungiServizio";
	}
	
}
</script>
</body>
</html>
