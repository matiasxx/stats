package com.mcr.stats.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.mcr.stats.Idao.IdaoResponsablePliego;
import com.mcr.stats.model.ResponsablePliego;

public class DaoResponsablePliego implements IdaoResponsablePliego{
	
	
	private static DaoResponsablePliego instance;
	
	private DaoResponsablePliego() {
		
	}
	
	public static DaoResponsablePliego getInstance() {
		
		if(instance == null) {
			instance = new DaoResponsablePliego();
		}
		return instance;
	}

	@Override
	public ArrayList<ResponsablePliego> getResponsablePliego() {
		
		String consulta = "select * from op_proyecto_responsablePliego rp where rp.estadoExistencia = 1";
		Vector<Object> datos = new Vector<>();
		ResultSet rs = null;
		ArrayList<ResponsablePliego> responsables = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				
				ResponsablePliego rp = new ResponsablePliego();
				rp.setIdResponsablePliego(rs.getInt(1));
				rp.setNombre(rs.getString(2));
				rp.setApellido(rs.getString(3));
				rp.setApellidoNombre(rp.getApellido()+", "+rp.getNombre());
				responsables.add(rp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responsables;
	}

}
