package com.mcr.stats.ViewOperaciones.Proyecto;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mcr.stats.model.contratacion.Contratacion;
import com.mcr.stats.servicios.*;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import org.tepi.filtertable.FilterTable;
import org.vaadin.dialogs.ConfirmDialog;

import com.mcr.stats.Ihandler.IhandlerLayProyecto;
import com.mcr.stats.model.CategoriaProyecto;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.ResponsablePliego;
import com.mcr.stats.model.Sector;
import com.mcr.stats.model.TipoDeContratacion;
import com.mcr.stats.model.cargas.LicenciaConducir;
import com.mcr.stats.tools.CargadorArchivos;
import com.vaadin.data.Validator;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LayProyectoBM extends VerticalLayout implements ClickListener, ValueChangeListener {





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DateField dtfFechaDesde;
	private DateField dtfFechaHasta;

	private ComboBox cmbCategorias;
	private TextField txtSubCategoria;
	private TextField txtNombreDeLaObra;
	private ComboBox cmbSector;
	private TextField txtSubSector;
	private TextField txtLatitud;
	private TextField txtLongitud;
	private TextField txtDetalleUbicacion;
	private TextArea txtMemoriaDescriptiva;

	private TextField txtMetrosLineales;
	private TextField txtMetrosCuadrados;
	private TextField txtConexiones;
	private TextField txtBeneficiarios;
	private TextField txtManzanas;
	private TextField txtParcelas;

	private ComboBox cmbTipoContratacion;
	private TextField txtPresupuestoOficial;
	private ComboBox cmbResponsablePliego;

	private Link linkPlano;
	private Link linkPliego;
	private Link linkCotizacion;
	private Link linkOtros;


	//sample = new Link("Google search", new ExternalResource(
	//       "http://www.google.com"));
	//sample.setDescription("Visit google.com");

	private Upload uploadPlanos;
	private DateField dtfFechaElevacion;
	private ProgressBar progressBar;

	private Upload uploadPliegoLicitatorio;
	private Upload uploadPlanillaCotizacion;
	private Upload uploadOtrosDocumentos;

	//private CargadorArchivos cargadorDeArchivos;
	private CargadorArchivos cargadorDeArchivosPlano;
	private CargadorArchivos cargadorDeArchivosPliego;
	private CargadorArchivos cargadorDeArchivosCotizacion;
	private CargadorArchivos cargadorDeArchivosOtros;




	private FilterTable filterTablaProyectos;
	private ComboBox cmbProyectos;
	private BeanItemContainer<Proyecto> container;
	private BeanFieldGroup<Proyecto> beanFieldGroup; 	

	private IhandlerLayProyecto handler;
	
	//comandos
	private Button btnBuscarProyectos;
	private Button btnLimpiarBusqueda;
	private Button btnModificarProyecto;
	private Button btnEliminarProyectos;


	public LayProyectoBM() {

		setMargin(true);
		setSpacing(true);
		setSizeFull();

		setBeanFieldGroup(new BeanFieldGroup<Proyecto>(Proyecto.class));
		addComponent(new Label("<font color='white'><strong><big>Baja/Modificacion de Proyectos", ContentMode.HTML));




		//addComponent(generarFechas());


		HorizontalLayout layUno = new HorizontalLayout();
		layUno.setSpacing(true);		
		layUno.addComponent(generarCmbCategoria());
		layUno.addComponent(generarTxtSubcategoria());

		HorizontalLayout layDos = new HorizontalLayout();
		layDos.setSpacing(true);		
		layDos.addComponent(generarTxtSubSector());
		layDos.addComponent(generarTxtLatitud());
		layDos.addComponent(generarTxtLongitud());

		HorizontalLayout layTres = new HorizontalLayout();
		layTres.setSpacing(true);
		layTres.addComponent(generarTxtMetrosLineales());
		layTres.addComponent(generarTxtMetrosCuadrados());
		layTres.addComponent(generarTxtConexiones());
		layTres.addComponent(generarTxtBeneficiarios());
		layTres.addComponent(generarTxtManzanas());
		layTres.addComponent(generarTxtParcelas());

		HorizontalLayout layCuatro = new HorizontalLayout();
		layCuatro.setSpacing(true);
		layCuatro.addComponent(generarCmbTipoContratacion());
		layCuatro.addComponent(generarTxtPresupuestoOficial());
		layCuatro.addComponent(generarCmbResponsables());


		//addComponent(generarTabla());
		addComponent(generarProyectos());
		addComponent(generarTxtNombreDeLaObra());
		addComponent(layUno);
		addComponent(generarCmbSector());
		addComponent(layDos);
		addComponent(generarTxtDetalleDeLaUbicacion());
		addComponent(generarTxtMemoriaDescriptiva());
		addComponent(layTres);
		addComponent(layCuatro);

		addComponent(new com.vaadin.ui.Label("<hr/>",ContentMode.HTML));		
		addComponent(generarUploadPlanoFechaElevacion());
		addComponent(new com.vaadin.ui.Label("<hr/>",ContentMode.HTML));
		addComponent(generarUploadPliegoLicitatorio());
		addComponent(new com.vaadin.ui.Label("<hr/>", ContentMode.HTML));
		addComponent(generarUploadPlanillaDeCotización());
		addComponent(new com.vaadin.ui.Label("<hr/>", ContentMode.HTML));
		addComponent(generarOtraDocumentacionImportancia());

		addComponent(generarComandos());



		//bindeo
		getBeanFieldGroup().bind(getCmbCategorias(),"categoriaProyecto");
		getBeanFieldGroup().bind(getTxtSubCategoria(), "subCategoria");
		getBeanFieldGroup().bind(getTxtNombreDeLaObra(),"nombreDeLaObra");
		getBeanFieldGroup().bind(getCmbSector(),"sector");
		getBeanFieldGroup().bind(getTxtSubSector(),"subSector");
		getBeanFieldGroup().bind(getTxtLatitud(),"latitud");
		getBeanFieldGroup().bind(getTxtLongitud(),"longitud");

		getBeanFieldGroup().bind(getTxtDetalleUbicacion(),"detalleUbicacion");
		getBeanFieldGroup().bind(getTxtMemoriaDescriptiva(),"memoriaDescriptivaDeLaObra");
		getBeanFieldGroup().bind(getTxtMetrosLineales(),"mtsLineales");
		getBeanFieldGroup().bind(getTxtMetrosCuadrados(),"mtsCuadrados");
		getBeanFieldGroup().bind(getTxtConexiones(),"conexiones");
		getBeanFieldGroup().bind(getTxtBeneficiarios(),"beneficiarios");
		getBeanFieldGroup().bind(getTxtManzanas(),"manzanas");
		getBeanFieldGroup().bind(getTxtParcelas(),"parcelas");

		getBeanFieldGroup().bind(getCmbTipoContratacion(),"tipoDeContratacion");
		getBeanFieldGroup().bind(getTxtPresupuestoOficial(),"presupuestoOficial");
		getBeanFieldGroup().bind(getCmbResponsablePliego(),"responsablePliego");
		getBeanFieldGroup().bind(getDtfFechaElevacion(), "fechaDeElevacion");



	}

	private Component generarProyectos() {

		cmbProyectos = new ComboBox("Proyectos", ServicioProyecto.getInstance().getProyectos());
		cmbProyectos.setItemCaptionPropertyId("nombreDeLaObra");
		cmbProyectos.setFilteringMode(FilteringMode.CONTAINS);
		cmbProyectos.addStyleName("my-modificado");
		cmbProyectos.setRequired(true);
		cmbProyectos.setWidth("806px");
		cmbProyectos.setNewItemsAllowed(false);
		cmbProyectos.setNullSelectionAllowed(false);
		cmbProyectos.focus();

		cmbProyectos.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {

				if(event !=null) {

					Proyecto proyecto = (Proyecto) event.getProperty().getValue();

					if (proyecto != null) {

						BeanItem<Proyecto> item = new BeanItem<>(proyecto);
						//item.getItemProperty("plano").setValue("https://www.comodoro.gov.ar/estadisticas/opublicas/"+item.getItemProperty("plano").getValue().toString());
						getBeanFieldGroup().setItemDataSource(item);

						String plano = item.getItemProperty("plano").getValue().toString();
						String pliego = item.getItemProperty("pliegoLicitatorio").getValue().toString();
						String cotizacion = item.getItemProperty("planillaCotizacion").getValue().toString();
						String otro = item.getItemProperty("otroDocumento").getValue().toString();

						System.out.println("soy plano:" + plano);
						System.out.println("soy pliego:" + pliego);
						System.out.println("soy cotizacion:" + cotizacion);
						System.out.println("soy otro:" + otro);

						if (!plano.equalsIgnoreCase("No posee información")) {

							getLinkPlano().setCaption(plano);
							getLinkPlano().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + plano));
							getLinkPlano().setTargetName("_blank");
						}

						if (!pliego.equalsIgnoreCase("No posee información")) {

							getLinkPliego().setCaption(pliego);
							getLinkPliego().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + pliego));
							getLinkPliego().setTargetName("_blank");
						}

						if (!cotizacion.equalsIgnoreCase("No posee información")) {
							getLinkCotizacion().setCaption(cotizacion);
							getLinkCotizacion().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + cotizacion));
							getLinkCotizacion().setTargetName("_blank");
						}

						if (!otro.equalsIgnoreCase("No posee información")) {
							getLinkOtros().setCaption(otro);
							getLinkOtros().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + otro));
							getLinkOtros().setTargetName("_blank");
						}
						}
					}
				}
		});
		return cmbProyectos;

	}

	private Component generarComandos() {
			
			HorizontalLayout lay = new HorizontalLayout();
			lay.setSpacing(true);		
			setBtnModificarProyecto(new Button("Guardar"));
			getBtnModificarProyecto().addClickListener(this);
			setBtnEliminarProyectos(new Button("Eliminar"));
			getBtnEliminarProyectos().addClickListener(this);
			lay.addComponent(getBtnModificarProyecto());
			lay.addComponent(getBtnEliminarProyectos());		
			return lay;
		
	}

	private Component generarUploadPlanoFechaElevacion() {

		GridLayout lay = new GridLayout();
		lay.setSpacing(true);
		lay.setSizeFull();


		
		setProgressBar(new ProgressBar(0.0f));
		getProgressBar().setWidth("300px");

		setCargadorDeArchivosPlano(new CargadorArchivos(getProgressBar()));

		//setLinkPlano(new Link("No posee plano",null));
		setLinkPlano(new Link(getCargadorDeArchivosPlano().getFile().getName(),null));
		getLinkPlano().addStyleName("linkecito");

		getCargadorDeArchivosPlano().setLinkPlano(getLinkPlano());
		

		setUploadPlanos(new Upload("Seleccionar plano",getCargadorDeArchivosPlano()));
		getUploadPlanos().setStyleName("bigupload");		
		getUploadPlanos().setButtonCaption("Subir plano");

		getUploadPlanos().addSucceededListener(getCargadorDeArchivosPlano());
		getUploadPlanos().addProgressListener(getCargadorDeArchivosPlano());
		getUploadPlanos().addStartedListener(getCargadorDeArchivosPlano());
		



		setDtfFechaElevacion(new DateField("Fecha de elevacion:"));
		getDtfFechaElevacion().setInvalidAllowed(false);
		getDtfFechaElevacion().setRangeEnd(new Date());
		getDtfFechaElevacion().setRequired(true);


		lay.addComponent(getLinkPlano());
		lay.addComponent(getUploadPlanos());
		lay.addComponent(getProgressBar());

		HorizontalLayout layH = new HorizontalLayout();
		layH.setSpacing(true);
		layH.setSizeFull();

		layH.addComponent(lay);
		layH.addComponent(getDtfFechaElevacion());
		//lay.addComponent(getDtfFechaElevacion());

		return layH;
	}


	private Component generarOtraDocumentacionImportancia() {

		GridLayout lay = new GridLayout();
		lay.setSpacing(true);
		lay.setSizeFull();

		setProgressBar(new ProgressBar(0.0f));
		getProgressBar().setWidth("300px");

		setCargadorDeArchivosOtros(new CargadorArchivos(getProgressBar()));

		setLinkOtros(new Link(getCargadorDeArchivosOtros().getFile().getName(),null));
		getLinkOtros().addStyleName("linkecito");

		setUploadOtrosDocumentos(new Upload("Seleccionar otros documentos (*.pdf)", getCargadorDeArchivosOtros()));
		getUploadOtrosDocumentos().setStyleName("bigupload");
		getUploadOtrosDocumentos().setButtonCaption("Subir documento");
		JavaScript.getCurrent().execute("document.getElementsByClassName('gwt-FileUpload')[3].setAttribute('accept', '.pdf')");

		getUploadOtrosDocumentos().addSucceededListener(getCargadorDeArchivosOtros());
		getUploadOtrosDocumentos().addProgressListener(getCargadorDeArchivosOtros());
		getUploadOtrosDocumentos().addStartedListener(getCargadorDeArchivosOtros());
		getUploadOtrosDocumentos().addFailedListener(getCargadorDeArchivosOtros());

		lay.addComponent(getLinkOtros());
		lay.addComponent(getUploadOtrosDocumentos());
		lay.addComponent(getProgressBar());

		return lay;

	}

	private Component generarUploadPlanillaDeCotización() {

		GridLayout lay = new GridLayout();
		lay.setSpacing(true);
		lay.setSizeFull();


		setProgressBar(new ProgressBar(0.0f));
		getProgressBar().setWidth("300px");

		setCargadorDeArchivosCotizacion(new CargadorArchivos(getProgressBar()));

		setLinkCotizacion(new Link(getCargadorDeArchivosCotizacion().getFile().getName(),null));
		getLinkCotizacion().addStyleName("linkecito");

		setUploadPlanillaCotizacion(new Upload("Seleccionar planilla cotizacion (*.pdf)", getCargadorDeArchivosCotizacion()));
		getUploadPlanillaCotizacion().setStyleName("bigupload");
		getUploadPlanillaCotizacion().setButtonCaption("Subir planilla");
		JavaScript.getCurrent().execute("document.getElementsByClassName('gwt-FileUpload')[2].setAttribute('accept', '.pdf')");

		getUploadPlanillaCotizacion().addSucceededListener(getCargadorDeArchivosCotizacion());
		getUploadPlanillaCotizacion().addProgressListener(getCargadorDeArchivosCotizacion());
		getUploadPlanillaCotizacion().addStartedListener(getCargadorDeArchivosCotizacion());
		getUploadPlanillaCotizacion().addFailedListener(getCargadorDeArchivosCotizacion());

		lay.addComponent(getLinkCotizacion());
		lay.addComponent(getUploadPlanillaCotizacion());
		lay.addComponent(getProgressBar());

		return lay;
	}

	private Component generarUploadPliegoLicitatorio() {

		GridLayout lay = new GridLayout();
		lay.setSpacing(true);
		lay.setSizeFull();



		setProgressBar(new ProgressBar(0.0f));
		getProgressBar().setWidth("300px");

		setCargadorDeArchivosPliego(new CargadorArchivos(getProgressBar()));

		setLinkPliego(new Link(getCargadorDeArchivosPliego().getFile().getName(),null));
		getLinkPliego().addStyleName("linkecito");


		setUploadPliegoLicitatorio(new Upload("Seleccionar pliego licitatorio (*.pdf)", getCargadorDeArchivosPliego()));
		getUploadPliegoLicitatorio().setStyleName("bigupload");
		getUploadPliegoLicitatorio().setButtonCaption("Subir pliego");
		JavaScript.getCurrent().execute("document.getElementsByClassName('gwt-FileUpload')[1].setAttribute('accept', '.pdf')");

		getUploadPliegoLicitatorio().addSucceededListener(getCargadorDeArchivosPliego());
		getUploadPliegoLicitatorio().addProgressListener(getCargadorDeArchivosPliego());
		getUploadPliegoLicitatorio().addStartedListener(getCargadorDeArchivosPliego());
		getUploadPliegoLicitatorio().addFailedListener(getCargadorDeArchivosPliego());

		lay.addComponent(getLinkPliego());
		lay.addComponent(getUploadPliegoLicitatorio());
		lay.addComponent(getProgressBar());

		return lay;

	}


	private Component generarCmbResponsables() {

		setCmbResponsablePliego(new ComboBox("Responsable del pliego:", ServicioResponsablePliego.getInstance().getResponsablesPliego()));
		getCmbResponsablePliego().setItemCaptionPropertyId("apellidoNombre");
		getCmbResponsablePliego().setNullSelectionAllowed(false);
		getCmbResponsablePliego().setNewItemsAllowed(false);
		getCmbResponsablePliego().setFilteringMode(FilteringMode.CONTAINS);
		getCmbResponsablePliego().setRequired(true);
		getCmbResponsablePliego().setWidth("304px");
		return getCmbResponsablePliego();
	}

	private Component generarTxtPresupuestoOficial() {

		setTxtPresupuestoOficial(new TextField("Presupuesto oficial:"));
		getTxtPresupuestoOficial().setWidth("185px");
		//Validator validator = new RegexpValidator("\\d{1,2}[\\,\\.]{1}\\d{1,2}", "Numero mal compuesto");
		Validator validator = new RegexpValidator("\\d*[\\,]{1}\\d{1,2}", "Numero mal compuesto");
		getTxtPresupuestoOficial().addValidator(validator);
		getTxtPresupuestoOficial().setRequired(true);
		return getTxtPresupuestoOficial();
	}

	private Component generarCmbTipoContratacion() {

		setCmbTipoContratacion(new ComboBox("Tipo de Contratacion:",ServicioTipoDeContratacion.getInstance().getTiposDeContratacion()));
		getCmbTipoContratacion().setItemCaptionPropertyId("tipoDeContratacion");
		getCmbTipoContratacion().setRequired(true);
		getCmbTipoContratacion().setFilteringMode(FilteringMode.CONTAINS);
		getCmbTipoContratacion().setWidth("299px");
		getCmbTipoContratacion().setNewItemsAllowed(false);
		getCmbTipoContratacion().setNullSelectionAllowed(false);		
		return getCmbTipoContratacion();

	}

	private Component generarTxtParcelas() {

		setTxtParcelas(new TextField("Cantidad de Parcelas:"));
		getTxtParcelas().setWidth("155px");
		return getTxtParcelas();
	}
	private Component generarTxtManzanas() {

		setTxtManzanas(new TextField("Cantidad de Manzanas:"));
		getTxtManzanas().setWidth("155px");
		return getTxtManzanas();
	}

	private Component generarTxtBeneficiarios() {

		setTxtBeneficiarios(new TextField("Beneficiarios:"));
		getTxtBeneficiarios().setWidth("125px");
		return getTxtBeneficiarios();
	}

	private Component generarTxtConexiones() {

		setTxtConexiones(new TextField("Conexiones:"));
		getTxtConexiones().setWidth("125px");
		return getTxtConexiones();
	}

	private Component generarTxtMetrosCuadrados() {

		setTxtMetrosCuadrados(new TextField("Mts Cuadrados:"));
		//Validator validator = new RegexpValidator("\\d*[\\,]{1}\\d{1,2}", "Numero mal compuesto");
		//getTxtMetrosCuadrados().addValidator(validator);
		getTxtMetrosCuadrados().setWidth("126px");
		return getTxtMetrosCuadrados();
	}

	private Component generarTxtMetrosLineales() {

		setTxtMetrosLineales(new TextField("Mts Lineales:"));
		//Validator validator = new RegexpValidator("\\d*[\\,]{1}\\d{1,2}", "Numero mal compuesto");
		//getTxtMetrosLineales().addValidator(validator);
		getTxtMetrosLineales().setWidth("126px");
		return getTxtMetrosLineales();
	}

	private Component generarTxtMemoriaDescriptiva() {

		setTxtMemoriaDescriptiva(new TextArea("Memoria descriptiva de la obra:"));
		getTxtMemoriaDescriptiva().setRequired(true);
		getTxtMemoriaDescriptiva().setWidth("872px");
		getTxtMemoriaDescriptiva().setHeight("100px");
		return getTxtMemoriaDescriptiva();
	}

	private Component generarTxtDetalleDeLaUbicacion() {

		setTxtDetalleUbicacion(new TextField("Detalle de la ubicacion:"));
		getTxtDetalleUbicacion().setWidth("872px");
		return getTxtDetalleUbicacion();
	}


	private Component generarTxtLongitud() {

		setTxtLongitud(new TextField("Longitud:"));
		getTxtLongitud().setRequired(true);
		Validator validator = new RegexpValidator("^(\\-?\\d+(\\.\\d+)?).\\s*(\\-?\\d+(\\.\\d+)?)$", "Longitud no valida");
		getTxtLongitud().addValidator(validator);
		getTxtLongitud().setWidth("130px");
		return getTxtLongitud();
	}

	private Component generarTxtLatitud() {

		setTxtLatitud(new TextField("Latitud:"));
		getTxtLatitud().setRequired(true);
		Validator validator = new RegexpValidator("^(\\-?\\d+(\\.\\d+)?).\\s*(\\-?\\d+(\\.\\d+)?)$", "Latitud no valida");
		getTxtLatitud().addValidator(validator);
		getTxtLatitud().setWidth("130px");
		return getTxtLatitud();
	}





	private Component generarTxtSubSector() {

		setTxtSubSector(new TextField("Subsector:"));
		Validator validator = new RegexpValidator("^(((?<!^)\\s(?!$)|[-a-zA-Z-0123456789ñÑáéíóúÁÉÍÓÚ])*)$", "Ingrese caracteres validos");
		getTxtSubSector().addValidator(validator);
		getTxtSubSector().setWidth("316px");
		return getTxtSubSector();
	}


	private Component generarCmbSector() {


		setCmbSector(new ComboBox("Sector:",ServicioSector.getInstance().getSectores()));
		getCmbSector().setItemCaptionPropertyId("nombreSector");
		getCmbSector().setRequired(true);
		getCmbSector().setFilteringMode(FilteringMode.CONTAINS);
		getCmbSector().setWidth("650px");
		getCmbSector().setNewItemsAllowed(false);
		getCmbSector().setNullSelectionAllowed(false);		
		return getCmbSector();

	}

	private Component generarTxtNombreDeLaObra() {

		setTxtNombreDeLaObra(new TextField("Nombre de la Obra:"));
		//Validator validator = new RegexpValidator("^(((?<!^)\\s(?!$)|[-a-zA-ZñÑáéíóúÁÉÍÓÚ0-9])*)$", "Ingrese caracteres validos");
		//Validator validator = new RegexpValidator("^([a-zA-z0-9-ñÑáéíóúÁÉÍÓÚ\"\\s]{2,255})$", "Ingrese caracteres validos");
		//getTxtNombreDeLaObra().addValidator(validator);

		getTxtNombreDeLaObra().setRequired(true);
		//getTxtNombreDeLaObra().setWidth("812px");
		getTxtNombreDeLaObra().setWidth("872px");
		return getTxtNombreDeLaObra();
	}

	private Component generarTabla() {

		setFilterTablaProyectos((new FilterTable("<big><strong>Proyectos")));
		getFilterTablaProyectos().setCaptionAsHtml(true);
		getFilterTablaProyectos().setContainerDataSource(ServicioProyecto.getInstance().getProyectos());


		getFilterTablaProyectos().setCaptionAsHtml(true);
		//getFilterTablaProyectos().setSizeFull();
		getFilterTablaProyectos().setSelectable(true);
		//getFilterTablaLicencia().setFilterGenerator(new DemoFilterGenerator());
		getFilterTablaProyectos().setFilterBarVisible(true);
		getFilterTablaProyectos().setPageLength(0);
		getFilterTablaProyectos().setVisibleColumns(new Object[]{"nombreDeLaObra",
				"categoriaProyecto.nombreCategoria",
				"sector.nombreSector"});



		getFilterTablaProyectos().setColumnHeaders(new String[]{"Nombre de la obra","Categoria","Sector"});


		//		getFilterTablaProyectos().setConverter("fechaCarga", new StringToDateConverter(){			
		//			private static final long serialVersionUID = 1L;
		//			protected java.text.DateFormat getFormat(java.util.Locale locale) {				
		//				return DateFormat.getDateInstance(DateFormat.SHORT, locale);
		//			}
		//		});



		getFilterTablaProyectos().addValueChangeListener(this);
		getFilterTablaProyectos().setColumnExpandRatio("nombreDeLaObra",-1);
		getFilterTablaProyectos().setColumnWidth("nombreDeLaObra",516);

		return getFilterTablaProyectos();
	}

	private Component generarTxtSubcategoria() {

		setTxtSubCategoria(new TextField("Subcategoria:"));
		getTxtSubCategoria().setWidth("310px");
		Validator validator = new RegexpValidator("^(((?<!^)\\s(?!$)|[-a-zA-ZñÑáéíóúÁÉÍÓÚ])*)$", "Ingrese caracteres validos");
		getTxtSubCategoria().addValidator(validator);
		return getTxtSubCategoria();
	}

	private Component generarCmbCategoria() {

		setCmbCategorias(new ComboBox("Categorias:",ServicioCategoria.getInstance().getCategorias()));
		getCmbCategorias().setItemCaptionPropertyId("nombreCategoria");
		getCmbCategorias().setFilteringMode(FilteringMode.CONTAINS);
		getCmbCategorias().setRequired(true);
		getCmbCategorias().setWidth("550px");
		getCmbCategorias().setNewItemsAllowed(false);
		getCmbCategorias().setNullSelectionAllowed(false);		
		return getCmbCategorias();
	}

	private Component generarFechas() {

		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);

		setDtfFechaDesde(new DateField("<big><strong>Fecha Desde:"));
		getDtfFechaDesde().setCaptionAsHtml(true);
		getDtfFechaDesde().setRequired(true);
		getDtfFechaDesde().setDateFormat("dd/MM/yyyy");
		getDtfFechaDesde().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaDesde().setValue(new Date());
		getDtfFechaDesde().setRangeEnd(new Date());

		setDtfFechaHasta(new DateField("<big><strong>Fecha Hasta:"));
		getDtfFechaHasta().setCaptionAsHtml(true);
		getDtfFechaHasta().setRequired(true);
		getDtfFechaHasta().setDateFormat("dd/MM/yyyy");
		getDtfFechaHasta().setDateOutOfRangeMessage("Fecha mal compuesta. dd/MM/yyyy");
		getDtfFechaHasta().setRangeEnd(new Date());
		getDtfFechaHasta().setValue(new Date());


		setBtnBuscarProyectos(new Button("Buscar"));
		getBtnBuscarProyectos().addClickListener(this);
		setBtnLimpiarBusqueda(new Button("Limpiar"));
		getBtnLimpiarBusqueda().addClickListener(this);

		lay.addComponent(getDtfFechaDesde());
		lay.addComponent(getDtfFechaHasta());
		lay.addComponent(getBtnBuscarProyectos());	
		lay.addComponent(getBtnLimpiarBusqueda());
		lay.setComponentAlignment(getBtnBuscarProyectos(), Alignment.BOTTOM_RIGHT);
		lay.setComponentAlignment(getBtnLimpiarBusqueda(), Alignment.BOTTOM_RIGHT);

		return lay;
	}


	public FilterTable getFilterTablaProyectos() {
		return filterTablaProyectos;
	}

	public void setFilterTablaProyectos(FilterTable filterTablaProyectos) {
		this.filterTablaProyectos = filterTablaProyectos;
	}

	public BeanItemContainer<Proyecto> getContainer() {
		return container;
	}


	public void setContainer(BeanItemContainer<Proyecto> container) {
		this.container = container;
	}


	@Override
	public void valueChange(ValueChangeEvent event) {

		editarProyecto((Proyecto)getFilterTablaProyectos().getValue());


	}

	private void editarProyecto(Proyecto proyecto) {

		if(proyecto != null) {

			BeanItem<Proyecto> item = new BeanItem<>(proyecto);
			//item.getItemProperty("plano").setValue("https://www.comodoro.gov.ar/estadisticas/opublicas/"+item.getItemProperty("plano").getValue().toString());
			getBeanFieldGroup().setItemDataSource(item);

			String plano = item.getItemProperty("plano").getValue().toString();
			String pliego = item.getItemProperty("pliegoLicitatorio").getValue().toString();
			String cotizacion = item.getItemProperty("planillaCotizacion").getValue().toString();
			String otro = item.getItemProperty("otroDocumento").getValue().toString();

			//System.out.println("soy plano:" + plano);

			if (!plano.isEmpty()) {

				getLinkPlano().setCaption(plano);
				getLinkPlano().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + plano));
				getLinkPlano().setTargetName("_blank");
			}

			if (!pliego.isEmpty()) {

				getLinkPliego().setCaption(pliego);
				getLinkPliego().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + pliego));
				getLinkPliego().setTargetName("_blank");
			}

			if (!cotizacion.isEmpty()) {
				getLinkCotizacion().setCaption(cotizacion);
				getLinkCotizacion().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + cotizacion));
				getLinkCotizacion().setTargetName("_blank");
			}

			if (!otro.isEmpty()) {
				getLinkOtros().setCaption(otro);
				getLinkOtros().setResource(new ExternalResource("https://www.comodoro.gov.ar/estadisticas/opublicas/" + otro));
				getLinkOtros().setTargetName("_blank");
			}




			//new Link
		}

	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		//if(getFilterTablaProyectos().getValue() != null) {
		if(getCmbProyectos().getValue() != null) {
			
			if( event.getSource() == getBtnModificarProyecto()) {
				
				if( !getCmbCategorias().isValid() ||
						!getTxtNombreDeLaObra().isValid()||
						!getCmbSector().isValid()||
						!getTxtMemoriaDescriptiva().isValid()||
						!getCmbTipoContratacion().isValid()||
						!getTxtPresupuestoOficial().isValid()||
						!getCmbResponsablePliego().isValid() ||
						!getTxtLatitud().isValid() ||
						!getTxtLongitud().isValid() ||
						!getDtfFechaElevacion().isValid()) {
					
					Notification noti = new Notification("Atencion", "Datos mal ingresados, incompletos y/o incorrectos", Type.ERROR_MESSAGE);
					noti.setDelayMsec(3000);
					noti.setPosition(Position.MIDDLE_CENTER);
					noti.show(Page.getCurrent());
					
				}else {
					
					ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea modificar este registro?", "SI", "NO", new ConfirmDialog.Listener() {
						
						private static final long serialVersionUID = 1L;

						@Override
						public void onClose(ConfirmDialog rta) {
						
							if(rta.isConfirmed()){
								getHandler().modificar();
							}
						}
						});				
				}
			}
			
			if( event.getSource() == getBtnEliminarProyectos()) {
				
				ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea eliminar "
							+ "este registro?", "SI", "NO", new ConfirmDialog.Listener() {
						
						private static final long serialVersionUID = 1L;

						@Override
						public void onClose(ConfirmDialog rta) {
						
							if(rta.isConfirmed()){
								getHandler().eliminar();
							}
						}
						});							    
			}			
		}else Notification.show("Seleccione un registro de la tabla primeramente", Type.ERROR_MESSAGE);

	}


	public BeanFieldGroup<Proyecto> getBeanFieldGroup() {
		return beanFieldGroup;
	}


	public void setBeanFieldGroup(BeanFieldGroup<Proyecto> beanFieldGroup) {
		this.beanFieldGroup = beanFieldGroup;
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

	public Button getBtnLimpiarBusqueda() {
		return btnLimpiarBusqueda;
	}

	public void setBtnLimpiarBusqueda(Button btnLimpiarBusqueda) {
		this.btnLimpiarBusqueda = btnLimpiarBusqueda;
	}

	public Button getBtnBuscarProyectos() {
		return btnBuscarProyectos;
	}

	public void setBtnBuscarProyectos(Button btnBuscarProyectos) {
		this.btnBuscarProyectos = btnBuscarProyectos;
	}

	public ComboBox getCmbCategorias() {
		return cmbCategorias;
	}

	public void setCmbCategorias(ComboBox cmbCategorias) {
		this.cmbCategorias = cmbCategorias;
	}

	public TextField getTxtSubCategoria() {
		return txtSubCategoria;
	}

	public void setTxtSubCategoria(TextField txtSubCategoria) {
		this.txtSubCategoria = txtSubCategoria;
	}

	public TextField getTxtNombreDeLaObra() {
		return txtNombreDeLaObra;
	}

	public void setTxtNombreDeLaObra(TextField txtNombreDeLaObra) {
		this.txtNombreDeLaObra = txtNombreDeLaObra;
	}

	public ComboBox getCmbSector() {
		return cmbSector;
	}

	public void setCmbSector(ComboBox cmbSector) {
		this.cmbSector = cmbSector;
	}

	public TextField getTxtSubSector() {
		return txtSubSector;
	}

	public void setTxtSubSector(TextField txtSubSector) {
		this.txtSubSector = txtSubSector;
	}

	public TextField getTxtLatitud() {
		return txtLatitud;
	}

	public void setTxtLatitud(TextField txtLatitud) {
		this.txtLatitud = txtLatitud;
	}

	public TextField getTxtLongitud() {
		return txtLongitud;
	}

	public void setTxtLongitud(TextField txtLongitud) {
		this.txtLongitud = txtLongitud;
	}

	public TextField getTxtDetalleUbicacion() {
		return txtDetalleUbicacion;
	}

	public void setTxtDetalleUbicacion(TextField txtDetalleUbicacion) {
		this.txtDetalleUbicacion = txtDetalleUbicacion;
	}

	public TextArea getTxtMemoriaDescriptiva() {
		return txtMemoriaDescriptiva;
	}

	public void setTxtMemoriaDescriptiva(TextArea txtMemoriaDescriptiva) {
		this.txtMemoriaDescriptiva = txtMemoriaDescriptiva;
	}

	public TextField getTxtMetrosLineales() {
		return txtMetrosLineales;
	}

	public void setTxtMetrosLineales(TextField txtMetrosLineales) {
		this.txtMetrosLineales = txtMetrosLineales;
	}

	public TextField getTxtMetrosCuadrados() {
		return txtMetrosCuadrados;
	}

	public void setTxtMetrosCuadrados(TextField txtMetrosCuadrados) {
		this.txtMetrosCuadrados = txtMetrosCuadrados;
	}

	public TextField getTxtConexiones() {
		return txtConexiones;
	}

	public void setTxtConexiones(TextField txtConexiones) {
		this.txtConexiones = txtConexiones;
	}

	public TextField getTxtBeneficiarios() {
		return txtBeneficiarios;
	}

	public void setTxtBeneficiarios(TextField txtBeneficiarios) {
		this.txtBeneficiarios = txtBeneficiarios;
	}

	public TextField getTxtManzanas() {
		return txtManzanas;
	}

	public void setTxtManzanas(TextField txtManzanas) {
		this.txtManzanas = txtManzanas;
	}

	public TextField getTxtParcelas() {
		return txtParcelas;
	}

	public void setTxtParcelas(TextField txtParcelas) {
		this.txtParcelas = txtParcelas;
	}

	public ComboBox getCmbTipoContratacion() {
		return cmbTipoContratacion;
	}

	public void setCmbTipoContratacion(ComboBox cmbTipoContratacion) {
		this.cmbTipoContratacion = cmbTipoContratacion;
	}

	public TextField getTxtPresupuestoOficial() {
		return txtPresupuestoOficial;
	}

	public void setTxtPresupuestoOficial(TextField txtPresupuestoOficial) {
		this.txtPresupuestoOficial = txtPresupuestoOficial;
	}

	public ComboBox getCmbResponsablePliego() {
		return cmbResponsablePliego;
	}

	public void setCmbResponsablePliego(ComboBox cmbResponsablePliego) {
		this.cmbResponsablePliego = cmbResponsablePliego;
	}

	public Upload getUploadPlanos() {
		return uploadPlanos;
	}

	public void setUploadPlanos(Upload uploadPlanos) {
		this.uploadPlanos = uploadPlanos;
	}

	public DateField getDtfFechaElevacion() {
		return dtfFechaElevacion;
	}

	public void setDtfFechaElevacion(DateField dtfFechaElevacion) {
		this.dtfFechaElevacion = dtfFechaElevacion;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public CargadorArchivos getCargadorDeArchivosPlano() {
		return cargadorDeArchivosPlano;
	}

	public void setCargadorDeArchivosPlano(CargadorArchivos cargadorDeArchivosPlano) {
		this.cargadorDeArchivosPlano = cargadorDeArchivosPlano;
	}

	public CargadorArchivos getCargadorDeArchivosPliego() {
		return cargadorDeArchivosPliego;
	}

	public void setCargadorDeArchivosPliego(CargadorArchivos cargadorDeArchivosPliego) {
		this.cargadorDeArchivosPliego = cargadorDeArchivosPliego;
	}

	public CargadorArchivos getCargadorDeArchivosCotizacion() {
		return cargadorDeArchivosCotizacion;
	}

	public void setCargadorDeArchivosCotizacion(CargadorArchivos cargadorDeArchivosCotizacion) {
		this.cargadorDeArchivosCotizacion = cargadorDeArchivosCotizacion;
	}

	public CargadorArchivos getCargadorDeArchivosOtros() {
		return cargadorDeArchivosOtros;
	}

	public void setCargadorDeArchivosOtros(CargadorArchivos cargadorDeArchivosOtros) {
		this.cargadorDeArchivosOtros = cargadorDeArchivosOtros;
	}

	public Link getLinkPlano() {
		return linkPlano;
	}

	public void setLinkPlano(Link linkPlano) {
		this.linkPlano = linkPlano;
	}

	public Button getBtnModificarProyecto() {
		return btnModificarProyecto;
	}

	public void setBtnModificarProyecto(Button btnModificarProyecto) {
		this.btnModificarProyecto = btnModificarProyecto;
	}

	public Button getBtnEliminarProyectos() {
		return btnEliminarProyectos;
	}

	public void setBtnEliminarProyectos(Button btnEliminarProyectos) {
		this.btnEliminarProyectos = btnEliminarProyectos;
	}

	public IhandlerLayProyecto getHandler() {
		return handler;
	}

	public void setHandler(IhandlerLayProyecto handler) {
		this.handler = handler;
	}

	public Upload getUploadPliegoLicitatorio() {
		return uploadPliegoLicitatorio;
	}

	public void setUploadPliegoLicitatorio(Upload uploadPliegoLicitatorio) {
		this.uploadPliegoLicitatorio = uploadPliegoLicitatorio;
	}

	public Upload getUploadPlanillaCotizacion() {
		return uploadPlanillaCotizacion;
	}

	public void setUploadPlanillaCotizacion(Upload uploadPlanillaCotizacion) {
		this.uploadPlanillaCotizacion = uploadPlanillaCotizacion;
	}

	public Upload getUploadOtrosDocumentos() {
		return uploadOtrosDocumentos;
	}

	public void setUploadOtrosDocumentos(Upload uploadOtrosDocumentos) {
		this.uploadOtrosDocumentos = uploadOtrosDocumentos;
	}

	public Link getLinkPliego() {
		return linkPliego;
	}

	public void setLinkPliego(Link linkPliego) {
		this.linkPliego = linkPliego;
	}

	public Link getLinkCotizacion() {
		return linkCotizacion;
	}

	public void setLinkCotizacion(Link linkCotizacion) {
		this.linkCotizacion = linkCotizacion;
	}

	public Link getLinkOtros() {
		return linkOtros;
	}

	public void setLinkOtros(Link linkOtros) {
		this.linkOtros = linkOtros;
	}

	public ComboBox getCmbProyectos() {
		return cmbProyectos;
	}

	public void setCmbProyectos(ComboBox cmbProyectos) {
		this.cmbProyectos = cmbProyectos;
	}

	public void modificarProyectoOK() {
		
		//Notification.show("Atención","Licencia de conducir modificada correctamente", Type.ASSISTIVE_NOTIFICATION);
		
		Notification noti = new Notification("Proyecto modificado correctamente", Type.ASSISTIVE_NOTIFICATION);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}

	public void modificarProyectoError() {
		
		//Notification.show("Atención","Error al modificar licencia de conducir", Type.ERROR_MESSAGE);
		
		Notification noti = new Notification("Error al modificar proyecto", Type.ERROR_MESSAGE);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}

	public void eliminarProyectoOK() {
		
		Notification noti = new Notification("Proyecto eliminado correctamente", Type.ASSISTIVE_NOTIFICATION);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}

	public void eliminarProyectoError() {
		
		Notification noti = new Notification("Error al eliminar proyecto", Type.ERROR_MESSAGE);
		noti.setDelayMsec(1500);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
		
	}


}
