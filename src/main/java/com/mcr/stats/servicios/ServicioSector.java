package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IservicioSector;
import com.mcr.stats.dao.DaoSector;
import com.mcr.stats.model.Sector;
import com.vaadin.data.util.BeanItemContainer;

public class ServicioSector implements IservicioSector{

	
	
	private static ServicioSector instance;
	
	private ServicioSector() {
		
	}
	
	public static ServicioSector getInstance() {
		
		if(instance == null) {		
			instance = new ServicioSector();
		}
		return instance;
		
	}
	
	
	@Override
	public BeanItemContainer<Sector> getSectores() {
		
		BeanItemContainer<Sector> container = new BeanItemContainer<Sector>(Sector.class, DaoSector.getInstance().getSectores());
		//container.sort("nombreSector", new Boolean(true));
		return container;
	}
	
	
	

}
