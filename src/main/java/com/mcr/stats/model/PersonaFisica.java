package com.mcr.stats.model;

public class PersonaFisica extends ClasePersona{
	
	

	private String nombre;
	private String apellido;
	private String tipoDeDocumento;
	private String numeroDeDocumento;
	private String genero;
	private String edad;
	private String tipoPersona;
	private boolean estadoExistencia;
	
	
	
	public PersonaFisica() {
					
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


	public String getTipoDeDocumento() {
		return tipoDeDocumento;
	}


	public void setTipoDeDocumento(String tipoDeDocumento) {
		this.tipoDeDocumento = tipoDeDocumento;
	}


	public String getNumeroDeDocumento() {
		return numeroDeDocumento;
	}


	public void setNumeroDeDocumento(String numeroDeDocumento) {
		this.numeroDeDocumento = numeroDeDocumento;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getEdad() {
		return edad;
	}


	public void setEdad(String edad) {
		this.edad = edad;
	}


	public boolean isEstadoExistencia() {
		return estadoExistencia;
	}


	public void setEstadoExistencia(boolean estadoExistencia) {
		this.estadoExistencia = estadoExistencia;
	}


	public String getTipoPersona() {
		return tipoPersona;
	}


	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((edad == null) ? 0 : edad.hashCode());
		result = prime * result + (estadoExistencia ? 1231 : 1237);
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((numeroDeDocumento == null) ? 0 : numeroDeDocumento.hashCode());
		result = prime * result + ((tipoDeDocumento == null) ? 0 : tipoDeDocumento.hashCode());
		result = prime * result + ((tipoPersona == null) ? 0 : tipoPersona.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaFisica other = (PersonaFisica) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (edad == null) {
			if (other.edad != null)
				return false;
		} else if (!edad.equals(other.edad))
			return false;
		if (estadoExistencia != other.estadoExistencia)
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numeroDeDocumento == null) {
			if (other.numeroDeDocumento != null)
				return false;
		} else if (!numeroDeDocumento.equals(other.numeroDeDocumento))
			return false;
		if (tipoDeDocumento == null) {
			if (other.tipoDeDocumento != null)
				return false;
		} else if (!tipoDeDocumento.equals(other.tipoDeDocumento))
			return false;
		if (tipoPersona == null) {
			if (other.tipoPersona != null)
				return false;
		} else if (!tipoPersona.equals(other.tipoPersona))
			return false;
		return true;
	}
	
	

}
