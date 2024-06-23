

	let addMenu1IsClick = true;

	const addMenu1 = document.getElementById("addServizio");
	
	function showMenuServizi(){
	
	
		if(addMenu1IsClick){
			addMenu1IsClick = false;
			addMenu1.className = "formAnimation aggiungiServizio";
					
	
		}else{
			addMenu1IsClick = true;
			addMenu1.className = "aggiungiServizio";
		}	
	}
		
		
	let addMenu2IsClick = true;
	
	const addMenu2 = document.getElementById("aggiungiCamera");
	
	function showMenuCamere(){
		
		
		if(addMenu2IsClick){
			addMenu2IsClick = false;
			addMenu2.className = "formAnimation aggiungiCamera";		
	
		}else{
			addMenu2IsClick = true;
			addMenu2.className = "aggiungiCamera";		
			
		}
	}

 
      function validateEmail() {
        const email = document.getElementById("email");
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (email.value === "" || emailPattern.test(email.value)) {
            email.style.border = "2px solid grey";
            return true;
        } else {
            email.style.border = "2px solid red";
            return false;
        }
    }

    function checkSelectedDate(dateElem) {
        const checkInElem = document.getElementById("checkindate");
        const checkOutElem = document.getElementById("checkoutdate");

        if (dateElem.id === "checkindate" && checkInElem.value !== "") {
            checkOutElem.min = getNextDate(checkInElem.value);
        } else if (dateElem.id === "checkoutdate" && checkOutElem.value !== "") {
            checkInElem.max = getPreviousDate(checkOutElem.value);
        }
    }

    function getNextDate(date) {
        let currentDate = new Date(date);
        currentDate.setDate(currentDate.getDate() + 1);
        return currentDate.toISOString().split('T')[0];
    }

    function getPreviousDate(date) {
        let currentDate = new Date(date);
        currentDate.setDate(currentDate.getDate() - 1);
        return currentDate.toISOString().split('T')[0];
    }

    function validateDates() {
        const checkInElem = document.getElementById("checkindate");
        const checkOutElem = document.getElementById("checkoutdate");
        const checkInDate = checkInElem.value ? new Date(checkInElem.value) : null;
        const checkOutDate = checkOutElem.value ? new Date(checkOutElem.value) : null;

        if (checkInDate && checkOutDate && checkInDate >= checkOutDate) {
            checkInElem.style.border = "2px solid red";
            checkOutElem.style.border = "2px solid red";
            return false;
        } else {
            checkInElem.style.border = "2px solid grey";
            checkOutElem.style.border = "2px solid grey";
            return true;
        }
    }

    function validateForm() {
        const isEmailValid = validateEmail();
        const areDatesValid = validateDates();
        return isEmailValid && areDatesValid;
    }
    
    function validateNome() {
        const nome = document.getElementById("nome");
        const nomePattern = /^[a-zA-Z]+(?: [a-zA-Z]+)*$/;
        if (nomePattern.test(nome.value)) {
            nome.style.border = "2px solid grey";
            return true;
        } else {
            nome.style.border = "2px solid red";
            return false;
        }
    }

    function validateDescrizione() {
        const descrizione = document.getElementById("descrizione");
        const descrizionePattern = /^[^<>#'"]*$/;
        if (descrizionePattern.test(descrizione.value)) {
            descrizione.style.border = "2px solid grey";
            return true;
        } else {
            descrizione.style.border = "2px solid red";
            return false;
        }
    }

    function validateServiceForm() {
        const isNomeValid = validateNome();
        const isDescrizioneValid = validateDescrizione();
        return isNomeValid && isDescrizioneValid;
    }

