<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
  <head>
 <meta charset="UTF-8">
	<title>Hotel Campus</title>
	<link rel="stylesheet" href="styles/login.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/styles/fontFamily.css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
	<script src="scripts/validate.js"></script>
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
     	
      <form action="Login" method="post" id="formLogin">
        <input
          id="email"
          class="email"
          type="email"
          name="email"
          placeholder="Inserisci email"
          onchange="validateFormElem(this, emailPattern, document.getElementById('errorEmail'), emailErrorMessage)"
        />
        <br />

        <input
          id="password"
          class="password"
          type="password"
          name="password"
          placeholder="Inserisci password"
          onchange="validateFormElem(this, passwordPattern, document.getElementById('errorPassword'), passwordErrorMessage)"
        />
        <br />
		
		<span id="errorEmail"></span>
		<span id="errorPassword"></span>
		
        <input type="submit" value="Login" onclick="return validateLogin()"/>
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