package com.mcr.stats.ViewOperaciones.Licencia;


import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.vaadin.dialogs.ConfirmDialog;

import com.mcr.stats.Ihandler.IhandlerLayLicencia;
import com.mcr.stats.dao.DaoCarne;
import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.servicios.ServicioCarne;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


public class LayLicencia extends VerticalLayout implements ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private DateField fechaCarga;
    private ComboBox cmbLugar;
    private TextField txtApellidoPersona;
    private TextField txtNombrePersona;
    private ComboBox cmbTipoDocumento;
    private TextField txtNumeroDocumento;
    private ComboBox cmbGenero;
    private TextField txtEdad;
    //private ComboBox cmbClaseCarne;
    private TextField txtValor;
    private OptionGroup optClaseCarneA;
    private OptionGroup optClaseCarneB;
    private OptionGroup optClaseCarneC;
    private OptionGroup optClaseCarneD;
    private OptionGroup optClaseCarneE;
    private OptionGroup optClaseCarneF;
    private OptionGroup optClaseCarneG;   
    private ComboBox cmbTipoDeCarne;
    
    
    private Button btnLimpiar;
    private Button btnGuardar;
    private IhandlerLayLicencia handler;
    
    public LayLicencia() {
		
    	setMargin(true);
    	setSpacing(true);
    	setSizeFull();
    	
    	
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
    	
    	HorizontalLayout layComandos = new HorizontalLayout();
    	layComandos.setSpacing(true);
    	layComandos.addComponent(generarBtnLimpiar());
    	layComandos.addComponent(generarBtnAceptar());
    	
    	
    	
    	//addComponent(generarFechaCarga());
    	addComponent(generarFechaLugar());
    	addComponent(grid);
    	addComponent(layDoc);
    	//addComponent(generarTxtValor());
    	addComponent(generarChkgCarne());
    	addComponent(generarCmbTipoCarne());
    	addComponent(layComandos);    	   	
    	cargarChkCarne();
    	
    	
    	//escuchadores
    	
    	
    	
        
        
      
    }
    
    	
    	public LayLicencia(Usuario usuario) {
		
    		
    		
    		setMargin(true);
        	setSpacing(true);
        	setSizeFull();
        	
        	
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
        	
        	HorizontalLayout layComandos = new HorizontalLayout();
        	layComandos.setSpacing(true);
        	layComandos.addComponent(generarBtnLimpiar());
        	layComandos.addComponent(generarBtnAceptar());
        	
        	
        	
        	//addComponent(generarFechaCarga());
        	//System.out.println("ssssssssssssss");
    		setCaptionAsHtml(true);
    		setCaption("<strong><big>Alta licencia de conducir" );
        	addComponent(generarFechaLugar());
        	addComponent(grid);
        	addComponent(layDoc);
        	//addComponent(generarTxtValor());
        	addComponent(generarChkgCarne());
        	addComponent(generarCmbTipoCarne());
        	addComponent(layComandos);    	   	
        	cargarChkCarne();
        	
        	
        //	System.out.println("Perfil usuario: " + usuario.getPerfil().recuperarPerfil());
    		
    		
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



	private Component generarCmbGenero() {

		setCmbGenero(new ComboBox("<big><strong>Sexo:"));
		getCmbGenero().setCaptionAsHtml(true);
		getCmbGenero().setNullSelectionAllowed(false);
		getCmbGenero().addItem("FEMENINO");
		getCmbGenero().addItem("MASCULINO");
		return getCmbGenero();
	}



	private Component generarBtnAceptar() {
		
		setBtnGuardar(new Button("Guardar"));
		getBtnGuardar().addClickListener(this);
		return getBtnGuardar();
	}



	private Component generarBtnLimpiar() {
		
		setBtnLimpiar(new Button("Limpiar"));
		getBtnLimpiar().addClickListener(this);
		return getBtnLimpiar();
	}



	private Component generarCmbTipoCarne() {

		setCmbTipoDeCarne(new ComboBox("<big><strong>Tipo de Carné:",ServicioCarne.getInstance().getTipoCarne()));
		getCmbTipoDeCarne().setCaptionAsHtml(true);
		getCmbTipoDeCarne().setRequired(true);
		getCmbTipoDeCarne().setNullSelectionAllowed(false);
		getCmbTipoDeCarne().setItemCaptionPropertyId("nombreTipoDeCarnet");
		getCmbTipoDeCarne().setWidth("400px");
		return getCmbTipoDeCarne();

	}



//	private Component generarCmbCarne() {
//		
//		setCmbClaseCarne(new ComboBox("Clase de Carne:",ServicioCarne.getInstance().getClaseCarne()));
//		getCmbClaseCarne().setRequired(true);
//		getCmbClaseCarne().setNullSelectionAllowed(false);
//		getCmbClaseCarne().setItemCaptionPropertyId("claseDeCarnet");
//		return getCmbClaseCarne();
//	}



	private Component generarTxtNumeroDeDocumento() {
		
		setTxtNumeroDocumento(new TextField("<big><strong>Numero de Documento:"));
		getTxtNumeroDocumento().setCaptionAsHtml(true);
		getTxtNumeroDocumento().setRequired(true);
		getTxtNumeroDocumento().setMaxLength(8);
		return getTxtNumeroDocumento();
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





	public DateField getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(DateField fechaCarga) {
		this.fechaCarga = fechaCarga;
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

	public Button getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(Button btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button btnGuardar) {
		this.btnGuardar = btnGuardar;
	}



	


	public ComboBox getCmbTipoDeCarne() {
		return cmbTipoDeCarne;
	}



	public void setCmbTipoDeCarne(ComboBox cmbTipoDeCarne) {
		this.cmbTipoDeCarne = cmbTipoDeCarne;
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



	public TextField getTxtValor() {
		return txtValor;
	}



	public void setTxtValor(TextField txtValor) {
		this.txtValor = txtValor;
	}



	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == getBtnGuardar()) {
			
			
			
			if(     !getFechaCarga().isValid() ||
					!getTxtNombrePersona().isValid() ||
					!getTxtApellidoPersona().isValid() ||
					!getCmbGenero().isValid() ||
					!getTxtEdad().isValid() ||
					!getCmbLugar().isValid() ||
					!getCmbTipoDocumento().isValid() ||
					!getTxtNumeroDocumento().isValid() ||
					!verificarChecks()){
								
				Notification noti = new Notification("Atencion", "Datos mal ingresados, incompletos y/o incorrectos", Type.ERROR_MESSAGE);
				noti.setDelayMsec(3000);
				noti.setPosition(Position.MIDDLE_CENTER);
				noti.show(Page.getCurrent());
			}else {
				
				ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea guardar "
						+ "este registro?", "SI", "NO", new ConfirmDialog.Listener() {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog rta) {
					
						if(rta.isConfirmed()){
							handler.guardar();
						}
					}
					});				
			}
		}
		
		if(event.getSource() == getBtnLimpiar()) {
		
			handler.limpiar();
		}
		
		
	}

	


	@SuppressWarnings("unchecked")
	private boolean verificarChecks() {
		
		Collection<ClaseDeCarnet> selectedItemsA = (Collection<ClaseDeCarnet>) getOptClaseCarneA().getValue();
		Collection<ClaseDeCarnet> selectedItemsB = (Collection<ClaseDeCarnet>) getOptClaseCarneB().getValue();
		Collection<ClaseDeCarnet> selectedItemsC = (Collection<ClaseDeCarnet>) getOptClaseCarneC().getValue();
		Collection<ClaseDeCarnet> selectedItemsD = (Collection<ClaseDeCarnet>) getOptClaseCarneD().getValue();
		Collection<ClaseDeCarnet> selectedItemsE = (Collection<ClaseDeCarnet>) getOptClaseCarneE().getValue();
		Collection<ClaseDeCarnet> selectedItemsF = (Collection<ClaseDeCarnet>) getOptClaseCarneF().getValue();
		
		
		if (selectedItemsA.size() == 0 &&
		selectedItemsB.size() == 0 &&
		selectedItemsC.size() == 0 &&
		selectedItemsD.size() == 0 &&
		selectedItemsE.size() == 0 &&
		selectedItemsF.size() == 0) {					
			 return false;
		}	
		return true;
	}


	public IhandlerLayLicencia getHandler() {
		return handler;
	}


	public void setHandler(IhandlerLayLicencia handler) {
		this.handler = handler;
	}


	public void guardarLicenciaOK() {
		
		Notification noti = new Notification("Registro guardardo correctamente", Type.ASSISTIVE_NOTIFICATION);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
	}


	public void guardarLicenciaError() {
		
		Notification.show("Registro no ha podido ser guardado", Type.ERROR_MESSAGE);
		
	}


	public ComboBox getCmbLugar() {
		return cmbLugar;
	}


	public void setCmbLugar(ComboBox cmbLugar) {
		this.cmbLugar = cmbLugar;
	}


	public OptionGroup getOptClaseCarneG() {
		return optClaseCarneG;
	}


	public void setOptClaseCarneG(OptionGroup optClaseCarneG) {
		this.optClaseCarneG = optClaseCarneG;
	}


	public void guardarLicenciaPersonaMismoDia() {
		
		Notification noti = new Notification("Registro no guardado. Posible carga repetida de Licencia para la misma persona. Verificar", Type.ERROR_MESSAGE);
		noti.setDelayMsec(5000);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}
    
    
    
    
    
    
}
