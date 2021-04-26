package com.practica.oee.modelo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CredencialesUsuarioDTO implements Serializable {

	private static final long serialVersionUID = -5543455127035033960L;

	private String email;

	private String contrasenia;

}
