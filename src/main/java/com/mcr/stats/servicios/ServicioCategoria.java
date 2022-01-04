package com.mcr.stats.servicios;

import java.io.Serializable;
import java.util.ArrayList;

import com.mcr.stats.Iservicios.IservicioCategoria;
import com.mcr.stats.dao.DaoCarne;
import com.mcr.stats.dao.DaoCategoria;
import com.mcr.stats.model.CategoriaProyecto;
import com.mcr.stats.model.TipoDeCarnet;
import com.vaadin.data.util.BeanItemContainer;

public class ServicioCategoria implements IservicioCategoria, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioCategoria instance;
	
	
	private ServicioCategoria() {		
	
	}
	
	public static ServicioCategoria getInstance() {
		
		if(instance == null) {
			instance = new ServicioCategoria();
		}
		return instance;		
	}
	
	
	@Override
	public BeanItemContainer<CategoriaProyecto> getCategorias() {
		
		BeanItemContainer<CategoriaProyecto> objects = new BeanItemContainer<CategoriaProyecto>(CategoriaProyecto.class,
				DaoCategoria.getInstance().getCategorias());
		return objects;
		
		
	}
	
	
	

}
