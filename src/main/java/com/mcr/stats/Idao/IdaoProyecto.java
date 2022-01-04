package com.mcr.stats.Idao;

import java.util.ArrayList;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;

public interface IdaoProyecto {
	
	
	int guardarProyecto(Usuario usuario, Proyecto proyecto);
	int modificarProyecto(Usuario usuario, Proyecto proyecto);
	int eliminarProyecto(Usuario usuario, Proyecto proyecto);
	ArrayList<Proyecto> getProyectos();
	ArrayList<Proyecto> getProyectosContrataciones();
	ArrayList<Proyecto> getProyectosCertificacion();
	ArrayList<Proyecto> getProyectosCertificado();
	ArrayList<Proyecto> getProyectosCompleto();
	ArrayList<Proyecto> getProyectosTodo();


}
