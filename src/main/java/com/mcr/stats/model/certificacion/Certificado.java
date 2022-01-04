package com.mcr.stats.model.certificacion;

import java.io.Serializable;
import java.util.Date;

public class Certificado implements Serializable {

    private int idCertificado;
    private String monto;
    private String periodo;
    private Date fechaCertificado;
    private boolean estadoExistencia;



    public Certificado(){

    }

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getFechaCertificado() {
        return fechaCertificado;
    }

    public void setFechaCertificado(Date fechaCertificado) {
        this.fechaCertificado = fechaCertificado;
    }

    public boolean isEstadoExistencia() {
        return estadoExistencia;
    }

    public void setEstadoExistencia(boolean estadoExistencia) {
        this.estadoExistencia = estadoExistencia;
    }
}
