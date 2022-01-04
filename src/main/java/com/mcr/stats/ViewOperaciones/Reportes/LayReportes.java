package com.mcr.stats.ViewOperaciones.Reportes;


import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;

import org.vaadin.dialogs.ConfirmDialog;

import com.mcr.stats.Ihandler.IhandlerLayReporte;
import com.mcr.stats.model.Usuario;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

public class LayReportes extends VerticalLayout implements ClickListener{

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//
	private ComboBox cmbSelectorReportes;
	private IhandlerLayReporte handler;
	
	
	//detalle
	private DateField dtfFechaDesdeDetalle;
	private DateField dtfFechaHastaDetalle;
	private ComboBox cmbLugarEmisionDetalle;
	private Button btnGenerarReporte;
	private Button btnValidarReporte;

	//obras
	private ComboBox cmbSelectorReportesObras;
	private Button btnGenerarReporteObras;

	public LayReportes(Usuario usuario) {
		
		setMargin(true);
		setSpacing(true);

		if(usuario.getAccesos().get(0).equalsIgnoreCase("Licencia de conducir")){

			removeAllComponents();

			HorizontalLayout lay = new HorizontalLayout();
			lay.setSpacing(true);
			lay.addComponent(generarFechaDesde());
			lay.addComponent(generarFechaHasta());
			lay.addComponent(generarCmbLugarEmision());
			
			HorizontalLayout layReporte = new HorizontalLayout();
			layReporte.setSpacing(true);
			layReporte.addComponent(generarBtnReporte());
			layReporte.addComponent(generarBtnValidar());

			addComponent(generarSelectorReportes());
			addComponent(lay);
			addComponent(layReporte);
		}

		if(usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas")){

			removeAllComponents();
			HorizontalLayout lay = new HorizontalLayout();
			lay.setSpacing(true);
			lay.addComponent(generarFechaDesde());
			lay.addComponent(generarFechaHasta());
			lay.addComponent(generarCmbSeleccionReporte());

			HorizontalLayout layReporte = new HorizontalLayout();
			layReporte.setSpacing(true);
			layReporte.addComponent(generarBtnReporteObras());
			layReporte.addComponent(generarBtnValidar());

			addComponent(lay);
			addComponent(layReporte);
		}

		if(usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - proyectos")){

			removeAllComponents();
			HorizontalLayout lay = new HorizontalLayout();
			lay.setSpacing(true);
			lay.addComponent(generarFechaDesde());
			lay.addComponent(generarFechaHasta());
			lay.addComponent(generarCmbSeleccionReporte());

			HorizontalLayout layReporte = new HorizontalLayout();
			layReporte.setSpacing(true);
			layReporte.addComponent(generarBtnReporteObras());
			layReporte.addComponent(generarBtnValidar());

			addComponent(lay);
			addComponent(layReporte);
		}
	}

	private Component generarBtnReporteObras() {

		setBtnGenerarReporteObras(new Button("Generar"));
		getBtnGenerarReporteObras().addClickListener(this);
		return getBtnGenerarReporteObras();

	}

	private Component generarCmbSeleccionReporte() {

		setCmbSelectorReportesObras(new ComboBox("<big><strong>Reporte:"));
		getCmbSelectorReportesObras().setRequired(true);
		getCmbSelectorReportesObras().setCaptionAsHtml(true);
		getCmbSelectorReportesObras().setWidth("400px");
		getCmbSelectorReportesObras().addItem("CANTIDAD DE OBRAS POR CATEGORIA");
		getCmbSelectorReportesObras().addItem("CANTIDAD DE OBRAS POR SECTOR");
		getCmbSelectorReportesObras().addItem("PRESUPUESTO OFICIAL POR CATEGORIA");
		getCmbSelectorReportesObras().addItem("PRESPUESTO OFICIAL POR TIPO CONTRATACION");
		getCmbSelectorReportesObras().setNewItemsAllowed(false);
		getCmbSelectorReportesObras().setNullSelectionAllowed(false);
		return getCmbSelectorReportesObras();

	}

	public LayReportes(Usuario usuario, int valorSeleccionado) {

		if(valorSeleccionado == 9){
			removeAllComponents();
		}
		if(valorSeleccionado == 10){

		}
		if(valorSeleccionado == 11){

		}
		if(valorSeleccionado == 12){

		}
	}
	
	private Component generarBtnValidar() {
		
		setBtnValidarReporte(new Button("Validar"));
		getBtnValidarReporte().addClickListener(this);
		return getBtnValidarReporte();
	}

