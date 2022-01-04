package com.mcr.stats.Iservicios;

import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.TipoDeCarnet;
import com.vaadin.data.util.BeanItemContainer;

public interface IservicioCarne {
	
	
	public BeanItemContainer<ClaseDeCarnet> getClaseCarne();
	public BeanItemContainer<ClaseDeCarnet> getClaseCarne(String valor);
	public BeanItemContainer<TipoDeCarnet> getTipoCarne();

}
