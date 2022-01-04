package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IServicioCertificacion;
import com.mcr.stats.dao.DaoCertificacion;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.contratacion.Contratacion;

import java.io.Serializable;

public class ServicioCertificacion implements IServicioCertificacion, Serializable {

    private static ServicioCertificacion instance;


    private ServicioCertificacion(){

    }

    public static ServicioCertificacion getInstance(){

        if(instance == null){
            instance = new ServicioCertificacion();
        }
        return instance;
    }


    @Override
    public int guardarCertificacion(Usuario usuario, Contratacion contratacion) {
        return DaoCertificacion.getInstance().guardarCertificacion(usuario,contratacion);
    }

    @Override
    public int modificarCertificacion(Usuario usuario, Contratacion contratacion) {
        return DaoCertificacion.getInstance().modificarCertificacion(usuario,contratacion);
    }

    @Override
    public int eliminarCertificacion(Usuario usuario, Contratacion contratacion) {
        return DaoCertificacion.getInstance().eliminarCertificacion(usuario,contratacion);
    }
}