	private boolean fechasDesdeDesdeMayor() {
		
		long timeDesde = getDtfFechaDesdeDetalle().getValue().getTime();
		long timeHasta = getDtfFechaHastaDetalle().getValue().getTime();		
		
		if(timeDesde > timeHasta) {
			return false;
		}return true;
	}
	
	private Component generarBtnReporte() {
		
		setBtnGenerarReporte(new Button("Generar"));
		getBtnGenerarReporte().addClickListener(this);
		return getBtnGenerarReporte();
	}

	private Component generarSelectorReportes() {
		
		setCmbSelectorReportes(new ComboBox("<big><strong>Reporte:"));
		getCmbSelectorReportes().setRequired(true);
		getCmbSelectorReportes().setCaptionAsHtml(true);
		getCmbSelectorReportes().setWidth("300px");
		getCmbSelectorReportes().addItem("DETALLE LICENCIAS");
		getCmbSelectorReportes().addItem("ESTADISTICA GENERAL");
		getCmbSelectorReportes().setNewItemsAllowed(false);
		getCmbSelectorReportes().setNullSelectionAllowed(false);
		return getCmbSelectorReportes();
	}

	
	private Component generarFechaHasta() {
		
		setDtfFechaHastaDetalle(new DateField("<big><strong>Hasta"));
		getDtfFechaHastaDetalle().setCaptionAsHtml(true);
		getDtfFechaHastaDetalle().setRequired(true);
		getDtfFechaHastaDetalle().setDateFormat("dd/MM/yyyy");
		getDtfFechaHastaDetalle().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaHastaDetalle().setRangeEnd(new Date());
		return getDtfFechaHastaDetalle();
	}

	private Component generarFechaDesde() {
		
		setDtfFechaDesdeDetalle(new DateField("<big><strong>Desde:"));
		getDtfFechaDesdeDetalle().setCaptionAsHtml(true);
		getDtfFechaDesdeDetalle().setRequired(true);
		getDtfFechaDesdeDetalle().setDateFormat("dd/MM/yyyy");
		getDtfFechaDesdeDetalle().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaDesdeDetalle().setRangeEnd(new Date());	
		return getDtfFechaDesdeDetalle();
	}

