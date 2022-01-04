package com.mcr.stats.presenter;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.mcr.stats.Ihandler.IhandlerLayReporte;
import com.mcr.stats.ViewOperaciones.Reportes.LayReportes;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.servicios.ServicioLicencia;
import com.mcr.stats.servicios.ServicioProyecto;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

public class PresenterReportes implements Serializable, IhandlerLayReporte {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String site = "https://www.comodoro.gov.ar";
	//public static String site = "http://localhost:8080";
	
	private LayReportes view;
	private ServicioLicencia serviceLicencia;
	private ServicioProyecto serviceProyecto;
	
	
	
	public PresenterReportes(LayReportes view, ServicioLicencia serviceLicencia) {
		
		this.view = view;
		this.serviceLicencia = serviceLicencia;


	}

	public PresenterReportes(LayReportes view, ServicioProyecto serviceProyecto) {

		this.view = view;
		this.serviceProyecto = serviceProyecto;
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void generarReporte() {
		
		
		SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat formatVista = new SimpleDateFormat("dd-MM-yyyy");
	
		
		//getCmbSelectorReportes().addItem("ESTADISTICA GENERAL");
		//getCmbSelectorReportes().addItem("DETALLE LICENCIAS");
		
		
		
		if(view.getCmbSelectorReportes().getValue().toString().equalsIgnoreCase("ESTADISTICA GENERAL")) {	
		
			String parameter3 = view.getCmbLugarEmisionDetalle().getValue().toString().toUpperCase();
			String filter = "";
			String jasperFile = "";
			
			if(parameter3.equalsIgnoreCase("general")) {
			
				filter = "vlp.fechaCarga BETWEEN '"+formatSQL.format(view.getDtfFechaDesdeDetalle().getValue())+"' " 
					+ "and '"+formatSQL.format(view.getDtfFechaHastaDetalle().getValue())+"' ";
				parameter3 = "general";
			}else {
				filter = "vlp.fechaCarga BETWEEN '"+formatSQL.format(view.getDtfFechaDesdeDetalle().getValue())+"' " 
					+ "and '"+formatSQL.format(view.getDtfFechaHastaDetalle().getValue())+"' "
							+ "and vlp.lugarEmision = '"+view.getCmbLugarEmisionDetalle().getValue().toString()+"';";
			}
			jasperFile = "report_licencias.jasper";


			Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"+
					"parameter1="+formatVista.format(view.getDtfFechaDesdeDetalle().getValue())+
					"&jasperFile="+jasperFile+
					"&parameter2="+formatVista.format(view.getDtfFechaHastaDetalle().getValue())+
					"&parameter3="+parameter3+
					"&filter="+ filter), "_blank", false);
		}
		
