package com.mcr.stats.ViewOperaciones.Licencia;

import com.mcr.stats.Ihandler.IhandlerLayLicencia;
import com.mcr.stats.dao.DaoCarne;
import com.mcr.stats.dao.DaoLicencia;
import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.ClasePersona;
import com.mcr.stats.model.TipoDeCarnet;
import com.mcr.stats.model.cargas.LicenciaConducir;
import com.mcr.stats.servicios.ServicioCarne;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.tepi.filtertable.FilterTable;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

public class LayLicenciaBM extends VerticalLayout implements ClickListener, ValueChangeListener {

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateField dtfFechaDesde;
	private DateField dtfFechaHasta;
	private FilterTable filterTablaLicencia;
	private DateField fechaCarga;
	private ComboBox cmbLugar;
	private TextField txtApellidoPersona;
	private TextField txtNombrePersona;
	private ComboBox cmbTipoDocumento;
	private TextField txtNumeroDocumento;
	private ComboBox cmbGenero;
	private TextField txtEdad;
	private TextField txtValor;
	private OptionGroup optClaseCarneA;
	private OptionGroup optClaseCarneB;
	private OptionGroup optClaseCarneC;
	private OptionGroup optClaseCarneD;
	private OptionGroup optClaseCarneE;
	private OptionGroup optClaseCarneF;
	private OptionGroup optClaseCarneG;
	private ComboBox cmbTipoDeCarne;
	
	private Button btnBuscarLicencias;
	private Button btnLimpiarBusqueda;
	private Button btnModificarLicencias;
	private Button btnEliminarLicencias;
	
	
	private BeanFieldGroup<LicenciaConducir> beanFieldGroup; 	
	private BeanItemContainer<LicenciaConducir> container;
	private IhandlerLayLicencia handler;
	
	public LayLicenciaBM() {
		
		setMargin(true);
    	setSpacing(true);
    	setSizeFull();

		addComponent(new Label("<font color='white'><strong><big>Baja/Modificacion de Licencias", ContentMode.HTML));
    	setContainer(new BeanItemContainer<>(LicenciaConducir.class,new ArrayList<LicenciaConducir>()));
    	setBeanFieldGroup(new BeanFieldGroup<>(LicenciaConducir.class));
    	
    	GridLayout grid = new GridLayout(4,1);
    	grid.setSpacing(true);
    	grid.addComponent(generarTxtApellido());
    	grid.addComponent(generarTxtNombre());
    	grid.addComponent(generarCmbGenero());
    	grid.addComponent(generarTxtEdad());
    	
    	
    	HorizontalLayout layDoc = new HorizontalLayout();
    	layDoc.setSpacing(true);
    	layDoc.addComponent(generarCmbTipoDeDocumento());
    	layDoc.addComponent(generarTxtNumeroDeDocumento());   
    	
    	
    	//grid.addComponent(generarFechaLugar());
    	
    	
    	
    	
    	
    	
    	addComponent(generarFechas());
    	addComponent(generarTabla());    	
       	addComponent(generarFechaLugar());
    	addComponent(grid);
    	addComponent(layDoc);
    	addComponent(generarChkgCarne());
    	addComponent(generarCmbTipoCarne());    	
    	cargarChkCarne();
    	
    	
    	
    	//bindeo
    	getBeanFieldGroup().bind(getFechaCarga(), "fechaCarga");
    	getBeanFieldGroup().bind(getCmbLugar(), "lugarDeEmision");
    	getBeanFieldGroup().bind(getTxtApellidoPersona(), "personaFisica.apellido");
		getBeanFieldGroup().bind(getTxtNombrePersona(), "personaFisica.nombre");
		getBeanFieldGroup().bind(getTxtEdad(), "personaFisica.edad");
		getBeanFieldGroup().bind(getCmbGenero(), "personaFisica.genero");
		getBeanFieldGroup().bind(getCmbTipoDocumento(), "personaFisica.tipoDeDocumento");
		getBeanFieldGroup().bind(getTxtNumeroDocumento(), "personaFisica.numeroDeDocumento");
		getBeanFieldGroup().bind(getCmbTipoDeCarne(), "tipoDeCarnet"); //es el objeto entero!!!
    	
    	
		
	
		
		//comandso
		addComponent(generarComandos());
		
		
		
    	
	}
	
