package com.mcr.stats.presenter;

import java.io.Serializable;
import java.util.Date;

import com.mcr.stats.Ihandler.IhandlerLayProyecto;
import com.mcr.stats.ViewOperaciones.Proyecto.LayProyecto;
import com.mcr.stats.ViewOperaciones.Proyecto.LayProyectoBM;
import com.mcr.stats.model.CategoriaProyecto;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.ResponsablePliego;
import com.mcr.stats.model.Sector;
import com.mcr.stats.model.TipoDeContratacion;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.servicios.ServicioProyecto;
import com.vaadin.data.Item;
import com.vaadin.ui.UI;

public class PresenterProyecto implements Serializable, IhandlerLayProyecto {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LayProyecto view;
	private LayProyectoBM viewBM;
	private ServicioProyecto service;
	
	
	public PresenterProyecto(LayProyecto view, ServicioProyecto service) {
		
		this.view = view;
		this.service = service;	
	}
	
	public PresenterProyecto(LayProyectoBM view, ServicioProyecto service) {
		
		this.viewBM = view;
		this.service = service;	
	}
	
	
	@Override
	public void limpiar() {
		

		view.getCmbCategorias().clear();
		view.getCmbResponsablePliego().clear();
		view.getCmbSector().clear();
		view.getCmbTipoContratacion().clear();
		view.getProgressBar().clear();
		view.getTxtBeneficiarios().clear();
		view.getTxtConexiones().clear();
		view.getTxtDetalleUbicacion().clear();
		view.getTxtLatitud().clear();
		view.getTxtLongitud().clear();
		view.getTxtManzanas().clear();
		view.getTxtMemoriaDescriptiva().clear();
		view.getTxtMetrosCuadrados().clear();
		view.getTxtMetrosLineales().clear();
		view.getTxtNombreDeLaObra().clear();
		view.getTxtParcelas().clear();
		view.getTxtPresupuestoOficial().clear();
		view.getTxtSubCategoria().clear();
		view.getTxtSubSector().clear();
		view.getDtfFechaElevacion().clear();
		view.getProgressBar().clear();

		
		 
		
		
	}

