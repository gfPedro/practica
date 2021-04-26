package com.practica.oee.jwt;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthToken extends AbstractAuthenticationToken {

	private final String idUsuario;

	public JwtAuthToken(String idUsuario, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.idUsuario = idUsuario;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return this.idUsuario;
	}

	@Override
	public Object getPrincipal() {
		return this.idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		return prime * result + ((this.idUsuario == null) ? 0 : this.idUsuario.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof JwtAuthToken)) {
			return false;
		}
		JwtAuthToken other = (JwtAuthToken) obj;
		if (this.idUsuario == null) {
			if (other.idUsuario != null) {
				return false;
			}
		} else if (!this.idUsuario.equals(other.idUsuario)) {
			return false;
		}
		return true;
	}

}
