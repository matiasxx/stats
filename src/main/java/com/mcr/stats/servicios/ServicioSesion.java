package com.mcr.stats.servicios;

import java.io.Serializable;
import com.mcr.stats.Iservicios.IservicioSesion;
import com.mcr.stats.dao.DaoSesion;
import com.mcr.stats.model.Credencial;
import com.mcr.stats.model.Usuario;

public class ServicioSesion implements Serializable, IservicioSesion{


	private static final long serialVersionUID = 1L;
	private static ServicioSesion instance;
	
	
	private ServicioSesion(){
		
	}
	
	public static ServicioSesion getInstance(){
		
		if(instance == null){
			instance = new ServicioSesion();
		}
		return instance;
		
	}

	@Override
	public Usuario login(Credencial credencial) {
		
		return DaoSesion.getInstance().login(credencial);
	}

	@Override
	public int logout(Usuario usuario) {
		
		return DaoSesion.getInstance().logout(usuario);
	}

	@Override
	public int actualizar(Usuario usuario) {
		
		return DaoSesion.getInstance().actualizar(usuario);
	}

	
	

}
