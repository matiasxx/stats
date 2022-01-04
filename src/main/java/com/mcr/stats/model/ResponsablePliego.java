package com.mcr.stats.model;

import java.io.Serializable;

public class ResponsablePliego implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idResponsablePliego;
	private String nombre;
	private String apellido;
	private String apellidoNombre;
	
	public ResponsablePliego() {
	
	}

	public ResponsablePliego(int idResponsablePliego, String nombre, String apellido) {
		
		this.idResponsablePliego = idResponsablePliego;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellidoNombre = this.apellido+", "+ this.nombre;
	
	}

	public int getIdResponsablePliego() {
		return idResponsablePliego;
	}

	public void setIdResponsablePliego(int idResponsablePliego) {
		this.idResponsablePliego = idResponsablePliego;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellidoNombre() {
		return apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((apellidoNombre == null) ? 0 : apellidoNombre.hashCode());
		result = prime * result + idResponsablePliego;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponsablePliego other = (ResponsablePliego) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (apellidoNombre == null) {
			if (other.apellidoNombre != null)
				return false;
		} else if (!apellidoNombre.equals(other.apellidoNombre))
			return false;
		if (idResponsablePliego != other.idResponsablePliego)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
	
	

}
