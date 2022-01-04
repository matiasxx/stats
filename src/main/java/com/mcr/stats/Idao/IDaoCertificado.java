package com.mcr.stats.Idao;

import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.certificacion.Certificado;

public interface IDaoCertificado {

    int guardarCertificado(Usuario usuario, Certificacion certificacion);
    int modificarCertificado(Usuario usuario, Certificacion certificacion, Certificado certificado);
    int eliminarCertificado(Usuario usuario, Certificacion certificacion, Certificado certificado);

}
