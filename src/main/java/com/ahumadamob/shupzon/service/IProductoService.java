package com.ahumadamob.shupzon.service;

import java.util.List;

import com.ahumadamob.shupzon.entity.Categoria;
import com.ahumadamob.shupzon.entity.Producto;

public interface IProductoService {	
	public List<Producto> getAll();
	public List<Producto> getByCategoria(Categoria categoria);
	public Producto getById(Long id);
	public boolean existsById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);
}
