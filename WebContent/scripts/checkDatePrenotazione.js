function checkSelectedDate(checkInElem){
	let checkOutElem = document.getElementById("checkoutdate");
	checkOutElem.value="";
	
	if(checkInElem.value==""){
		checkOutElem.setAttribute("disabled", "disabled");
	}else{
		checkOutElem.removeAttribute("disabled");
		checkOutElem.min=getNextDate(checkInElem.value);
	}
}

function getNextDate(date){
	let currentDate = new Date(date);

    // Incrementa il giorno di 1
    currentDate.setDate(currentDate.getDate() + 1);

    // Ottieni la data nel formato "YYYY-MM-DD"
    let nextDate = currentDate.toISOString().split('T')[0];

    return nextDate;
}