<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Utente"%>
<!DOCTYPE html>

<html>
<head>
	<link rel="stylesheet" href="styles/navBar.css" type="text/css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
</head>

<%
	Utente user = (Utente) request.getSession().getAttribute("utente");
%>

<body>
	<nav>
		<div class="logo"><img src="images/logo.png" alt="Logo"></div>
		<div class="menu">
			<ul>
				<li><a href="homepage.jsp">Home</a></li>
				<li><a href="#">Prenota ora</a></li>
				<li><a href="servizi.jsp">Servizi</a></li>
				<li><a href="#">Recensioni</a></li>
			</ul>
		</div>
		<div class="login">
			<a href="#"><img alt="Cart" src="images/cart.png"></a>
			
			<%if(user==null){%>
				<a href="login.jsp">Login</a>
			<%}else{%>
				<p>utente</p>
				<a href="<%=request.getContextPath()%>/common/Logout">logout</a>
			<%} %>
		</div>
		
	</nav>
</body>
</html>