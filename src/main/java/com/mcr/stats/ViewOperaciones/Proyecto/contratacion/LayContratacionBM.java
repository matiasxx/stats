package com.mcr.stats.ViewOperaciones.Proyecto.contratacion;

import com.mcr.stats.Ihandler.IhandlerLayContratacionBM;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.contratacion.Contratacion;
import com.mcr.stats.servicios.ServicioContratacion;
import com.mcr.stats.servicios.ServicioEmpresa;
import com.mcr.stats.servicios.ServicioProyecto;
import com.mcr.stats.servicios.ServicioRepresentanteTecnico;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.FieldEvents;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;

import java.math.BigDecimal;

public class LayContratacionBM extends VerticalLayout {

    private ComboBox cmbProyectos;
    private TextField txtExpedienteMunicipal;
    private TextField txtNroContratacion;
    private TextField txtExpedienteContable;
    private DateField dtfFechaApertura;
    private TextField txtMontoContrato;
    private TextField txtAjuste;
    private ComboBox cmbEmpresas;
    private ComboBox cmbRepresentante;


    private BeanFieldGroup<Contratacion> beanFieldGroup = new BeanFieldGroup<>(Contratacion.class);
    private IhandlerLayContratacionBM handler;

    Boolean deshabilitarComponentesVistaProyecto;

    public LayContratacionBM(boolean deshabilitar){

        setMargin(true);
        setSpacing(true);
        setSizeFull();
        deshabilitarComponentesVistaProyecto = deshabilitar;

        if(! deshabilitar) {
            addComponent(new Label("<font color='white'><strong><big>Baja/Modificacion Contratación", ContentMode.HTML));
            addComponent(generarCmbProyectos());
            addComponent(generarFormContrataciones());
            addComponent(generarComandos());
        }
        else{
            addComponent(new Label("<font color='white'><strong><big>Visualizador de Contrataciónes", ContentMode.HTML));
            addComponent(generarCmbProyectos());
            addComponent(generarFormContrataciones());
            //deshabilitarComponentes();
        }

        beanFieldGroup.bind(txtExpedienteMunicipal, "expedienteMunicipal");
        beanFieldGroup.bind(txtNroContratacion, "numeroDeContratacion");
        beanFieldGroup.bind(txtExpedienteContable, "expedidnteContable");
        beanFieldGroup.bind(dtfFechaApertura, "fechaApertura");
        beanFieldGroup.bind(txtMontoContrato, "montoContratado");
        beanFieldGroup.bind(txtAjuste, "ajuste");
        beanFieldGroup.bind(cmbEmpresas, "empresa");
        beanFieldGroup.bind(cmbRepresentante, "representanteTecnico");
    }

    private void deshabilitarComponentes() {


        txtExpedienteMunicipal.setEnabled(false);
        txtNroContratacion.setEnabled(false);
        txtExpedienteContable.setEnabled(false);
        dtfFechaApertura.setEnabled(false);
        txtMontoContrato.setEnabled(false);
        txtAjuste.setEnabled(false);
        cmbEmpresas.setEnabled(false);
        cmbRepresentante.setEnabled(false);
        cmbProyectos.focus();
    }


