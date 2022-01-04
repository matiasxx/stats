package com.mcr.stats.model;

import com.mcr.stats.model.contratacion.Contratacion;

import java.io.Serializable;
import java.util.Date;

public class Proyecto implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int idProyecto;
    private CategoriaProyecto categoriaProyecto;
    private String subCategoria;
    private String nombreDeLaObra;
    private Sector sector;
    private String subSector;
    private String latitud;
    private String longitud;
    private String detalleUbicacion;
    private String memoriaDescriptivaDeLaObra;
    private String mtsLineales;
    private String mtsCuadrados;
    private String conexiones;
    private String beneficiarios;
    private String manzanas;
    private String parcelas;
    private TipoDeContratacion tipoDeContratacion;
    private String presupuestoOficial;
    private ResponsablePliego responsablePliego;
    private String plano;
    private Date fechaDeElevacion;
    private String pliegoLicitatorio;
    private String planillaCotizacion;
    private String otroDocumento;
    private boolean estadoExistencia;

    private Contratacion contratacion;

    public Proyecto() {
        // TODO Auto-generated constructor stub
    }

    public CategoriaProyecto getCategoriaProyecto() {
        return categoriaProyecto;
    }


    public void setCategoriaProyecto(CategoriaProyecto categoriaProyecto) {
        this.categoriaProyecto = categoriaProyecto;
    }


    public String getPlano() {
        return plano;
    }


    public void setPlano(String plano) {
        this.plano = plano;
    }


    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public boolean isEstadoExistencia() {
        return estadoExistencia;
    }

    public void setEstadoExistencia(boolean estadoExistencia) {
        this.estadoExistencia = estadoExistencia;
    }

    public String getSubCategoria() {
        return subCategoria;
    }


    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }


    public String getNombreDeLaObra() {
        return nombreDeLaObra;
    }


    public void setNombreDeLaObra(String nombreDeLaObra) {
        this.nombreDeLaObra = nombreDeLaObra;
    }


    public Sector getSector() {
        return sector;
    }


    public void setSector(Sector sector) {
        this.sector = sector;
    }


    public String getSubSector() {
        return subSector;
    }


    public void setSubSector(String subSector) {
        this.subSector = subSector;
    }


    public String getLatitud() {
        return latitud;
    }


    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }


    public String getLongitud() {
        return longitud;
    }


    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


    public String getDetalleUbicacion() {
        return detalleUbicacion;
    }


    public void setDetalleUbicacion(String detalleUbicacion) {
        this.detalleUbicacion = detalleUbicacion;
    }


    public String getMemoriaDescriptivaDeLaObra() {
        return memoriaDescriptivaDeLaObra;
    }


    public void setMemoriaDescriptivaDeLaObra(String memoriaDescriptivaDeLaObra) {
        this.memoriaDescriptivaDeLaObra = memoriaDescriptivaDeLaObra;
    }


    public String getMtsLineales() {
        return mtsLineales;
    }


    public void setMtsLineales(String mtsLineales) {
        this.mtsLineales = mtsLineales;
    }


    public String getMtsCuadrados() {
        return mtsCuadrados;
    }


    public void setMtsCuadrados(String mtsCuadrados) {
        this.mtsCuadrados = mtsCuadrados;
    }


    public String getConexiones() {
        return conexiones;
    }


    public void setConexiones(String conexiones) {
        this.conexiones = conexiones;
    }


    public String getBeneficiarios() {
        return beneficiarios;
    }


    public void setBeneficiarios(String beneficiarios) {
        this.beneficiarios = beneficiarios;
    }


    public String getManzanas() {
        return manzanas;
    }


    public void setManzanas(String manzanas) {
        this.manzanas = manzanas;
    }


    public String getParcelas() {
        return parcelas;
    }


    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }


    public TipoDeContratacion getTipoDeContratacion() {
        return tipoDeContratacion;
    }


    public void setTipoDeContratacion(TipoDeContratacion tipoDeContratacion) {
        this.tipoDeContratacion = tipoDeContratacion;
    }


    public String getPresupuestoOficial() {
        return presupuestoOficial;
    }


    public void setPresupuestoOficial(String presupuestoOficial) {
        this.presupuestoOficial = presupuestoOficial;
    }


    public ResponsablePliego getResponsablePliego() {
        return responsablePliego;
    }


    public void setResponsablePliego(ResponsablePliego responsablePliego) {
        this.responsablePliego = responsablePliego;
    }

    public Date getFechaDeElevacion() {
        return fechaDeElevacion;
    }

    public void setFechaDeElevacion(Date fechaDeElevacion) {
        this.fechaDeElevacion = fechaDeElevacion;
    }

    public String getPliegoLicitatorio() {
        return pliegoLicitatorio;
    }

    public void setPliegoLicitatorio(String pliegoLicitatorio) {
        this.pliegoLicitatorio = pliegoLicitatorio;
    }

    public String getPlanillaCotizacion() {
        return planillaCotizacion;
    }

    public void setPlanillaCotizacion(String planillaCotizacion) {
        this.planillaCotizacion = planillaCotizacion;
    }

    public String getOtroDocumento() {
        return otroDocumento;
    }

    public void setOtroDocumento(String otroDocumento) {
        this.otroDocumento = otroDocumento;
    }

    public Contratacion getContratacion() {
        return contratacion;
    }

    public void setContratacion(Contratacion contratacion) {
        this.contratacion = contratacion;
    }
}
