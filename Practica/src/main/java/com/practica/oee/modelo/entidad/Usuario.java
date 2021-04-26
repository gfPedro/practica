package com.practica.oee.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "USUARIOS")
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 947774072625502793L;

	@Id
	@Getter
	@Setter
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "ID", nullable = false, precision = 19, scale = 0)
	protected Long id;

	@NotNull
	@Column(name = "TX_NOMBRE")
	private String nombre;

	@NotNull
	@Column(name = "NU_EDAD")
	private Integer edad;

	@NotNull
	@Column(name = "TX_EMAIL")
	private String email;

	@NotNull
	@Column(name = "TX_PASS")
	private String contrasenia;

}