	private Component generarComandos() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		
		setBtnModificarLicencias(new Button("Guardar"));
		getBtnModificarLicencias().addClickListener(this);
		setBtnEliminarLicencias(new Button("Eliminar"));
		getBtnEliminarLicencias().addClickListener(this);
		lay.addComponent(getBtnModificarLicencias());
		lay.addComponent(getBtnEliminarLicencias());
		
		return lay;
	}

	private Component generarCmbTipoCarne() {

		setCmbTipoDeCarne(new ComboBox("<big><strong>Tipo de Carné:",ServicioCarne.getInstance().getTipoCarne()));
		getCmbTipoDeCarne().setCaptionAsHtml(true);
		getCmbTipoDeCarne().setRequired(true);
		getCmbTipoDeCarne().setNullSelectionAllowed(false);
		getCmbTipoDeCarne().setItemCaptionPropertyId("nombreTipoDeCarnet");
		getCmbTipoDeCarne().setWidth("400px");
		getCmbTipoDeCarne().setNewItemsAllowed(false);
		getCmbTipoDeCarne().setImmediate(true);
		return getCmbTipoDeCarne();

	}

	
	private void cargarChkCarne() {
		
		  
        
        Iterator<ClaseDeCarnet> iterador = DaoCarne.getInstance().getClaseCarne().iterator();
        
        while(iterador.hasNext()) {       
        	
        	ClaseDeCarnet cdc = iterador.next();
        	//System.out.println("Clase de carnet:" + cdc.getClaseDeCarnet());
        	if(cdc.getClaseDeCarnet().matches("[^A]*")) {
        		if(cdc.getClaseDeCarnet().matches("[^B]*")) {
        			if(cdc.getClaseDeCarnet().matches("[^C]*")) {
        				if(cdc.getClaseDeCarnet().matches("[^D]*")) {
        					if(cdc.getClaseDeCarnet().matches("[^E]*")) {
        						if(cdc.getClaseDeCarnet().matches("[^F]*")) {
        							if(cdc.getClaseDeCarnet().matches("[^G]*")) {
            						}
        							else {
        								getOptClaseCarneG().getContainerDataSource().addItem(cdc);
            							getOptClaseCarneG().setItemCaptionPropertyId("claseDeCarnet");
        							}
        						}
        						else {
        							//System.out.println("Cotiene F:" + cdc.getClaseDeCarnet());
        							getOptClaseCarneF().getContainerDataSource().addItem(cdc);
        							getOptClaseCarneF().setItemCaptionPropertyId("claseDeCarnet");
        							
        						}
        					}else {
        						//System.out.println("Cotiene E:" + cdc.getClaseDeCarnet());
        						getOptClaseCarneE().getContainerDataSource().addItem(cdc);
        						getOptClaseCarneE().setItemCaptionPropertyId("claseDeCarnet");}
        				}else {
        					//System.out.println("Cotiene D:" + cdc.getClaseDeCarnet());
        					getOptClaseCarneD().getContainerDataSource().addItem(cdc);
        					getOptClaseCarneD().setItemCaptionPropertyId("claseDeCarnet");}
        			}else {
        				//System.out.println("Cotiene C:" + cdc.getClaseDeCarnet());
        				getOptClaseCarneC().getContainerDataSource().addItem(cdc);
        				getOptClaseCarneC().setItemCaptionPropertyId("claseDeCarnet");}
        		}else {
        			//System.out.println("Cotiene B:" + cdc.getClaseDeCarnet());
        			getOptClaseCarneB().getContainerDataSource().addItem(cdc);
        			getOptClaseCarneB().setItemCaptionPropertyId("claseDeCarnet");}      		
        	}else {
        		//System.out.println("Cotiene A:" + cdc.getClaseDeCarnet());
        		getOptClaseCarneA().getContainerDataSource().addItem(cdc);
        		getOptClaseCarneA().setItemCaptionPropertyId("claseDeCarnet");}
        } 
	
}


	
	private Component generarChkgCarne() {
	
	
	HorizontalLayout lay = new HorizontalLayout();
	lay.setSpacing(true);
	//lay.setCaption("Clases de carné");
	
		setOptClaseCarneA(new OptionGroup("<big><strong>Clases de carné:", new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class)));
		getOptClaseCarneA().setCaptionAsHtml(true);
		getOptClaseCarneA().setItemCaptionPropertyId("claseDeCarnet");
		getOptClaseCarneA().setMultiSelect(true);
		
		setOptClaseCarneB(new OptionGroup("", new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class)));
		getOptClaseCarneB().setItemCaptionPropertyId("claseDeCarnet");
		getOptClaseCarneB().setMultiSelect(true);
		
		setOptClaseCarneC(new OptionGroup("", new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class)));
		getOptClaseCarneC().setItemCaptionPropertyId("claseDeCarnet");
		getOptClaseCarneC().setMultiSelect(true);
		
		setOptClaseCarneD(new OptionGroup("", new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class)));
		getOptClaseCarneD().setItemCaptionPropertyId("claseDeCarnet");
		getOptClaseCarneD().setMultiSelect(true);
		
		setOptClaseCarneE(new OptionGroup("", new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class)));
		getOptClaseCarneE().setItemCaptionPropertyId("claseDeCarnet");
		getOptClaseCarneE().setMultiSelect(true);
		
		setOptClaseCarneF(new OptionGroup("", new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class)));
		getOptClaseCarneF().setItemCaptionPropertyId("claseDeCarnet");
		getOptClaseCarneF().setMultiSelect(true);
		
		setOptClaseCarneG(new OptionGroup("", new BeanItemContainer<ClaseDeCarnet>(ClaseDeCarnet.class)));
		getOptClaseCarneG().setItemCaptionPropertyId("claseDeCarnet");
		getOptClaseCarneG().setMultiSelect(true);
		
	
		lay.addComponent(getOptClaseCarneA());
		lay.addComponent(getOptClaseCarneB());
		lay.addComponent(getOptClaseCarneC());
		lay.addComponent(getOptClaseCarneD());
		lay.addComponent(getOptClaseCarneE());
		lay.addComponent(getOptClaseCarneF());
		lay.addComponent(getOptClaseCarneG());
		
	return lay;
}
	
	private Component generarTxtNumeroDeDocumento() {
		
		setTxtNumeroDocumento(new TextField("<big><strong>Número de Documento:"));
		getTxtNumeroDocumento().setCaptionAsHtml(true);
		getTxtNumeroDocumento().setRequired(true);
		return getTxtNumeroDocumento();
	}
	
	private Component generarCmbGenero() {

		setCmbGenero(new ComboBox("<big><strong>Sexo:"));
		getCmbGenero().setCaptionAsHtml(true);
		getCmbGenero().setNullSelectionAllowed(false);
		getCmbGenero().addItem("FEMENINO");
		getCmbGenero().addItem("MASCULINO");
		return getCmbGenero();
	}
	
	private Component generarCmbTipoDeDocumento() {
		
		setCmbTipoDocumento(new ComboBox("<big><strong>Tipo de Documento:"));
		getCmbTipoDocumento().setCaptionAsHtml(true);
		getCmbTipoDocumento().setRequired(true);
		getCmbTipoDocumento().setNullSelectionAllowed(false);
		getCmbTipoDocumento().addItem("DU");
		getCmbTipoDocumento().addItem("DNI");
		getCmbTipoDocumento().addItem("PASAPORTE");
		return getCmbTipoDocumento();
	}



	private Component generarTxtEdad() {
		
		setTxtEdad(new TextField("<big><strong>Edad:"));
		getTxtEdad().setCaptionAsHtml(true);
		getTxtEdad().setRequired(true);
		return getTxtEdad();
	}



	private Component generarTxtNombre() {
		
		setTxtNombrePersona(new TextField("<big><strong>Nombre:"));
		getTxtNombrePersona().setCaptionAsHtml(true);
		getTxtNombrePersona().setRequired(true);
		return getTxtNombrePersona();
	}



	private Component generarTxtApellido() {
		
		setTxtApellidoPersona(new TextField("<big><strong>Apellido:"));
		getTxtApellidoPersona().setCaptionAsHtml(true);
		getTxtApellidoPersona().focus();
		getTxtApellidoPersona().setRequired(true);
		return getTxtApellidoPersona();
	}
	
	private Component generarFechaLugar() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		
		setFechaCarga(new DateField("<big><strong>Fecha Carga:"));
		getFechaCarga().setCaptionAsHtml(true);
		getFechaCarga().setRequired(true);
		getFechaCarga().setDateFormat("dd/MM/yyyy");
		getFechaCarga().setValue(new Date());	
		getFechaCarga().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getFechaCarga().setRangeEnd(new Date());
		
		
		setCmbLugar(new ComboBox("<big><strong>Centro de emision:"));
		getCmbLugar().setCaptionAsHtml(true);
		getCmbLugar().setNewItemsAllowed(false);
		getCmbLugar().setNullSelectionAllowed(false);
		getCmbLugar().addItem("MOSCONI");
		getCmbLugar().addItem("VIAMONTE");
		getCmbLugar().addItem("JUBILADOS");
		
		lay.addComponent(getFechaCarga());
		lay.addComponent(getCmbLugar());
		
	return lay;
	
	}
	

	private Component generarFechas() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		
		setDtfFechaDesde(new DateField("<big><strong>Fecha Desde:"));
		getDtfFechaDesde().setCaptionAsHtml(true);
		getDtfFechaDesde().setRequired(true);
		getDtfFechaDesde().setDateFormat("dd/MM/yyyy");
		getDtfFechaDesde().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaDesde().setRangeEnd(new Date());
		
		setDtfFechaHasta(new DateField("<big><strong>Fecha Hasta:"));
		getDtfFechaHasta().setCaptionAsHtml(true);
		getDtfFechaHasta().setRequired(true);
		getDtfFechaHasta().setDateFormat("dd/MM/yyyy");
		getDtfFechaHasta().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaHasta().setRangeEnd(new Date());
		
		
		setBtnBuscarLicencias(new Button("Buscar"));
		getBtnBuscarLicencias().addClickListener(this);
		setBtnLimpiarBusqueda(new Button("Limpiar"));
		getBtnLimpiarBusqueda().addClickListener(this);
		
		lay.addComponent(getDtfFechaDesde());
		lay.addComponent(getDtfFechaHasta());
		lay.addComponent(getBtnBuscarLicencias());	
		lay.addComponent(getBtnLimpiarBusqueda());
		lay.setComponentAlignment(getBtnBuscarLicencias(), Alignment.BOTTOM_RIGHT);
		lay.setComponentAlignment(getBtnLimpiarBusqueda(), Alignment.BOTTOM_RIGHT);
		
		return lay;
	}

	private Component generarTabla() {
		
		setFilterTablaLicencia(new FilterTable("<big><strong>Licencias"));
		getFilterTablaLicencia().setCaptionAsHtml(true);
		getContainer().addNestedContainerProperty("personaFisica.nombre");	
		getContainer().addNestedContainerProperty("personaFisica.numeroDeDocumento");
		getContainer().addNestedContainerProperty("personaFisica.apellido");
		getContainer().addNestedContainerProperty("personaFisica.nombre");
		getContainer().addNestedContainerProperty("tipoDeCarnet.nombreTipoDeCarnet");
		getFilterTablaLicencia().setContainerDataSource(getContainer());
			
		
		
		getFilterTablaLicencia().setCaptionAsHtml(true);
		getFilterTablaLicencia().setSizeFull();		
		getFilterTablaLicencia().setSelectable(true);
		//getFilterTablaLicencia().setFilterGenerator(new DemoFilterGenerator());
		getFilterTablaLicencia().setFilterBarVisible(true);
		getFilterTablaLicencia().setPageLength(8);
		
		
		getFilterTablaLicencia().setVisibleColumns(new Object[]{"fechaCarga","lugarDeEmision",
																"tipoDeCarnet.nombreTipoDeCarnet",
																"personaFisica.apellido",
																"personaFisica.nombre",
																"personaFisica.numeroDeDocumento"});
		
		getFilterTablaLicencia().setColumnHeaders(new String[]{"Fecha","Emision","Tipo Carne","Apellido","Nombre","N~ de Documento"});
		
		
		
		getFilterTablaLicencia().setConverter("fechaCarga", new StringToDateConverter(){			
			private static final long serialVersionUID = 1L;
			protected java.text.DateFormat getFormat(java.util.Locale locale) {				
					return DateFormat.getDateInstance(DateFormat.SHORT, locale);
				}
			});

		getFilterTablaLicencia().addValueChangeListener(this);
		return getFilterTablaLicencia();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == getBtnBuscarLicencias()) {
			
			if(!getDtfFechaDesde().isValid() ||
					!getDtfFechaHasta().isValid() || 
					!fechasDesdeDesdeMayor()) {
				
				Notification noti = new Notification("Atencion", "Fechas mal ingresadas, incompletas y/o incorrectas", Type.ERROR_MESSAGE);
				noti.setDelayMsec(3000);
				noti.setPosition(Position.MIDDLE_CENTER);
				noti.show(Page.getCurrent());
				
			}else {
				getHandler().buscarLicencias();
			}
		}
		if(event.getSource() == getBtnLimpiarBusqueda()) {
			
			handler.limpiarBusquedas();
		}

		if(event.getSource() == getBtnModificarLicencias()) {
			
			
			ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea modificar "
					+ "este registro?", "SI", "NO", new ConfirmDialog.Listener() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public void onClose(ConfirmDialog rta) {
				
					if(rta.isConfirmed()){
						handler.modificarLicencias();
					}
				}
				});
			
			
		}

		if(event.getSource() == getBtnEliminarLicencias()) {
	
			if(getFilterTablaLicencia().getValue() != null) {
			
			ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea eliminar "
					+ "este registro?", "SI", "NO", new ConfirmDialog.Listener() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public void onClose(ConfirmDialog rta) {
				
					if(rta.isConfirmed()){
						handler.eliminarLicencias();
					}
				}
				});			
		    }
		}
	}

	private boolean fechasDesdeDesdeMayor() {
		
		long timeDesde = getDtfFechaDesde().getValue().getTime();
		long timeHasta = getDtfFechaHasta().getValue().getTime();		
		
		if(timeDesde > timeHasta) {
			return false;
		}return true;
	}

	public FilterTable getFilterTablaLicencia() {
		return filterTablaLicencia;
	}

	public void setFilterTablaLicencia(FilterTable filterTablaLicencia) {
		this.filterTablaLicencia = filterTablaLicencia;
	}

	public DateField getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(DateField fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public ComboBox getCmbLugar() {
		return cmbLugar;
	}

	public void setCmbLugar(ComboBox cmbLugar) {
		this.cmbLugar = cmbLugar;
	}

	public TextField getTxtApellidoPersona() {
		return txtApellidoPersona;
	}

	public void setTxtApellidoPersona(TextField txtApellidoPersona) {
		this.txtApellidoPersona = txtApellidoPersona;
	}

	public TextField getTxtNombrePersona() {
		return txtNombrePersona;
	}

	public void setTxtNombrePersona(TextField txtNombrePersona) {
		this.txtNombrePersona = txtNombrePersona;
	}

	public ComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(ComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public TextField getTxtNumeroDocumento() {
		return txtNumeroDocumento;
	}

	public void setTxtNumeroDocumento(TextField txtNumeroDocumento) {
		this.txtNumeroDocumento = txtNumeroDocumento;
	}

	public ComboBox getCmbGenero() {
		return cmbGenero;
	}

	public void setCmbGenero(ComboBox cmbGenero) {
		this.cmbGenero = cmbGenero;
	}

	public TextField getTxtEdad() {
		return txtEdad;
	}

	public void setTxtEdad(TextField txtEdad) {
		this.txtEdad = txtEdad;
	}

	public TextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(TextField txtValor) {
		this.txtValor = txtValor;
	}

	public OptionGroup getOptClaseCarneA() {
		return optClaseCarneA;
	}

	public void setOptClaseCarneA(OptionGroup optClaseCarneA) {
		this.optClaseCarneA = optClaseCarneA;
	}

	public OptionGroup getOptClaseCarneB() {
		return optClaseCarneB;
	}

	public void setOptClaseCarneB(OptionGroup optClaseCarneB) {
		this.optClaseCarneB = optClaseCarneB;
	}

	public OptionGroup getOptClaseCarneC() {
		return optClaseCarneC;
	}

	public void setOptClaseCarneC(OptionGroup optClaseCarneC) {
		this.optClaseCarneC = optClaseCarneC;
	}

	public OptionGroup getOptClaseCarneD() {
		return optClaseCarneD;
	}

	public void setOptClaseCarneD(OptionGroup optClaseCarneD) {
		this.optClaseCarneD = optClaseCarneD;
	}

	public OptionGroup getOptClaseCarneE() {
		return optClaseCarneE;
	}

	public void setOptClaseCarneE(OptionGroup optClaseCarneE) {
		this.optClaseCarneE = optClaseCarneE;
	}

	public OptionGroup getOptClaseCarneF() {
		return optClaseCarneF;
	}

	public void setOptClaseCarneF(OptionGroup optClaseCarneF) {
		this.optClaseCarneF = optClaseCarneF;
	}

	public OptionGroup getOptClaseCarneG() {
		return optClaseCarneG;
	}

	public void setOptClaseCarneG(OptionGroup optClaseCarneG) {
		this.optClaseCarneG = optClaseCarneG;
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

	public Button getBtnBuscarLicencias() {
		return btnBuscarLicencias;
	}

	public void setBtnBuscarLicencias(Button btnBuscarLicencias) {
		this.btnBuscarLicencias = btnBuscarLicencias;
	}

	public IhandlerLayLicencia getHandler() {
		return handler;
	}

	public void setHandler(IhandlerLayLicencia handler) {
		this.handler = handler;
	}

	public BeanItemContainer<LicenciaConducir> getContainer() {
		return container;
	}

	public void setContainer(BeanItemContainer<LicenciaConducir> container) {
		this.container = container;
	}

	public Button getBtnLimpiarBusqueda() {
		return btnLimpiarBusqueda;
	}

	public void setBtnLimpiarBusqueda(Button btnLimpiarBusqueda) {
		this.btnLimpiarBusqueda = btnLimpiarBusqueda;
	}

	public ComboBox getCmbTipoDeCarne() {
		return cmbTipoDeCarne;
	}

	public void setCmbTipoDeCarne(ComboBox cmbTipoDeCarne) {
		this.cmbTipoDeCarne = cmbTipoDeCarne;
	}

	public BeanFieldGroup<LicenciaConducir> getBeanFieldGroup() {
		return beanFieldGroup;
	}

	public void setBeanFieldGroup(BeanFieldGroup<LicenciaConducir> beanFieldGroup) {
		this.beanFieldGroup = beanFieldGroup;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		editarArea((LicenciaConducir)getFilterTablaLicencia().getValue());
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void editarArea(LicenciaConducir licencia) {
		
		if(licencia != null) {
			
			BeanItem<LicenciaConducir> item = new BeanItem<>(licencia);
			getBeanFieldGroup().setItemDataSource(item);
			
			
			
			getOptClaseCarneA().setValue(null);
			getOptClaseCarneB().setValue(null);
			getOptClaseCarneC().setValue(null);
			getOptClaseCarneD().setValue(null);
			getOptClaseCarneE().setValue(null);
			getOptClaseCarneF().setValue(null);
			getOptClaseCarneG().setValue(null);
			
			
			Iterator<ClaseDeCarnet> iterador  = licencia.getClaseDeCarnet().iterator();
			while(iterador.hasNext()) {
				
				ClaseDeCarnet cdc = iterador.next();
	        	//System.out.println("Clase de carnetLLLLLLLLLL:" + cdc.getClaseDeCarnet());
	        	if(cdc.getClaseDeCarnet().matches("[^A]*")) {
	        		if(cdc.getClaseDeCarnet().matches("[^B]*")) {
	        			if(cdc.getClaseDeCarnet().matches("[^C]*")) {
	        				if(cdc.getClaseDeCarnet().matches("[^D]*")) {
	        					if(cdc.getClaseDeCarnet().matches("[^E]*")) {
	        						if(cdc.getClaseDeCarnet().matches("[^F]*")) {
	        							if(cdc.getClaseDeCarnet().matches("[^G]*")) {
	            						}
	        							else {
	        								Iterator<ClaseDeCarnet> iteradorOptG = (Iterator)getOptClaseCarneG().getItemIds().iterator();	        		
	    	        		        		while (iteradorOptG.hasNext()) {	        			
	    	        		        			ClaseDeCarnet cdcOptG = iteradorOptG. next();
	    	        		        		if(cdcOptG.getClaseDeCarnet().equalsIgnoreCase(cdc.getClaseDeCarnet())) {	        		
	    	        		        			     getOptClaseCarneG().select(cdcOptG);	        			 	        			     
	    	        		        			}
	    	        		        		}	    	        								
	        							}
	        						}
	        						else {
	        							
	        							Iterator<ClaseDeCarnet> iteradorOptF = (Iterator)getOptClaseCarneF().getItemIds().iterator();	        		
		        		        		while (iteradorOptF.hasNext()) {	        			
		        		        			ClaseDeCarnet cdcOptF = iteradorOptF. next();
		        		        			if(cdcOptF.getClaseDeCarnet().equalsIgnoreCase(cdc.getClaseDeCarnet())) {	        		
		        		        			     getOptClaseCarneF().select(cdcOptF);	        			 	        			     
		        		        			}
		        		        		}	    
	        						}
	        					}else {
	        					Iterator<ClaseDeCarnet> iteradorOptE = (Iterator)getOptClaseCarneE().getItemIds().iterator();	        		
	        		        		while (iteradorOptE.hasNext()) {	        			
	        		        			ClaseDeCarnet cdcOptE = iteradorOptE. next();
	        		        			if(cdcOptE.getClaseDeCarnet().equalsIgnoreCase(cdc.getClaseDeCarnet())) {	        		
	        		        			     getOptClaseCarneE().select(cdcOptE);	        			 	        			     
	        		        			}
	        		        		}	        					
	        					}
	        						
	        				}else {
	        					Iterator<ClaseDeCarnet> iteradorOptD = (Iterator)getOptClaseCarneD().getItemIds().iterator();	        		
	    		        		while (iteradorOptD.hasNext()) {	        			
	    		        			ClaseDeCarnet cdcOptD = iteradorOptD. next();
	    		        		if(cdcOptD.getClaseDeCarnet().equalsIgnoreCase(cdc.getClaseDeCarnet())) {	        		
	    		        			     getOptClaseCarneD().select(cdcOptD);	        			 	        			     
	    		        			}
	    		        		}       				
	        				}
	        			}else {	        				
	        				Iterator<ClaseDeCarnet> iteradorOptC = (Iterator)getOptClaseCarneC().getItemIds().iterator();	        		
			        		while (iteradorOptC.hasNext()) {	        			
			        			ClaseDeCarnet cdcOptC = iteradorOptC.next();
			        		if(cdcOptC.getClaseDeCarnet().equalsIgnoreCase(cdc.getClaseDeCarnet())) {	        		
			        			     getOptClaseCarneC().select(cdcOptC);	        			 	        			     
			        			}
			        		}       			
	        			}
	        		}else {
	        			Iterator<ClaseDeCarnet> iteradorOptB = (Iterator)getOptClaseCarneB().getItemIds().iterator();	        		
		        		while (iteradorOptB.hasNext()) {	        			
		        			ClaseDeCarnet cdcOptB = iteradorOptB. next();		        		
		        			if(cdcOptB.getClaseDeCarnet().equalsIgnoreCase(cdc.getClaseDeCarnet())) {	        		
		        			     getOptClaseCarneB().select(cdcOptB);	        			 	        			     
		        			}
		        		}        		
	        		}        		
	        	}else {	        	
	        		Iterator<ClaseDeCarnet> iteradorOptA = (Iterator)getOptClaseCarneA().getItemIds().iterator();	        		
	        		while (iteradorOptA.hasNext()) {	        			
	        			ClaseDeCarnet cdcOptA = iteradorOptA. next();	        			
	        			if(cdcOptA.getClaseDeCarnet().equalsIgnoreCase(cdc.getClaseDeCarnet())) {	        		
	        			     getOptClaseCarneA().select(cdcOptA);	        			 	        			     
	        			}
	        		}	        
			}
			}
			
		}
		
	}

	public Button getBtnModificarLicencias() {
		return btnModificarLicencias;
	}

	public void setBtnModificarLicencias(Button btnModificarLicencias) {
		this.btnModificarLicencias = btnModificarLicencias;
	}

	public Button getBtnEliminarLicencias() {
		return btnEliminarLicencias;
	}

	public void setBtnEliminarLicencias(Button btnEliminarLicencias) {
		this.btnEliminarLicencias = btnEliminarLicencias;
	}

	public void modificarLicenciaOK() {
		
		//Notification.show("Atención","Licencia de conducir modificada correctamente", Type.ASSISTIVE_NOTIFICATION);
		
		Notification noti = new Notification("Licencia de conducir modificada correctamente", Type.ASSISTIVE_NOTIFICATION);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}

	public void modificarLicenciaError() {
		
		//Notification.show("Atención","Error al modificar licencia de conducir", Type.ERROR_MESSAGE);
		
		Notification noti = new Notification("Error al modificar licencia de conducir", Type.ASSISTIVE_NOTIFICATION);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}

	public void eliminarLicenciasOK() {
		
		Notification noti = new Notification("Licencia de conducir eliminada correctamente", Type.ASSISTIVE_NOTIFICATION);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
		
	}

	public void eliminarLicenciasError() {
		
		Notification noti = new Notification("Error al eliminar licencia de conducir", Type.ASSISTIVE_NOTIFICATION);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());		
	}

}
