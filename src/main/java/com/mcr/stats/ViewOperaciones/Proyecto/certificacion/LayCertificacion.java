package com.mcr.stats.ViewOperaciones.Proyecto.certificacion;

import com.mcr.stats.Ihandler.IhandlerLayCertificacion;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.contratacion.Contratacion;
import com.mcr.stats.servicios.ServicioContratacion;
import com.mcr.stats.servicios.ServicioProyecto;
import com.vaadin.data.Property;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import javafx.geometry.HPos;
import org.eclipse.jdt.internal.core.CreateTypeHierarchyOperation;
import org.vaadin.dialogs.ConfirmDialog;

import java.io.Serializable;
import java.util.Date;

public class LayCertificacion extends VerticalLayout implements Serializable {


    private ComboBox cmbNumeroContratacion;
    private Label lblNombreObral;
    private DateField dtfFechaInicio;
    private TextField txtModificacionProyecto;
    private TextField txtACuadroModificatorio;
    private TextField txtObservaciones;
    private TextField txtRedeterminacion;

    private IhandlerLayCertificacion handler;

    public LayCertificacion() {

        setMargin(true);
        setSpacing(true);
        setSizeFull();

        addComponent(new Label("<font color='white'><strong><big>Alta Certificacion", ContentMode.HTML));
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
                            if(dtfFechaInicio.isValid()) {
                                handler.guardarCertificacion();
                            }else Notification.show("Fecha de inicio es requerida", Notification.Type.ERROR_MESSAGE);
                        }
                    }
                });
            }
        }));
        lay.addComponent(new Button("Limpiar"));

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

    public IhandlerLayCertificacion getHandler() {
        return handler;
    }

    public void setHandler(IhandlerLayCertificacion handler) {
        this.handler = handler;
    }

    public void guardarCertificacionOK() {

        Notification noti = new Notification("Certificacion guardada correctamente", Notification.Type.ASSISTIVE_NOTIFICATION);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }

    public void guardarCertificacionError() {

        Notification noti = new Notification("Certificacion no ha podido ser guardada", Notification.Type.ERROR_MESSAGE);
        noti.setDelayMsec(1500);
        noti.setPosition(Position.MIDDLE_CENTER);
        noti.show(Page.getCurrent());
    }
}
