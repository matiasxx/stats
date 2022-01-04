package com.mcr.stats;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.mcr.stats.ViewOperaciones.ViewOperaciones;
import com.mcr.stats.presenter.PresenterSesion;
import com.mcr.stats.servicios.ServicioSesion;
import com.mcr.stats.tools.ErrorView;
import com.mcr.stats.ui.ViewSesion.ViewSesion;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("stats")
@Title("Sistema de Carga Estadistica - MCR")
public class StatsUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(StatsUI.class);
	Navigator navigator;
	
	

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		addStyleName("backColorGrey");
		
		log.info("**** Inicio de sistema....****");
		
		
		//System.out.println(vaadinRequest.getRemoteAddr());		
		//String ipAddress = vaadinRequest.getHeader("x-forwarded-for");		
		//System.out.println(ipAddress);
		
		
		navigator = new Navigator(this, this);
		
		ViewSesion viewSesion = new ViewSesion();
		PresenterSesion presenterSesion = new PresenterSesion(viewSesion, ServicioSesion.getInstance());
		viewSesion.setHandler(presenterSesion);
		
		ViewOperaciones viewOperaciones = new ViewOperaciones();
		viewOperaciones.setHandler(presenterSesion);
		
		
		
		
		navigator.addView("", viewSesion);
		navigator.addView(ViewOperaciones.NAME, viewOperaciones);
		navigator.setErrorView(ErrorView.class);
		
		
		
		
		System.out.println(hashPassword("antilef01"));
		//System.out.println();
		//System.out.println(hashPassword("navarro01"));
		//System.out.println(hashPassword("guzman01"));
		//System.out.println(hashPassword("soto01"));

	
		
	}
    
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
//	
//	private boolean checkPass(String plainPassword, String hashedPassword) {
//		
//		boolean rta = false;		
//		if (BCrypt.checkpw(plainPassword, hashedPassword)) {
//			rta = true;
//		}
//		return rta;	
//	}

	@WebServlet(urlPatterns = "/*", name = "UIStatsServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = StatsUI.class, productionMode = false)
    public static class UIStatsServlet extends VaadinServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	}
}
