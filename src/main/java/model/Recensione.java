package model;

import java.io.Serializable;

public class Recensione  implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	private int codRecensione;
	private String descrizione;
	private Integer voto;
	private String utente;
	
	public Recensione() {
		codRecensione = -1;
		descrizione = "";
		voto = -1;
		utente = null;
	}
	
	//-=======================SETTER=======================-
	
	public void setCodRecensione(int codRecensione) {
		this.codRecensione = codRecensione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	//-=======================GETTER=======================-
	
	public int getCodRecensione() {
		return codRecensione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getUtente() {
		return utente;
	}
	
	public Integer getVoto() {
		return voto;
	}
}
