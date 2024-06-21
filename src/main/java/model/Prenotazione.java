package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Prenotazione implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Timestamp dataPrenotazione;
	private Date dataInizio;
	private Date dataFine;
	private int importo;
	private String utente;
	
	public Prenotazione() {
		this.id = -1;
		this.dataPrenotazione = null;
		this.dataInizio = null;
		this.dataFine = null;
		this.importo = -1;
		this.utente = "";
	}

	//-=======================SETTER=======================-
	public void setId(int id) {
		this.id = id;
	}

	public void setDataPrenotazione(Timestamp dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public void setImporto(int importo) {
		this.importo = importo;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	//-=======================GETTER=======================-
	public int getId() {
		return id;
	}

	public Timestamp getDataPrenotazione() {
		return dataPrenotazione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public int getImporto() {
		return importo;
	}

	public String getUtente() {
		return utente;
	}
	
}
