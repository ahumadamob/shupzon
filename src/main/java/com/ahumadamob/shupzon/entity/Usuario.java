package com.ahumadamob.shupzon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario extends BaseEntity {
    
	@NotNull
	@Column(nullable = false, unique = true)
    private String nombre;

    @Column
    private String contrasena;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    


}
