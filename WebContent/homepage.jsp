<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hotel Campus</title>
	<link rel="stylesheet" href="styles/homepage.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/styles/fontFamily.css">
	<meta name="viewport" content="initial-scale=1, width=device-width">
</head>
<body>
	<% request.setAttribute("in", "home"); %>
	<%@ include file="navigationBar.jsp"%>
	
	<section class="banner">
		<img class="bannerImage" alt="Immagine Campus Unisa" src="images/bannerFotoAereaCampus.jpg">
	</section>
	<section class="testoDiBenvenuto">
		<p>Benvenuti all'Hotel Campus, il vostro rifugio accogliente nei pressi dell'incantevole Università degli Studi di Salerno.
		 Siamo qui per offrirvi un soggiorno confortevole e piacevole, sia che siate studenti desiderosi di esplorare questa affascinante 
		 città universitaria, sia che siate orgogliosi parenti in visita per celebrare il traguardo dei laureandi.<br></p><hr/><p>
		 Presso l'Hotel Campus, ci impegniamo a soddisfare le vostre esigenze con servizi di alta qualità e un personale attento e disponibile. 
		 Godetevi la nostra prima colazione a buffet per iniziare la giornata con energia, rilassatevi nella nostra area lounge o approfittate 
		 del Wi-Fi gratuito per rimanere connessi con il resto del mondo.<br><hr/><p>
		 Che siate qui per affari, per piacere o per celebrare una tappa importante della vostra vita accademica, all'Hotel Campus troverete 
		 sempre un ambiente accogliente e familiare che renderà il vostro soggiorno indimenticabile. Siamo ansiosi di darvi il benvenuto e di 
		 rendere la vostra esperienza a Salerno memorabile.
		 </p>
	</section>
	
	<section class="tipologie">
		<hr>
		<h1>Scopri tutte le nostre tipologie di camere:</h1>
		
		<div class="container">
    		<div class="slide">
      			<div
        			class="item"
        			style="background-image: url(images/luxury.jpg)"
      			>		
        			<div class="content">
          				<div class="name">EXCLUSIVE</div>
          				<div class="des">
           		 			Il livello Exclusive garantisce un’esperienza unica e personalizzata, con accesso a servizi e strutture riservati a pochi. Perfetto per chi desidera il massimo dell’esclusività, privacy e attenzione individuale, godendo di un lusso senza pari.
          				</div>
        			</div>
      			</div>
      			<div
        			class="item"
        			style="background-image: url(images/standard.jpg)"
      			>		
        			<div class="content">
          				<div class="name">STANDARD</div>
          				<div class="des">
           		 			Il livello Standard offre servizi essenziali e di qualità affidabile. Ideale per chi cerca comfort e funzionalità a un prezzo accessibile, mantenendo un elevato standard di ospitalità.
          				</div>
        			</div>
      			</div>
      			<div
        			class="item"
        			style="background-image: url(images/deluxe.jpg)"
      			>		
        			<div class="content">
          				<div class="name">DELUXE</div>
          				<div class="des">
           		 			Il livello Deluxe rappresenta un’esperienza superiore, con servizi e comfort extra. Perfetto per chi desidera un tocco di lusso e maggiore attenzione ai dettagli, senza rinunciare alla convenienza.
          				</div>
        			</div>
      			</div>
      			<div
        			class="item"
        			style="background-image: url(images/exclusive.jpg)"
      			>		
        			<div class="content">
          				<div class="name">LUXURY</div>
          				<div class="des">
           		 			Il livello Luxury offre un’esperienza di alta classe con servizi premium, design raffinato e comfort esclusivo. Ideale per chi cerca il massimo del lusso e desidera essere coccolato con i migliori servizi e strutture.
          				</div>
        			</div>
      			</div>
      			
    		</div>
    		
    		<div class="button">
      				<button class="prev" onclick="precedente()"><i class="arrow left"></i></button>
      				<button class="next" onclick="successivo()"><i class="arrow right"></i></button>
    		</div>
  		</div>
	</section>
	
	<script>
		function successivo(){
			let items = document.querySelectorAll('.item')
		    document.querySelector('.slide').appendChild(items[0])
		} 
		
		function precedente(){
			 let items = document.querySelectorAll('.item')
			 document.querySelector('.slide').prepend(items[items.length - 1])
		}
	</script>
	
	<%@ include file="footer.jsp"%>
</body>
</html>