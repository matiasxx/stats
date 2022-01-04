package com.mcr.stats.ViewOperaciones;

import com.mcr.stats.model.Usuario;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;


public class ViewOperacionesCuerpo extends HorizontalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavegadorAdministrativo navegadorAdministrativo;
	private HorizontalSplitPanel divisor;

	public ViewOperacionesCuerpo() {

		
		setNavegadorAdministrativo(new NavegadorAdministrativo());		
		setDivisor(new HorizontalSplitPanel());
		getDivisor().setSplitPosition(295,Unit.PIXELS);
		setWidth("100%");
		getDivisor().addComponent(getNavegadorAdministrativo());
		addComponent(getDivisor());
		setExpandRatio(getDivisor(), 5);
		setMargin(true);
		setSizeFull();
		
	}


	public NavegadorAdministrativo getNavegadorAdministrativo() {
		return navegadorAdministrativo;
	}

	public void setNavegadorAdministrativo(NavegadorAdministrativo navegadorAdministrativo) {
		this.navegadorAdministrativo = navegadorAdministrativo;
	}

	public HorizontalSplitPanel getDivisor() {
		return divisor;
	}

	public void setDivisor(HorizontalSplitPanel divisor) {
		this.divisor = divisor;
	}
	
	
	
	
}
