package com.mcr.stats.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.mcr.stats.Idao.IdaoCategoria;
import com.mcr.stats.model.CategoriaProyecto;

public class DaoCategoria implements IdaoCategoria{

	
	
	private static DaoCategoria instance;
	
	private DaoCategoria() {
		
	}
	
	public static DaoCategoria getInstance() {
		
		if(instance == null) {
			instance = new DaoCategoria();
		}
		return instance;
	}
	
	
	
	@Override
	public ArrayList<CategoriaProyecto> getCategorias() {
		
		String consulta = "select * from op_proyecto_categoria pc where pc.estadoExistencia = 1";
		Vector<Object> datos = new Vector<>();
		ResultSet rs = null;
		ArrayList<CategoriaProyecto> categorias = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				
				CategoriaProyecto cp = new CategoriaProyecto();
				cp.setIdCategoria(rs.getInt(1));
				cp.setNombreCategoria(rs.getString(2).toUpperCase());
				categorias.add(cp);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return categorias;
	}
	
	

}
