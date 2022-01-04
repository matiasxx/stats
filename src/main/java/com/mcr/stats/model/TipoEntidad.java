package com.mcr.stats.model;

public class TipoEntidad {
	
	private int idTipoEntidad;
	private String tipoEntidad;
	private TipoEntidad dependencia;
	private boolean estadoExistencia;
	
	
	
	public TipoEntidad() {
	
	}



	public int getIdTipoEntidad() {
		return idTipoEntidad;
	}



	public void setIdTipoEntidad(int idTipoEntidad) {
		this.idTipoEntidad = idTipoEntidad;
	}



	public String getTipoEntidad() {
		return tipoEntidad;
	}



	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}



	public TipoEntidad getDependencia() {
		return dependencia;
	}



	public void setDependencia(TipoEntidad dependencia) {
		this.dependencia = dependencia;
	}



	public boolean isEstadoExistencia() {
		return estadoExistencia;
	}



	public void setEstadoExistencia(boolean estadoExistencia) {
		this.estadoExistencia = estadoExistencia;
	}
	
	
	
	

}
