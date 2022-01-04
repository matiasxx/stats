package com.mcr.stats.model;

public abstract class ClasePersona {

	private int id;
	
	
	public ClasePersona() {
		
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClasePersona other = (ClasePersona) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
	
}
