package com.mcr.stats.model;

import java.io.Serializable;

public class NivelDos extends Perfil implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NivelDos() {
	
	}
	
	@Override
	public String recuperarPerfil() {		
		return "nivel Dos";
	}

	
}
