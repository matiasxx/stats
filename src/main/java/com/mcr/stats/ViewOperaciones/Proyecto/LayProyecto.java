package com.mcr.stats.ViewOperaciones.Proyecto;

import com.vaadin.ui.*;

import java.util.Date;

import org.vaadin.dialogs.ConfirmDialog;

import com.mcr.stats.Ihandler.IhandlerLayProyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.servicios.ServicioCategoria;
import com.mcr.stats.servicios.ServicioResponsablePliego;
import com.mcr.stats.servicios.ServicioSector;
import com.mcr.stats.servicios.ServicioTipoDeContratacion;
import com.mcr.stats.tools.CargadorArchivos;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;

public class LayProyecto extends VerticalLayout implements ClickListener {



    /**
     *
     */
    private static final long serialVersionUID = 1L;
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

    private Upload uploadPlanos;
    private DateField dtfFechaElevacion;

    private Upload uploadPliegoLicitatorio;
    private Upload uploadPlanillaCotizacion;
    private Upload uploadOtrosDocumentos;


    private ProgressBar progressBar;

    private Button btnGuardar;
    private Button btnLimpiar;

    private CargadorArchivos cargadorDeArchivosPlano;
    private CargadorArchivos cargadorDeArchivosPliego;
    private CargadorArchivos cargadorDeArchivosCotizacion;
    private CargadorArchivos cargadorDeArchivosOtros;


    private IhandlerLayProyecto handler;

    public LayProyecto() {

        setMargin(true);
        setSpacing(true);
        setSizeFull();


        //setCaptionAsHtml(true);
        //setCaption("<strong><big>Alta Proyecto");
        addComponent(new Label("<font color='white'><strong><big>Alta Proyecto", ContentMode.HTML));

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


        addComponent(layUno);
        addComponent(generarTxtNombreDeLaObra());
        addComponent(generarCmbSector());
        addComponent(layDos);
        addComponent(generarTxtDetalleDeLaUbicacion());
        addComponent(generarTxtMemoriaDescriptiva());
        addComponent(layTres);
        addComponent(layCuatro);

        addComponent(new com.vaadin.ui.Label("<hr/>", ContentMode.HTML));
        addComponent(generarUploadPlanoFechaElevacion());
        addComponent(new com.vaadin.ui.Label("<hr/>", ContentMode.HTML));
        addComponent(generarUploadPliegoLicitatorio());
        addComponent(new com.vaadin.ui.Label("<hr/>", ContentMode.HTML));
        addComponent(generarUploadPlanillaDeCotización());
        addComponent(new com.vaadin.ui.Label("<hr/>", ContentMode.HTML));
        addComponent(generarOtraDocumentacionImportancia());

        addComponent(generarComandos());

        //UI.getCurrent().equals("$('.gwt-FileUpload').attr('accept', 'application/pdf');");


    }

