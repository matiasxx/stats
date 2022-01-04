package com.mcr.stats.model.contratacion;

import java.io.Serializable;
import java.util.Objects;

public class Empresa implements Serializable {

    private int idEmpresa;
    private String razonSocial;
    private String cuit;
    private int estadoEmpresa;


    public Empresa(){

    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public int getEstadoEmpresa() {
        return estadoEmpresa;
    }

    public void setEstadoEmpresa(int estadoEmpresa) {
        this.estadoEmpresa = estadoEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return idEmpresa == empresa.idEmpresa && estadoEmpresa == empresa.estadoEmpresa && Objects.equals(razonSocial, empresa.razonSocial) && Objects.equals(cuit, empresa.cuit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, razonSocial, cuit, estadoEmpresa);
    }
}
