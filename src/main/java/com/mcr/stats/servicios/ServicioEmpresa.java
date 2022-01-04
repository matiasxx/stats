package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IservicioEmpresa;
import com.mcr.stats.dao.DaoEmpresa;
import com.mcr.stats.dao.DaoProyecto;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.contratacion.Empresa;
import com.vaadin.data.util.BeanItemContainer;

import java.util.ArrayList;

public class ServicioEmpresa implements IservicioEmpresa {

    private static ServicioEmpresa instance;


    private ServicioEmpresa(){

    }


    public static ServicioEmpresa getInstance(){

        if(instance == null){
            instance = new ServicioEmpresa();
        }
        return instance;
    }

    @Override
    public BeanItemContainer<Empresa> getEmpresas() {
        BeanItemContainer<Empresa> containter = new BeanItemContainer<>(Empresa.class, DaoEmpresa.getInstance().getEmpresas());
        return containter;
    }
}
