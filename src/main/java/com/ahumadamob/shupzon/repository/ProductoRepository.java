package com.ahumadamob.shupzon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.shupzon.entity.Categoria;
import com.ahumadamob.shupzon.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	public List<Producto> findAllByOrderByNombreAsc();
	List<Producto> findByCategoriaOrderByNombreAsc(Categoria categoria);
}
