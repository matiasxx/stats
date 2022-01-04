package com.mcr.stats.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.mcr.stats.Idao.IdaoCarnet;
import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.TipoDeCarnet;

public class DaoCarne implements IdaoCarnet{
	
	
	
	private static DaoCarne instance;
	private static Logger log = Logger.getLogger(DaoCarne.class);
	
	private DaoCarne() {
		
	}
	
	public static DaoCarne getInstance() {
		
		if(instance == null) {
			instance = new DaoCarne();
		}
		return instance;
	}
	
	

	@Override
	public ArrayList<ClaseDeCarnet> getClaseCarne() {
		
		System.out.println("entro a getClaseCarne()");
		String consulta = "select * from clasesDeCarnet cde where cde.estadoExistencia = 1";
		Vector<Object> datos = new Vector<>();
		ResultSet rs = null;
		ArrayList<ClaseDeCarnet> clases = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			//conexion.setConexion(ConnectionPool.getInstance().getConnection());
			//conexion.getConexion().setAutoCommit(false);
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				
				ClaseDeCarnet cdc = new ClaseDeCarnet();
				cdc.setId(rs.getInt(1));
				cdc.setClaseDeCarnet(rs.getString(2));
				cdc.setEstadoExistencia(rs.getInt(3) > 0);
				clases.add(cdc);
				
			}
			
		} catch (SQLException e) {
			log.error("Error en getClaseCarne:"+e) ;
		}	
		return clases;
		
		
	}

	@Override
	public ArrayList<TipoDeCarnet> getTipoCarne() {
		
		String consulta = "select * from tipoDeCarnet tdc where tdc.estadoExistencia = 1";
		Vector<Object> datos = new Vector<>();
		ResultSet rs = null;
		ArrayList<TipoDeCarnet> clases = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			//conexion.setConexion(ConnectionPool.getInstance().getConnection());
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				
				//TipoDeCarnet tdc = new TipoDeCarnet();
				//tdc.setId(rs.getInt(1));
				//tdc.setNombreTipoDeCarnet(rs.getString(2));
				//tdc.setEstadoExistencia(rs.getInt(3) > 0);
				TipoDeCarnet tdc = new TipoDeCarnet(rs.getInt(1));
				clases.add(tdc);
			}
			conexion.cerrarRecursos();
			
		} catch (SQLException e) {
			log.error("Error en getTipoCarne:" + e);
		}	
		return clases;
	}

	@Override
	public ArrayList<ClaseDeCarnet> getClaseCarne(String valor) {
		
		String consulta = "select * from clasesDeCarnet cde where cde.estadoExistencia = 1 "
				+ "and cde.clasesDeCarne like '%"+valor+"%'";
		Vector<Object> datos = new Vector<>();
		ResultSet rs = null;
		ArrayList<ClaseDeCarnet> clases = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			//conexion.setConexion(ConnectionPool.getInstance().getConnection());		
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				
				ClaseDeCarnet cdc = new ClaseDeCarnet();
				cdc.setId(rs.getInt(1));
				cdc.setClaseDeCarnet(rs.getString(2));
				cdc.setEstadoExistencia(rs.getInt(3) > 0);
				clases.add(cdc);
			}
			conexion.cerrarRecursos();
		} catch (SQLException e) {
			log.error("Error en getClaseCarne:"+e);
		}	
		return clases;
		
	}

}
