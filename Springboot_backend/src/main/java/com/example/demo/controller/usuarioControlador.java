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

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class usuarioControlador {

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	@GetMapping
	public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable) {

		return ResponseEntity.ok(usuarioRepositorio.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@Validated @RequestBody Usuario usuario) {
		Usuario usuarioguardado = usuarioRepositorio.save(usuario);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuarioguardado.getId()).toUri();
		return ResponseEntity.created(ubicacion).body(usuarioguardado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @Validated @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		usuario.setId(usuarioOptional.get().getId());
		usuarioRepositorio.save(usuario);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable int id) {
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		usuarioRepositorio.delete(usuarioOptional.get());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioporId(@PathVariable int id) {
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(usuarioOptional.get());

	}

}
