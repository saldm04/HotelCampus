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
	}
	
}


let burgerMenuIsClick = false;


function showMenu(){
	
	const burgerMenu = document.getElementById("burger__menu");
	const menuContent = document.getElementById("menu__content");
	
	if(burgerMenuIsClick){
		burgerMenuIsClick = false;
		burgerMenu.className = "burger_menu__close__active";
		menuContent.className = "showMenu";		
			
	
	}else{
		burgerMenuIsClick = true;
		menuContent.className = "menustandard";	
		burgerMenu.className = "";	
	}
	
}


