<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Utente, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" href="styles/editDatiAccount.css" type="text/css">
	<script src="scripts/validate.js"></script>
</head>
<body>
	<%
		Utente account = (Utente) request.getSession().getAttribute("utente");
		List<String> errors = (List<String>) request.getSession().getAttribute("problemDetectd");
		String esitoPositivo = (String) request.getAttribute("esitoPositivo");
		String esitoNegativo = (String) request.getAttribute("esitoNegativo");
	%>
	
	<div class="mainContainer">
		<section class="dati">
			<h1>Dati account</h1>
			<p>Email: <%=account.getEmail()%></p>
			<p>Nome: <%=account.getNome()%></p>
			<p>Cognome: <%=account.getCognome()%></p>
			<p>Nazionalità: <%=account.getNazionalità()%></p>
			<p>Data di nascita: <%=account.getDataNascita()%></p>
		</section>
		<section class="password">
			<h1>Modifica password</h1>
			<form action="common/editDatiAccount" method="post">
				<label for="oldPass"><input type="password" name="oldPass" id="oldPass" required
				onchange="validateFormElem(this, passwordPattern, document.getElementById('errorPassword'), passwordErrorMessage)"></label>
				<label for="newPass"><input type="password" name="newPass" id="newPass" required
				onchange="validateFormElem(this, passwordPattern, document.getElementById('errorPassword'), passwordErrorMessage)"></label>
				<input type="submit" value="Modifica password">
			</form>
			<span id="errorPassword"></span>
		</section>
		<%if(esitoPositivo!=null){%>
		<p class="esitoPositivo"><%=esitoPositivo%></p>
		<%}%>
		<% if(errors != null){  %>
      		<div class="warning">
      			<img src="images/warning.png" alt="" />
      			<span>
      				<h4>Attenzione</h4>
      				<p>
      				<% for (String error: errors){ %>
					<%=error %> <br>		
					<% }%>
					</p>
      			</span>
      		</div>
      	<% request.getSession().removeAttribute("problemDetectd");} %>
      	
      	<%if(esitoNegativo!=null){%>
      	<div class="warning">
      		<img src="images/warning.png" alt="" />
      		<span>
				<p><%=esitoNegativo%></p>
			</span>
		</div>
		<%}%>
		
	</div>
	
</body>
</html>