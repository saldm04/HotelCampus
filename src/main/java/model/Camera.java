package model;

import java.io.Serializable;

public class Camera implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int numero;
	private int numeroMaxOspiti;
	private int quadratura;
	private int costo;
	private String tipo;
	private String descrizione;
	private Boolean disponibile;
	private byte[] img1;
	private byte[] img2;
	private byte[] img3;
	private byte[] img4;
	
	public Camera(){
		this.numero = 0;
		this.numeroMaxOspiti = 0;
		this.quadratura = 0;
		this.costo = 0;
		this.tipo = "";
		this.descrizione = "";
		this.disponibile = true;
		this.img1 = null;
		this.img2 = null;
		this.img3 = null;
		this.img4 = null;
	}

	//-=======================SETTER=======================-
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setNumeroMaxOspiti(int numeroMaxOspiti) {
		this.numeroMaxOspiti = numeroMaxOspiti;
	}
	
	public void setQuadratura(int quadratura) {
		this.quadratura = quadratura;
	}
	
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	
	public void setImg3(byte[] img3) {
		this.img3 = img3;
	}
	
	public void setImg4(byte[] img4) {
		this.img4 = img4;
	}
	
	
	//-=======================GETTER=======================-
	public int getNumero() {
		return numero;
	}

	public int getNumeroMaxOspiti() {
		return numeroMaxOspiti;
	}

	public int getQuadratura() {
		return quadratura;
	}

	public int getCosto() {
		return costo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Boolean isDisponibile() {
		return disponibile;
	}

	public byte[] getImg1() {
		return img1;
	}

	public byte[] getImg2() {
		return img2;
	}

	public byte[] getImg3() {
		return img3;
	}

	public byte[] getImg4() {
		return img4;
	}
	
}
