<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Utente"%>
<!DOCTYPE html>

<html>
<head>
	<link rel="stylesheet" href="styles/navBar.css" type="text/css">
	<link
      href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css"
      rel="stylesheet"
    />
	<meta name="viewport" content="initial-scale=1, width=device-width">
	<script type="text/javascript" src="scripts/navBarScript.js"></script>
	
</head>

<%
	Utente user = (Utente) request.getSession().getAttribute("utente");
	String x = (String) request.getAttribute("in");
%>

<body onload="switchElement('<%=x%>')">
	<nav>
		<div class="logo"><img src="images/logo.png" alt="Logo"></div>
		<div class="menu">
			<ul id="menu__content" class="menustandard">	
				<li class="underlineAnimation" id="home"><a href="homepage.jsp" >Home</a></li>
				<li class="underlineAnimation" id="prenotaOra"><a href="#">Prenota ora</a></li>
				<li class="underlineAnimation" id="servizi"><a href="servizi.jsp" >Servizi</a></li>
				<li class="underlineAnimation" id="recensioni"><a href="#" >Recensioni</a></li>
			</ul>
		</div>
		
		<div class="login">
			<a href="#"><img alt="Cart" src="images/cart.png"></a>
			
			<%if(user==null){%>
				<a href="login.jsp">Login</a>
			<%}else{%>
				<p>utente </p>
				<a href="<%=request.getContextPath()%>/common/Logout">logout</a>
			<%} %>
			<div id="burger__menu" onclick="showMenu()" class="">
                <i class="ri-menu-line burger__menu__view" ></i>
                <i class="ri-close-line burger__menu__close" ></i>
        </div>
		</div>
		
		
	</nav>
	

</body>
</html>