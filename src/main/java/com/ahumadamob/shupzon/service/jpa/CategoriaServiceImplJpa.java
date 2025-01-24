package com.ahumadamob.shupzon.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahumadamob.shupzon.entity.Categoria;
import com.ahumadamob.shupzon.repository.CategoriaRepository;
import com.ahumadamob.shupzon.service.ICategoriaService;

@Service
public class CategoriaServiceImplJpa implements ICategoriaService {
	
	@Autowired
	private CategoriaRepository r;

	@Override
	public List<Categoria> getAll() {
		return r.findAllByOrderByNombreAsc();
	}

	@Override
	public Categoria getById(Long id) {
		return (id==null)?null:r.findById(id).orElse(null);
	}

	@Override
	public Boolean existsById(Long id) {
		return (id == null)?false:r.existsById(id);
	}

	@Override
	public Categoria save(Categoria categoria) {
		return r.save(categoria);
	}

	@Override
	public void deleteById(Long id) {
		r.deleteById(id);		
	}

}
