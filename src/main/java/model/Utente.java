package model;

import java.io.Serializable;
import java.sql.Date;

public class Utente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String email;
	private String nazionalità;
	private String password;
	private Boolean admin;
	private Date dataNascita;
	 
	
	public Utente() {
		nome = "";
		cognome = "";
		email = "";
		password = "";
		dataNascita = null;
		nazionalità = "";
		admin = false;
	}
	
	//-=======================SETTER=======================-
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public void setNazionalità(String nazionalità) {
		this.nazionalità = nazionalità;
	}
	
	public void setAdmin(Boolean value) {
		this.admin = value;
	}
	
	//-=======================GETTER=======================-
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public String getNazionalità() {
		return nazionalità;
	}
	
	public Boolean isAdmin() {
		return admin;
	}
	
	public String getPassword() {
		return password;
	}
}
