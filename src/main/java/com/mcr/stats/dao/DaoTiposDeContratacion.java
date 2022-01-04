package com.mcr.stats.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.mcr.stats.Idao.IdaoTiposDeContratacion;
import com.mcr.stats.model.CategoriaProyecto;
import com.mcr.stats.model.TipoDeContratacion;

public class DaoTiposDeContratacion implements IdaoTiposDeContratacion{

	
	private static DaoTiposDeContratacion instance;
	
	private DaoTiposDeContratacion() {
		
	}
	
	public static DaoTiposDeContratacion getInstance() {
		
		if(instance == null) {
			instance = new DaoTiposDeContratacion();			
		}
		return instance;
	}
	
	
	
	@Override
	public ArrayList<TipoDeContratacion> getTiposDeConcentracion() {
		
		String consulta = "select * from op_proyecto_tipoContratacion tc where tc.estadoExistencia = 1";
		Vector<Object> datos = new Vector<>();
		ResultSet rs = null;
		ArrayList<TipoDeContratacion> contrataciones = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				
				TipoDeContratacion tc = new TipoDeContratacion();
				tc.setIdTipo(rs.getInt(1));
				tc.setTipoDeContratacion(rs.getString(2).toUpperCase());
				contrataciones.add(tc);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return contrataciones;
	}
	
	
	
	

}
