package com.mcr.stats.ViewOperaciones.Proyecto.certificacion;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.certificacion.Certificado;
import com.mcr.stats.servicios.ServicioProyecto;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.Iterator;

public class LayCertificacionViewer extends VerticalLayout {

    private ComboBox cmbNumeroContratacion;
    private Label lblNombreObral;

    public LayCertificacionViewer(){

        setMargin(true);
        setSpacing(true);
        setSizeFull();

        addComponent(new Label("<font color='white'><strong><big> Visualizador de Certificados", ContentMode.HTML));
        addComponent(generarCmbNumeroContratacion());
        addComponent(generarLblNombreObra());
    }

    private Component generarLblNombreObra() {

        lblNombreObral = new Label("<font color='white'><strong>Nombre de la obra:",ContentMode.HTML);
        return  lblNombreObral;
    }


    private Component generarCmbNumeroContratacion() {

        cmbNumeroContratacion = new ComboBox("Nro Contratación", ServicioProyecto.getInstance().getProyectosCompleto());
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

                        String lblAuxiliar = //" <p style='color:white;font-size:14px;'>" +
                                " <font color='white'><strong>Nombre de la obra: <br>"+proyecto.getNombreDeLaObra()+"" +
                                "" +
                                "<br>Expediente contable: "+proyecto.getContratacion().getExpedidnteContable()+
                                "<br>Monto contradado: "+proyecto.getContratacion().getMontoContratado()+
                                "<br>Numero de certificacion: "+proyecto.getContratacion().getCertificacion().getIdCertificacion()+
                                "<br>Cantidad de certificados: " + proyecto.getContratacion().getCertificacion().getCertificados().size()+" ";


                        if (proyecto.getContratacion().getCertificacion().getCertificados().size() != 0) {

                            lblAuxiliar = lblAuxiliar + //"<br><table style='width:100%'  cellpadding='3' border='1'> " +
                                    "                    <br>" +
                                    "                    <br><table border='1' style='width:75%'> " +
                                    "                    <tr>" +
                                    "                    <th align='center'>Monto Certificado <strong/><bold/></th>" +
                                    "                    <th align='center'>Periodo<strong/><bold/></th>" +
                                    "                    <th align='center'>Fecha certificado<strong/><bold/></th>" +
                                    "                    </tr>";


                            Iterator<Certificado> iteradorCertificado = proyecto.getContratacion().getCertificacion().getCertificados().iterator();
                            while (iteradorCertificado.hasNext()) {

                                Certificado certificado = iteradorCertificado.next();
                                lblAuxiliar = lblAuxiliar + "<tr>"+
                                        "<td align='center'>"+certificado.getMonto() +"</td>"+
                                        "<td align='center'>"+certificado.getPeriodo()+"</td>"+
                                        "<td align='center'>"+certificado.getFechaCertificado()+"</td>"+
                                        "</tr>";
                            }
                            lblAuxiliar = lblAuxiliar +"</table> ";
                        }
                        lblNombreObral.setValue(lblAuxiliar);
                    }else lblNombreObral.setValue("<p style='color:white;font-size:14px;'> <font color='white'><strong>Nombre de la obra: <br><big></p>");
                }
            }
        });
        return cmbNumeroContratacion;
    }

}