		if(view.getCmbSelectorReportes().getValue().toString().equalsIgnoreCase("DETALLE LICENCIAS")) {	
		
			String parameter3 = view.getCmbLugarEmisionDetalle().getValue().toString().toUpperCase();
			String filter = "";
			String jasperFile = "";
			
			if(parameter3.equalsIgnoreCase("general")) {
				
				filter = "lc.fechaCarga BETWEEN '"+formatSQL.format(view.getDtfFechaDesdeDetalle().getValue())+"' " 
						+ "and '"+formatSQL.format(view.getDtfFechaHastaDetalle().getValue())+"' "
								+ "AND var.estadoExistencia = 1 order BY lc.fechaCarga desc, p.apellido";
			}else {
				filter = "lc.fechaCarga BETWEEN '"+formatSQL.format(view.getDtfFechaDesdeDetalle().getValue())+"' " 
						+ "and '"+formatSQL.format(view.getDtfFechaHastaDetalle().getValue())+"' "
								+ "and lc.lugarEmision = '"+view.getCmbLugarEmisionDetalle().getValue().toString()+"'"
										+ "AND var.estadoExistencia = 1 " + 
										"order BY lc.fechaCarga desc, p.apellido";					 
			}
			
			jasperFile = "reporte_licencia_detalle.jasper";
			Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"
					+ "parameter1="+formatVista.format(view.getDtfFechaDesdeDetalle().getValue())+"&jasperFile="+jasperFile+"&parameter2="+
					formatVista.format(view.getDtfFechaHastaDetalle().getValue())+"&parameter3="+
					parameter3+"&filter="+ filter), "_blank", false);	
			
			
			//Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"
			//		+ "parameter1="+formatVista.format(view.getDtfFechaDesdeDetalle().getValue())+"&jasperFile="+jasperFile+"&parameter2="+
			//		formatVista.format(view.getDtfFechaHastaDetalle().getValue())+"&parameter3="+
			//		parameter3+"&filter="+ filter), "_blank", false);
		}
		view.getDtfFechaDesdeDetalle().clear();
		view.getDtfFechaHastaDetalle().clear();
		view.getCmbLugarEmisionDetalle().clear();
		view.getCmbSelectorReportes().clear();
	}


	@Override
	public void validar() {
		
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");
		String centro = "";
		
		
		if(view.getCmbLugarEmisionDetalle().getValue().toString().equalsIgnoreCase("viamonte")) {
			centro = "viamonte";
		}
		if(view.getCmbLugarEmisionDetalle().getValue().toString().equalsIgnoreCase("mosconi")) {
			centro = "mosconi";
		}
		if(view.getCmbLugarEmisionDetalle().getValue().toString().equalsIgnoreCase("jubilados")) {
			centro = "jubilados";
		}
		
		int rta = serviceLicencia.validar(usuario, view.getDtfFechaDesdeDetalle().getValue(),
				view.getDtfFechaHastaDetalle().getValue(),
				centro); 
		
		if( rta == 1) {
			
			view.validarOK();			
			view.getDtfFechaDesdeDetalle().clear();
			view.getDtfFechaHastaDetalle().clear();
			view.getCmbSelectorReportes().clear();
			view.getCmbLugarEmisionDetalle().clear();
			
		}else  if (rta == 0 ) {
					view.validarError();
			   }else view.validarErrorAprobacion();
		
		
		
		
	}

	@Override
	public void generarReporteObras() {

		SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatVista = new SimpleDateFormat("dd-MM-yyyy");

		if(view.getCmbSelectorReportesObras().getValue().toString().
				equalsIgnoreCase("CANTIDAD DE OBRAS POR CATEGORIA")) {

			String jasperFile = "op_main_cantidadObras.jasper";
			String filter = "op.fechaDeElevacion BETWEEN '"+formatSQL.format(view.getDtfFechaDesdeDetalle().getValue())+"' "
					+ "and '"+formatSQL.format(view.getDtfFechaHastaDetalle().getValue())+"' "
					+ "AND  op.estadoExistencia = 1";

			Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"+
					"parameter1="+formatVista.format(view.getDtfFechaDesdeDetalle().getValue())+
					"&jasperFile="+jasperFile+"&parameter2="+
					formatVista.format(view.getDtfFechaHastaDetalle().getValue())+"&filter="+ filter),
					"_blank", false);
		}


		if(view.getCmbSelectorReportesObras().getValue().toString().
				equalsIgnoreCase("CANTIDAD DE OBRAS POR SECTOR")) {


			String jasperFile = "op_main_cantidadPorSector.jasper";
			String filter = "op.fechaDeElevacion BETWEEN '"+formatSQL.format(view.getDtfFechaDesdeDetalle().getValue())+"' "
					+ "and '"+formatSQL.format(view.getDtfFechaHastaDetalle().getValue())+"' "
					+ "AND  op.estadoExistencia = 1 "
					+ "ORDER BY ops.sector asc";


			Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"+
							"parameter1="+formatVista.format(view.getDtfFechaDesdeDetalle().getValue())+
							"&jasperFile="+jasperFile+"&parameter2="+
							formatVista.format(view.getDtfFechaHastaDetalle().getValue())+"&filter="+ filter),
					"_blank", false);
		}

		if(view.getCmbSelectorReportesObras().getValue().toString().
				equalsIgnoreCase("PRESUPUESTO OFICIAL POR CATEGORIA")) {

			String jasperFile = "op_main_presuOficialCategoria.jasper";
			String filter = "op.fechaDeElevacion BETWEEN '"+formatSQL.format(view.getDtfFechaDesdeDetalle().getValue())+"' "
					+ "and '"+formatSQL.format(view.getDtfFechaHastaDetalle().getValue())+"' "
					+ "AND  op.estadoExistencia = 1 "
					//+ "ORDER BY opc.nombreCategoria, opt.tipoProcedimiento asc";
					+ "ORDER BY opc.nombreCategoria";


			Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"+
					"parameter1="+formatVista.format(view.getDtfFechaDesdeDetalle().getValue())+
					"&jasperFile="+jasperFile+"&parameter2="+
					formatVista.format(view.getDtfFechaHastaDetalle().getValue())+"&filter="+ filter), "_blank", false);


		}
		if(view.getCmbSelectorReportesObras().getValue().toString().equalsIgnoreCase("PRESPUESTO OFICIAL POR TIPO CONTRATACION")) {

			String jasperFile = "";
			jasperFile = "op_main_presuTipoContratacion.jasper";
			Page.getCurrent().open(new ExternalResource(site+"/stats/Reportes?"+
					"jasperFile="+jasperFile), "_blank", false);
		}



	}


}
