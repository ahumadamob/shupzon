package com.ahumadamob.shupzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahumadamob.shupzon.dto.ResponseDTO;
import com.ahumadamob.shupzon.entity.Usuario;
import com.ahumadamob.shupzon.service.IUsuarioService;
import com.ahumadamob.shupzon.util.BuildResponse;

@RestController
@RequestMapping(path="/api/v1/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO<List<Usuario>>> getUsuarios() {
		List<Usuario> usuarios = usuarioService.getAll();
		return	(usuarios.isEmpty()) ? BuildResponse.notFound("No se encontraron usuarios."):
			BuildResponse.success(usuarios);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO<Usuario>> getUsuarioById(@PathVariable("id") Long id){
		return	usuarioService.existsById(id)? BuildResponse.success(usuarioService.getById(id)):
				BuildResponse.notFound("No se encontró el usuario con id {0}.", id);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<Usuario>> createUsuario(@RequestBody Usuario usuario){
		return	!usuarioService.existsById(usuario.getId())? BuildResponse.created(
				usuarioService.save(usuario), "Usuario creado correctamente"):
				BuildResponse.badRequest("Ya existe el usuario con id {0}.", usuario.getId());
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO<Usuario>> updateUsuario(@RequestBody Usuario usuario){
		return	usuarioService.existsById(usuario.getId())? BuildResponse.success(
				usuarioService.save(usuario), "Usuario modificada correctamente"):
				BuildResponse.badRequest("No existe el usuario con id {0}.", usuario.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDTO<Object>> deleteUsuario(@PathVariable("id") Long id){
		if(usuarioService.existsById(id)) {
			usuarioService.deleteById(id);
			return BuildResponse.success("Usuario eliminado correctamente.");
		}else {
			return BuildResponse.badRequest("No existe el usuario con id {0}.", id);
		}				
	}	
	
}
