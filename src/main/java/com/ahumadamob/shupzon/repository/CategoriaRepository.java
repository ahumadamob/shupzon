package com.ahumadamob.shupzon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.shupzon.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public List<Categoria> findAllByOrderByNombreAsc();

}
