package com.mcr.stats.servicios;

import java.io.Serializable;

import com.mcr.stats.Iservicios.IservicioProyecto;
import com.mcr.stats.dao.DaoProyecto;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;

public class ServicioProyecto implements IservicioProyecto, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioProyecto instance;
	
	private ServicioProyecto() {
		
	}
	
	public static ServicioProyecto getInstance() {
		
		if(instance == null) {
			instance = new ServicioProyecto();
		}
		return instance;		
	}

	@Override
	public int guardarProyecto(Usuario usuario, Proyecto proyecto) {
		return DaoProyecto.getInstance().guardarProyecto(usuario, proyecto) ;
	}

	@Override
	public BeanItemContainer<Proyecto> getProyectos() {
		
		BeanItemContainer<Proyecto> containter = new BeanItemContainer<>(Proyecto.class, DaoProyecto.getInstance().getProyectos());
		containter.addNestedContainerProperty("categoriaProyecto.nombreCategoria");
		containter.addNestedContainerProperty("sector.nombreSector");
		return containter;
	}

	@Override
	public BeanItemContainer<Proyecto> getProyectosContrataciones() {

		BeanItemContainer<Proyecto> containter = new BeanItemContainer<>(Proyecto.class,
				DaoProyecto.getInstance().getProyectosContrataciones());
		return containter;
	}

	@Override
	public BeanItemContainer<Proyecto> getProyectosCertificacion() {
		return null;
	}

	@Override
	public BeanItemContainer<Proyecto> getProyectosCertificado() {

		BeanItemContainer<Proyecto> containter = new BeanItemContainer<>(Proyecto.class,
				DaoProyecto.getInstance().getProyectosCertificado());
		containter.addNestedContainerProperty("contratacion.numeroDeContratacion");
		return containter;
	}

	@Override
	public BeanItemContainer<Proyecto> getProyectosCompleto() {

		BeanItemContainer<Proyecto> containter = new BeanItemContainer<>(Proyecto.class,
				DaoProyecto.getInstance().getProyectosCompleto());
		containter.addNestedContainerProperty("contratacion.numeroDeContratacion");
		return containter;
	}

	@Override
	public BeanItemContainer<Proyecto> getProyectosTodo() {

		BeanItemContainer<Proyecto> containter = new BeanItemContainer<>(Proyecto.class,
				DaoProyecto.getInstance().getProyectosTodo());
		containter.addNestedContainerProperty("contratacion.numeroDeContratacion");
		return containter;
	}

	@Override
	public int modificarProyecto(Usuario usuario, Proyecto proyecto) {
		// TODO Auto-generated method stub
		return DaoProyecto.getInstance().modificarProyecto(usuario, proyecto);
	}

	@Override
	public int eliminarProyecto(Usuario usuario, Proyecto proyecto) {
		
		return DaoProyecto.getInstance().eliminarProyecto(usuario, proyecto);
	}
	
	

}
