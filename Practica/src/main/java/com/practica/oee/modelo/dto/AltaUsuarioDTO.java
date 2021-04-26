package com.practica.oee.modelo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AltaUsuarioDTO implements Serializable {

	private static final long serialVersionUID = -2173234818363626340L;

	private String nombre;

	private Integer edad;

	private String email;

	private String contrasenia;

}
