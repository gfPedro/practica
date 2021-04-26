package com.practica.oee.servicio.usuario;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.practica.oee.modelo.dto.AltaUsuarioDTO;
import com.practica.oee.modelo.dto.CredencialesUsuarioDTO;
import com.practica.oee.modelo.dto.UsuarioDTO;
import com.practica.oee.modelo.entidad.Usuario;

public interface UsuarioServicio extends Serializable {

	UsuarioDTO crear(AltaUsuarioDTO dto);

	Page<UsuarioDTO> obtenerListado(Integer pagina, Integer tamanioPagina, String orden);

	Optional<Usuario> obtenerUsuarioPorEmailYContrasenia(CredencialesUsuarioDTO dto);

}
