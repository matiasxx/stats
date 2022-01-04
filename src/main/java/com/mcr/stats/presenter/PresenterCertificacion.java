package com.mcr.stats.presenter;

import com.mcr.stats.Ihandler.IhandlerLayCertificacion;
import com.mcr.stats.Ihandler.IhandlerLayCertificado;
import com.mcr.stats.ViewOperaciones.Proyecto.certificacion.LayCertificacion;
import com.mcr.stats.ViewOperaciones.Proyecto.certificacion.LayCertificacionBM;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.certificacion.Certificado;
import com.mcr.stats.model.contratacion.Contratacion;
import com.mcr.stats.servicios.ServicioCertificacion;
import com.mcr.stats.servicios.ServicioCertificado;
import com.mcr.stats.servicios.ServicioProyecto;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import java.io.Serializable;
import java.util.ArrayList;

public class PresenterCertificacion implements Serializable, IhandlerLayCertificacion, IhandlerLayCertificado {

    private LayCertificacion view;
    private LayCertificacionBM viewBM;
    private ServicioCertificacion service;
    private ServicioCertificado serviceCertificado;

    public PresenterCertificacion(LayCertificacion view, ServicioCertificacion service){

        this.view = view;
        this.service = service;
    }

    public PresenterCertificacion(LayCertificacionBM viewBM, ServicioCertificacion service){

        this.viewBM = viewBM;
        this.service = service;
    }

    public PresenterCertificacion(LayCertificacionBM viewBM, ServicioCertificacion service, ServicioCertificado serviceCertificado){

        this.viewBM = viewBM;
        this.service = service;
        this.serviceCertificado = serviceCertificado;
    }

