package com.mcr.stats.model;

import java.io.Serializable;

public class ClaseDeCarnet implements Serializable {
	
	
	private int id;
	private String claseDeCarnet;
	private boolean estadoExistencia;
	
	
	public ClaseDeCarnet() {
		
		
	}
	
	public ClaseDeCarnet(int id, String claseDeCarne) {
		
		this.id = id;
		this.claseDeCarnet = claseDeCarne;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getClaseDeCarnet() {
		return claseDeCarnet;
	}


	public void setClaseDeCarnet(String claseDeCarnet) {
		this.claseDeCarnet = claseDeCarnet;
	}


	public boolean isEstadoExistencia() {
		return estadoExistencia;
	}


	public void setEstadoExistencia(boolean estadoExistencia) {
		this.estadoExistencia = estadoExistencia;
	}
	
	

}
