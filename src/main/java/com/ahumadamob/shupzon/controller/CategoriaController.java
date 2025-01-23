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
import com.ahumadamob.shupzon.entity.Categoria;
import com.ahumadamob.shupzon.service.ICategoriaService;
import com.ahumadamob.shupzon.util.BuildResponse;


@RestController
@RequestMapping(path="/api/v1/categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO<List<Categoria>>> getCategorias() {
		List<Categoria> categorias = categoriaService.getAll();
		return	(categorias.isEmpty()) ? BuildResponse.notFound("No se encontraron categorías"):
			BuildResponse.success(categorias);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO<Categoria>> getCategoriaById(@PathVariable("id") Long id){
		return	categoriaService.existsById(id)? BuildResponse.success(categoriaService.getById(id)):
				BuildResponse.notFound("No se encontró la categoría con id {0}.", id);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<Categoria>> createCategoria(@RequestBody Categoria categoria){
		return	!categoriaService.existsById(categoria.getId())? BuildResponse.created(
				categoriaService.save(categoria), "Categoría creada correctamente"):
				BuildResponse.badRequest("Ya existe la categoría con id {0}.", categoria.getId());
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO<Categoria>> updateCategoria(@RequestBody Categoria categoria){
		return	categoriaService.existsById(categoria.getId())? BuildResponse.success(
				categoriaService.save(categoria), "Categoría modificada correctamente"):
				BuildResponse.badRequest("No existe la categoría con id {0}.", categoria.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDTO<Object>> deleteCategoria(@PathVariable("id") Long id){
		if(categoriaService.existsById(id)) {
			categoriaService.deleteById(id);
			return BuildResponse.success("Categoría eliminada correctamente.");
		}else {
			return BuildResponse.badRequest("No existe el alumno con id {0}.", id);
		}				
	}
	

}
