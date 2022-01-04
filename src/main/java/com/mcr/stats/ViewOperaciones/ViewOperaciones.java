package com.mcr.stats.ViewOperaciones;

import java.io.Serializable;

import com.mcr.stats.ViewOperaciones.Proyecto.certificacion.LayCertificacion;
import com.mcr.stats.ViewOperaciones.Proyecto.certificacion.LayCertificacionBM;
import com.mcr.stats.ViewOperaciones.Proyecto.certificacion.LayCertificacionViewer;
import com.mcr.stats.ViewOperaciones.Proyecto.certificacion.LayCertificado;
import com.mcr.stats.ViewOperaciones.Proyecto.contratacion.LayContratacion;
import com.mcr.stats.ViewOperaciones.Proyecto.contratacion.LayContratacionBM;
import com.mcr.stats.presenter.*;
import com.mcr.stats.servicios.*;
import org.vaadin.dialogs.ConfirmDialog;

import com.mcr.stats.Ihandler.IhandlerViewSesion;
import com.mcr.stats.Iview.IViewOperaciones;
import com.mcr.stats.Iview.IViewSesion;
import com.mcr.stats.ViewOperaciones.DatosPersonales.LayDatosPersonales;
import com.mcr.stats.ViewOperaciones.Licencia.LayLicencia;
import com.mcr.stats.ViewOperaciones.Licencia.LayLicenciaBM;
import com.mcr.stats.ViewOperaciones.Licencia.LayLicenciaReporte;
import com.mcr.stats.ViewOperaciones.Proyecto.LayProyecto;
import com.mcr.stats.ViewOperaciones.Proyecto.LayProyectoBM;
import com.mcr.stats.ViewOperaciones.Reportes.LayReportes;
import com.mcr.stats.model.Usuario;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ViewOperaciones extends VerticalLayout implements IViewSesion, Serializable, ValueChangeListener {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String NAME = "viewOperaciones";

    private ViewOperacionesEncabezado viewOperacionesEncabezado;
    private ViewOperacionesCuerpo viewOperacionesCuerpo;
    private IhandlerViewSesion handler;


    public ViewOperaciones() {


        addComponent(generarEncabezado());
        addComponent(generarCuerpo());
        getViewOperacionesCuerpo().getNavegadorAdministrativo().addValueChangeListener(this);

    }

    private Component generarCuerpo() {

        this.setViewOperacionesCuerpo(new ViewOperacionesCuerpo());
        return this.getViewOperacionesCuerpo();
    }

    private Component generarEncabezado() {

        this.setViewOperacionesEncabezado(new ViewOperacionesEncabezado());
        return this.getViewOperacionesEncabezado();
    }

    @Override
    public void enter(ViewChangeEvent event) {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");

        if (usuario != null) {
            this.getViewOperacionesEncabezado().getLblEncabezado().setValue("<p><strong>Bienvenido: " +
                    usuario.getApellido().toUpperCase() + ", " + usuario.getNombre().toUpperCase() +
                    "<br>Perfil: " + usuario.getPerfil().recuperarPerfil().toUpperCase().toUpperCase() + " - " +
                    usuario.getAccesos().get(0).toUpperCase() + "<br></strong><p>");

            getViewOperacionesCuerpo().getNavegadorAdministrativo().init(usuario);
        }

    }


    public ViewOperacionesEncabezado getViewOperacionesEncabezado() {
        return viewOperacionesEncabezado;
    }


    public void setViewOperacionesEncabezado(ViewOperacionesEncabezado viewOperacionesEncabezado) {
        this.viewOperacionesEncabezado = viewOperacionesEncabezado;
    }


    public ViewOperacionesCuerpo getViewOperacionesCuerpo() {
        return viewOperacionesCuerpo;
    }


    public void setViewOperacionesCuerpo(ViewOperacionesCuerpo viewOperacionesCuerpo) {
        this.viewOperacionesCuerpo = viewOperacionesCuerpo;
    }

    @Override
    public void valueChange(ValueChangeEvent event) {

        if (event.getProperty().getValue() != null) {

            int valorSeleccionado = (Integer) event.getProperty().getValue();

            Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
            PresenterLicencia presenterLicencia;
            PresenterProyecto presenterProyecto;
            PresenterContratacion presenterContratacion;
            PresenterDatosPersonales presenterDP;
            PresenterCertificacion presenterCertificacion;
            PresenterCertificado presenterCertificado;

            System.out.println("valor seleccionado: " + valorSeleccionado);


            if (usuario.getAccesos().get(0).equalsIgnoreCase("Licencia de conducir")) {


                if (valorSeleccionado == 3 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) { //alta

                    LayLicencia layLicencia = new LayLicencia(usuario);
                    presenterLicencia = new PresenterLicencia(layLicencia, ServicioLicencia.getInstance());
                    layLicencia.setHandler(presenterLicencia);
                    cambioLayouts(layLicencia);
                }

                if (valorSeleccionado == 4 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) { //alta

                    LayLicenciaBM layLicenciaBM = new LayLicenciaBM();
                    presenterLicencia = new PresenterLicencia(layLicenciaBM, ServicioLicencia.getInstance());
                    layLicenciaBM.setHandler(presenterLicencia);
                    cambioLayouts(layLicenciaBM);
                }

                if (valorSeleccionado == 5 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) { //alta

                    LayDatosPersonales layDatosPersonales = new LayDatosPersonales();
                    presenterDP = new PresenterDatosPersonales(layDatosPersonales, ServicioSesion.getInstance());
                    layDatosPersonales.setHandler(presenterDP);
                    cambioLayouts(layDatosPersonales);
                }

                if (valorSeleccionado == 6 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) { //alta

                    ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea cerrar sesión?",
                            "SI", "NO", new ConfirmDialog.Listener() {

                                private static final long serialVersionUID = 1L;
                                @Override
                                public void onClose(ConfirmDialog rta) {
                                    if (rta.isConfirmed()) {
                                        handler.logout();
                                    }
                                }
                            });
                }


                if (valorSeleccionado == 4 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) { //alta

                    LayLicencia layLicencia = new LayLicencia(usuario);
                    presenterLicencia = new PresenterLicencia(layLicencia, ServicioLicencia.getInstance());
                    layLicencia.setHandler(presenterLicencia);
                    cambioLayouts(layLicencia);
                }

                if (valorSeleccionado == 5 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) { //baja / modificaciones

                    LayLicenciaBM layLicenciaBM = new LayLicenciaBM();
                    presenterLicencia = new PresenterLicencia(layLicenciaBM, ServicioLicencia.getInstance());
                    layLicenciaBM.setHandler(presenterLicencia);
                    cambioLayouts(layLicenciaBM);
                }
                if (valorSeleccionado == 6 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) { //reportes

                    LayReportes layResportes = new LayReportes(usuario);
                    PresenterReportes pReporter = new PresenterReportes(layResportes, ServicioLicencia.getInstance());
                    layResportes.setHandler(pReporter);
                    cambioLayouts(layResportes);
                }
            }


            //perfil obras publicas!
            if (usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - proyectos")) {


                if (valorSeleccionado == 4 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) { //alta proyecto

                    LayProyecto layProyecto = new LayProyecto();
                    presenterProyecto = new PresenterProyecto(layProyecto, ServicioProyecto.getInstance());
                    layProyecto.setHandler(presenterProyecto);
                    cambioLayouts(layProyecto);
                }


                if (valorSeleccionado == 6 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) { //alta proyecto

                    LayProyecto layProyecto = new LayProyecto();
                    presenterProyecto = new PresenterProyecto(layProyecto, ServicioProyecto.getInstance());
                    layProyecto.setHandler(presenterProyecto);
                    cambioLayouts(layProyecto);

                }

                if (valorSeleccionado == 6 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) { //Baja/Modificacion proyecto

                    LayProyectoBM layProyectoBM = new LayProyectoBM();
                    presenterProyecto = new PresenterProyecto(layProyectoBM, ServicioProyecto.getInstance());
                    layProyectoBM.setHandler(presenterProyecto);
                    cambioLayouts(layProyectoBM);
                }

                if (valorSeleccionado == 8 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) { //Baja/Modificacion proyecto

                    LayProyectoBM layProyectoBM = new LayProyectoBM();
                    presenterProyecto = new PresenterProyecto(layProyectoBM, ServicioProyecto.getInstance());
                    layProyectoBM.setHandler(presenterProyecto);
                    cambioLayouts(layProyectoBM);
                }

                if(valorSeleccionado == 10 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")){

                    LayReportes layReportes = new LayReportes(usuario);
                    PresenterReportes pReporter = new PresenterReportes(layReportes, ServicioProyecto.getInstance());
                    layReportes.setHandler(pReporter);
                    cambioLayouts(layReportes);
                }

                if(valorSeleccionado == 9 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")){

                    boolean dehabilitar = true;
                    LayContratacionBM layContratacionBM = new LayContratacionBM(dehabilitar);
                    cambioLayouts(layContratacionBM);
                }

//                if (valorSeleccionado == 7 && usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) { //Baja/Modificacion proyecto
//
//                    Lay layProyectoBM = new LayProyectoBM();
//                    presenterProyecto = new PresenterProyecto(layProyectoBM, ServicioProyecto.getInstance());
//                    layProyectoBM.setHandler(presenterProyecto);
//                    cambioLayouts(layProyectoBM);
//                }


            }


            if ((usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - contrataciones") && valorSeleccionado == 5) ||
                (usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - proyectos") && valorSeleccionado == 12) ||
                (usuario.getAccesos().get(0).equalsIgnoreCase("licencia de conducir") && valorSeleccionado == 8) ||
                (usuario.getAccesos().get(0).equalsIgnoreCase("Obras Publicas - Certificaciones") && valorSeleccionado == 8)) { //salir

                ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea cerrar sesión?",
                        "SI", "NO", new ConfirmDialog.Listener() {

                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog rta) {

                                if (rta.isConfirmed()) {
                                    handler.logout();
                                }
                            }
                        });

            }

            if ((usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - contrataciones") && valorSeleccionado == 14) ||
                (usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - proyectos") && valorSeleccionado == 11) ||
                (usuario.getAccesos().get(0).equalsIgnoreCase("licencia de conducir") && valorSeleccionado == 7) ||
                (usuario.getAccesos().get(0).equalsIgnoreCase("Obras Publicas - Certificaciones") && valorSeleccionado == 9)) {

                LayDatosPersonales layDatosPersonales = new LayDatosPersonales();
                presenterDP = new PresenterDatosPersonales(layDatosPersonales, ServicioSesion.getInstance());
                layDatosPersonales.setHandler(presenterDP);
                cambioLayouts(layDatosPersonales);
            }


            if(usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas") &&
                    (valorSeleccionado == 6 || valorSeleccionado == 7 || valorSeleccionado == 8 ||
                            valorSeleccionado == 10 || valorSeleccionado ==	12 || valorSeleccionado == 13 )){
                Notification.show("Atencion", "Sin implementacion (click)", Notification.Type.ERROR_MESSAGE);
            }

            if (usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - contrataciones")) {

                //por ahora no preguntamos nivel solo ya que solo es nivel 1

                if(valorSeleccionado == 3){

                    LayContratacion layContratacion = new LayContratacion();
                    presenterContratacion = new PresenterContratacion(layContratacion, ServicioContratacion.getInstance());
                    layContratacion.setHandler(presenterContratacion);
                    cambioLayouts(layContratacion);
                }
                if(valorSeleccionado == 4){

                    boolean dehabilitar = false;
                    LayContratacionBM layContratacionBM = new LayContratacionBM(dehabilitar);
                    presenterContratacion = new PresenterContratacion(layContratacionBM, ServicioContratacion.getInstance());
                    layContratacionBM.setHandler(presenterContratacion);
                    cambioLayouts(layContratacionBM);
                }

            }

            if (usuario.getAccesos().get(0).equalsIgnoreCase("obras publicas - certificaciones")) {

                if(valorSeleccionado == 4) {

                    LayCertificacion layCertificacion = new LayCertificacion();
                    presenterCertificacion = new PresenterCertificacion(layCertificacion, ServicioCertificacion.getInstance());
                    layCertificacion.setHandler(presenterCertificacion);
                    cambioLayouts(layCertificacion);
                }
                if(valorSeleccionado == 5) {

                    LayCertificado layCertificado = new LayCertificado();
                    presenterCertificado = new PresenterCertificado(layCertificado, ServicioCertificado.getInstance());
                    layCertificado.setHandler(presenterCertificado);
                    cambioLayouts(layCertificado);
                }

                if(valorSeleccionado == 6) {

                    LayCertificacionBM layCertificacionBM = new LayCertificacionBM();
                    presenterCertificacion = new PresenterCertificacion(layCertificacionBM, ServicioCertificacion.getInstance(), ServicioCertificado.getInstance());
                    layCertificacionBM.setHandlerCertificacion(presenterCertificacion);
                    layCertificacionBM.setHandlerCertificado(presenterCertificacion);
                    cambioLayouts(layCertificacionBM);

                }

                if(valorSeleccionado == 7) {

                    LayCertificacionViewer layViewer = new LayCertificacionViewer();
                    cambioLayouts(layViewer);

                }
            }
        }

    }

    private void cambioLayouts(VerticalLayout lay) {

        if (this.getViewOperacionesCuerpo().getDivisor().getSecondComponent() != null) {
            this.getViewOperacionesCuerpo().getDivisor().removeComponent(this.getViewOperacionesCuerpo().
                    getDivisor().getSecondComponent());
            this.getViewOperacionesCuerpo().getDivisor().addComponent(lay);
        } else this.getViewOperacionesCuerpo().getDivisor().addComponent(lay);
    }



    public IhandlerViewSesion getHandler() {
        return handler;
    }

    public void setHandler(IhandlerViewSesion handler) {
        this.handler = handler;
    }


}
