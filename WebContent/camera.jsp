<%@page import="model.Camera"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Hotel Campus</title>
<base href="<%=request.getContextPath()%>/" />
	<link rel="stylesheet" href="styles/camera.css" type="text/css">
<meta charset="UTF-8">

</head>
<body>
	<%@ include file="navigationBar.jsp"%>
	
	<% 
		Camera camera = (Camera)request.getAttribute("camera");
	
		if(camera == null || camera.getNumero() == 0){
			response.sendRedirect("homepage.jsp");
		}
	%>
	
	<section class="camera">
	
		
		<div class="primaryInformation">
			<img alt="Immagine camera 1" src="./GetPicture?beanType=camera&id=<%=camera.getNumero()%>&numberImg=1">
			<div class="alignInformation">
				<div><h1>Tipologia camera:</h1><span><%=camera.getTipo()%></span></div>
				<div><h1>Quadratura:</h1><span><%=camera.getQuadratura()%> mq</span></div>
				<div><h1>Numero massimo di ospiti:</h1><span><%=camera.getNumeroMaxOspiti()%></span></div>
				<div><h1>N°:</h1><span><%=camera.getNumero()%></span></div>
				<div><h1>Costo per notte:</h1><span><%=camera.getCosto()%></span></div>
			</div>
		</div>
		<div class="secondaryInformation">
			<div>
				<% if(camera.getTipo().equals("standard")){ %>
					<p>La nostra camera Standard offre un ambiente confortevole e accogliente, ideale per chi cerca un soggiorno piacevole a un prezzo accessibile. La camera è dotata di tutti i servizi essenziali, tra cui TV a schermo piatto, connessione Wi-Fi gratuita, aria condizionata e un bagno privato con set di cortesia. Perfetta per viaggiatori d’affari o turisti che desiderano una base funzionale da cui esplorare la città.</p>
				<%}else if(camera.getTipo().equals("deluxe")){ %>
					<p>La camera Deluxe è progettata per chi cerca un tocco di lusso durante il proprio soggiorno. Con un design elegante e raffinato, questa camera spaziosa offre una TV a schermo piatto con canali satellitari, connessione Wi-Fi ad alta velocità, aria condizionata, minibar e una cassaforte. Il bagno privato è dotato di una grande vasca da bagno e di un set di cortesia premium. Godetevi il comfort aggiuntivo e i servizi esclusivi che rendono ogni soggiorno indimenticabile.</p>
				<%}else if(camera.getTipo().equals("luxury")){ %>
					<p>La camera Luxury rappresenta il massimo del comfort e dell’eleganza. Arredata con materiali pregiati e dotata di tecnologie all’avanguardia, questa camera offre una TV OLED da 55 pollici, connessione Wi-Fi ultraveloce, aria condizionata regolabile, minibar con selezione di bevande di alta qualità e una cassaforte di dimensioni laptop. Il bagno privato include una doccia a pioggia, una vasca idromassaggio e un set di cortesia di lusso. Inoltre, potrete usufruire di un’area lounge privata all’interno della camera per momenti di relax e intimità.</p>
				<%}else if(camera.getTipo().equals("exclusive")){ %>
					<p>La camera Exclusive è pensata per chi desidera un’esperienza unica e personalizzata, con servizi su misura per soddisfare ogni esigenza. Questa suite esclusiva offre un ampio spazio con vista panoramica, una TV a schermo piatto da 65 pollici, connessione Wi-Fi ultraveloce, aria condizionata centralizzata e un minibar premium. Il bagno privato è dotato di doccia a pioggia, vasca idromassaggio, sauna privata e un set di cortesia esclusivo. Godetevi anche l’accesso a una spa privata in camera, con massaggi su richiesta e trattamenti benessere personalizzati. Un servizio di maggiordomo dedicato è disponibile per garantire che ogni aspetto del vostro soggiorno sia impeccabile.
					</p>
				<%}%>
			</div>
			
			<img alt="" onerror="handleError(this);" src="./GetPicture?beanType=camera&id=<%=camera.getNumero()%>&numberImg=2">
			
		</div>
	</section>
	
	<%@ include file="footer.jsp"%>
</body>
</html>