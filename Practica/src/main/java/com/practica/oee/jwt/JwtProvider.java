package com.practica.oee.jwt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.practica.oee.modelo.entidad.Usuario;
import com.practica.oee.repositorio.UsuarioRepositorio;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	public String jwtSecret = "secretoLargoQueDeberiaIrEnConfiguracionExternaAlCodigo";

	private Usuario usuario;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public String generateJwtToken(String userName, Usuario usuario) {
		this.usuario = usuario;

		return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
				.signWith(SignatureAlgorithm.HS256, this.jwtSecret).compact();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public JwtAuthToken getJwtToken(String token) {
		String id = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody().getSubject();
		if (id != null) {
			this.establecerUsuario(id);
			if (this.usuario == null) {
				return null;
			}
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("LOGADO"));
			return new JwtAuthToken(id, authorities);
		}
		return null;
	}

	private void establecerUsuario(String idStr) {
		Long id = Long.parseLong(idStr);
		if (this.usuario == null) {
			Optional<Usuario> optUsuario = this.usuarioRepositorio.findById(id);
			if (optUsuario.isPresent()) {
				this.usuario = optUsuario.get();
			}
		}
	}

	public Optional<String> obtenerUsuarioAuditor() {
		String idStr = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (idStr != null) {
			Long idUsuario = Long.parseLong(idStr);
			Optional<Usuario> optUsuario = this.usuarioRepositorio.findById(idUsuario);
			if (optUsuario.isPresent()) {
				return Optional.of(optUsuario.get().getEmail());
			}
			return Optional.empty();
		}
		return Optional.empty();
	}

	public String obtenerNombre() {
		String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (id != null) {
			this.establecerUsuario(id);
			if (this.usuario == null) {
				return null;
			}
			return this.usuario.getNombre();
		}
		return null;
	}

	public Long obtenerIdUsuarioJwt() {
		String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (id != null) {
			this.establecerUsuario(id);
			if (this.usuario == null) {
				return null;
			}
			return this.usuario.getId();
		}
		return null;
	}

	public Claims getBodyFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
	}
}
