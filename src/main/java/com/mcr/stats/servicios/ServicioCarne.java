package com.mcr.stats.servicios;

import java.io.Serializable;
import java.util.ArrayList;

import com.mcr.stats.Iservicios.IservicioCarne;
import com.mcr.stats.dao.DaoCarne;
import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.TipoDeCarnet;
import com.vaadin.data.util.BeanItemContainer;

public class ServicioCarne implements Serializable, IservicioCarne{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioCarne instance;
	
	private ServicioCarne() {
		
		
	}
	
	public static ServicioCarne getInstance(){
		
		if(instance == null){
			instance = new ServicioCarne();
		}
		return instance;
	}
	
	


	@Override
	public BeanItemContainer<ClaseDeCarnet> getClaseCarne() {
		

		ArrayList<ClaseDeCarnet> claseDeCarnets = DaoCarne.getInstance().getClaseCarne();		
		BeanItemContainer<ClaseDeCarnet> objects = new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class,claseDeCarnets);
		return objects;
	}

	@Override
	public BeanItemContainer<TipoDeCarnet> getTipoCarne() {
		
		ArrayList<TipoDeCarnet> tiposDeCarnets = DaoCarne.getInstance().getTipoCarne();		
		BeanItemContainer<TipoDeCarnet> objects = new BeanItemContainer<TipoDeCarnet>(TipoDeCarnet.class,tiposDeCarnets);
		return objects;
	}

	@Override
	public BeanItemContainer<ClaseDeCarnet> getClaseCarne(String valorA) {
		
		ArrayList<ClaseDeCarnet> claseDeCarnets = DaoCarne.getInstance().getClaseCarne(valorA);		
		BeanItemContainer<ClaseDeCarnet> objects = new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class,claseDeCarnets);
		return objects;
	}
	

}
