package com.practica.oee.servicio.usuario;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.oee.modelo.dto.AltaUsuarioDTO;
import com.practica.oee.modelo.dto.CredencialesUsuarioDTO;
import com.practica.oee.modelo.dto.UsuarioDTO;
import com.practica.oee.modelo.entidad.Usuario;
import com.practica.oee.modelo.excepcion.PracticaExcepcion;
import com.practica.oee.repositorio.UsuarioRepositorio;
import com.practica.oee.utils.Paginacion;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {

	private static final long serialVersionUID = 4368758874488217843L;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private transient ModelMapper modelMapper;

	@Override
	public UsuarioDTO crear(AltaUsuarioDTO dto) {
		Usuario usuario = this.modelMapper.map(dto, Usuario.class);
		usuario.setContrasenia(dto.getContrasenia());
		if (usuario.getId() != null) {
			throw new PracticaExcepcion("Error de creación: el identificador debe ser nulo");
		}
		try {
			return this.modelMapper.map(this.usuarioRepositorio.saveAndFlush(usuario), UsuarioDTO.class);
		} catch (Exception e) {
			throw new PracticaExcepcion(
					"Error de creación: no se ha podido crear el usuario, vuelva a intentarlo más tarde");
		}

	}

	@Override
	public Page<UsuarioDTO> obtenerListado(Integer pagina, Integer tamanioPagina, String orden) {
		return this.usuarioRepositorio.findAll(Paginacion.obtenerPaginable(pagina, tamanioPagina, orden))
				.map(e -> this.modelMapper.map(e, UsuarioDTO.class));
	}

	@Override
	public Optional<Usuario> obtenerUsuarioPorEmailYContrasenia(CredencialesUsuarioDTO dto) {
		return this.usuarioRepositorio.findByEmailAndContrasenia(dto.getEmail(), dto.getContrasenia());
	}

}
