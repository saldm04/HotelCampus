package model;

import java.io.Serializable;

public class Servizio implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
	
	private String nome;
	private String descrizione;
	private int costo;
	private Boolean disponibile;
	
	public Servizio() {
		this.nome = "";
		this.descrizione = "";
		this.costo = 0;
		this.disponibile = false;
	}
	
	//-=======================SETTER=======================-
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public void setDisponibile(Boolean disponibile) {
		this.disponibile = disponibile;
	}
	
	//-=======================GETTER=======================-
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public int getCosto() {
		return costo;
	}
	
	public Boolean isDisponibile() {
		return disponibile;
	}
}
