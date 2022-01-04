package com.mcr.stats.presenter;

import com.mcr.stats.Ihandler.IhandlerLayContratacion;
import com.mcr.stats.Ihandler.IhandlerLayContratacionBM;
import com.mcr.stats.ViewOperaciones.Proyecto.contratacion.LayContratacion;
import com.mcr.stats.ViewOperaciones.Proyecto.contratacion.LayContratacionBM;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.contratacion.Contratacion;
import com.mcr.stats.model.contratacion.Empresa;
import com.mcr.stats.model.contratacion.RepresentanteTecnico;
import com.mcr.stats.servicios.ServicioContratacion;
import com.vaadin.ui.UI;

import java.io.Serializable;

public class PresenterContratacion implements Serializable, IhandlerLayContratacion , IhandlerLayContratacionBM {

    private LayContratacion view;
    private LayContratacionBM viewBM;
    private ServicioContratacion service;


    public PresenterContratacion(LayContratacion view, ServicioContratacion service){

        this.view = view;
        this.service = service;
    }

    public PresenterContratacion(LayContratacionBM viewBM, ServicioContratacion service){

        this.viewBM = viewBM;
        this.service = service;
    }


    @Override
    public void guardarContratacion() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");

        Proyecto proyecto = (Proyecto) view.getCmbProyectos().getValue();
        Contratacion contratacion = new Contratacion();
        contratacion.setExpedienteMunicipal(view.getTxtExpedienteMunicipal().getValue());
        contratacion.setNumeroDeContratacion(view.getTxtNroContratacion().getValue());
        contratacion.setExpedidnteContable(view.getTxtExpedienteContable().getValue());
        contratacion.setFechaApertura(view.getDtfFechaApertura().getValue());
        contratacion.setMontoContratado(view.getTxtMontoContrato().getValue());
        contratacion.setAjuste(view.getTxtAjuste().getValue());
        contratacion.setEmpresa((Empresa) view.getCmbEmpresas().getValue());
        contratacion.setRepresentanteTecnico((RepresentanteTecnico) view.getCmbRepresentante().getValue());
        contratacion.setEstadoContratacion(1);

        proyecto.setContratacion(contratacion);

        int rta = service.guardarContratacion(usuario,proyecto);

        if(rta !=0){
            view.guardarContratacionOK();
            limpiar();
        }else view.guardarContratacionError();
    }

    @Override
    public void eliminarContratacion() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) viewBM.getCmbProyectos().getValue();
        proyecto.setContratacion(viewBM.getBeanFieldGroup().getItemDataSource().getBean());

        int rta = service.eliminarContratacion(usuario,proyecto);
        if(rta !=0){
            viewBM.eliminarContratacionOK();
            limpiarBM();
        }else viewBM.eliminarContratacionesError();

    }

    @Override
    public void modificarContratacion() {

        Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario");

        Proyecto proyecto = (Proyecto) viewBM.getCmbProyectos().getValue();

        System.out.println(proyecto.getContratacion().getIdContratacion());

        Contratacion contratacion = new Contratacion();
        contratacion.setIdContratacion(viewBM.getBeanFieldGroup().getItemDataSource().getBean().getIdContratacion());
        contratacion.setExpedienteMunicipal(viewBM.getTxtExpedienteMunicipal().getValue());
        contratacion.setNumeroDeContratacion(viewBM.getTxtNroContratacion().getValue());
        contratacion.setExpedidnteContable(viewBM.getTxtExpedienteContable().getValue());
        contratacion.setFechaApertura(viewBM.getDtfFechaApertura().getValue());
        contratacion.setMontoContratado(viewBM.getTxtMontoContrato().getValue());
        contratacion.setAjuste(viewBM.getTxtAjuste().getValue());
        contratacion.setEmpresa((Empresa) viewBM.getCmbEmpresas().getValue());
        contratacion.setRepresentanteTecnico((RepresentanteTecnico) viewBM.getCmbRepresentante().getValue());
        contratacion.setEstadoContratacion(1);

        proyecto.setContratacion(contratacion);

        int rta = service.modificarContratacion(usuario,proyecto);

        if(rta !=0){
            viewBM.modificarContratacionOK();
            limpiarBM();
        }else viewBM.modificarContratacionError();
    }

    @Override
    public void limpiarBM() {

        viewBM.getCmbProyectos().clear();
        viewBM.getTxtExpedienteMunicipal().clear();
        viewBM.getTxtNroContratacion().clear();
        viewBM.getTxtExpedienteContable().clear();
        viewBM.getDtfFechaApertura().clear();
        viewBM.getTxtMontoContrato().clear();
        viewBM.getTxtAjuste().clear();
        viewBM.getCmbEmpresas().clear();
        viewBM.getCmbRepresentante().clear();
        viewBM.getDtfFechaApertura().clear();
        viewBM.getCmbProyectos().focus();
    }

    @Override
    public void limpiar() {

        view.getCmbProyectos().clear();
        view.getTxtExpedienteMunicipal().clear();
        view.getTxtNroContratacion().clear();
        view.getTxtExpedienteContable().clear();
        view.getDtfFechaApertura().clear();
        view.getTxtMontoContrato().clear();
        view.getTxtAjuste().clear();
        view.getCmbEmpresas().clear();
        view.getCmbRepresentante().clear();
    }


}
