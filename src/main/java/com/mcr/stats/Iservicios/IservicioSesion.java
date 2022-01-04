package com.mcr.stats.Iservicios;


import com.mcr.stats.model.Credencial;
import com.mcr.stats.model.Usuario;

public interface IservicioSesion {
	
	public Usuario login(Credencial credencial);
	public int logout(Usuario usuario);
	public int actualizar(Usuario usuario);
	

}
