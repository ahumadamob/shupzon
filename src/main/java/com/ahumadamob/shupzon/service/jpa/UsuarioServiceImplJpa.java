package com.ahumadamob.shupzon.service.jpa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ahumadamob.shupzon.entity.Usuario;
import com.ahumadamob.shupzon.repository.UsuarioRepository;
import com.ahumadamob.shupzon.service.IUsuarioService;

@Service
public class UsuarioServiceImplJpa implements IUsuarioService {
	
	private UsuarioRepository r;

	@Override
	public List<Usuario> getAll() {
		return r.findAllByOrderByNombreAsc(); 
	}

	@Override
	public Usuario getById(Long id) {
		return (id==null)?null:r.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Long id) {
		return (id == null)?false:r.existsById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return r.save(usuario);
	}

	@Override
	public void deleteById(Long id) {
		r.deleteById(id);	
	}

}
