package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IserviceContratacion;
import com.mcr.stats.dao.DaoContratacion;
import com.mcr.stats.dao.DaoProyecto;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.contratacion.Contratacion;
import com.vaadin.data.util.BeanItemContainer;

import java.io.Serializable;
import java.util.ArrayList;

public class ServicioContratacion implements IserviceContratacion , Serializable{

    private static ServicioContratacion instance;

    private ServicioContratacion(){
    }

    public static ServicioContratacion getInstance(){

        if(instance == null){
            instance = new ServicioContratacion();
        }
        return instance;
    }

    @Override
    public int guardarContratacion(Usuario usuario, Proyecto proyecto) {
        return DaoContratacion.getInstance().guardarContratacion(usuario,proyecto);
    }

    @Override
    public int eliminarContratacion(Usuario usuario, Proyecto proyecto) {
        return DaoContratacion.getInstance().eliminarContratacion(usuario,proyecto);
    }

    @Override
    public int modificarContratacion(Usuario usuario, Proyecto proyecto) {
        return DaoContratacion.getInstance().modificarContratacion(usuario,proyecto);
    }

    @Override
    public BeanItemContainer<Contratacion> getContrataciones() {

        BeanItemContainer<Contratacion> containter = new BeanItemContainer<>(Contratacion.class,
                DaoContratacion.getInstance().getContrataciones());
        return containter;
    }

    @Override
    public Contratacion buscarContratacion(Proyecto proyecto) {
        return DaoContratacion.getInstance().buscarContratacion(proyecto);
    }
}
