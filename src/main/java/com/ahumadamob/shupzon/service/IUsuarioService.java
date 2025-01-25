package com.ahumadamob.shupzon.service;

import java.util.List;

import com.ahumadamob.shupzon.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> getAll();
	public Usuario getById(Long id);
	public boolean existsById(Long id);
	public Usuario save(Usuario usuario);
	public void deleteById(Long id);
}