	@Override
	public void guardar() {
		
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");
		
		Proyecto proyecto = new Proyecto();
				
		proyecto.setCategoriaProyecto((CategoriaProyecto) view.getCmbCategorias().getValue());
		proyecto.setSubCategoria(view.getTxtSubCategoria().getValue());
		proyecto.setNombreDeLaObra(view.getTxtNombreDeLaObra().getValue());
		proyecto.setSector((Sector)view.getCmbSector().getValue());
		proyecto.setSubSector(view.getTxtSubSector().getValue());
		proyecto.setLatitud(view.getTxtLatitud().getValue());
		proyecto.setLongitud(view.getTxtLongitud().getValue());
		proyecto.setDetalleUbicacion(view.getTxtDetalleUbicacion().getValue());
		proyecto.setMemoriaDescriptivaDeLaObra(view.getTxtMemoriaDescriptiva().getValue());
		proyecto.setMtsLineales(view.getTxtMetrosLineales().getValue());
		proyecto.setMtsCuadrados(view.getTxtMetrosCuadrados().getValue());
		proyecto.setConexiones(view.getTxtConexiones().getValue());
		proyecto.setBeneficiarios(view.getTxtBeneficiarios().getValue());
		proyecto.setManzanas(view.getTxtManzanas().getValue());
		proyecto.setParcelas(view.getTxtParcelas().getValue());
		proyecto.setTipoDeContratacion((TipoDeContratacion)view.getCmbTipoContratacion().getValue());
		proyecto.setPresupuestoOficial(view.getTxtPresupuestoOficial().getValue());
		proyecto.setResponsablePliego((ResponsablePliego)view.getCmbResponsablePliego().getValue());
		proyecto.setFechaDeElevacion(view.getDtfFechaElevacion().getValue());
		
		
		if(view.getCargadorDeArchivosPlano().getFile() != null) {
			proyecto.setPlano(view.getCargadorDeArchivosPlano().getFile().getName());
			System.out.println("plano:" + proyecto.getPlano());
		} else proyecto.setPlano("No posee información");


		if(view.getCargadorDeArchivosPliego().getFile() != null) {
			proyecto.setPliegoLicitatorio(view.getCargadorDeArchivosPliego().getFile().getName());
			System.out.println("pliego:" + proyecto.getPliegoLicitatorio());
		} else proyecto.setPliegoLicitatorio("No posee información");

		if(view.getCargadorDeArchivosCotizacion().getFile() != null) {
			proyecto.setPlanillaCotizacion(view.getCargadorDeArchivosCotizacion().getFile().getName());
			System.out.println("planilla coti:" + proyecto.getPlanillaCotizacion());
		} else proyecto.setPlanillaCotizacion("No posee información");


		if(view.getCargadorDeArchivosOtros().getFile() != null) {
			proyecto.setOtroDocumento(view.getCargadorDeArchivosOtros().getFile().getName());
			System.out.println("otros:" + proyecto.getOtroDocumento());
		} else proyecto.setOtroDocumento("No posee información");



		proyecto.setEstadoExistencia(true);		
		
		
		int rta = service.guardarProyecto(usuario, proyecto);
		
		if(rta != 0) {
			view.guardarProyectoOK();
			limpiar();
			view.getCmbCategorias().focus();
		}else view.guardarProyectoError();
		
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modificar() {
		
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");	
		
		//Item itemProyecto = viewBM.getFilterTablaProyectos().getItem(viewBM.getFilterTablaProyectos().getValue());
		Item itemProyecto = viewBM.getCmbProyectos().getItem(viewBM.getCmbProyectos().getValue());

		System.out.println(itemProyecto.getItemProperty("idProyecto").getValue().toString());
		
		Proyecto proyecto = new Proyecto();
		
		proyecto.setIdProyecto(Integer.valueOf(itemProyecto.getItemProperty("idProyecto").getValue().toString()));
		proyecto.setCategoriaProyecto((CategoriaProyecto) viewBM.getCmbCategorias().getValue());
		proyecto.setSubCategoria(viewBM.getTxtSubCategoria().getValue());
		proyecto.setNombreDeLaObra(viewBM.getTxtNombreDeLaObra().getValue());
		proyecto.setSector((Sector)viewBM.getCmbSector().getValue());
		proyecto.setSubSector(viewBM.getTxtSubSector().getValue());
		proyecto.setLatitud(viewBM.getTxtLatitud().getValue());
		proyecto.setLongitud(viewBM.getTxtLongitud().getValue());
		proyecto.setDetalleUbicacion(viewBM.getTxtDetalleUbicacion().getValue());
		proyecto.setMemoriaDescriptivaDeLaObra(viewBM.getTxtMemoriaDescriptiva().getValue());
		proyecto.setMtsLineales(viewBM.getTxtMetrosLineales().getValue());
		proyecto.setMtsCuadrados(viewBM.getTxtMetrosCuadrados().getValue());
		proyecto.setConexiones(viewBM.getTxtConexiones().getValue());
		proyecto.setBeneficiarios(viewBM.getTxtBeneficiarios().getValue());
		proyecto.setManzanas(viewBM.getTxtManzanas().getValue());
		proyecto.setParcelas(viewBM.getTxtParcelas().getValue());
		proyecto.setTipoDeContratacion((TipoDeContratacion)viewBM.getCmbTipoContratacion().getValue());
		proyecto.setPresupuestoOficial(viewBM.getTxtPresupuestoOficial().getValue());
		proyecto.setResponsablePliego((ResponsablePliego)viewBM.getCmbResponsablePliego().getValue());
		proyecto.setFechaDeElevacion(viewBM.getDtfFechaElevacion().getValue());


		//System.out.println("file: "+viewBM.getCargadorDeArchivosPlano().getFile().getName());
		//System.out.println("file: "+viewBM.getCargadorDeArchivosPliego().getFile());
		//System.out.println("file: "+viewBM.getCargadorDeArchivosCotizacion().getFile());
		//System.out.println("file: "+viewBM.getCargadorDeArchivosOtros().getFile());



		if(!viewBM.getCargadorDeArchivosPlano().getFile().getName().equalsIgnoreCase("No posee información")) {
			proyecto.setPlano(viewBM.getCargadorDeArchivosPlano().getFile().getName());
			//System.out.println("plano:" + proyecto.getPlano());
		} else proyecto.setPlano("No posee información");


		if(!viewBM.getCargadorDeArchivosPliego().getFile().getName().equalsIgnoreCase("No posee información")) {
			proyecto.setPliegoLicitatorio(viewBM.getCargadorDeArchivosPliego().getFile().getName());
			//System.out.println("pliego:" + proyecto.getPliegoLicitatorio());
		} else proyecto.setPliegoLicitatorio("No posee información");

		if(!viewBM.getCargadorDeArchivosCotizacion().getFile().getName().equalsIgnoreCase("No posee información")) {
			proyecto.setPlanillaCotizacion(viewBM.getCargadorDeArchivosCotizacion().getFile().getName());
			//System.out.println("planilla coti:" + proyecto.getPlanillaCotizacion());
		} else proyecto.setPlanillaCotizacion("No posee información");


		if(!viewBM.getCargadorDeArchivosOtros().getFile().getName().equalsIgnoreCase("No posee información")) {
			proyecto.setOtroDocumento(viewBM.getCargadorDeArchivosOtros().getFile().getName());
			//System.out.println("otros:" + proyecto.getOtroDocumento());
		} else proyecto.setOtroDocumento("No posee información");


/*
		if(viewBM.getCargadorDeArchivosPlano().getFile() != null) {
			proyecto.setPlano(viewBM.getCargadorDeArchivosPlano().getFile().getName());
			System.out.println("plano:" + proyecto.getPlano());
		} else proyecto.setPlano("");


		if(viewBM.getCargadorDeArchivosPliego().getFile() != null) {
			proyecto.setPliegoLicitatorio(viewBM.getCargadorDeArchivosPliego().getFile().getName());
			System.out.println("pliego:" + proyecto.getPliegoLicitatorio());
		} else proyecto.setPliegoLicitatorio("");

		if(viewBM.getCargadorDeArchivosCotizacion().getFile() != null) {
			proyecto.setPlanillaCotizacion(viewBM.getCargadorDeArchivosCotizacion().getFile().getName());
			System.out.println("planilla coti:" + proyecto.getPlanillaCotizacion());
		} else proyecto.setPlanillaCotizacion("");


		if(viewBM.getCargadorDeArchivosOtros().getFile() != null) {
			proyecto.setOtroDocumento(viewBM.getCargadorDeArchivosOtros().getFile().getName());
			System.out.println("otros:" + proyecto.getOtroDocumento());
		} else proyecto.setOtroDocumento("");
*/





//		System.out.println("plano:" + proyecto.getPlano());
//		System.out.println("pliego:" + proyecto.getPliegoLicitatorio());
//		System.out.println("planilla coti:" + proyecto.getPlanillaCotizacion());
//		System.out.println("otros:" + proyecto.getOtroDocumento());


		//if(viewBM.getCargadorDeArchivos().getFile() != null) {
		//	proyecto.setPlano(viewBM.getCargadorDeArchivos().getFile().getName());
		//} else proyecto.setPlano("");
		//proyecto.setEstadoExistencia(true);
		

		int rta = service.modificarProyecto(usuario, proyecto);
		
		if(rta !=0) {
			viewBM.modificarProyectoOK();	
			limpiarBM();
			itemProyecto.getItemProperty("categoriaProyecto").setValue(proyecto.getCategoriaProyecto());			
			itemProyecto.getItemProperty("subCategoria").setValue(proyecto.getSubCategoria());
			itemProyecto.getItemProperty("nombreDeLaObra").setValue(proyecto.getNombreDeLaObra());
			itemProyecto.getItemProperty("sector").setValue(proyecto.getSector());
			itemProyecto.getItemProperty("subSector").setValue(proyecto.getSubSector());
			itemProyecto.getItemProperty("latitud").setValue(proyecto.getLatitud());
			itemProyecto.getItemProperty("longitud").setValue(proyecto.getLongitud());
			itemProyecto.getItemProperty("detalleUbicacion").setValue(proyecto.getDetalleUbicacion());
			itemProyecto.getItemProperty("memoriaDescriptivaDeLaObra").setValue(proyecto.getMemoriaDescriptivaDeLaObra());
			itemProyecto.getItemProperty("mtsLineales").setValue(proyecto.getMtsLineales());
			itemProyecto.getItemProperty("mtsCuadrados").setValue(proyecto.getMtsCuadrados());
			itemProyecto.getItemProperty("conexiones").setValue(proyecto.getConexiones());
			itemProyecto.getItemProperty("beneficiarios").setValue(proyecto.getBeneficiarios());
			itemProyecto.getItemProperty("manzanas").setValue(proyecto.getManzanas());
			itemProyecto.getItemProperty("parcelas").setValue(proyecto.getParcelas());
			itemProyecto.getItemProperty("tipoDeContratacion").setValue(proyecto.getTipoDeContratacion());
			itemProyecto.getItemProperty("presupuestoOficial").setValue(proyecto.getPresupuestoOficial());
			itemProyecto.getItemProperty("responsablePliego").setValue(proyecto.getResponsablePliego());
			itemProyecto.getItemProperty("fechaDeElevacion").setValue(proyecto.getFechaDeElevacion());

			itemProyecto.getItemProperty("pliegoLicitatorio").setValue(proyecto.getPliegoLicitatorio());
			itemProyecto.getItemProperty("planillaCotizacion");
			itemProyecto.getItemProperty("otroDocumento");




			//itemProyecto.getItemProperty("plano").setValue(viewBM.getCargadorDeArchivos().getFile().getName());
			
			//licenciaTable.getItemProperty("fechaCarga").setValue(licencia.getFechaCarga());		
			//licenciaTable.getItemProperty("lugarDeEmision").setValue(licencia.getLugarDeEmision());
			//licenciaTable.getItemProperty("tipoDeCarnet.nombreTipoDeCarnet").setValue(licencia.getTipoDeCarnet().getNombreTipoDeCarnet());
			//licenciaTable.getItemProperty("personaFisica.nombre").setValue(licencia.getPersonaFisica().getNombre());
			//licenciaTable.getItemProperty("personaFisica.apellido").setValue(licencia.getPersonaFisica().getApellido());
		//	licenciaTable.getItemProperty("personaFisica.numeroDeDocumento").setValue(licencia.getPersonaFisica().getNumeroDeDocumento());
			//licenciaTable.getItemProperty("claseDeCarnet").setValue(licencia.getClaseDeCarnet());
			//limpiarModificacion();	
		}else {
			viewBM.modificarProyectoError();
		}
		
		
		
	}

	@Override
	public void eliminar() {
		
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");			
		Item itemProyecto = viewBM.getFilterTablaProyectos().getItem(viewBM.getFilterTablaProyectos().getValue());
		
		Proyecto proyecto = new Proyecto();		
		proyecto.setIdProyecto(Integer.valueOf(itemProyecto.getItemProperty("idProyecto").getValue().toString()));
		
		int rta = service.eliminarProyecto(usuario, proyecto);
		
		if(rta !=0) {
			viewBM.eliminarProyectoOK();	
			limpiarBM();			
		}else {
			viewBM.eliminarProyectoError();
		}
		
		
		
	}

	@Override
	public void limpiarBM() {
		
		viewBM.getTxtNombreDeLaObra().clear();
		viewBM.getCmbCategorias().clear();
		viewBM.getTxtSubCategoria().clear();
		viewBM.getCmbSector().clear();
		viewBM.getTxtSubSector().clear();
		viewBM.getTxtLatitud().clear();
		viewBM.getTxtLongitud().clear();
		viewBM.getTxtDetalleUbicacion().clear();
		viewBM.getTxtMemoriaDescriptiva().clear();
		viewBM.getTxtMetrosLineales().clear();
		viewBM.getTxtMetrosCuadrados().clear();
		viewBM.getTxtConexiones().clear();
		viewBM.getTxtBeneficiarios().clear();
		viewBM.getTxtManzanas().clear();
		viewBM.getTxtParcelas().clear();
		viewBM.getCmbTipoContratacion().clear();
		viewBM.getTxtPresupuestoOficial().clear();
		viewBM.getCmbResponsablePliego().clear();
		viewBM.getDtfFechaElevacion().clear();
		//viewBM.getFilterTablaProyectos().clear();
		viewBM.getCmbProyectos().clear();
		viewBM.getLinkPlano().setCaption("No posee plano");
		viewBM.getLinkPlano().setResource(null);
		
		
	}

}
