package com.ahumadamob.shupzon.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Categoria extends BaseEntity {
	
	@NotNull
	private String nombre;
	private String descripcion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
