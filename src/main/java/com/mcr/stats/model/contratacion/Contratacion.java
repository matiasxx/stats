package com.mcr.stats.model.contratacion;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.certificacion.Certificacion;

import java.io.Serializable;
import java.util.Date;

public class Contratacion implements Serializable {

    private int idContratacion;
    private String expedienteMunicipal;
    private String numeroDeContratacion;
    private String expedidnteContable;
    private Date fechaApertura;
    private String montoContratado;
    private String ajuste;
    private Empresa empresa;
    private RepresentanteTecnico representanteTecnico;
    private int estadoContratacion;

    private Certificacion certificacion;


    public Contratacion(){

    }

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public String getExpedienteMunicipal() {
        return expedienteMunicipal;
    }

    public void setExpedienteMunicipal(String expedienteMunicipal) {
        this.expedienteMunicipal = expedienteMunicipal;
    }

    public String getNumeroDeContratacion() {
        return numeroDeContratacion;
    }

    public void setNumeroDeContratacion(String numeroDeContratacion) {
        this.numeroDeContratacion = numeroDeContratacion;
    }

    public String getExpedidnteContable() {
        return expedidnteContable;
    }

    public void setExpedidnteContable(String expedidnteContable) {
        this.expedidnteContable = expedidnteContable;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getMontoContratado() {
        return montoContratado;
    }

    public void setMontoContratado(String montoContratado) {
        this.montoContratado = montoContratado;
    }

    public String getAjuste() {
        return ajuste;
    }

    public void setAjuste(String ajuste) {
        this.ajuste = ajuste;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getEstadoContratacion() {
        return estadoContratacion;
    }

    public void setEstadoContratacion(int estadoContratacion) {
        this.estadoContratacion = estadoContratacion;
    }

    public RepresentanteTecnico getRepresentanteTecnico() {return representanteTecnico;}

    public void setRepresentanteTecnico(RepresentanteTecnico representanteTecnico) {this.representanteTecnico = representanteTecnico;}

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(Certificacion certificacion) {
        this.certificacion = certificacion;
    }
}
