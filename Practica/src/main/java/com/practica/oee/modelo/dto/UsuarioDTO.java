package com.practica.oee.modelo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = -133708840609220626L;

	private Long id;

	private String nombre;

	private Integer edad;

	private String email;

}
