package com.mcr.stats.ViewOperaciones.Licencia;

import java.util.Date;

import com.mcr.stats.Ihandler.IhandlerLayLicencia;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class LayLicenciaReporte extends VerticalLayout implements ClickListener, ValueChangeListener  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateField dtfFechaDesde;
	private DateField dtfFechaHasta;
	private ComboBox cmbLugarEmision;
	private Button btnGenerarResporte;
	
	private IhandlerLayLicencia handler;

	public LayLicenciaReporte() {
	
		
		setMargin(true);
		setSpacing(true);
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		
		lay.addComponent(generarFechaDesde());
		lay.addComponent(generarFechaHasta());	
		
		
		addComponent(lay);
		addComponent(generarCmbLugarEmision());
		addComponent(generarComandos());
		
		//setComponentAlignment(getBtnGenerarResporte(), Alignment.BOTTOM_RIGHT);
		
		
		
		
	}

	private Component generarCmbLugarEmision() {
		
		
		setCmbLugarEmision(new ComboBox("<big><strong>Centro de emision:"));
		getCmbLugarEmision().setCaptionAsHtml(true);
		getCmbLugarEmision().setNewItemsAllowed(false);
		getCmbLugarEmision().setNullSelectionAllowed(false);
		getCmbLugarEmision().addItem("MOSCONI");
		getCmbLugarEmision().addItem("VIAMONTE");
		getCmbLugarEmision().addItem("JUBILADOS");
		getCmbLugarEmision().addItem("GENERAL");
		return getCmbLugarEmision();
	}

	private Component generarComandos() {
		
		setBtnGenerarResporte(new Button("Generar reporte"));
		getBtnGenerarResporte().addClickListener(this);
		return getBtnGenerarResporte();
	}

	private Component generarFechaHasta() {
		
		setDtfFechaHasta(new DateField("<big><strong>Hasta"));
		getDtfFechaHasta().setCaptionAsHtml(true);
		getDtfFechaHasta().setRequired(true);
		getDtfFechaHasta().setDateFormat("dd/MM/yyyy");
		getDtfFechaHasta().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaHasta().setRangeEnd(new Date());
		return getDtfFechaHasta();
	}

	private Component generarFechaDesde() {
		
		setDtfFechaDesde(new DateField("<big><strong>Desde:"));
		getDtfFechaDesde().setCaptionAsHtml(true);
		getDtfFechaDesde().setRequired(true);
		getDtfFechaDesde().setDateFormat("dd/MM/yyyy");
		getDtfFechaDesde().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaDesde().setRangeEnd(new Date());	
		return getDtfFechaDesde();
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
	
		if(event.getSource() == getBtnGenerarResporte()) {
			
			if(getCmbLugarEmision().getValue() != null) {
			
			if(!getDtfFechaDesde().isValid() ||
					!getDtfFechaHasta().isValid() || 
					!fechasDesdeDesdeMayor() ||
					!getCmbLugarEmision().isValid()) {
				
				Notification noti = new Notification("Atencion", "Verificar ingresos", Type.ERROR_MESSAGE);
				noti.setDelayMsec(3000);
				noti.setPosition(Position.MIDDLE_CENTER);
				noti.show(Page.getCurrent());
				
			}else {
				getHandler().generarReportes();
			}
		}else Notification.show("Seleccione un centro de emisión", Type.ERROR_MESSAGE);
		}
		
	}
	
	
	private boolean fechasDesdeDesdeMayor() {
		
		long timeDesde = getDtfFechaDesde().getValue().getTime();
		long timeHasta = getDtfFechaHasta().getValue().getTime();		
		
		if(timeDesde > timeHasta) {
			return false;
		}return true;
	}


	public DateField getDtfFechaDesde() {
		return dtfFechaDesde;
	}

	public void setDtfFechaDesde(DateField dtfFechaDesde) {
		this.dtfFechaDesde = dtfFechaDesde;
	}

	public DateField getDtfFechaHasta() {
		return dtfFechaHasta;
	}

	public void setDtfFechaHasta(DateField dtfFechaHasta) {
		this.dtfFechaHasta = dtfFechaHasta;
	}

	public Button getBtnGenerarResporte() {
		return btnGenerarResporte;
	}

	public void setBtnGenerarResporte(Button btnGenerarResporte) {
		this.btnGenerarResporte = btnGenerarResporte;
	}

	public IhandlerLayLicencia getHandler() {
		return handler;
	}

	public void setHandler(IhandlerLayLicencia handler) {
		this.handler = handler;
	}

	public ComboBox getCmbLugarEmision() {
		return cmbLugarEmision;
	}

	public void setCmbLugarEmision(ComboBox cmbLugarEmision) {
		this.cmbLugarEmision = cmbLugarEmision;
	}

}
