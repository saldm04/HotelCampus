const nomeCognomeNazionalitaPattern = /^[A-Za-z]+$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

const nomeErrorMessage = "Il nome può contenere solo lettere<br/>";
const cognomeErrorMessage = "Il cognome può contenere solo lettere<br/>";
const emailErrorMessage = "Inserire un'email nella forma: username@domain.ext<br/>";
const passwordErrorMessage = "La password deve contere: almeno 8 caratteri, una lettera, una cifra<br/>";
const nazionalitaErrorMessage = "La nazionalità può contenere solo lettere<br/>";

function validate() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanNome = document.getElementById("errorNome");
	if(!validateFormElem(form.nome, nomeCognomeNazionalitaPattern, spanNome, nomeErrorMessage)){
		valid = false;
	} 
	let spanCognome = document.getElementById("errorCognome");
	if (!validateFormElem(form.cognome, nomeCognomeNazionalitaPattern, spanCognome, cognomeErrorMessage)){
		valid = false;
	}
	let spanNazionalita = document.getElementById("errorNazionalita");
	if (!validateFormElem(form.nazionalita, nomeCognomeNazionalitaPattern, spanNazionalita, cognomeErrorMessage)){
		valid = false;
	}
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)){
		valid = false;
	}
	let spanPassword = document.getElementById("errorPassword");
	if (!validateFormElem(form.password, passwordPattern, spanPassword, passwordErrorMessage)){
		valid = false;
	}
	
	return valid;
}

function validateFormElem(formElem, pattern, span, message) {
	if(formElem.value.match(pattern)){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.innerHTML = message;
	span.style.color = "red";
	return false;
}