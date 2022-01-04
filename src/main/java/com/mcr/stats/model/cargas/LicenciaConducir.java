package com.mcr.stats.model.cargas;

import java.util.List;

import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.ClasePersona;
import com.mcr.stats.model.PersonaFisica;
import com.mcr.stats.model.TipoDeCarnet;

public class LicenciaConducir extends Estadisticas {
	
	
	
	private PersonaFisica personaFisica;
	private String valor;
	private List <ClaseDeCarnet> claseDeCarnet;
	private TipoDeCarnet tipoDeCarnet;
	
	
	public LicenciaConducir() {
		
	}


	public PersonaFisica getPersonaFisica() {
		return personaFisica;
	}


	public void setPersonaFisica(PersonaFisica personaFisica) {
		this.personaFisica = personaFisica;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public List<ClaseDeCarnet> getClaseDeCarnet() {
		return claseDeCarnet;
	}


	public void setClaseDeCarnet(List<ClaseDeCarnet> claseDeCarnet) {
		this.claseDeCarnet = claseDeCarnet;
	}


	public TipoDeCarnet getTipoDeCarnet() {
		return tipoDeCarnet;
	}


	public void setTipoDeCarnet(TipoDeCarnet tipoDeCarnet) {
		this.tipoDeCarnet = tipoDeCarnet;
	}


	
	
	

}
