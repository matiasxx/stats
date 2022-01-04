package com.mcr.stats.model;

import java.io.Serializable;

public class TipoDeContratacion implements Serializable{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idTipo;
	private String tipoDeContratacion;
	
	public TipoDeContratacion() {
		
	}

	public TipoDeContratacion(int idTipo, String tipoDeContratacion) {
	
		this.idTipo = idTipo;
		this.tipoDeContratacion = tipoDeContratacion;
		
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getTipoDeContratacion() {
		return tipoDeContratacion;
	}

	public void setTipoDeContratacion(String tipoDeContratacion) {
		this.tipoDeContratacion = tipoDeContratacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipo;
		result = prime * result + ((tipoDeContratacion == null) ? 0 : tipoDeContratacion.hashCode());
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
		TipoDeContratacion other = (TipoDeContratacion) obj;
		if (idTipo != other.idTipo)
			return false;
		if (tipoDeContratacion == null) {
			if (other.tipoDeContratacion != null)
				return false;
		} else if (!tipoDeContratacion.equals(other.tipoDeContratacion))
			return false;
		return true;
	}
	

}
