package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "city")
	private String city;
	@Column(name = "phone")
	private Long phone;
	@Column(name = "email", nullable = false, length = 50, unique = true)
	private String email;

	@OneToMany(mappedBy = "congresista_id", cascade = CascadeType.ALL)
	private Set<congresista> congresistas = new HashSet<>();

	public usuario() {

	}

	public usuario(Long id, String name, String lastname, String city, Long phone, String email,
			Set<congresista> congresistas) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.city = city;
		this.phone = phone;
		this.email = email;
		this.congresistas = congresistas;
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

	public Set<congresista> getCongresistas() {
		return congresistas;
	}

	public void setCongresistas(Set<congresista> congresistas) {
		this.congresistas = congresistas;
	}

}
