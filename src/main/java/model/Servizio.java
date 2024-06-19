package model;

import java.io.Serializable;

public class Servizio implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
	
	private String nome;
	private String descrizione;
	private int costo;
	private Boolean disponibile;
	private byte[] img1;
	private byte[] img2;
	
	public Servizio() {
		this.nome = "";
		this.descrizione = "";
		this.costo = 0;
		this.disponibile = true;
		this.img1=null;
		this.img2=null;
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
	
	public void setImg1(byte[] img1) {
		this.img1 = img1;
	}
	
	public void setImg2(byte[] img2) {
		this.img2 = img2;
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
	
	public byte[] getImg1(){
		return img1;
	}
	
	public byte[] getImg2(){
		return img2;
	}
}
