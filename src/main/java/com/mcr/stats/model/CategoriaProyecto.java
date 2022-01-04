package com.mcr.stats.model;

import java.io.Serializable;

public class CategoriaProyecto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCategoria;
	private String nombreCategoria;

	public CategoriaProyecto() {
		
	}

	public CategoriaProyecto(int idCategoria, String nombreCategoria) {
		
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCategoria;
		result = prime * result + ((nombreCategoria == null) ? 0 : nombreCategoria.hashCode());
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
		CategoriaProyecto other = (CategoriaProyecto) obj;
		if (idCategoria != other.idCategoria)
			return false;
		if (nombreCategoria == null) {
			if (other.nombreCategoria != null)
				return false;
		} else if (!nombreCategoria.equals(other.nombreCategoria))
			return false;
		return true;
	}
	
	

}
