package com.mcr.stats.ViewOperaciones.Proyecto.certificacion;

import com.mcr.stats.Ihandler.IhandlerLayCertificado;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.servicios.ServicioProyecto;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;

import java.io.Serializable;

public class LayCertificado extends VerticalLayout implements Serializable {

    private ComboBox cmbNumeroContratacion;
    private Label lblNombreObral;
    private TextField txtCertificadoMonto;
    private TextField txtPeriodo;
    private DateField dtfFechaCertificado;

    private IhandlerLayCertificado handler;



    public LayCertificado(){

        setMargin(true);
        setSpacing(true);
        setSizeFull();

        addComponent(new Label("<font color='white'><strong><big>Alta Certificado", ContentMode.HTML));
        addComponent(generarCmbNumeroContratacion());
        addComponent(generarLblNombreObra());
        addComponent(generarLay());

        addComponent(generarComandos());

    }

    private Component generarComandos() {

        HorizontalLayout lay = new HorizontalLayout();
        lay.setSpacing(true);

        lay.addComponent(new Button("Guardar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea guardar "
                        + "esta certificación?", "SI", "NO", new ConfirmDialog.Listener() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClose(ConfirmDialog rta) {

                        if(rta.isConfirmed()){
                                handler.guardarCertificado();
                        }
                    }
                });

            }
        }));
        lay.addComponent(new Button("limpiar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                handler.limpiarCertificado();
            }
        }));

        return lay;

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

    private Component generarCmbNumeroContratacion() {

        cmbNumeroContratacion = new ComboBox("Nro Contratación", ServicioProyecto.getInstance().getProyectosCertificado());
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
                    }else lblNombreObral.setValue("<font color='white'><strong>Nombre de la obra: <br><big>");
                }


            }
        });
        return cmbNumeroContratacion;
    }

    private Component generarLblNombreObra() {

        lblNombreObral = new Label("<font color='white'><strong>Nombre de la obra:",ContentMode.HTML);
        return  lblNombreObral;
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

    public IhandlerLayCertificado getHandler() {
        return handler;
    }

    public void setHandler(IhandlerLayCertificado handler) {
        this.handler = handler;
    }
}
