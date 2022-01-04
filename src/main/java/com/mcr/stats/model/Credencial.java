package com.mcr.stats.model;

import java.io.Serializable;

public class Credencial implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreDeUsuario;
	private String contrasenia;
	
	
	public Credencial() {
		
		
		
	}


	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}


	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
	

}
