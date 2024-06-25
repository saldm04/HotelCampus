function switchElement(element){		
	switch(element){
	case "home":
		var x = document.getElementById("home");
		x.className = "underline";
		break;
	case "servizi":
		var x = document.getElementById("servizi");
		x.className = "underline";
		break;
	case "prenotaOra":
		var x = document.getElementById("prenotaOra");
		x.className = "underline";
		break;
	}
}


let burgerMenuIsClick = true;


function showMenu(){
	
	const burgerMenu = document.getElementById("burger__menu");
	const menuContent = document.getElementById("menu__content");
	const oscuraSfondo = document.getElementById("oscuraSfondo");
	
	if(burgerMenuIsClick){
		burgerMenuIsClick = false;
		burgerMenu.className = "burger_menu__close__active";
		oscuraSfondo.className = "oscuraSfondoAttivo";
		menuContent.className = "showMenu";		
			
	
	}else{
		burgerMenuIsClick = true;
		oscuraSfondo.className = "oscuraSfondo";

		menuContent.className = "menustandard";	
		burgerMenu.className = "";	
	}
	
}

//funzione generale per non mostare nulla in caso di immagine non trovata
 function handleError(img) {
            img.style.display = 'none'; // Nasconde l'immagine
 }


