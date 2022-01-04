package com.mcr.stats.Iservicios;

import java.util.Date;

import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.cargas.LicenciaConducir;
import com.vaadin.data.util.BeanItemContainer;

public interface IservicioLicencia {
	
	
	
	public int guardarLicencia(Usuario usuario, LicenciaConducir licencia);
	public int modificarLicencia(Usuario usuario, LicenciaConducir licencia);
	public int eliminarLicencia(Usuario usuario, LicenciaConducir licencia);
	public BeanItemContainer<LicenciaConducir>buscarLicencias(Date fechaDesde, Date fechaHasta);
	
	public int validar (Usuario usuario, Date fechaDesde, Date fechaHasta, String centro);
	
	

}
