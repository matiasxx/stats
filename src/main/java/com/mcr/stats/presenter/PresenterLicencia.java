package com.mcr.stats.presenter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mcr.stats.Ihandler.IhandlerLayLicencia;
import com.mcr.stats.ViewOperaciones.Licencia.LayLicencia;
import com.mcr.stats.ViewOperaciones.Licencia.LayLicenciaBM;
import com.mcr.stats.ViewOperaciones.Licencia.LayLicenciaReporte;
import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.PersonaFisica;
import com.mcr.stats.model.TipoDeCarnet;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.cargas.LicenciaConducir;
import com.mcr.stats.servicios.ServicioLicencia;
import com.vaadin.data.Item;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

public class PresenterLicencia implements Serializable, IhandlerLayLicencia{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String site = "https://www.comodoro.gov.ar";
	//public static String site = "http://localhost:8080";
	private LayLicencia view;
	private ServicioLicencia service;
	private LayLicenciaBM viewBM;
	private LayLicenciaReporte viewR;
	
	
	public PresenterLicencia(LayLicenciaReporte viewR, ServicioLicencia service) {
		
		this.viewR = viewR;
		this.service = service;
	}
	
	public PresenterLicencia(LayLicencia view, ServicioLicencia service) {
	
		this.view = view;
		this.service = service;
	}
	
