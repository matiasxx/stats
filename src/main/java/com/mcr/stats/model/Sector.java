package com.mcr.stats.model;

import java.io.Serializable;

public class Sector implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSector;
	private String nombreSector;
	private boolean estadoExistencia;
	
	
	public Sector() {
		
	}


	public Sector(String nombreSector) {
		
		this.nombreSector = nombreSector;
	}


	public Sector(int idSector, String nombreSector) {
		
		this.idSector = idSector;
		this.nombreSector = nombreSector; 
	}


	public int getIdSector() {
		return idSector;
	}


	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}


	public String getNombreSector() {
		return nombreSector;
	}


	public void setNombreSector(String nombreSector) {
		this.nombreSector = nombreSector;
	}

	public boolean isEstadoExistencia() {
		return estadoExistencia;
	}


	public void setEstadoExistencia(boolean estadoExistencia) {
		this.estadoExistencia = estadoExistencia;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (estadoExistencia ? 1231 : 1237);
		result = prime * result + idSector;
		result = prime * result + ((nombreSector == null) ? 0 : nombreSector.hashCode());
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
		Sector other = (Sector) obj;
		if (estadoExistencia != other.estadoExistencia)
			return false;
		if (idSector != other.idSector)
			return false;
		if (nombreSector == null) {
			if (other.nombreSector != null)
				return false;
		} else if (!nombreSector.equals(other.nombreSector))
			return false;
		return true;
	}
	
	

}