    @Override
    public void guardarCertificacion() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) view.getCmbNumeroContratacion().getValue();

        Certificacion certificacion = new Certificacion();
        certificacion.setFechaInicio(view.getDtfFechaInicio().getValue());
        certificacion.setModificacionProyecto(view.getTxtModificacionProyecto().getValue());
        certificacion.setCuadroModificatoriol(view.getTxtACuadroModificatorio().getValue());
        certificacion.setObservaciones(view.getTxtObservaciones().getValue());
        certificacion.setRedeterminacion(view.getTxtRedeterminacion().getValue());
        certificacion.setEstadoExistencia(true);

        certificacion.setNumeroDeContratacion(proyecto.getContratacion().getNumeroDeContratacion());
        proyecto.getContratacion().setCertificacion(certificacion);

       if(service.guardarCertificacion(usuario, proyecto.getContratacion()) != 0) {
           view.guardarCertificacionOK();
           limpiarCertificacion();
       }else view.guardarCertificacionError();


    }

    @Override
    public void limpiarCertificacion() {

        view.getCmbNumeroContratacion().clear();
        view.getDtfFechaInicio().clear();
        view.getTxtModificacionProyecto().clear();
        view.getTxtACuadroModificatorio().clear();
        view.getTxtObservaciones().clear();
        view.getTxtRedeterminacion().clear();
    }

   @Override
    public void modificarCertificacion() {

       Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
       Proyecto proyecto = (Proyecto) viewBM.getCmbNumeroContratacion().getValue();

       proyecto.getContratacion().getCertificacion().setFechaInicio(viewBM.getDtfFechaInicio().getValue());
       proyecto.getContratacion().getCertificacion().setModificacionProyecto(viewBM.getTxtModificacionProyecto().getValue());
       proyecto.getContratacion().getCertificacion().setCuadroModificatoriol(viewBM.getTxtACuadroModificatorio().getValue());

       proyecto.getContratacion().getCertificacion().setObservaciones(viewBM.getTxtObservaciones().getValue());
       proyecto.getContratacion().getCertificacion().setRedeterminacion(viewBM.getTxtRedeterminacion().getValue());
       proyecto.getContratacion().getCertificacion().setEstadoExistencia(true);

       proyecto.getContratacion().getCertificacion().setNumeroDeContratacion(proyecto.getContratacion().getNumeroDeContratacion());
       if (service.modificarCertificacion(usuario, proyecto.getContratacion()) != 0) {
           viewBM.modificarCertificacionOK();
           limpiarCertificacionBM();
       } else viewBM.modificarCertificacionError();

    }

    public void limpiarCertificacionBM() {

           viewBM.getCmbNumeroContratacion().clear();
           viewBM.getDtfFechaInicio().clear();
           viewBM.getTxtModificacionProyecto().clear();
           viewBM.getTxtACuadroModificatorio().clear();
           viewBM.getTxtObservaciones().clear();
           viewBM.getTxtRedeterminacion().clear();
    }

    @Override
    public void eliminarCertificacion() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) viewBM.getCmbNumeroContratacion().getValue();

        if (service.eliminarCertificacion(usuario, proyecto.getContratacion()) != 0) {
            viewBM.eliminarCertificacionOK();
            viewBM.getCmbNumeroContratacion().setContainerDataSource(ServicioProyecto.getInstance().getProyectosTodo());
            limpiarCertificacionBM();
        } else viewBM.eliminarCertificacionError();
    }

    @Override
    public void eliminarCertificado() {



        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) viewBM.getCmbNumeroContratacion().getValue();

        Certificado certificado = (Certificado) viewBM.getFilterTablaCertificados().getValue();

        if (serviceCertificado.eliminarCertificado(usuario,proyecto.getContratacion().getCertificacion(), certificado) !=0){
            viewBM.eliminarCertificadoOK();
            limpiarCertificado();
        }else viewBM.eliminarCertificadoError();

    }

    @Override
    public void limpiarCertificadoBM() {


    }


    @Override
    public void limpiarCertificado() {


        viewBM.getTxtCertificadoMonto().clear();
        viewBM.getTxtPeriodo().clear();
        viewBM.getDtfFechaCertificado().clear();
        //viewBM.getFilterTablaCertificados().clear();
        


        if(viewBM.getFilterTablaCertificados().getValue() != null) {
            viewBM.getContainer().removeItem(viewBM.getFilterTablaCertificados().getValue());
            viewBM.getFilterTablaCertificados().refreshRowCache();
        }



    }
    @Override
    public void guardarCertificado() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) viewBM.getCmbNumeroContratacion().getValue();


        Certificado certificado = new Certificado();
        certificado.setMonto(viewBM.getTxtCertificadoMonto().getValue());
        certificado.setPeriodo(viewBM.getTxtPeriodo().getValue());
        certificado.setFechaCertificado(viewBM.getDtfFechaCertificado().getValue());
        certificado.setEstadoExistencia(true);

        proyecto.getContratacion().getCertificacion().setCertificados(new ArrayList<Certificado>());
        proyecto.getContratacion().getCertificacion().getCertificados().add(certificado);

        if (serviceCertificado.guardarCertificado(usuario,proyecto.getContratacion().getCertificacion()) != 0){
            viewBM.guardarCertificadoOK();
        }else viewBM.guardarCertificadoError();

        limpiarCertificado();

    }

    @Override
    public void modificarCertificado() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) viewBM.getCmbNumeroContratacion().getValue();

        Certificado certificado = (Certificado) viewBM.getFilterTablaCertificados().getValue();
        certificado.setMonto(viewBM.getTxtCertificadoMonto().getValue());
        certificado.setPeriodo(viewBM.getTxtPeriodo().getValue());
        certificado.setFechaCertificado(viewBM.getDtfFechaCertificado().getValue());

        //proyecto.getContratacion().getCertificacion().setCertificados(new ArrayList<Certificado>());
        //proyecto.getContratacion().getCertificacion().getCertificados().add(certificado);

        if (serviceCertificado.modificarCertificado(usuario,proyecto.getContratacion().getCertificacion(), certificado) !=0){
            viewBM.modificarCertificadoOK();

            Item itemTabla = (Item) viewBM.getFilterTablaCertificados().getItem(viewBM.getFilterTablaCertificados().getValue());
            itemTabla.getItemProperty("monto").setValue(certificado.getMonto());
            itemTabla.getItemProperty("periodo").setValue(certificado.getPeriodo());
            itemTabla.getItemProperty("fechaCertificado").setValue(certificado.getFechaCertificado());
            limpiarCertificadoBM();

        }else viewBM.modificarCertificadoError();

    }
}
