
package com.ahumadamob.shupzon.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahumadamob.shupzon.entity.Categoria;
import com.ahumadamob.shupzon.entity.Producto;
import com.ahumadamob.shupzon.repository.ProductoRepository;
import com.ahumadamob.shupzon.service.IProductoService;

@Service
public class ProductoServiceImplJpa implements IProductoService {	
	
	@Autowired
	private ProductoRepository r;

	@Override
	public List<Producto> getAll() {
		return r.findAllByOrderByNombreAsc();
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		return r.findByCategoriaOrderByNombreAsc(categoria);
	}

	@Override
	public Producto getById(Long id) {
		return r.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Long id) {
		return (id == null)?false:r.existsById(id);
	}

	@Override
	public Producto save(Producto producto) {
		return r.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		r.deleteById(id);		
	}

}
