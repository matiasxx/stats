package com.mcr.stats.model.contratacion;

import java.io.Serializable;
import java.util.Objects;

public class RepresentanteTecnico implements Serializable {

    private int idRepresentante;
    private String nombreRepresentante;
    private String apellidoRepresentante;
    private String apellidoNombre;
    private int estado;


    public RepresentanteTecnico(){

    }

    public int getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(int idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getApellidoRepresentante() {
        return apellidoRepresentante;
    }

    public void setApellidoRepresentante(String apellidoRepresentante) {
        this.apellidoRepresentante = apellidoRepresentante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getApellidoNombre() {
        return apellidoNombre;
    }

    public void setApellidoNombre(String apellidoNombre) {
        this.apellidoNombre = apellidoNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepresentanteTecnico that = (RepresentanteTecnico) o;
        return idRepresentante == that.idRepresentante && estado == that.estado && nombreRepresentante.equals(that.nombreRepresentante) && apellidoRepresentante.equals(that.apellidoRepresentante) && apellidoNombre.equals(that.apellidoNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepresentante, nombreRepresentante, apellidoRepresentante, apellidoNombre, estado);
    }
}

