package com.mcr.stats.ViewOperaciones.Proyecto.certificacion;

import com.mcr.stats.Ihandler.IhandlerLayCertificacion;
import com.mcr.stats.Ihandler.IhandlerLayCertificado;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.certificacion.Certificado;
import com.mcr.stats.servicios.ServicioProyecto;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.tepi.filtertable.FilterTable;
import org.vaadin.dialogs.ConfirmDialog;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class LayCertificacionBM extends VerticalLayout implements Serializable, Property.ValueChangeListener {

    private ComboBox cmbNumeroContratacion;
    private Label lblNombreObral;

    //Certificacion
    private DateField dtfFechaInicio;
    private TextField txtModificacionProyecto;
    private TextField txtACuadroModificatorio;
    private TextField txtObservaciones;
    private TextField txtRedeterminacion;

    //Certificados
    private TextField txtCertificadoMonto;
    private TextField txtPeriodo;
    private DateField dtfFechaCertificado;





    private FilterTable filterTablaCertificados;
    private BeanItemContainer<Certificado> container = new BeanItemContainer<>(Certificado.class);

    private BeanFieldGroup<Certificacion> beanFieldGroup = new BeanFieldGroup<>(Certificacion.class);
    private BeanFieldGroup<Certificado> beanFieldGroupCertificado = new BeanFieldGroup<>(Certificado.class);



    private IhandlerLayCertificado handlerCertificado;
    private IhandlerLayCertificacion handlerCertificacion;


    public LayCertificacionBM(){

        setMargin(true);
        setSpacing(true);
        setSizeFull();

        addComponent(new Label("<font color='white'><strong><big>Baja/Modificacion Certificacion", ContentMode.HTML));
        addComponent(generarCmbNumeroContratacion());
        addComponent(generarLblNombreObra());


        GridLayout lay = new GridLayout();
        lay.setColumns(3);
        lay.setRows(3);
        lay.setSpacing(true);

        lay.addComponent(generarDtfFechaInicio());
        lay.addComponent(generarTxtModificacionProyecto());
        lay.addComponent(generarTxtACuadromodificatorio());
        lay.addComponent(generarTxtObservaciones());
        lay.addComponent(generarTxtRedeterminacion());







        addComponent(lay);
        addComponent(generarComandosCertificacion());
        addComponent(generarTabla());
        addComponent(generarLay());
        addComponent(generarComandosCertificados());


        beanFieldGroup.bind(dtfFechaInicio, "fechaInicio");
        beanFieldGroup.bind(txtModificacionProyecto, "modificacionProyecto");
        beanFieldGroup.bind(txtACuadroModificatorio, "cuadroModificatoriol");
        beanFieldGroup.bind(txtObservaciones, "observaciones");
        beanFieldGroup.bind(txtRedeterminacion, "redeterminacion");
        //getBeanFieldGroup().bind(getCmbTipoDeCarne(), "tipoDeCarnet"); //es el objeto entero!!!


        beanFieldGroupCertificado.bind(txtCertificadoMonto,"monto");
        beanFieldGroupCertificado.bind(txtPeriodo,"periodo");
        beanFieldGroupCertificado.bind(dtfFechaCertificado,"fechaCertificado");



     

    }

    private Component generarComandosCertificados() {

        HorizontalLayout lay = new HorizontalLayout();
        lay.setSpacing(true);
        lay.addComponent(new Button("Guardar certificado", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea guardar "
                        + "este registro?", "SI", "NO", new ConfirmDialog.Listener() {

                    @Override
                    public void onClose(ConfirmDialog rta) {
                        if(rta.isConfirmed()){

                            if(filterTablaCertificados.getValue() != null){
                                handlerCertificado.modificarCertificado();
                            }else handlerCertificado.guardarCertificado();

                        }
                    }
                });
            }
        }));
        lay.addComponent(new Button("Eliminar certificado", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if(filterTablaCertificados.getValue() != null) {
                    ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea eliminar "
                            + "este registro?", "SI", "NO", new ConfirmDialog.Listener() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public void onClose(ConfirmDialog rta) {

                            if (rta.isConfirmed()) {
                                handlerCertificado.eliminarCertificado();
                            }
                        }
                    });
                }else Notification.show("Seleccione primeramente un certificado", Notification.Type.ERROR_MESSAGE);
            }
        }));

        lay.addComponent(new Button("Limpiar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                handlerCertificado.limpiarCertificado();
            }
        }));

        return lay;

    }

    private Component generarComandosCertificacion() {

        HorizontalLayout lay = new HorizontalLayout();
        lay.setSpacing(true);
        lay.addComponent(new Button("Guardar certificacion", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if(cmbNumeroContratacion.getValue() != null) {
                    ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea modificar "
                            + "este registro?", "SI", "NO", new ConfirmDialog.Listener() {

                        @Override
                        public void onClose(ConfirmDialog rta) {
                            if (rta.isConfirmed()) {
                                handlerCertificacion.modificarCertificacion();
                            }
                        }
                    });
                }else Notification.show("Seleccione primeramente número de contratación", Notification.Type.ERROR_MESSAGE);


            }
        }));

        lay.addComponent(new Button("Eliminar certificacion", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea eliminar "
                        + "este registro?", "SI", "NO", new ConfirmDialog.Listener() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClose(ConfirmDialog rta) {

                        if(rta.isConfirmed()){
                            handlerCertificacion.eliminarCertificacion();
                        }
                    }
                });
            }
        }));
        return lay;

    }

    private Component generarTabla() {

        filterTablaCertificados = new FilterTable("<big><strong>Certificados");

        //filterTablaCertificados.setCaptionAsHtml(true);
//        container.add
//        getContainer().addNestedContainerProperty("personaFisica.nombre");
//        getContainer().addNestedContainerProperty("personaFisica.numeroDeDocumento");
//        getContainer().addNestedContainerProperty("personaFisica.apellido");

        filterTablaCertificados.setContainerDataSource(container);
        filterTablaCertificados.setWidth("806px");
        filterTablaCertificados.setCaptionAsHtml(true);
        filterTablaCertificados.setSelectable(true);
        filterTablaCertificados.setPageLength(0);
        filterTablaCertificados.setVisibleColumns(new Object[]{"monto","periodo", "fechaCertificado"});
        filterTablaCertificados.setColumnHeaders(new String[]{"Monto", "Periodo", "Fecha certificado"});
        filterTablaCertificados.setConverter("fechaCertificado", new StringToDateConverter(){
            private static final long serialVersionUID = 1L;
            protected java.text.DateFormat getFormat(java.util.Locale locale) {
                return DateFormat.getDateInstance(DateFormat.SHORT, locale);
            }
        });

        filterTablaCertificados.addValueChangeListener(this);

        return filterTablaCertificados;
    }


    private Component generarLay() {

        HorizontalLayout lay = new HorizontalLayout();
        lay.setSpacing(true);

        txtCertificadoMonto = new TextField("Monto Certificado:");
        txtPeriodo = new TextField("Periodo:");
        dtfFechaCertificado = new DateField("Fecha Certificado");

        lay.addComponent(txtCertificadoMonto);
        lay.addComponent(txtPeriodo);
        lay.addComponent(dtfFechaCertificado);

        return lay;
    }

    private Component generarTxtRedeterminacion() {

        txtRedeterminacion = new TextField("Redeterminacion");
        return  txtRedeterminacion;
    }

    private Component generarTxtObservaciones() {

        txtObservaciones = new TextField("Observaciones:");
        return txtObservaciones;
    }

    private Component generarTxtACuadromodificatorio() {

        txtACuadroModificatorio = new TextField("Cuadro Modificatorio");
        return txtACuadroModificatorio;
    }


    private Component generarTxtModificacionProyecto() {

        txtModificacionProyecto = new TextField("Modificación proyecto:");
        return txtModificacionProyecto;
    }

    private Component generarDtfFechaInicio() {

        dtfFechaInicio = new DateField("Fecha Inicio:");
        dtfFechaInicio.setRequired(true);
        dtfFechaInicio.setInvalidAllowed(false);
        dtfFechaInicio.setRangeEnd(new Date());
        dtfFechaInicio.setRequired(true);

        return dtfFechaInicio;

    }

    private Component generarLblNombreObra() {

        lblNombreObral = new Label("<font color='white'><strong>Nombre de la obra:",ContentMode.HTML);
        return  lblNombreObral;
    }

    private Component generarCmbNumeroContratacion() {

        cmbNumeroContratacion = new ComboBox("Nro Contratación", ServicioProyecto.getInstance().getProyectosTodo());
        cmbNumeroContratacion.setItemCaptionPropertyId("contratacion.numeroDeContratacion");
        cmbNumeroContratacion.setFilteringMode(FilteringMode.STARTSWITH);
        cmbNumeroContratacion.addStyleName("my-modificado");
        cmbNumeroContratacion.setRequired(true);
        cmbNumeroContratacion.setWidth("806px");
        cmbNumeroContratacion.setNewItemsAllowed(false);
        cmbNumeroContratacion.setNullSelectionAllowed(false);
        cmbNumeroContratacion.focus();

        cmbNumeroContratacion.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if (event != null) {
                    Proyecto proyecto = (Proyecto) event.getProperty().getValue();
                    if(proyecto != null) {
                        lblNombreObral.setValue("<font color='white'><strong>Nombre de la obra: <br>"+proyecto.getNombreDeLaObra()+"<big>");
                        if (proyecto.getContratacion().getCertificacion().getCertificados().size() != 0){
                            container.addAll(proyecto.getContratacion().getCertificacion().getCertificados());
                        }else container.removeAllItems();

                        BeanItem<Certificacion> item = new BeanItem<>(proyecto.getContratacion().getCertificacion());
                        beanFieldGroup.setItemDataSource(item);

                    }else lblNombreObral.setValue("<font color='white'><strong>Nombre de la obra: <br><big>");
                }


            }
        });
        return cmbNumeroContratacion;
    }

    public IhandlerLayCertificado getHandlerCertificado() {
        return handlerCertificado;
    }

    public void setHandlerCertificado(IhandlerLayCertificado handlerCertificado) {
        this.handlerCertificado = handlerCertificado;
    }

    public IhandlerLayCertificacion getHandlerCertificacion() {
        return handlerCertificacion;
    }

    public void setHandlerCertificacion(IhandlerLayCertificacion handlerCertificacion) {
        this.handlerCertificacion = handlerCertificacion;
    }

    public ComboBox getCmbNumeroContratacion() {
        return cmbNumeroContratacion;
    }

    public void setCmbNumeroContratacion(ComboBox cmbNumeroContratacion) {
        this.cmbNumeroContratacion = cmbNumeroContratacion;
    }

    public Label getLblNombreObral() {
        return lblNombreObral;
    }

    public void setLblNombreObral(Label lblNombreObral) {
        this.lblNombreObral = lblNombreObral;
    }

    public DateField getDtfFechaInicio() {
        return dtfFechaInicio;
    }

    public void setDtfFechaInicio(DateField dtfFechaInicio) {
        this.dtfFechaInicio = dtfFechaInicio;
    }

    public TextField getTxtModificacionProyecto() {
        return txtModificacionProyecto;
    }

    public void setTxtModificacionProyecto(TextField txtModificacionProyecto) {
        this.txtModificacionProyecto = txtModificacionProyecto;
    }

    public TextField getTxtACuadroModificatorio() {
        return txtACuadroModificatorio;
    }

    public void setTxtACuadroModificatorio(TextField txtACuadroModificatorio) {
        this.txtACuadroModificatorio = txtACuadroModificatorio;
    }

    public TextField getTxtObservaciones() {
        return txtObservaciones;
    }

    public void setTxtObservaciones(TextField txtObservaciones) {
        this.txtObservaciones = txtObservaciones;
    }

    public TextField getTxtRedeterminacion() {
        return txtRedeterminacion;
    }

    public void setTxtRedeterminacion(TextField txtRedeterminacion) {
        this.txtRedeterminacion = txtRedeterminacion;
    }

    public TextField getTxtCertificadoMonto() {
        return txtCertificadoMonto;
    }

    public void setTxtCertificadoMonto(TextField txtCertificadoMonto) {
        this.txtCertificadoMonto = txtCertificadoMonto;
    }

    public TextField getTxtPeriodo() {
        return txtPeriodo;
    }

    public void setTxtPeriodo(TextField txtPeriodo) {
        this.txtPeriodo = txtPeriodo;
    }

    public DateField getDtfFechaCertificado() {
        return dtfFechaCertificado;
    }

    public void setDtfFechaCertificado(DateField dtfFechaCertificado) {
        this.dtfFechaCertificado = dtfFechaCertificado;
    }

    public FilterTable getFilterTablaCertificados() {
        return filterTablaCertificados;
    }

    public void setFilterTablaCertificados(FilterTable filterTablaCertificados) {
        this.filterTablaCertificados = filterTablaCertificados;
    }

    public BeanItemContainer<Certificado> getContainer() {
        return container;
    }

    public void setContainer(BeanItemContainer<Certificado> container) {
        this.container = container;
    }

    public BeanFieldGroup<Certificacion> getBeanFieldGroup() {
        return beanFieldGroup;
    }

    public void setBeanFieldGroup(BeanFieldGroup<Certificacion> beanFieldGroup) {
        this.beanFieldGroup = beanFieldGroup;
    }

    public BeanFieldGroup<Certificado> getBeanFieldGroupCertificado() {
        return beanFieldGroupCertificado;
    }

    public void setBeanFieldGroupCertificado(BeanFieldGroup<Certificado> beanFieldGroupCertificado) {
        this.beanFieldGroupCertificado = beanFieldGroupCertificado;
    }



    public void modificarCertificacionOK() {

        Notification noti = new Notification("Certificacion modificada correctamente", Notification.Type.ASSISTIVE_NOTIFICATION);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }

    public void modificarCertificacionError() {

        Notification noti = new Notification("Certificacion no ha podido ser modificada", Notification.Type.ERROR_MESSAGE);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());

    }


    public void eliminarCertificacionOK() {

        Notification noti = new Notification("Certificacion eliminada correctamente", Notification.Type.ASSISTIVE_NOTIFICATION);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());

    }

    public void eliminarCertificacionError() {

        Notification noti = new Notification("Certificacion no ha podido ser eliminada", Notification.Type.ERROR_MESSAGE);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {

        if(event.getProperty() == filterTablaCertificados){

            if( filterTablaCertificados.getValue() != null) {
                BeanItem<Certificado> item = new BeanItem<>((Certificado) filterTablaCertificados.getValue());
                beanFieldGroupCertificado.setItemDataSource(item);
            }
        }
    }

    public void guardarCertificadoError() {

        Notification noti = new Notification("Certificado no ha podido ser guardado", Notification.Type.ERROR_MESSAGE);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }

    public void guardarCertificadoOK() {

        Notification noti = new Notification("Certificado guardado correctamente", Notification.Type.ASSISTIVE_NOTIFICATION);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }

    public void modificarCertificadoOK() {

        Notification noti = new Notification("Certificado modificado correctamente", Notification.Type.ASSISTIVE_NOTIFICATION);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }

    public void modificarCertificadoError() {

        Notification noti = new Notification("Certificado no ha podido ser modificado", Notification.Type.ERROR_MESSAGE);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }

    public void eliminarCertificadoOK() {

        Notification noti = new Notification("Certificado eliminado correctamente", Notification.Type.ASSISTIVE_NOTIFICATION);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }

    public void eliminarCertificadoError() {

        Notification noti = new Notification("Certificado no ha podido ser eliminado", Notification.Type.ERROR_MESSAGE);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }
}
