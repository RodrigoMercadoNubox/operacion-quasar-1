package com.init.operacion.quasar.model;

public class Transportador extends PortaCarga {

	private String message;

	public Transportador(Posicion position, String message) {
		this.setPosition(position);
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