	private Component generarCmbLugarEmision() {
		
		
		setCmbLugarEmisionDetalle(new ComboBox("<big><strong>Centro de emision:"));
		getCmbLugarEmisionDetalle().setRequired(true);
		getCmbLugarEmisionDetalle().setCaptionAsHtml(true);
		getCmbLugarEmisionDetalle().setNewItemsAllowed(false);
		getCmbLugarEmisionDetalle().setNullSelectionAllowed(false);
		getCmbLugarEmisionDetalle().addItem("MOSCONI");
		getCmbLugarEmisionDetalle().addItem("VIAMONTE");
		getCmbLugarEmisionDetalle().addItem("JUBILADOS");
		getCmbLugarEmisionDetalle().addItem("GENERAL");
		return getCmbLugarEmisionDetalle();
	}

	
	@Override
	public void buttonClick(ClickEvent event) {
	
		if(event.getSource() == getBtnGenerarReporte()) {
			
			if(!getDtfFechaDesdeDetalle().isValid() ||
					!getDtfFechaHastaDetalle().isValid() || 
					!fechasDesdeDesdeMayor() ||
					!getCmbLugarEmisionDetalle().isValid()||
					!getCmbSelectorReportes().isValid()) {
				
				Notification noti = new Notification("Atencion", "Verificar ingresos", Type.ERROR_MESSAGE);
				noti.setDelayMsec(2000);
				noti.setPosition(Position.MIDDLE_CENTER);
				noti.show(Page.getCurrent());
				
			}else {
				getHandler().generarReporte();
			}		
		}
		
		if(event.getSource() == getBtnValidarReporte()) {
			
							
			if(!getDtfFechaDesdeDetalle().isValid() ||
					!getDtfFechaHastaDetalle().isValid() || 
					!fechasDesdeDesdeMayor() ||
					!getCmbLugarEmisionDetalle().isValid()||
					!getCmbSelectorReportes().isValid()) {
					
					Notification noti = new Notification("Atencion", "Verificar ingresos", Type.ERROR_MESSAGE);
					noti.setDelayMsec(3000);
					noti.setPosition(Position.MIDDLE_CENTER);
					noti.show(Page.getCurrent());
				}else {
					
					//System.out.println(cmbLugarEmisionDetalle.getValue().toString());
			
					
					
					if(getCmbSelectorReportes().getValue().toString().equalsIgnoreCase("DETALLE LICENCIAS")){
					
					ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea validar los registros para las fechas ingresadas?", "SI", "NO", new ConfirmDialog.Listener() {
						
						private static final long serialVersionUID = 1L;

						@Override
						public void onClose(ConfirmDialog rta) {
						
							if(rta.isConfirmed()){
								handler.validar();
							}
						}
						});		
						
				}else {
				
				Notification noti = new Notification("Atencion", "Solo es posible validar <strong>Detalle de Licencias</strong>", 
						Type.ERROR_MESSAGE,true);
				noti.setDelayMsec(2000);
				noti.setPosition(Position.MIDDLE_CENTER);
				noti.show(Page.getCurrent());
			}
		}
		}

		if(event.getSource() == getBtnGenerarReporteObras()) {

			if(!getDtfFechaDesdeDetalle().isValid() ||
					!getDtfFechaHastaDetalle().isValid() ||
					!fechasDesdeDesdeMayor() ||
					!getCmbSelectorReportesObras().isValid()) {

				Notification noti = new Notification("Atencion", "Verificar ingresos", Type.ERROR_MESSAGE);
				noti.setDelayMsec(2000);
				noti.setPosition(Position.MIDDLE_CENTER);
				noti.show(Page.getCurrent());

			}else {
				getHandler().generarReporteObras();
			}

		}

			
	}

	public Button getBtnGenerarReporteObras() {
		return btnGenerarReporteObras;
	}

	public void setBtnGenerarReporteObras(Button btnGenerarReporteObras) {
		this.btnGenerarReporteObras = btnGenerarReporteObras;
	}

	public ComboBox getCmbSelectorReportesObras() {
		return cmbSelectorReportesObras;
	}

	public void setCmbSelectorReportesObras(ComboBox cmbSelectorReportesObras) {
		this.cmbSelectorReportesObras = cmbSelectorReportesObras;
	}

	public DateField getDtfFechaDesdeDetalle() {
		return dtfFechaDesdeDetalle;
	}

	public void setDtfFechaDesdeDetalle(DateField dtfFechaDesdeDetalle) {
		this.dtfFechaDesdeDetalle = dtfFechaDesdeDetalle;
	}

	public DateField getDtfFechaHastaDetalle() {
		return dtfFechaHastaDetalle;
	}

	public void setDtfFechaHastaDetalle(DateField dtfFechaHastaDetalle) {
		this.dtfFechaHastaDetalle = dtfFechaHastaDetalle;
	}

	public ComboBox getCmbLugarEmisionDetalle() {
		return cmbLugarEmisionDetalle;
	}

	public void setCmbLugarEmisionDetalle(ComboBox cmbLugarEmisionDetalle) {
		this.cmbLugarEmisionDetalle = cmbLugarEmisionDetalle;
	}

	public ComboBox getCmbSelectorReportes() {
		return cmbSelectorReportes;
	}

	public void setCmbSelectorReportes(ComboBox cmbSelectorReportes) {
		this.cmbSelectorReportes = cmbSelectorReportes;
	}

	public Button getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public void setBtnGenerarReporte(Button btnGenerarReporte) {
		this.btnGenerarReporte = btnGenerarReporte;
	}

	public IhandlerLayReporte getHandler() {
		return handler;
	}

	public void setHandler(IhandlerLayReporte handler) {
		this.handler = handler;
	}

	public Button getBtnValidarReporte() {
		return btnValidarReporte;
	}

	public void setBtnValidarReporte(Button btnValidarReporte) {
		this.btnValidarReporte = btnValidarReporte;
	}

	public void validarOK() {
		
		Notification noti = new Notification("Atencion","Licencias validadas <strong>correctamente</strong>", 
				Type.ASSISTIVE_NOTIFICATION,true);
		noti.setDelayMsec(2000);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
	}

	public void validarError() {
		
		Notification noti = new Notification("Atencion", "No es posible validar <strong>Detalle de Licencias</strong>", 
				Type.ERROR_MESSAGE,true);
		noti.setDelayMsec(2000);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}

	public void validarErrorAprobacion() {
		
		Notification noti = new Notification("Atencion", "No es posible validar <strong>Detalle de Licencias</strong> por encontrarse aprobada", 
				Type.ERROR_MESSAGE,true);
		noti.setDelayMsec(2000);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}

}
