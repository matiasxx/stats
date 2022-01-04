package com.mcr.stats.model.cargas;

import java.util.Date;

public abstract class Estadisticas {
	
	
	private int id;
	private Date fechaCarga;
	private Date fechaEstadistica;
	private boolean estadoExistencia;
	private String lugarDeEmision;
	
	public Estadisticas() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEstadoExistencia() {
		return estadoExistencia;
	}

	public void setEstadoExistencia(boolean estadoExistencia) {
		this.estadoExistencia = estadoExistencia;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public Date getFechaEstadistica() {
		return fechaEstadistica;
	}

	public void setFechaEstadistica(Date fechaEstadistica) {
		this.fechaEstadistica = fechaEstadistica;
	}

	public String getLugarDeEmision() {
		return lugarDeEmision;
	}

	public void setLugarDeEmision(String lugarDeEmision) {
		this.lugarDeEmision = lugarDeEmision;
	}
	
	
	

}
