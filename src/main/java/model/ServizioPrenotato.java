package model;

import java.io.Serializable;

public class ServizioPrenotato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int costo;
	private String servizio;
	private int prenotazione;
	
	public ServizioPrenotato(){
		this.id=0;
		this.costo=0;
		this.servizio="";
		this.prenotazione=0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getServizio() {
		return servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	public int getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(int prenotazione) {
		this.prenotazione = prenotazione;
	}
}
