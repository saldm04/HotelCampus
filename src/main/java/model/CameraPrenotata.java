package model;

import java.io.Serializable;

public class CameraPrenotata implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int costo;
	private int camera;
	private int prenotazione;
	private String tipo;
	
	public CameraPrenotata(){
		this.id=0;
		this.costo=0;
		this.camera=0;
		this.prenotazione=0;
		this.tipo="";
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public int getCamera() {
		return camera;
	}

	public void setCamera(int camera) {
		this.camera = camera;
	}

	public int getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(int prenotazione) {
		this.prenotazione = prenotazione;
	}
}
