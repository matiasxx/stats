package com.mcr.stats.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.mcr.stats.Idao.IdaoSector;
import com.mcr.stats.model.CategoriaProyecto;
import com.mcr.stats.model.Sector;

public class DaoSector implements IdaoSector {

	
	private static DaoSector instance;
	
	private DaoSector() {
		
	}
	
	public static DaoSector getInstance() {
		
		if(instance == null) {			
			instance = new DaoSector();		}
		return instance;
	}
	
	@Override
	public ArrayList<Sector> getSectores() {
		
		String consulta = "select * from op_proyecto_sector s where s.estadoExistencia = 1";
		Vector<Object> datos = new Vector<>();
		ResultSet rs = null;
		ArrayList<Sector> sectores = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				
				Sector s = new Sector();
				s.setIdSector(rs.getInt(1));
				s.setNombreSector(rs.getString(2).toUpperCase());
				sectores.add(s);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return sectores;
		
	}
	
	
	

}
