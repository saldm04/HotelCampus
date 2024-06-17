<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
	<link rel="stylesheet" href="styles/signIn.css" type="text/css">
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
		
		// Ottieni la data corrente
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = dateFormat.format(today);
		
	%>
    <section>
      <div class="center">
        <img src="images/logo.png" alt="logo" />
        <span><a href="login.jsp">Accedi /</a> Registrati </span>

        <form action="SignIn" method="post" id="regForm">
          <fieldset>
            <legend>Anagrafica</legend>
            <input
              id="nome"
              type="text"
              name="nome"
              placeholder="Inserisci nome"
              required
              onchange="validateFormElem(this, nomeCognomeNazionalitaPattern, document.getElementById('errorNome'), nomeErrorMessage)"
            />
            <br />
            <input
              id="cognome"
              type="text"
              name="cognome"
              placeholder="Inserisci cognome"
              required
              onchange="validateFormElem(this, nomeCognomeNazionalitaPattern, document.getElementById('errorCognome'), cognomeErrorMessage)"
            />
            <br />
            <input
              id="dataNascita"
              type="date"
              name="dataNascita"
              min="1900-01-01"
              max="<%=todayStr%>"
              required
            />
            <br />
 
            
            <input
              id="nazionalita"
              type="text"
              name="nazionalita"
              placeholder="Inserisci nazionalità"
              required
              oninput="cercaNazionalita()"
              onchange="validateFormElem(this, nomeCognomeNazionalitaPattern, document.getElementById('errorNazionalita'), nazionalitaErrorMessage)"
            />
            <br/>
            <div class="suggerimentiNazione">
         	</div>
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
              required
              onchange="validateFormElem(this, emailPattern, document.getElementById('errorEmail'), emailErrorMessage)"
            />
            <br />

            <input
              id="password"
              type="password"
              name="password"
              placeholder="Inserisci password"
              required
              onchange="validateFormElem(this, passwordPattern, document.getElementById('errorPassword'), passwordErrorMessage)"
            />
            <br />
          </fieldset>
          
          <div class="divErrorSpan">
	          <span id="errorNome"></span>
	          <span id="errorCognome"></span>
	          <span id="errorNazionalita"></span>
	          <span id="errorEmail"></span>
	          <span id="errorPassword"></span>
          </div>
          
          <input type="submit" value="Registrati" onclick="return validate()"/>
        </form>
      </div>
    </section>
  </body>
</html>