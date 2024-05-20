<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
	<link rel="stylesheet" href="styles/signIn.css" type="text/css">
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
        <span><a href="login.jsp">Accedi /</a> Registrati </span>

        <form action="SignIn" method="post">
          <fieldset>
            <legend>Anagrafica</legend>
            <input
              id="nome"
              type="text"
              name="nome"
              placeholder="Inserisci nome"
            />
            <br />
            <input
              id="cognome"
              type="text"
              name="cognome"
              placeholder="Inserisci cognome"
            />
            <br />
            <input
              id="dataNascita"
              type="date"
              name="dataNascita"
              text-align="center"
            />
            <br />
            <!-- <input
              id="nazionalita"
              type="text"
              name="nazionalita"
              placeholder="Inserisci nazionalità"
            />-->
            
   
            <br />
          </fieldset>
          <br />
          <fieldset>
            <legend>Credenziali</legend>
            <input
              id="email"
              type="email"
              name="email"
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
          </fieldset>
          <input type="submit" value="SignIn" />
        </form>
      </div>
    </section>
  </body>
</html>