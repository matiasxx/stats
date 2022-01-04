package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IservicioProyecto;
import com.mcr.stats.Iservicios.IservicioResponsablePliego;
import com.mcr.stats.dao.DaoResponsablePliego;
import com.mcr.stats.model.ResponsablePliego;
import com.vaadin.data.util.BeanItemContainer;

public class ServicioResponsablePliego implements IservicioResponsablePliego{

	
	private static ServicioResponsablePliego instance;
	
	private ServicioResponsablePliego() {
		
	}
	
	public static ServicioResponsablePliego getInstance() {
		
		if(instance == null) {
			instance = new ServicioResponsablePliego();
		}
		return instance;
	}
	
	@Override
	public BeanItemContainer<ResponsablePliego> getResponsablesPliego() {
		
		BeanItemContainer<ResponsablePliego> containter = new BeanItemContainer<>(ResponsablePliego.class, 
				DaoResponsablePliego.getInstance().getResponsablePliego());
		return containter; 
	}
	
	

}
