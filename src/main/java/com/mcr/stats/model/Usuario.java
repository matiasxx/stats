package com.mcr.stats.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int idUsuario;
	private String nombre;
	private String apellido;
	private String email;
	private Perfil perfil;
	private Sector sector;
	private String numeroDeDocumento;
	private List<String> accesos;
	private Credencial credencial;
	private boolean estadoExistencia;
	
	
	
	public Usuario() {
		
		setAccesos(new ArrayList<String>());
	}



	public int getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}



	public Sector getSector() {
		return sector;
	}



	public void setSector(Sector sector) {
		this.sector = sector;
	}



	public Credencial getCredencial() {
		return credencial;
	}



	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}



	public boolean isEstadoExistencia() {
		return estadoExistencia;
	}



	public void setEstadoExistencia(boolean estadoExistencia) {
		this.estadoExistencia = estadoExistencia;
	}



	public List<String> getAccesos() {
		return accesos;
	}



	public void setAccesos(List<String> accesos) {
		this.accesos = accesos;
	}



	public String getNumeroDeDocumento() {
		return numeroDeDocumento;
	}



	public void setNumeroDeDocumento(String numeroDeDocumento) {
		this.numeroDeDocumento = numeroDeDocumento;
	}
	
	
}