    private Component generarComandos() {

        HorizontalLayout lay = new HorizontalLayout();
        lay.setSpacing(true);

        lay.addComponent(new Button("Limpiar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                cmbProyectos.clear();
                txtAjuste.clear();
                txtExpedienteContable.clear();
                txtNroContratacion.clear();
                txtMontoContrato.clear();
                txtExpedienteMunicipal.clear();
                cmbEmpresas.clear();
                cmbRepresentante.clear();
                cmbProyectos.focus();

            }
        }));

        lay.addComponent(new Button("Guardar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {


                if(!cmbProyectos.isValid() ||
                        !txtAjuste.isValid() ||
                        !txtExpedienteContable.isValid() ||
                        !txtNroContratacion.isValid() ||
                        !txtMontoContrato.isValid() ||
                        !txtExpedienteMunicipal.isValid() ||
                        !cmbEmpresas.isValid() ||
                        !cmbRepresentante.isValid()||
                        !(cmbProyectos.getValue() != null)){

                    Notification.show("Datos incorrectos y/o faltantes", Notification.Type.ERROR_MESSAGE);
                }else
                    ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea modificar esta contratacion?", "SI", "NO", new ConfirmDialog.Listener() {

                        private static final long serialVersionUID = 1L;
                        @Override
                        public void onClose(ConfirmDialog rta) {
                            if (rta.isConfirmed()) {
                                handler.modificarContratacion();
                            }
                        }
                    });
            }
        }));

        lay.addComponent(new Button("Eliminar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if(beanFieldGroup.getItemDataSource().getBean() != null){
                    ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea eliminar esta contratacion?", "SI", "NO", new ConfirmDialog.Listener() {

                        private static final long serialVersionUID = 1L;
                        @Override
                        public void onClose(ConfirmDialog rta) {
                            if (rta.isConfirmed()) {
                                handler.eliminarContratacion();
                            }
                        }
                    });
                }
            }
        }));

    return lay;

    }


    private Component generarCmbProyectos() {

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

                    if ((Proyecto) event.getProperty().getValue() != null) {

                        Contratacion contratacion = ServicioContratacion.getInstance().buscarContratacion((Proyecto) event.getProperty().getValue());
                        if (contratacion != null) {
                            beanFieldGroup.setItemDataSource(contratacion);
                            txtAjuste.setEnabled(false);
                            if(deshabilitarComponentesVistaProyecto){
                                deshabilitarComponentes();
                            }
                        } else {
                                Notification.show("Atencion", "Proyecto no posee contratacion",
                                Notification.Type.ERROR_MESSAGE);
                                cmbProyectos.focus();

                        }
                    }
                }
            }
        });


        return cmbProyectos;
    }

    private Component generarFormContrataciones() {

        GridLayout frmContrataciones = new GridLayout();
        frmContrataciones.setSpacing(true);
        frmContrataciones.setColumns(3);
        frmContrataciones.setRows(3);
        frmContrataciones.addComponent(generarTxtExpedienteMunicipal());
        frmContrataciones.addComponent(generarTxtNroContratacion());
        frmContrataciones.addComponent(generarTxtExpedienteContable());
        frmContrataciones.addComponent(generarDtfFechaApertura());
        frmContrataciones.addComponent(generarTxtMontoContrato());
        frmContrataciones.addComponent(generarTxtAjuste());
        frmContrataciones.addComponent(generarCmbEmpresas());
        frmContrataciones.addComponent(generarCmbRepresentante());
        return frmContrataciones;
    }

    private Component generarTxtExpedienteMunicipal() {

        txtExpedienteMunicipal = new TextField("Expediente municipal");
        txtExpedienteMunicipal.setRequired(true);
        //txtExpedienteMunicipal.addValidator(new RegexpValidator("",""));
        return txtExpedienteMunicipal;
    }

    private Component generarCmbRepresentante() {

        cmbRepresentante = new ComboBox("Representante tecnico",
                ServicioRepresentanteTecnico.getInstance().getRepresentanteTecnico());
        cmbRepresentante.setItemCaptionPropertyId("apellidoNombre");
        cmbRepresentante.setWidth("300px");
        cmbRepresentante.setFilteringMode(FilteringMode.CONTAINS);
        cmbRepresentante.addStyleName("my-modificado");
        cmbRepresentante.setNewItemsAllowed(false);
        cmbRepresentante.setNullSelectionAllowed(false);
        cmbRepresentante.setRequired(true);
        return cmbRepresentante;
    }

    private Component generarCmbEmpresas() {

        cmbEmpresas = new ComboBox("Empresa adjudicataria", ServicioEmpresa.getInstance().getEmpresas());
        cmbEmpresas.setItemCaptionPropertyId("razonSocial");
        cmbEmpresas.setWidth("300px");
        cmbEmpresas.setFilteringMode(FilteringMode.CONTAINS);
        cmbEmpresas.addStyleName("my-modificado");
        cmbEmpresas.setNewItemsAllowed(false);
        cmbEmpresas.setNullSelectionAllowed(false);
        cmbEmpresas.setRequired(true);
        return cmbEmpresas;
    }

    private Component generarTxtAjuste() {
        txtAjuste = new TextField("Ajuste");
        txtAjuste.setEnabled(false);
        return txtAjuste;
    }

    private Component generarTxtMontoContrato() {

        txtMontoContrato = new TextField("Monto contrato");
        txtMontoContrato.setRequired(true);
//        txtMontoContrato.addBlurListener(new FieldEvents.BlurListener() {
//            @Override
//            public void blur(FieldEvents.BlurEvent event) {
//
//                if(txtMontoContrato.getValue() != null &&
//                        !txtMontoContrato.getValue().equalsIgnoreCase("0")){
//
//
//                    String ajusteConPunto = txtAjuste.getValue().replace(",", ".");
//                    String montoConPunto = txtMontoContrato.getValue().replace(",", ".");
//                    //System.out.println(ajusteConPunto);
//                    //System.out.println(Double.parseDouble(txtMontoContrato.getValue()));
//
//                    double primero = Double.parseDouble(ajusteConPunto);
//                    double segundo = Double.parseDouble(txtMontoContrato.getValue());
//
//                    BigDecimal c = BigDecimal.valueOf(primero).subtract(BigDecimal.valueOf(segundo));
//                    String ajusteConComa = c.toString().replace(".", ",");
//                    txtAjuste.setValue(ajusteConComa);
//
//
//                }
//            }
//        });


        return txtMontoContrato;
    }

    private Component generarDtfFechaApertura() {

        dtfFechaApertura = new DateField("Fecha de apertura");
        dtfFechaApertura.setRequired(true);
        return dtfFechaApertura;
    }

    private Component generarTxtExpedienteContable() {

        txtExpedienteContable = new TextField("Expediente contable");
        txtExpedienteContable.setRequired(true);
        return txtExpedienteContable;
    }

    private Component generarTxtNroContratacion() {

        txtNroContratacion = new TextField("N° contratacion");
        txtNroContratacion.setRequired(true);
        //txtNroContratacion.addValidator(new RegexpValidator("",""));
        return txtNroContratacion;
    }

    public ComboBox getCmbProyectos() {
        return cmbProyectos;
    }

    public void setCmbProyectos(ComboBox cmbProyectos) {
        this.cmbProyectos = cmbProyectos;
    }

    public TextField getTxtExpedienteMunicipal() {
        return txtExpedienteMunicipal;
    }

    public void setTxtExpedienteMunicipal(TextField txtExpedienteMunicipal) {
        this.txtExpedienteMunicipal = txtExpedienteMunicipal;
    }

    public TextField getTxtNroContratacion() {
        return txtNroContratacion;
    }

    public void setTxtNroContratacion(TextField txtNroContratacion) {
        this.txtNroContratacion = txtNroContratacion;
    }

    public TextField getTxtExpedienteContable() {
        return txtExpedienteContable;
    }

    public void setTxtExpedienteContable(TextField txtExpedienteContable) {
        this.txtExpedienteContable = txtExpedienteContable;
    }

    public DateField getDtfFechaApertura() {
        return dtfFechaApertura;
    }

    public void setDtfFechaApertura(DateField dtfFechaApertura) {
        this.dtfFechaApertura = dtfFechaApertura;
    }

    public TextField getTxtMontoContrato() {
        return txtMontoContrato;
    }

    public void setTxtMontoContrato(TextField txtMontoContrato) {
        this.txtMontoContrato = txtMontoContrato;
    }

    public TextField getTxtAjuste() {
        return txtAjuste;
    }

    public void setTxtAjuste(TextField txtAjuste) {
        this.txtAjuste = txtAjuste;
    }

    public ComboBox getCmbEmpresas() {
        return cmbEmpresas;
    }

    public void setCmbEmpresas(ComboBox cmbEmpresas) {
        this.cmbEmpresas = cmbEmpresas;
    }

    public ComboBox getCmbRepresentante() {
        return cmbRepresentante;
    }

    public void setCmbRepresentante(ComboBox cmbRepresentante) {
        this.cmbRepresentante = cmbRepresentante;
    }

    public IhandlerLayContratacionBM getHandler() {
        return handler;
    }

    public void setHandler(IhandlerLayContratacionBM handler) {
        this.handler = handler;
    }

    public BeanFieldGroup<Contratacion> getBeanFieldGroup() {
        return beanFieldGroup;
    }

    public void setBeanFieldGroup(BeanFieldGroup<Contratacion> beanFieldGroup) {
        this.beanFieldGroup = beanFieldGroup;
    }

    public void modificarContratacionError() {

        Notification.show("Error al modificar contratacion", Notification.Type.ERROR_MESSAGE);
    }

    public void modificarContratacionOK() {

        Notification.show("Contratacion guardarda exitosamente", Notification.Type.HUMANIZED_MESSAGE);
    }

    public void eliminarContratacionesError() {
        Notification.show("Error al eliminar contratacion", Notification.Type.ERROR_MESSAGE);
    }

    public void eliminarContratacionOK() {

        Notification.show("Contratacion eliminada exitosamente", Notification.Type.HUMANIZED_MESSAGE);

    }
}
