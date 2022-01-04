package com.mcr.stats.Idao;

import java.util.ArrayList;

import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.TipoDeCarnet;

public interface IdaoCarnet {
	
	public ArrayList<ClaseDeCarnet> getClaseCarne();
	public ArrayList<ClaseDeCarnet> getClaseCarne(String valor);
	public ArrayList<TipoDeCarnet> getTipoCarne();
	
	
}
