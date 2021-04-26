package com.practica.oee.repositorio;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.practica.oee.modelo.entidad.Usuario;

@Repository
public interface UsuarioRepositorio
		extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>, Serializable {

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByEmailAndContrasenia(String email, String contrasenia);

}
