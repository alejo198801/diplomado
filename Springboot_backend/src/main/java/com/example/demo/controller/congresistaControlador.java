package com.example.demo.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Congresista;
import com.example.demo.model.Proyecto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CongresistaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.proyectoRepository;

@RestController
@RequestMapping("/api/congresista")
public class congresistaControlador {

	@Autowired
	private proyectoRepository proyectoRepositorio;
	@Autowired
	private CongresistaRepository congresistaRepositorio;
	@Autowired
	private UsuarioRepository usuarioRepositorio;

	@GetMapping
	public ResponseEntity<Page<Congresista>> ListarCongresistas(Pageable pageable) {

		return ResponseEntity.ok(congresistaRepositorio.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<Congresista> GuardaCongresista(@Validated @RequestBody Congresista congresista) {

		Optional<Proyecto> proyectoOptional = proyectoRepositorio.findById(congresista.getProyectos().getId());
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(congresista.getUsuarios().getId());
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		if (!proyectoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		congresista.setUsuarios(usuarioOptional.get());
		congresista.setProyectos(proyectoOptional.get());
		Congresista congresistaGuardado = congresistaRepositorio.save(congresista);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(congresistaGuardado.getCongresista_id()).toUri();
		return ResponseEntity.created(ubicacion).body(congresistaGuardado);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Congresista> actualizarCongresista(@PathVariable Long id,
			@Validated @RequestBody Congresista congresista) {

		Optional<Proyecto> proyectoOptional = proyectoRepositorio.findById(congresista.getProyectos().getId());
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(congresista.getUsuarios().getId());

		if (!proyectoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		Optional<Congresista> congresistaOptional = congresistaRepositorio.findById(id);
		if (!congresistaOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		congresista.setProyectos(proyectoOptional.get());
		congresista.setUsuarios(usuarioOptional.get());
		congresista.setCongresista_id(congresistaOptional.get().getCongresista_id());
		congresistaRepositorio.save(congresista);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Congresista> eliminarCongresista(@PathVariable Long id) {
		Optional<Congresista> congresistaOptional = congresistaRepositorio.findById(id);
		if (!congresistaOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		congresistaRepositorio.delete(congresistaOptional.get());
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Congresista> encontrarCongresistaPorId(@PathVariable Long id) {
		Optional<Congresista> congresistaOptional = congresistaRepositorio.findById(id);
		if (!congresistaOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(congresistaOptional.get());

	}

}
