package com.mcr.stats.servicios;

import com.mcr.stats.Iservicios.IServicioCertificado;
import com.mcr.stats.dao.DaoCertificado;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.certificacion.Certificado;

import java.io.Serializable;

public class ServicioCertificado implements Serializable, IServicioCertificado {

    private static ServicioCertificado instance;

    private ServicioCertificado(){

    }

    public static ServicioCertificado getInstance(){

        if(instance == null){
            instance = new ServicioCertificado();
        }return instance;
    }


    @Override
    public int guardarCertificado(Usuario usuario, Certificacion certificacion) {
        return DaoCertificado.getInstance().guardarCertificado(usuario,certificacion);
    }

    @Override
    public int modificarCertificado(Usuario usuario, Certificacion certificacion, Certificado certificado) {
        return DaoCertificado.getInstance().modificarCertificado(usuario,certificacion, certificado);
    }

    @Override
    public int eliminarCertificado(Usuario usuario, Certificacion certificacion, Certificado certificado) {
        return DaoCertificado.getInstance().eliminarCertificado(usuario,certificacion, certificado);
    }
}
