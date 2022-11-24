package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "congresista")
public class congresista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long congresista_id;
	@NonNull
	@Column(name = "nombre")
	private String name;
	@NonNull
	@Column(name = "apellido")
	private String lastname;
	@NonNull
	@Column(name = "ciudad")
	private String city;
	@NonNull
	@Column(name = "Telefono")
	private Long phone;
	@NonNull
	@Column(name = "email")
	private String email;
	@NonNull
	@Column(name = "partido")
	private String partido;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private usuario usuarios;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "proyecto_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private proyecto proyectos;

	public congresista() {

	}

	public congresista(Long congresista_id, String name, String lastname, String city, Long phone, String email,
			String partido, usuario usuarios, proyecto proyectos) {
		super();
		this.congresista_id = congresista_id;
		this.name = name;
		this.lastname = lastname;
		this.city = city;
		this.phone = phone;
		this.email = email;
		this.partido = partido;
		this.usuarios = usuarios;
		this.proyectos = proyectos;
	}

	public Long getCongresista_id() {
		return congresista_id;
	}

	public void setCongresista_id(Long congresista_id) {
		this.congresista_id = congresista_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(usuario usuarios) {
		this.usuarios = usuarios;
	}

	public proyecto getProyectos() {
		return proyectos;
	}

	public void setProyectos(proyecto proyectos) {
		this.proyectos = proyectos;
	}

}
