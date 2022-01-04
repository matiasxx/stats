package com.mcr.stats.Iservicios;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;

public interface IservicioProyecto {
	
	
	int guardarProyecto (Usuario usuario, Proyecto proyecto);
	int modificarProyecto (Usuario usuario, Proyecto proyecto);
	int eliminarProyecto (Usuario usuario, Proyecto proyecto);
	BeanItemContainer<Proyecto> getProyectos();
	BeanItemContainer<Proyecto> getProyectosContrataciones();
	BeanItemContainer<Proyecto> getProyectosCertificacion();
	BeanItemContainer<Proyecto> getProyectosCertificado();
	BeanItemContainer<Proyecto> getProyectosCompleto();
	BeanItemContainer<Proyecto> getProyectosTodo();
}
