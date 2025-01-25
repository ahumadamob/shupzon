package com.ahumadamob.shupzon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.shupzon.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public List<Usuario> findAllByOrderByNombreAsc();

}
