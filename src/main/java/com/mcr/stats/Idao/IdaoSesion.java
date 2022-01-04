package com.mcr.stats.Idao;


import com.mcr.stats.model.Credencial;
import com.mcr.stats.model.Usuario;

public interface IdaoSesion {


	
	public Usuario login(Credencial credencial);
	public int logout(Usuario usuario);

	
}
