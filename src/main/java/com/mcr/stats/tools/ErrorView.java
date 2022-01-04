package com.mcr.stats.tools;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ErrorView extends VerticalLayout implements View{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label explicacion;
	
	public ErrorView(){
		
		setMargin(true);
		setSpacing(true);
		
		Label header = new Label("<p><strong>La vista no puede ser encontrada<strong></p>", ContentMode.HTML);
		header.addStyleName(ValoTheme.LABEL_H1);
		addComponent(header);
		addComponent(explicacion = new Label("",ContentMode.HTML));
	}
	

	@Override
	public void enter(ViewChangeEvent event) {
		
		
		
		explicacion.setValue(String.format("Esta intentando acceder a una vista -> ('%s')) que no existe."
				+ "Por favor reingrese al sistemas: <a href='http://www.comodoro.gov.ar/stats/'> Reingreso sistema de estadisticas</a> ",event.getViewName()));
		
		
		
//		Notification notificacion = new Notification("Atención","Verifique su correo electronico para completar el proceso de suscripción. "
//	    		+ "<br><b>Si no recibe un correo en los siguientes minutos, pudiera ser"
//	    		+ "<br><b>que ingresó mal la dirección de correo electrónico ó que se filtró como SPAM o Correo No Deseado."
//	    		+ "<br><b>Si el proceso no se completa en las siguientes 24 hs, se cancelaró y deberó realizar una nueva suscripción  "
//	    		+ "<br><a href='http://www.comodoro.gov.ar/eboleta2/'>Volver a e-boleta</a> ", Type.ASSISTIVE_NOTIFICATION,true);
		
	}

	public Label getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(Label explicacion) {
		this.explicacion = explicacion;
	}
	
	
	

}
