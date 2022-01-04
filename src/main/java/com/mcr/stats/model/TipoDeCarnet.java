package com.mcr.stats.model;

public class TipoDeCarnet {
	
	
	private int id;
	private String nombreTipoDeCarnet;
	private boolean estadoExistencia;
	
	
	public TipoDeCarnet() {
		
	}


	public TipoDeCarnet(int idTipoCarne) {
		
		
		setId(idTipoCarne);
		switch (idTipoCarne) {
		case 1: setNombreTipoDeCarnet("ORIGINAL");			
		break;
		case 2: setNombreTipoDeCarnet("RENOVACION");			
		break;
		case 3: setNombreTipoDeCarnet("DUPLICADO");			
		break;
		case 4: setNombreTipoDeCarnet("DUPLICADO POR CAMBIO DE DATOS");			
		break;
		case 5: setNombreTipoDeCarnet("AMP. CON CAMBIO DE CLASE");			
		break;
		case 6: setNombreTipoDeCarnet("RENOVACION CON AMPLIACION");			
		break;
		default:
			break;
		}
		
		//System.out.println("id:" + getId());
		//System.out.println("nombreTipoCarne:" + getNombreTipoDeCarnet());
		
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

	public String getNombreTipoDeCarnet() {
		return nombreTipoDeCarnet;
	}


	public void setNombreTipoDeCarnet(String nombreTipoDeCarnet) {
		this.nombreTipoDeCarnet = nombreTipoDeCarnet;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		TipoDeCarnet other = (TipoDeCarnet) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	


	
	
	

}
