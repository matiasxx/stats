package com.mcr.stats.presenter;

import java.io.Serializable;

import com.mcr.stats.Ihandler.IhandlerLayDatosPersonales;
import com.mcr.stats.ViewOperaciones.DatosPersonales.LayDatosPersonales;
import com.mcr.stats.model.Credencial;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.servicios.ServicioSesion;
import com.vaadin.ui.UI;

public class PresenterDatosPersonales implements Serializable, IhandlerLayDatosPersonales{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LayDatosPersonales view;
	private ServicioSesion service;
	
	
	public PresenterDatosPersonales(LayDatosPersonales view, ServicioSesion service) {
		
		this.view = view;
		this.service = service;
	}


	@Override
	public void actualizar() {
		
		
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");
		usuario.setNombre(view.getTxtNombre().getValue());
		usuario.setApellido(view.getTxtApellido().getValue());
		usuario.setNumeroDeDocumento(view.getTxtNumeroDeDocumento().getValue());
		usuario.getCredencial().setContrasenia(view.getTxtPassword().getValue());
		
		if(service.actualizar(usuario) != 0) {
			view.actualizarOK();	
			view.getTxtNombre().clear();
			view.getTxtApellido().clear();
			view.getTxtNumeroDeDocumento().clear();
			view.getTxtPassword().clear();
			view.getTxtPasswordReingreso().clear();
		}else view.actualizarError();
	}
	
	
	
	
	
	
	

}
