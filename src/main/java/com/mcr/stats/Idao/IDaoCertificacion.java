package com.mcr.stats.Idao;

import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.contratacion.Contratacion;

public interface IDaoCertificacion {

    int guardarCertificacion(Usuario usuario, Contratacion contratacion);
    int modificarCertificacion(Usuario usuario, Contratacion contratacion);
    int eliminarCertificacion(Usuario usuario, Contratacion contratacion);
}
