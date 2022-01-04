package com.mcr.stats.servicios;

import java.util.Date;

import com.mcr.stats.Iservicios.IservicioLicencia;
import com.mcr.stats.dao.DaoLicencia;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.cargas.LicenciaConducir;
import com.vaadin.data.util.BeanItemContainer;

public class ServicioLicencia implements IservicioLicencia {
	
	
	private static ServicioLicencia instance;
	
	
	private ServicioLicencia() {
			
	}
	
	public static ServicioLicencia getInstance() {
		
		if(instance == null) {			
			instance = new ServicioLicencia();
		}		
		return instance;
		
		
	}

	@Override
	public int guardarLicencia(Usuario usuario, LicenciaConducir licencia) {
		
		return DaoLicencia.getInstance().guardarLicencia(usuario, licencia);
	}

	@Override
	public BeanItemContainer<LicenciaConducir> buscarLicencias(Date fechaDesde, Date fechaHasta) {
		
		BeanItemContainer<LicenciaConducir> containter = new BeanItemContainer<>(LicenciaConducir.class, 
				DaoLicencia.getInstance().buscarLicencias(fechaDesde, fechaHasta));				   
		return containter;
	}

	@Override
	public int modificarLicencia(Usuario usuario, LicenciaConducir licencia) {
		
		return DaoLicencia.getInstance().modificarLicencia(usuario, licencia);
	}

	@Override
	public int validar(Usuario usuario, Date fechaDesde, Date fechaHasta, String centro) {
		
		return DaoLicencia.getInstance().validar(usuario, fechaDesde, fechaHasta, centro);
	}

	@Override
	public int eliminarLicencia(Usuario usuario, LicenciaConducir licencia) {
		
		return DaoLicencia.getInstance().eliminarLicencia(usuario, licencia);
	}
	

}