    private Component generarOtraDocumentacionImportancia() {

        GridLayout lay = new GridLayout();
        lay.setSpacing(true);
        lay.setSizeFull();

		setProgressBar(new ProgressBar(0.0f));
		getProgressBar().setWidth("300px");

		setCargadorDeArchivosOtros(new CargadorArchivos(getProgressBar()));

		setUploadOtrosDocumentos(new Upload("Seleccionar otros documentos (*.pdf)", getCargadorDeArchivosOtros()));
		getUploadOtrosDocumentos().setStyleName("bigupload");
		getUploadOtrosDocumentos().setButtonCaption("Subir documento");
		JavaScript.getCurrent().execute("document.getElementsByClassName('gwt-FileUpload')[3].setAttribute('accept', '.pdf')");

		getUploadOtrosDocumentos().addSucceededListener(getCargadorDeArchivosOtros());
		getUploadOtrosDocumentos().addProgressListener(getCargadorDeArchivosOtros());
		getUploadOtrosDocumentos().addStartedListener(getCargadorDeArchivosOtros());
        getUploadOtrosDocumentos().addFailedListener(getCargadorDeArchivosOtros());

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

		setUploadPlanillaCotizacion(new Upload("Seleccionar planilla cotizacion (*.pdf)", getCargadorDeArchivosCotizacion()));
		getUploadPlanillaCotizacion().setStyleName("bigupload");
		getUploadPlanillaCotizacion().setButtonCaption("Subir planilla");
		JavaScript.getCurrent().execute("document.getElementsByClassName('gwt-FileUpload')[2].setAttribute('accept', '.pdf')");

		getUploadPlanillaCotizacion().addSucceededListener(getCargadorDeArchivosCotizacion());
		getUploadPlanillaCotizacion().addProgressListener(getCargadorDeArchivosCotizacion());
		getUploadPlanillaCotizacion().addStartedListener(getCargadorDeArchivosCotizacion());
        getUploadPlanillaCotizacion().addFailedListener(getCargadorDeArchivosCotizacion());

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

        setUploadPliegoLicitatorio(new Upload("Seleccionar pliego licitatorio (*.pdf)", getCargadorDeArchivosPliego()));
        getUploadPliegoLicitatorio().setStyleName("bigupload");
        getUploadPliegoLicitatorio().setButtonCaption("Subir pliego");
        JavaScript.getCurrent().execute("document.getElementsByClassName('gwt-FileUpload')[1].setAttribute('accept', '.pdf')");

		getUploadPliegoLicitatorio().addSucceededListener(getCargadorDeArchivosPliego());
		getUploadPliegoLicitatorio().addProgressListener(getCargadorDeArchivosPliego());
		getUploadPliegoLicitatorio().addStartedListener(getCargadorDeArchivosPliego());
		getUploadPliegoLicitatorio().addFailedListener(getCargadorDeArchivosPliego());


        lay.addComponent(getUploadPliegoLicitatorio());
        lay.addComponent(getProgressBar());

		return lay;

    }


