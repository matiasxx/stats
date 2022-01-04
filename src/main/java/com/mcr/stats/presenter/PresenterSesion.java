package com.mcr.stats.presenter;

import java.io.Serializable;

import com.mcr.stats.Ihandler.IhandlerViewSesion;
import com.mcr.stats.model.Credencial;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.servicios.ServicioSesion;
import com.mcr.stats.ui.ViewSesion.ViewSesion;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;


public class PresenterSesion implements Serializable, IhandlerViewSesion{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewSesion view;
	private ServicioSesion service;
	
	public PresenterSesion(ViewSesion view, ServicioSesion service) {
	
		this.view = view;
		this.service = service;
	}

	@Override
	public void login() {
		
		Credencial credencial = new Credencial();
		credencial.setNombreDeUsuario(view.getTxtNombreDeUsuario().getValue());
		credencial.setContrasenia(view.getTxtContrasenia().getValue());
		
		Usuario usuario = service.login(credencial);
		
		if ( usuario != null) {
			view.loginOk(usuario);
		}else {
			view.loginError();			
		}
		
	}

	@Override
	public void logout() {
		
		VaadinSession.getCurrent().getSession().invalidate();
		Page.getCurrent().setLocation("/stats/");			
		
	}

}
