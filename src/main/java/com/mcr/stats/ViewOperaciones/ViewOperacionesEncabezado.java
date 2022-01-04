package com.mcr.stats.ViewOperaciones;

import java.io.File;


import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

public class ViewOperacionesEncabezado extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lblEncabezado;
	private Image membrete;
	
	public ViewOperacionesEncabezado() {
		
		
		addComponent(generarEncabezado());
		addComponent(generarImagenStats());		
		addStyleName("view-encabezado");
		setSizeFull();
		setHeight("97px");
		
		setComponentAlignment(getMembrete(), Alignment.BOTTOM_RIGHT);
		setComponentAlignment(getLblEncabezado(), Alignment.BOTTOM_LEFT);
	}

	private Component generarImagenStats() {
		
		
//		FileResource resource = new FileResource(new File(
//				getClass().getClassLoader().getResource("membrete.png").getFile()));
//		
//		this.setMembrete(new Image("Comodoro Digital", resource));
//		return this.getMembrete();
		

		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath +"/WEB-INF/imagenes/membrete.png"));
		
		//this.setImage(new Image("Centro Puyerredon Medicina del Trabajo", resource));
		//return this.getImage();
		
		setMembrete(new Image("Comodoro Digital", resource));
		return getMembrete();
		
	}

	private Component generarEncabezado() {
		
		this.setLblEncabezado(new Label("", ContentMode.HTML));
		return this.getLblEncabezado();
	}

	public Label getLblEncabezado() {
		return lblEncabezado;
	}

	public void setLblEncabezado(Label lblEncabezado) {
		this.lblEncabezado = lblEncabezado;
	}

	public Image getMembrete() {
		return membrete;
	}

	public void setMembrete(Image membrete) {
		this.membrete = membrete;
	}
	
	
}
