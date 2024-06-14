<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
  <head>
 <meta charset="UTF-8">
	<title>Hotel Campus</title>
	<link rel="stylesheet" href="styles/login.css" type="text/css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
  </head>
  <body>
  	<%@ include file="navigationBar.jsp"%>
  	
  	<% 
  		if(request.getSession().getAttribute("utente") != null){
  			response.sendRedirect("homepage.jsp");
  			return;
  		}
  	
		List<String> errors = (List<String>) request.getAttribute("errors");
	%>
    <section>
     <div class="center">
     	<img src="images/logo.png" alt="logo" />
      <span>Accedi <a href="signIn.jsp">/ Registrati</a></span>
     	
      <form action="Login" method="post">
        <input
          id="username"
          type="email"
          name="username"
          placeholder="Inserisci email"
        />
        <br />

        <input
          id="password"
          type="password"
          name="password"
          placeholder="Inserisci password"
        />
        <br />

        <input type="submit" value="Login" />
      </form>
      
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
      <% } %>
      </div>
    </section>
  </body>
</html>