package com.mcr.stats.model.certificacion;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.contratacion.Contratacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Certificacion implements Serializable {


    private int idCertificacion;
    private Date fechaInicio;
    private String modificacionProyecto;
    private String montoAcumulado;
    private String porcentajeAvance;
    private String redeterminacion;
    private String cuadroModificatoriol;
    private String observaciones;
    private String numeroDeContratacion;
    private boolean estadoExistencia;
    private ArrayList<Certificado> certificados;





    public Certificacion(){

    }

    public int getIdCertificacion() {
        return idCertificacion;
    }

    public void setIdCertificacion(int idCertificacion) {
        this.idCertificacion = idCertificacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getModificacionProyecto() {
        return modificacionProyecto;
    }

    public void setModificacionProyecto(String modificacionProyecto) {
        this.modificacionProyecto = modificacionProyecto;
    }

    public String getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(String montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }

    public String getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(String porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public String getRedeterminacion() {
        return redeterminacion;
    }

    public void setRedeterminacion(String redeterminacion) {
        this.redeterminacion = redeterminacion;
    }

    public String getCuadroModificatoriol() {
        return cuadroModificatoriol;
    }

    public void setCuadroModificatoriol(String cuadroModificatoriol) {
        this.cuadroModificatoriol = cuadroModificatoriol;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isEstadoExistencia() {
        return estadoExistencia;
    }

    public void setEstadoExistencia(boolean estadoExistencia) {
        this.estadoExistencia = estadoExistencia;
    }

    public String getNumeroDeContratacion() {
        return numeroDeContratacion;
    }
    public void setNumeroDeContratacion(String numeroDeContratacion) {
        this.numeroDeContratacion = numeroDeContratacion;
    }

    public ArrayList<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(ArrayList<Certificado> certificados) {
        this.certificados = certificados;
    }
}
