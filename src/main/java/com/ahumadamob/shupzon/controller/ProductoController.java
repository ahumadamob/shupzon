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
import com.ahumadamob.shupzon.entity.Producto;
import com.ahumadamob.shupzon.service.ICategoriaService;
import com.ahumadamob.shupzon.service.IProductoService;
import com.ahumadamob.shupzon.util.BuildResponse;

@RestController
@RequestMapping(path="/api/v1/producto")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO<List<Producto>>> getProductos() {
		List<Producto> productos = productoService.getAll();
		return	(productos.isEmpty()) ? BuildResponse.notFound("No se encontraron productos"):
			BuildResponse.success(productos);
	}
	
	@GetMapping("/categoria/{id_categoria}")
	public ResponseEntity<ResponseDTO<List<Producto>>> getProductosByCategoria(@PathVariable("id_categoria") Long idCategoria) {
		Categoria categoria = categoriaService.getById(idCategoria);
		if(categoria==null) {
			return BuildResponse.badRequest("No existe la categoría con id {0}.", idCategoria);
		}else {
			List<Producto> productos = productoService.getByCategoria(categoria);
			return	(productos.isEmpty()) ? BuildResponse.notFound("No se encontraron productos"):
				BuildResponse.success(productos);	
		}		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO<Producto>> getProductoById(@PathVariable("id") Long id){
		return	productoService.existsById(id)? BuildResponse.success(productoService.getById(id)):
				BuildResponse.notFound("No se encontró el producto con id {0}.", id);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<Producto>> createProducto(@RequestBody Producto producto){
		Categoria categoria = this.getCategoriaFromProducto(producto);
		if(categoria==null) {
			return this.getInvalidCategoriaMessage(producto);
		}else {		
			return	!productoService.existsById(producto.getId())? BuildResponse.created(
					productoService.save(producto), "Producto creado correctamente"):
					BuildResponse.badRequest("Ya existe el producto con id {0}.", producto.getId());
		}
	}
	
	@PutMapping	
	public ResponseEntity<ResponseDTO<Producto>> updateProducto(@RequestBody Producto producto){
		Categoria categoria = this.getCategoriaFromProducto(producto);
		if(categoria==null) {
			return this.getInvalidCategoriaMessage(producto);
		}else {
		return	productoService.existsById(producto.getId())? BuildResponse.success(
				productoService.save(producto), "Producto modificado correctamente"):
				BuildResponse.badRequest("No existe el producto con id {0}.", producto.getId());	
		}		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDTO<Object>> deleteProducto(@PathVariable("id") Long id){
		if(productoService.existsById(id)) {
			productoService.deleteById(id);
			return BuildResponse.success("Producto eliminado correctamente.");
		}else {
			return BuildResponse.badRequest("No existe el producto con id {0}.", id);
		}				
	}
	
	private Categoria getCategoriaFromProducto(Producto producto) {
		if(producto.getCategoria()==null) {
			return null;
		}else {
			return categoriaService.getById(producto.getCategoria().getId());
		}
	}
	
	private ResponseEntity<ResponseDTO<Producto>> getInvalidCategoriaMessage(Producto producto) {
		if(producto.getCategoria()==null) {
			return BuildResponse.badRequest("categoria", "No se ha especificado la categoría.");
		}else {
			return BuildResponse.badRequest("No existe la categoría con id {0}.", producto.getCategoria().getId());
		}	
	}
	

}
