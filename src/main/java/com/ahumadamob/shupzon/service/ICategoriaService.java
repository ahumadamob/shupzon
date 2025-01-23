package com.ahumadamob.shupzon.service;

import java.util.List;
import com.ahumadamob.shupzon.entity.Categoria;

public interface ICategoriaService {
	public List<Categoria> getAll();
	public Categoria getById(Long id);
	public Boolean existsById(Long id);
	public Categoria save(Categoria categoria);
	public void deleteById(Long id);
}
