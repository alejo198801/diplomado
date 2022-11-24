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

import com.example.demo.model.proyecto;
import com.example.demo.repository.proyectoRepository;

@RestController
@RequestMapping("/api/proyecto")
public class proyectoControlador {

	@Autowired
	private proyectoRepository proyectoRepositorio;

	@GetMapping
	public ResponseEntity<Page<proyecto>> listarProyectos(Pageable pageable) {

		return ResponseEntity.ok(proyectoRepositorio.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<proyecto> guardarProyecto(@Validated @RequestBody proyecto proyecto) {

		proyecto proyectoguardado = proyectoRepositorio.save(proyecto);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(proyectoguardado.getId()).toUri();
		return ResponseEntity.created(ubicacion).body(proyectoguardado);

	}

	@PutMapping("/{id}")
	public ResponseEntity<proyecto> actualizarProyecto(@PathVariable Long id,
			@Validated @RequestBody proyecto proyecto) {
		Optional<proyecto> proyectoOptional = proyectoRepositorio.findById(id);
		if (!proyectoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		proyecto.setId(proyectoOptional.get().getId());
		proyectoRepositorio.save(proyecto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<proyecto> eliminarProyecto(@PathVariable Long id) {
		Optional<proyecto> proyectoOptional = proyectoRepositorio.findById(id);
		if (!proyectoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		proyectoRepositorio.delete(proyectoOptional.get());
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<proyecto> obtenerProyectoPorid(@PathVariable Long id) {
		Optional<proyecto> proyectoOptional = proyectoRepositorio.findById(id);
		if (!proyectoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(proyectoOptional.get());

	}

}
