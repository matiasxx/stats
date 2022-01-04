package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IservicioRepresentanteTecnico;
import com.mcr.stats.dao.DaoEmpresa;
import com.mcr.stats.dao.DaoRepresentanteTecnico;
import com.mcr.stats.model.contratacion.Empresa;
import com.mcr.stats.model.contratacion.RepresentanteTecnico;
import com.vaadin.data.util.BeanItemContainer;

import java.io.Serializable;

public class ServicioRepresentanteTecnico implements Serializable, IservicioRepresentanteTecnico {

    private static  ServicioRepresentanteTecnico instance;


    private ServicioRepresentanteTecnico(){

    }

    public static ServicioRepresentanteTecnico getInstance(){

        if(instance == null){
            instance = new ServicioRepresentanteTecnico();
        }
        return instance;
    }


    @Override
    public BeanItemContainer<RepresentanteTecnico> getRepresentanteTecnico() {

        BeanItemContainer<RepresentanteTecnico> containter = new BeanItemContainer<>
                (RepresentanteTecnico.class, DaoRepresentanteTecnico.getInstance().getRepresentanteTecnico());
        return containter;


    }
}
