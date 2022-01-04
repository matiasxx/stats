package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IservicioTiposDeContratacion;
import com.mcr.stats.dao.DaoTiposDeContratacion;
import com.mcr.stats.model.TipoDeContratacion;
import com.vaadin.data.util.BeanItemContainer;

public class ServicioTipoDeContratacion implements IservicioTiposDeContratacion{

	
	
	private static ServicioTipoDeContratacion instance;
	
	private ServicioTipoDeContratacion() {
		
	}
	
	public static ServicioTipoDeContratacion getInstance() {
		
		if(instance == null) {
			instance = new ServicioTipoDeContratacion();
		}
		return instance;
	}
	
	
	@Override
	public BeanItemContainer<TipoDeContratacion> getTiposDeContratacion() {
		
		BeanItemContainer<TipoDeContratacion> container = new BeanItemContainer<TipoDeContratacion>(TipoDeContratacion.class,
				DaoTiposDeContratacion.getInstance().getTiposDeConcentracion());
		return container;
	}

}
