package com.practica.oee.modelo.excepcion;

public class PracticaExcepcion extends RuntimeException {

	private static final long serialVersionUID = -8053859158420832669L;

	public PracticaExcepcion(String message) {
		super(message);
	}

	public PracticaExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
}