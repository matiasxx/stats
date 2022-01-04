package com.mcr.stats.presenter;

import com.mcr.stats.Ihandler.IhandlerLayCertificado;
import com.mcr.stats.ViewOperaciones.Proyecto.certificacion.LayCertificado;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificado;
import com.mcr.stats.servicios.ServicioCertificado;
import com.vaadin.ui.UI;

import java.io.Serializable;
import java.util.ArrayList;

public class PresenterCertificado implements Serializable, IhandlerLayCertificado {


    private LayCertificado view;
    private ServicioCertificado service;

    public PresenterCertificado(LayCertificado view, ServicioCertificado service){

        this.view = view;
        this.service = service;
    }

    @Override
    public void guardarCertificado() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) view.getCmbNumeroContratacion().getValue();


        Certificado certificado = new Certificado();
        certificado.setMonto(view.getTxtCertificadoMonto().getValue());
        certificado.setPeriodo(view.getTxtPeriodo().getValue());
        certificado.setFechaCertificado(view.getDtfFechaCertificado().getValue());
        certificado.setEstadoExistencia(true);

        proyecto.getContratacion().getCertificacion().setCertificados(new ArrayList<Certificado>());
        proyecto.getContratacion().getCertificacion().getCertificados().add(certificado);



        int rta = service.guardarCertificado(usuario,proyecto.getContratacion().getCertificacion());
        limpiarCertificado();



    }

    @Override
    public void limpiarCertificado() {

        view.getCmbNumeroContratacion().clear();
        view.getLblNombreObral().setValue("");
        view.getTxtCertificadoMonto().clear();
        view.getTxtPeriodo().clear();
        view.getDtfFechaCertificado().clear();
    }

    @Override
    public void modificarCertificado() {

    }

    @Override
    public void eliminarCertificado() {

    }

    @Override
    public void limpiarCertificadoBM() {

    }
}
