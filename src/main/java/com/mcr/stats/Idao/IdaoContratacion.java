package com.mcr.stats.Idao;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.contratacion.Contratacion;

import java.util.ArrayList;

public interface IdaoContratacion {

    int guardarContratacion(Usuario usuario, Proyecto proyecto);
    int eliminarContratacion(Usuario usuario, Proyecto proyecto);
    int modificarContratacion(Usuario usuario, Proyecto proyecto);
    ArrayList<Contratacion> getContrataciones();

    Contratacion buscarContratacion(Proyecto proyecto);
}
