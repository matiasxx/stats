package com.mcr.stats.ViewOperaciones.Proyecto.contratacion;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.mcr.stats.Ihandler.IhandlerLayContratacion;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.contratacion.Contratacion;
import com.mcr.stats.servicios.ServicioContratacion;
import com.mcr.stats.servicios.ServicioEmpresa;
import com.mcr.stats.servicios.ServicioProyecto;
import com.mcr.stats.servicios.ServicioRepresentanteTecnico;
import com.vaadin.data.Property;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.inputmask.InputMask;
import org.vaadin.inputmask.client.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

public class LayContratacion extends VerticalLayout implements Serializable {


    private ComboBox cmbProyectos;
    private TextField txtExpedienteMunicipal;
    private TextField txtNroContratacion;
    private TextField txtExpedienteContable;
    private DateField dtfFechaApertura;
    private TextField txtMontoContrato;
    InputMask currencyInputMask = new InputMask(Alias.CURRENCY);


    private TextField txtAjuste;
    private ComboBox cmbEmpresas;
    private ComboBox cmbRepresentante;

    private IhandlerLayContratacion handler;


    public LayContratacion(){

        setMargin(true);
        setSpacing(true);
        setSizeFull();


        addComponent(new Label("<font color='white'><strong><big>Alta Contratación", ContentMode.HTML));
        addComponent(generarCmbProyectos());
        addComponent(generarFormContrataciones());
        addComponent(generarComandos());
    }



    private Component generarComandos() {

        HorizontalLayout lay = new HorizontalLayout();
        lay.setSpacing(true);
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
                !cmbRepresentante.isValid()){

                    Notification.show("Datos incorrectos y/o faltantas", Notification.Type.ERROR_MESSAGE);
                }else
                    ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea guardar este registro?", "SI", "NO", new ConfirmDialog.Listener() {

                        private static final long serialVersionUID = 1L;
                        @Override
                        public void onClose(ConfirmDialog rta) {
                            if (rta.isConfirmed()) {
                                handler.guardarContratacion();
                            }
                        }
                    });
            }
        }));


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

        return lay;
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

    private Component generarCmbRepresentante() {
        cmbRepresentante = new ComboBox("Representante tecnico", ServicioRepresentanteTecnico.getInstance().getRepresentanteTecnico());
        cmbRepresentante.setItemCaptionPropertyId("apellidoNombre");
        cmbRepresentante.setWidth("300px");
        cmbRepresentante.setFilteringMode(FilteringMode.CONTAINS);
        cmbRepresentante.addStyleName("my-modificado");
        cmbRepresentante.setNewItemsAllowed(false);
        cmbRepresentante.setNullSelectionAllowed(false);

        cmbRepresentante.setWidth("300px");
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
        txtMontoContrato.setInputPrompt("Decimal con coma");
        txtMontoContrato.setRequired(true);

//        currencyInputMask.setPrefix("");
//        currencyInputMask.setGroupSeparator(".");
//        currencyInputMask.setRadixPoint(",");
//        currencyInputMask.extend(txtMontoContrato);

        ;


        txtMontoContrato.addBlurListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {


                if (!txtMontoContrato.getValue().equalsIgnoreCase("")) {

                    if(!txtMontoContrato.getValue().contains(".")) {

                        if (txtMontoContrato.getValue() != null &&
                                !txtMontoContrato.getValue().equalsIgnoreCase("0")) {

                            String ajusteConPunto = txtAjuste.getValue().replace(",", ".");
                            String montoConPunto = txtMontoContrato.getValue().replace(",", ".");


                            // System.out.println(ajusteConPunto);
                            // System.out.println(montoConPunto);
                            //System.out.println(ajusteConPunto);
                            //System.out.println(Double.parseDouble(txtMontoContrato.getValue()));

                            double primero = Double.parseDouble(ajusteConPunto);
                            double segundo = Double.parseDouble(montoConPunto);

                            BigDecimal c = BigDecimal.valueOf(primero).subtract(BigDecimal.valueOf(segundo));
                            String ajusteConComa = c.toString().replace(".", ",");

                            txtAjuste.setValue(ajusteConComa);

                            //  System.out.println(ajusteConPunto);
                            //  System.out.println(montoConPunto);
                            //  System.out.println(ajusteConComa);
                        }
                    }else Notification.show("La parte decimal del valor debe expresarse con coma", Notification.Type.ERROR_MESSAGE);
                }
            }
        });


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

    private Component generarTxtExpedienteMunicipal() {

        txtExpedienteMunicipal = new TextField("Expediente municipal");
        txtExpedienteMunicipal.setRequired(true);
        //txtExpedienteMunicipal.addValidator(new RegexpValidator("",""));
        return txtExpedienteMunicipal;
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

                if(event !=null){
                    Proyecto proyecto = (Proyecto) event.getProperty().getValue();
                    if(proyecto != null) {
                        txtAjuste.setValue(proyecto.getPresupuestoOficial());

                        txtExpedienteContable.clear();
                        txtNroContratacion.clear();
                        txtMontoContrato.clear();
                        txtExpedienteMunicipal.clear();
                        cmbEmpresas.clear();
                        cmbRepresentante.clear();
                        cmbProyectos.focus();
                    }
                }



            }
        });


        return cmbProyectos;
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

    public IhandlerLayContratacion getHandler() {
        return handler;
    }

    public void setHandler(IhandlerLayContratacion handler) {
        this.handler = handler;
    }

    public void guardarContratacionOK() {

        Notification.show("Contratacion guardarda exitosamente", Notification.Type.HUMANIZED_MESSAGE);
    }

    public void guardarContratacionError() {

        Notification.show("Error al guardar contratacion", Notification.Type.ERROR_MESSAGE);
    }


}