    private Component generarUploadPlanoFechaElevacion() {

        GridLayout lay = new GridLayout();
        lay.setSpacing(true);
        lay.setSizeFull();


        setProgressBar(new ProgressBar(0.0f));
        getProgressBar().setWidth("300px");

        setCargadorDeArchivosPlano(new CargadorArchivos(getProgressBar()));


        setUploadPlanos(new Upload("Seleccionar plano (*.pdf)", getCargadorDeArchivosPlano()));
        getUploadPlanos().setStyleName("bigupload");
        getUploadPlanos().setButtonCaption("Subir plano");
        JavaScript.getCurrent().execute("document.getElementsByClassName('gwt-FileUpload')[0].setAttribute('accept', '.pdf')");

        getUploadPlanos().addSucceededListener(getCargadorDeArchivosPlano());
        getUploadPlanos().addProgressListener(getCargadorDeArchivosPlano());
        getUploadPlanos().addStartedListener(getCargadorDeArchivosPlano());
        getUploadPlanos().addFailedListener(getCargadorDeArchivosPlano());


        setDtfFechaElevacion(new DateField("Fecha de elevacion:"));
        getDtfFechaElevacion().setInvalidAllowed(false);
        getDtfFechaElevacion().setRangeEnd(new Date());
        getDtfFechaElevacion().setRequired(true);


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

    private Component generarComandos() {

        HorizontalLayout lay = new HorizontalLayout();
        lay.setSpacing(true);
        setBtnGuardar(new Button("Guardar"));
        getBtnGuardar().addClickListener(this);
        setBtnLimpiar(new Button("Limpiar"));
        getBtnLimpiar().addClickListener(this);
        lay.addComponent(getBtnGuardar());
        lay.addComponent(getBtnLimpiar());
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

        setCmbTipoContratacion(new ComboBox("Tipo de Contratacion:", ServicioTipoDeContratacion.getInstance().getTiposDeContratacion()));
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
        //Validator validator = new RegexpValidator("^(((?<!^)\\s(?!$)|[-a-zA-Z-0123456789ñÑáéíóúÁÉÍÓÚ])*)$", "Ingrese caracteres validos");
        //getTxtSubSector().addValidator(validator);
        getTxtSubSector().setWidth("316px");
        return getTxtSubSector();
    }


    private Component generarCmbSector() {


        setCmbSector(new ComboBox("Sector:", ServicioSector.getInstance().getSectores()));
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

    private Component generarTxtSubcategoria() {

        setTxtSubCategoria(new TextField("Subcategoria:"));
        getTxtSubCategoria().setWidth("310px");
        Validator validator = new RegexpValidator("^(((?<!^)\\s(?!$)|[-a-zA-ZñÑáéíóúÁÉÍÓÚ])*)$", "Ingrese caracteres validos");
        getTxtSubCategoria().addValidator(validator);
        return getTxtSubCategoria();
    }

    private Component generarCmbCategoria() {

        setCmbCategorias(new ComboBox("Categorias:", ServicioCategoria.getInstance().getCategorias()));
        getCmbCategorias().setItemCaptionPropertyId("nombreCategoria");
        getCmbCategorias().setFilteringMode(FilteringMode.CONTAINS);
        getCmbCategorias().setRequired(true);
        getCmbCategorias().setWidth("550px");
        getCmbCategorias().setNewItemsAllowed(false);
        getCmbCategorias().setNullSelectionAllowed(false);
        return getCmbCategorias();
    }

    public LayProyecto(Usuario usuario) {


        setMargin(true);
        setSpacing(true);
        setSizeFull();
        setCaptionAsHtml(true);
        setCaption("<strong><big>Alta Proyecto");

    }


    @Override
    public void buttonClick(ClickEvent event) {

        if (event.getSource() == getBtnGuardar()) {

            if (!getCmbCategorias().isValid() ||
                    !getTxtNombreDeLaObra().isValid() ||
                    !getCmbSector().isValid() ||
                    !getTxtMemoriaDescriptiva().isValid() ||
                    !getCmbTipoContratacion().isValid() ||
                    !getTxtPresupuestoOficial().isValid() ||
                    !getCmbResponsablePliego().isValid() ||
                    !getTxtLatitud().isValid() ||
                    !getTxtLongitud().isValid() ||
                    !getDtfFechaElevacion().isValid()) {

                Notification noti = new Notification("Atencion", "Datos mal ingresados, incompletos y/o incorrectos", Type.ERROR_MESSAGE);
                noti.setDelayMsec(3000);
                noti.setPosition(Position.MIDDLE_CENTER);
                noti.show(Page.getCurrent());

            } else {

                ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea guardar este registro?", "SI", "NO", new ConfirmDialog.Listener() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClose(ConfirmDialog rta) {

                        if (rta.isConfirmed()) {
                            getHandler().guardar();
                        }
                    }
                });
            }

        }

        if (event.getSource() == getBtnLimpiar()) {

            getHandler().limpiar();
        }
    }


    public DateField getDtfFechaElevacion() {
        return dtfFechaElevacion;
    }

    public void setDtfFechaElevacion(DateField dtfFechaElevacion) {
        this.dtfFechaElevacion = dtfFechaElevacion;
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


    public ProgressBar getProgressBar() {
        return progressBar;
    }


    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }


    public Button getBtnGuardar() {
        return btnGuardar;
    }


    public void setBtnGuardar(Button btnGuardar) {
        this.btnGuardar = btnGuardar;
    }


    public Button getBtnLimpiar() {
        return btnLimpiar;
    }


    public void setBtnLimpiar(Button btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
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

    public void guardarProyectoOK() {

        Notification noti = new Notification("Proyecto guardardo correctamente", Type.ASSISTIVE_NOTIFICATION);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());

    }

    public void guardarProyectoError() {

        Notification noti = new Notification("Proyecto no ha podido ser guardado", Type.ERROR_MESSAGE);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());

    }


}