	public PresenterLicencia(LayLicenciaBM viewBM, ServicioLicencia service) {
		
		this.viewBM = viewBM;
		this.service = service;
	}
	
	
	@Override
	public void limpiar() {
			
		
		//view.getFechaCarga().clear();
		view.getTxtApellidoPersona().clear();
		view.getTxtNombrePersona().clear();
		view.getCmbGenero().clear();
		view.getTxtEdad().clear();
		view.getCmbTipoDocumento().clear();
		view.getTxtNumeroDocumento().clear();
		view.getOptClaseCarneA().clear();
		view.getOptClaseCarneB().clear();
		view.getOptClaseCarneC().clear();
		view.getOptClaseCarneD().clear();
		view.getOptClaseCarneE().clear();
		view.getOptClaseCarneF().clear();	
		view.getCmbTipoDeCarne().clear();
		view.getCmbLugar().clear();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void guardar() {
		
		LicenciaConducir licencia = new LicenciaConducir();
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");
		
		licencia.setFechaEstadistica(new Date());
		licencia.setFechaCarga(view.getFechaCarga().getValue());
		licencia.setLugarDeEmision(view.getCmbLugar().getValue().toString());
		licencia.setEstadoExistencia(true);
		
		PersonaFisica persona = new PersonaFisica();	
		persona.setApellido(view.getTxtApellidoPersona().getValue());
		persona.setNombre(view.getTxtNombrePersona().getValue());
		persona.setGenero(view.getCmbGenero().getValue().toString());
		persona.setEdad(view.getTxtEdad().getValue());
		persona.setTipoDeDocumento(view.getCmbTipoDocumento().getValue().toString());
		persona.setNumeroDeDocumento(view.getTxtNumeroDocumento().getValue());
		persona.setEstadoExistencia(true);
				
		licencia.setPersonaFisica(persona);
		
		
		licencia.setClaseDeCarnet(new ArrayList<ClaseDeCarnet>());
		
		Collection<ClaseDeCarnet> selectedItemsA = (Collection<ClaseDeCarnet>) view.getOptClaseCarneA().getValue();
		Collection<ClaseDeCarnet> selectedItemsB = (Collection<ClaseDeCarnet>) view.getOptClaseCarneB().getValue();
		Collection<ClaseDeCarnet> selectedItemsC = (Collection<ClaseDeCarnet>) view.getOptClaseCarneC().getValue();
		Collection<ClaseDeCarnet> selectedItemsD = (Collection<ClaseDeCarnet>) view.getOptClaseCarneD().getValue();
		Collection<ClaseDeCarnet> selectedItemsE = (Collection<ClaseDeCarnet>) view.getOptClaseCarneE().getValue();
		Collection<ClaseDeCarnet> selectedItemsF = (Collection<ClaseDeCarnet>) view.getOptClaseCarneF().getValue();
		
		if(selectedItemsA.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsA.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsB.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsB.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsC.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsC.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsD.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsD.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsE.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsE.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsF.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsF.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		
		licencia.setTipoDeCarnet((TipoDeCarnet) view.getCmbTipoDeCarne().getValue());
		
		
		int rta = service.guardarLicencia(usuario, licencia);
		
		
		if(rta == 3) {
			view.guardarLicenciaPersonaMismoDia();
		}else if(rta !=0) {
			view.guardarLicenciaOK();
			limpiar();
		}else view.guardarLicenciaError();

		
		
	}

	@Override
	public void buscarLicencias() {
		
		viewBM.getFilterTablaLicencia().getContainerDataSource().removeAllItems();
		viewBM.getFilterTablaLicencia().removeAllItems();		
		
		Iterator<LicenciaConducir> iterador = service.buscarLicencias(viewBM.getDtfFechaDesde().getValue(), viewBM.getDtfFechaHasta().getValue()).getItemIds().iterator();
				
		while(iterador.hasNext()) {		
			LicenciaConducir lc = iterador.next();
		viewBM.getFilterTablaLicencia().getContainerDataSource().addItem(lc);
		}
		
		if (viewBM.getFilterTablaLicencia().getContainerDataSource().size() == 0) {
			
			Notification.show("Atencion","Busqueda sin resultados", Type.ERROR_MESSAGE);
		}

	}

	@Override
	public void limpiarBusquedas() {
		
		viewBM.getDtfFechaDesde().clear();
		viewBM.getDtfFechaHasta().clear();
		viewBM.getFilterTablaLicencia().getContainerDataSource().removeAllItems();
		viewBM.getFilterTablaLicencia().removeAllItems();
		
		
		viewBM.getFechaCarga().clear();
		viewBM.getCmbLugar().clear();
		viewBM.getTxtApellidoPersona().clear();
		viewBM.getTxtNombrePersona().clear();
		viewBM.getCmbGenero().clear();
		viewBM.getTxtEdad().clear();
		viewBM.getCmbTipoDocumento().clear();
		
		viewBM.getOptClaseCarneA().setValue(null);
		viewBM.getOptClaseCarneB().setValue(null);
		viewBM.getOptClaseCarneC().setValue(null);
		viewBM.getOptClaseCarneD().setValue(null);
		viewBM.getOptClaseCarneE().setValue(null);
		viewBM.getOptClaseCarneF().setValue(null);
		viewBM.getOptClaseCarneG().setValue(null);
		
		

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modificarLicencias() {
		
		
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");		
		Item licenciaTable = viewBM.getFilterTablaLicencia().getItem(viewBM.getFilterTablaLicencia().getValue());
		

		LicenciaConducir licencia = new LicenciaConducir();
		licencia.setId(Integer.valueOf(licenciaTable.getItemProperty("id").getValue().toString()));
		//licencia.setFechaEstadistica(new Date());   Que fecha le ponemos?
		licencia.setFechaCarga(viewBM.getFechaCarga().getValue());
		licencia.setLugarDeEmision(viewBM.getCmbLugar().getValue().toString());
		licencia.setEstadoExistencia(true);
		
		
		
		
		
		PersonaFisica persona = (PersonaFisica)licenciaTable.getItemProperty("personaFisica").getValue();	
		PersonaFisica personaUpdate = new PersonaFisica();
		
		licencia.setPersonaFisica(personaUpdate);	
		
		personaUpdate.setId(persona.getId());
		personaUpdate.setApellido(viewBM.getTxtApellidoPersona().getValue());
		personaUpdate.setNombre(viewBM.getTxtNombrePersona().getValue());
		personaUpdate.setGenero(viewBM.getCmbGenero().getValue().toString());
		personaUpdate.setEdad(viewBM.getTxtEdad().getValue());
		personaUpdate.setTipoDeDocumento(viewBM.getCmbTipoDocumento().getValue().toString());
		personaUpdate.setNumeroDeDocumento(viewBM.getTxtNumeroDocumento().getValue());
		personaUpdate.setEstadoExistencia(true);
				
		
		
		
		licencia.setClaseDeCarnet(new ArrayList<ClaseDeCarnet>());
		
		Collection<ClaseDeCarnet> selectedItemsA = (Collection<ClaseDeCarnet>) viewBM.getOptClaseCarneA().getValue();
		Collection<ClaseDeCarnet> selectedItemsB = (Collection<ClaseDeCarnet>) viewBM.getOptClaseCarneB().getValue();
		Collection<ClaseDeCarnet> selectedItemsC = (Collection<ClaseDeCarnet>) viewBM.getOptClaseCarneC().getValue();
		Collection<ClaseDeCarnet> selectedItemsD = (Collection<ClaseDeCarnet>) viewBM.getOptClaseCarneD().getValue();
		Collection<ClaseDeCarnet> selectedItemsE = (Collection<ClaseDeCarnet>) viewBM.getOptClaseCarneE().getValue();
		Collection<ClaseDeCarnet> selectedItemsF = (Collection<ClaseDeCarnet>) viewBM.getOptClaseCarneF().getValue();
		Collection<ClaseDeCarnet> selectedItemsG = (Collection<ClaseDeCarnet>) viewBM.getOptClaseCarneG().getValue();
		
		licencia.getClaseDeCarnet().clear();
		
		if(selectedItemsA.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsA.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsB.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsB.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsC.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsC.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsD.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsD.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsE.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsE.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsF.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsF.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		if(selectedItemsG.size() != 0) {
			Iterator<ClaseDeCarnet> iterator = selectedItemsG.iterator();
			while (iterator.hasNext()) {
			    
				ClaseDeCarnet cdc = iterator.next();
				licencia.getClaseDeCarnet().add(cdc);
			}
		}
		
		licencia.setTipoDeCarnet((TipoDeCarnet) viewBM.getCmbTipoDeCarne().getValue());
		
		
		int rta = service.modificarLicencia(usuario, licencia);
		
		if(rta !=0) {
			viewBM.modificarLicenciaOK();			
			licenciaTable.getItemProperty("fechaCarga").setValue(licencia.getFechaCarga());		
			licenciaTable.getItemProperty("lugarDeEmision").setValue(licencia.getLugarDeEmision());
			licenciaTable.getItemProperty("tipoDeCarnet.nombreTipoDeCarnet").setValue(licencia.getTipoDeCarnet().getNombreTipoDeCarnet());
			licenciaTable.getItemProperty("personaFisica.nombre").setValue(licencia.getPersonaFisica().getNombre());
			licenciaTable.getItemProperty("personaFisica.apellido").setValue(licencia.getPersonaFisica().getApellido());
			licenciaTable.getItemProperty("personaFisica.numeroDeDocumento").setValue(licencia.getPersonaFisica().getNumeroDeDocumento());
			licenciaTable.getItemProperty("claseDeCarnet").setValue(licencia.getClaseDeCarnet());
			limpiarModificacion();	
		}else {
			viewBM.modificarLicenciaError();
		}
		
		
		
	}

	@Override
	public void eliminarLicencias() {
		
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");
		Item licenciaTable = viewBM.getFilterTablaLicencia().getItem(viewBM.getFilterTablaLicencia().getValue());		

		LicenciaConducir licencia = new LicenciaConducir();
		licencia.setId(Integer.valueOf(licenciaTable.getItemProperty("id").getValue().toString()));

		
		if(service.eliminarLicencia(usuario, licencia) != 0) {
			viewBM.eliminarLicenciasOK();
			limpiarModificacion();
		}else viewBM.eliminarLicenciasError(); 
		

		
		
		
	}

	@Override
	public void limpiarModificacion() {
		
		viewBM.getDtfFechaDesde().clear();
		viewBM.getDtfFechaHasta().clear();
		//viewBM.getFilterTablaLicencia().getContainerDataSource().removeAllItems();
		//viewBM.getFilterTablaLicencia().removeAllItems();
		
		
		viewBM.getFechaCarga().clear();
		viewBM.getCmbLugar().clear();
		viewBM.getTxtApellidoPersona().clear();
		viewBM.getTxtNombrePersona().clear();
		viewBM.getCmbGenero().clear();
		viewBM.getTxtEdad().clear();
		viewBM.getCmbTipoDocumento().clear();
		viewBM.getTxtNumeroDocumento().clear();
		viewBM.getCmbTipoDeCarne().clear();
		
		
		viewBM.getOptClaseCarneA().setValue(null);
		viewBM.getOptClaseCarneB().setValue(null);
		viewBM.getOptClaseCarneC().setValue(null);
		viewBM.getOptClaseCarneD().setValue(null);
		viewBM.getOptClaseCarneE().setValue(null);
		viewBM.getOptClaseCarneF().setValue(null);
		viewBM.getOptClaseCarneG().setValue(null);
		
		viewBM.getFilterTablaLicencia().setValue(null);
		
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void generarReportes() {
		
		
		SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat formatVista = new SimpleDateFormat("dd-MM-yyyy");
		
		
		//(vlp.fechaCarga BETWEEN $P{fechaDesdePrincipal} AND $P{fechaHastaPrincipal})
		
		//String parameter1 = formatSQL.format(viewR.getDtfFechaDesde().getValue());
		//String parameter2 = formatSQL.format(viewR.getDtfFechaHasta().getValue());
		String parameter3 = viewR.getCmbLugarEmision().getValue().toString().toUpperCase();
		String filter = "";
	
		if(parameter3.equalsIgnoreCase("general")){
			
			filter = "vlp.fechaCarga BETWEEN '"+formatSQL.format(viewR.getDtfFechaDesde().getValue())+"' " 
					+ "and '"+formatSQL.format(viewR.getDtfFechaHasta().getValue())+"' ";
			
			parameter3 = "general";
			
		
			
		}else {
		
			filter = "vlp.fechaCarga BETWEEN '"+formatSQL.format(viewR.getDtfFechaDesde().getValue())+"' " 
					+ "and '"+formatSQL.format(viewR.getDtfFechaHasta().getValue())+"' "
							+ "and vlp.lugarEmision = '"+viewR.getCmbLugarEmision().getValue().toString()+"';";
		}
		
		Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"
				+ "parameter1="+formatVista.format(viewR.getDtfFechaDesde().getValue())+"&parameter2="+
				formatVista.format(viewR.getDtfFechaHasta().getValue())+"&parameter3="+
				parameter3+"&filter="+ filter), "_blank", false);
		
		
		viewR.getDtfFechaDesde().clear();
		viewR.getDtfFechaHasta().clear();
		viewR.getCmbLugarEmision().clear();

	
	}


		
			
		
		
}
