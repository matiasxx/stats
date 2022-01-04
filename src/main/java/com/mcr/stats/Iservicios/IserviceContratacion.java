package com.mcr.stats.Iservicios;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.contratacion.Contratacion;
import com.vaadin.data.util.BeanItemContainer;

import java.util.ArrayList;

public interface IserviceContratacion {


    int guardarContratacion(Usuario usuario, Proyecto proyecto);
    int eliminarContratacion(Usuario usuario, Proyecto proyecto);
    int modificarContratacion(Usuario usuario, Proyecto proyecto);
    BeanItemContainer<Contratacion> getContrataciones();

    Contratacion buscarContratacion(Proyecto proyecto);
}
