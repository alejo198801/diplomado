package com.example.demo.model;

import java.sql.Date;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "proyecto", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@Column(name = "nombre")
	private String name;
	@NonNull
	@Column(name = "descripcion")
	private String descripcion;
	@NonNull
	@Column(name = "fecha_creacion")
	private Date creacion;

	@OneToMany(mappedBy = "congresista_id", cascade = CascadeType.ALL)
	private Set<congresista> congresista_proyecto = new HashSet<>();

	public proyecto() {

	}

	public proyecto(Long id, String name, String descripcion, Date creacion, Set<congresista> congresista_proyecto) {
		super();
		this.id = id;
		this.name = name;
		this.descripcion = descripcion;
		this.creacion = creacion;
		this.congresista_proyecto = congresista_proyecto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public Set<congresista> getCongresista_proyecto() {
		return congresista_proyecto;
	}

	public void setCongresista_proyecto(Set<congresista> congresista_proyecto) {
		this.congresista_proyecto = congresista_proyecto;
	}

}
