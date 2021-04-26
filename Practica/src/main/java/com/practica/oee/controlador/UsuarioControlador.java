package com.practica.oee.controlador;

import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practica.oee.jwt.JwtProvider;
import com.practica.oee.modelo.dto.AltaUsuarioDTO;
import com.practica.oee.modelo.dto.CredencialesUsuarioDTO;
import com.practica.oee.modelo.dto.JwtResponse;
import com.practica.oee.modelo.dto.UsuarioDTO;
import com.practica.oee.modelo.entidad.Usuario;
import com.practica.oee.repositorio.UsuarioRepositorio;
import com.practica.oee.servicio.usuario.UsuarioServicio;

@RestController
@RequestMapping("servicios/usuarios")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private UsuarioRepositorio ur;

	@Autowired
	JwtProvider jwtProvider;

	@ResponseBody
	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> obtenerPagina(@RequestParam(required = false) Integer pagina,
			@RequestParam(required = false) Integer tamanioPagina, @RequestParam(required = false) String filter,
			@RequestParam(required = false) String orden) {
		return ResponseEntity.ok(this.usuarioServicio.obtenerListado(pagina, tamanioPagina, orden));
	}

	@ResponseBody
	@PostMapping
	public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody AltaUsuarioDTO dto) {
		return ResponseEntity.ok(this.usuarioServicio.crear(dto));
	}

	@ResponseBody
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtResponse> login(@RequestBody CredencialesUsuarioDTO dto) throws AuthenticationException {
		Optional<Usuario> optUsuario = this.usuarioServicio.obtenerUsuarioPorEmailYContrasenia(dto);
		if (!optUsuario.isPresent()) {
			throw new AuthenticationException("No existe un usuario con los datos introducidos");
		}
		String token = this.jwtProvider.generateJwtToken(String.valueOf(optUsuario.get().getId()), optUsuario.get());
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
