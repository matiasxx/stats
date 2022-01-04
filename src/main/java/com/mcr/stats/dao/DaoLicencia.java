package com.mcr.stats.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.mcr.stats.Idao.IdaoLicencia;
import com.mcr.stats.model.ClaseDeCarnet;
import com.mcr.stats.model.ClasePersona;
import com.mcr.stats.model.PersonaFisica;
import com.mcr.stats.model.TipoDeCarnet;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.cargas.LicenciaConducir;
import com.vaadin.server.VaadinService;

public class DaoLicencia implements IdaoLicencia{

	
	private static DaoLicencia instance;
	
	private static final Logger log = Logger.getLogger(DaoLicencia.class);
	
	private DaoLicencia() {
		
		
	}
	
	public static DaoLicencia getInstance() {
		
		if(instance == null) {
			instance = new DaoLicencia();
		}
		return instance;
	}
	
	
	@Override
	public int guardarLicencia(Usuario usuario, LicenciaConducir licencia) {
		
		
		if (verificarCargaMismoDia(licencia) != 3) {		
		
		String consulta = "select * from personas p where p.tipoDocumento = ? and p.numeroDeDocumento = ? and p.estadoExistencia = 1";
		Vector<Object>datos = new Vector<>();
		
		PersonaFisica persona = (PersonaFisica) licencia.getPersonaFisica();
		datos.add(persona.getTipoDeDocumento());
		datos.add(persona.getNumeroDeDocumento());
		ResultSet rs = null;
		int idPersona = 0;
		int idLicencia = 0;
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rs = conexion.select(consulta, datos);
			if(rs.next()) { //persona existe			
				persona.setId(rs.getInt(1)); //recupero id de la persona			
			}else { //persona no existe, la insertamos
				
				consulta = "insert into personas (nombre, apellido, tipoDocumento, " +
						"numeroDeDocumento, genero, edad, estadoExistencia) values (?,?,?,?,?,?,?)";
				datos.clear();
				datos.add(persona.getNombre());
				datos.add(persona.getApellido());
				datos.add(persona.getTipoDeDocumento());
				datos.add(persona.getNumeroDeDocumento());
				datos.add(persona.getGenero());
				datos.add(persona.getEdad());
				datos.add(true);
				idPersona = conexion.insert(consulta, datos);
				persona.setId(idPersona);			
			}
			
			consulta = "insert into licenciaConducir (fechaCarga,fechaEstadistica,idPersona, idTipoDeCarne, estadoExistencia, lugarEmision, estadoAprobacion)"
					+ " values (?,?,?,?,?,?,?)";
			datos.clear();
			datos.add(new Date(licencia.getFechaCarga().getTime()));
			datos.add(new Date(licencia.getFechaEstadistica().getTime()));
			datos.add(persona.getId());
			datos.add(licencia.getTipoDeCarnet().getId());
			datos.add(true);
			datos.add(licencia.getLugarDeEmision().toUpperCase());
			datos.add(0);
			idLicencia = conexion.insert(consulta, datos);
			
			
			Iterator<ClaseDeCarnet> iterador = licencia.getClaseDeCarnet().iterator();
			while(iterador.hasNext()) {
			
				ClaseDeCarnet cdc = iterador.next();
				consulta = "insert into ldc_cdc(idLicencia,idCdC,estadoExistencia) values (?,?,1)";
				datos.clear();
				datos.add(idLicencia);
				datos.add(cdc.getId());
				conexion.insert(consulta, datos);
			}
				
				consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
				datos.clear();
				datos.add(new Timestamp(new java.util.Date().getTime()));
				datos.add(usuario.getIdUsuario());
				datos.add("guardarLicencia() - Licencia conducir");
				datos.add(idLicencia);
				
				int idLog = conexion.insert(consulta, datos);
				
				if(idLog != 0) {					
					conexion.getConexion().commit();
					conexion.cerrarRecursos();
					return idLog;
				}else {
					conexion.getConexion().rollback();
				}		
		} catch (SQLException e) {
			log.error("Error guardarLicencia:" + e);
		}
		return 0;
		
		}else return 3; //retorno un 3 para indicar que el mismo dia ya se cargo a esa persona
	}

	private int verificarCargaMismoDia(LicenciaConducir licencia) {
		
		String consulta = "select * from personas p where p.numeroDeDocumento = ? and p.estadoExistencia = 1";
		Vector<Object> datos = new Vector<>();
		datos.add(licencia.getPersonaFisica().getNumeroDeDocumento());
		ResultSet rs = null;
		ResultSet rsLicencia = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			if(rs.next()) {
				//la persona existe. Es necesario verificar si el mismo dia tuvo carga
				int idPersona = rs.getInt(1);
				consulta = "select * from licenciaConducir lc where lc.idPersona = ? and lc.estadoExistencia = 1 and lc.fechaCarga = ?";
				datos.clear();
				datos.add(idPersona);
				datos.add(new Date(licencia.getFechaCarga().getTime()));				
				rsLicencia = conexion.queryConsulta(consulta, datos);
				if(rsLicencia.next()) {										
					log.info("** ATENCION POSIBLE CARGA PARA UNA PERSONA EL MISMO DIA **");
					return 3;					
				}else return 1; //esta ok, puede tener licencias cargadas pero puede haber sido en otra fecha
							
			}else return 1; //la persona no existe, entonces es posible guardar licencia-persona
			
			
		} catch (SQLException e) {
			log.error("Error al generar verificacionMismoDia: " + e);			
		}	
		return 1; //saldra por return anteriores
	}

	@Override
	public ArrayList<LicenciaConducir> buscarLicencias(java.util.Date fechaDesde, java.util.Date fechaHasta) {
	
		String consulta = "select * from licenciaConducir lc where lc.estadoExistencia = 1 and "
				+ "lc.fechaCarga BETWEEN ? AND ?;";
		Vector<Object>datos = new Vector<>();
		datos.add(new Date(fechaDesde.getTime()));
		datos.add(new Date(fechaHasta.getTime()));
		ResultSet rs = null;
		ArrayList<LicenciaConducir> licencias = new ArrayList<>();
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rs = conexion.select(consulta, datos);
			while(rs.next()) {
								
				LicenciaConducir licencia = new LicenciaConducir();
				licencia.setId(rs.getInt(1));
				licencia.setFechaCarga(rs.getDate(2));
				licencia.setLugarDeEmision(rs.getString(7));
				licencia.setPersonaFisica(buscarContribuyente(rs.getInt(4),conexion));
				licencia.setClaseDeCarnet(buscarClasesDeCarne(rs.getInt(1),conexion));
				licencia.setTipoDeCarnet(new TipoDeCarnet(rs.getInt(5)));				
				licencias.add(licencia);
				
			}
			conexion.cerrarRecursos();
		} catch (SQLException e) {
			log.error("Error buscarLicencias:" + e);			
		}
		
		return licencias;
	}

	private ArrayList<ClaseDeCarnet> buscarClasesDeCarne(int idLicencia, Conexion conexion) throws SQLException {
		
		String consulta = "select * from ldc_cdc lc inner join clasesDeCarnet cdc on lc.idCdC = cdc.idCarne" + 
				" where lc.idLicencia = ? and lc.estadoExistencia = 1";
		Vector<Object>datos = new Vector<>();
		datos.add(idLicencia);
		ArrayList<ClaseDeCarnet> clasesDeCarne = new ArrayList<>();
		ResultSet rs =null;
		rs = conexion.select(consulta, datos);
		while(rs.next()) {
			
			ClaseDeCarnet cdc = new ClaseDeCarnet();
			cdc.setId(rs.getInt(4));
			cdc.setClaseDeCarnet(rs.getString(5));
			clasesDeCarne.add(cdc);
		}
		
		return clasesDeCarne;
	}

	private PersonaFisica buscarContribuyente(int idContribuyente, Conexion conexion) throws SQLException {
		
		String consulta  = "select * from personas p where p.idPersona = ? and p.estadoExistencia =1";
		Vector<Object> datos = new Vector<>();
		datos.add(idContribuyente);
		ResultSet rs = null;
		rs = conexion.select(consulta, datos);
		PersonaFisica pf = null;
		if(rs.next()) {
			pf = new PersonaFisica();
			pf.setId(rs.getInt(1));
			pf.setNombre(rs.getString(2).toUpperCase());
			pf.setApellido(rs.getString(3).toUpperCase());
			pf.setTipoDeDocumento(rs.getString(4));
			pf.setNumeroDeDocumento(rs.getString(5));
			pf.setGenero(rs.getString(6));
			pf.setEdad(rs.getString(7));
			pf.setEstadoExistencia(rs.getInt(7) > 0);
		}
		
		return pf;
	}

	@Override
	public int modificarLicencia(Usuario usuario, LicenciaConducir licencia) {
		
		String consulta = "update licenciaConducir set fechaCarga = ?, idTipoDeCarne = ?, lugarEmision = ? where idLicencia = ?"
				+ " and estadoExistencia = 1";
		Vector<Object>datos = new Vector<>();
		datos.add(new Date(licencia.getFechaCarga().getTime()));
		datos.add(licencia.getTipoDeCarnet().getId());
		datos.add(licencia.getLugarDeEmision());
		datos.add(licencia.getId());
		int rta = 0;
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rta = conexion.update(consulta, datos);
			if(rta != 0) {
				
				consulta = "update personas set nombre = ?, apellido = ?, tipoDocumento = ?, numeroDeDocumento = ?, genero = ?, edad = ?"
						+ " where idPersona = ? and estadoExistencia = 1";
				datos.clear();
				datos.add(licencia.getPersonaFisica().getNombre());
				datos.add(licencia.getPersonaFisica().getApellido());
				datos.add(licencia.getPersonaFisica().getTipoDeDocumento());
				datos.add(licencia.getPersonaFisica().getNumeroDeDocumento());
				datos.add(licencia.getPersonaFisica().getGenero());
				datos.add(licencia.getPersonaFisica().getEdad());
				datos.add(licencia.getPersonaFisica().getId());
				rta = conexion.update(consulta, datos);
				if(rta != 0) {
					
					consulta = "update ldc_cdc set estadoExistencia = 1 where idLicencia = ? and estadoExistencia = 1";
					datos.clear();
					datos.add(licencia.getId());				 
					rta = conexion.update(consulta, datos); 
					
					if (rta != 0) {
					

						Iterator<ClaseDeCarnet> iteradorCDC = licencia.getClaseDeCarnet().iterator();
						while(iteradorCDC.hasNext()) {

							ClaseDeCarnet cdc = iteradorCDC.next();
							consulta = "insert into ldc_cdc (idLicencia,idCdC,estadoExistencia) values (?,?,1)";
							datos.clear();
							datos.add(licencia.getId());
							datos.add(cdc.getId());
							rta = conexion.insert(consulta, datos);						
						}
						
						consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
						datos.clear();
						datos.add(new Timestamp(new java.util.Date().getTime()));
						datos.add(usuario.getIdUsuario());
						datos.add("modificarLicencia() - idLicencia");
						datos.add(licencia.getId());
						
						String header = VaadinService.getCurrentRequest().getHeader("x-forwarded-for");						
						log.info("ip:"+ ip(header));
						
						rta = conexion.insert(consulta, datos);
						if (rta != 0) {
							conexion.getConexion().commit();
							return 1;
						}else {
							conexion.getConexion().rollback();
							return 0;
						}
						

						
				 }else log.info("Error al actualizar lcd_cdc en modificarLicencia()");
			   }else log.info("Error al actualizar persona en modificarLicencia()");			
			}else log.info("Error al actualizar licencia en modificarLicencia()");
			
	
			
			
		} catch (SQLException e) {
			log.error("Error en modificarLicencia: " +e);
		
			return 0;
		}
		
		
		return 1;
	}

	private String ip(String header) {
		
		if(header == null || "".equals(header)){
		    return "";}
		try {
			return InetAddress.getByName(header).getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ip mal";
		
	}

	@Override
	public int validar(Usuario usuario, java.util.Date fechaDesde, java.util.Date fechaHasta, String centro) {
		
		String consulta = "";
		Vector<Object>datos = new Vector<>();		
		SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd");	
		datos.add(formatSQL.format(fechaDesde));
		datos.add(formatSQL.format(fechaHasta));
		
		
		
		if(!centro.isEmpty()) {
			
			consulta = "update licenciaConducir lc set lc.estadoAprobacion = 1 where (lc.fechaCarga between ? and ?) and lc.lugarEmision = ?";
			datos.add(centro);
			
		}else consulta = "update licenciaConducir lc set lc.estadoAprobacion = 1 where lc.fechaCarga between ? and ? ";
		
		
		int rta = 0;
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			
			consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new java.util.Date().getTime()));
			datos.add(usuario.getIdUsuario());
			datos.add("validar() - desde:"+ formatSQL.format(fechaDesde) +" hasta:"+formatSQL.format(fechaHasta));
			datos.add(usuario.getIdUsuario());			
			String header = VaadinService.getCurrentRequest().getHeader("x-forwarded-for");						
			log.info("ip:"+ ip(header));			
			rta = conexion.insert(consulta, datos);
			if (rta != 0) {
				conexion.getConexion().commit();
				return 1;
			}else {
				conexion.getConexion().rollback();
				return 0;
			}			
		} catch (SQLException e) {
			log.error("Error al validar: " + e);			
		}
		
		
		return 1;
	}

	@Override
	public int eliminarLicencia(Usuario usuario, LicenciaConducir licencia) {

		
		String consulta = "select * from licenciaConducir lc where lc.idLicencia = ? and lc.estadoAprobacion = 0";
		Vector<Object> datos = new Vector<>();
		datos.add(licencia.getId());
		int rta = 0;
		ResultSet rs = null;
		try {
		
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);		
			rs = conexion.select(consulta, datos);
			if(rs.next()) {
				
				consulta = "update licenciaConducir lc set lc.estadoExistencia = 0 where lc.idLicencia = ? and lc.estadoExistencia = 1";
				rta = conexion.update(consulta, datos);
				
				if(rta != 0) {

				consulta = "update ldc_cdc set estadoExistencia = 0 where idLicencia = ?";
				datos.clear();
				datos.add(licencia.getId());
				rta = conexion.update(consulta, datos);
				if (rta != 0) {				

					consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
					datos.clear();
					datos.add(new Timestamp(new java.util.Date().getTime()));
					datos.add(usuario.getIdUsuario());
					datos.add("eliminarLicencia()");
					datos.add(usuario.getIdUsuario());			
					String header = VaadinService.getCurrentRequest().getHeader("x-forwarded-for");						
					log.info("ip:"+ ip(header));			
					rta = conexion.insert(consulta, datos);
					if (rta != 0) {
						conexion.getConexion().commit();
						return 1;
					}else {
						conexion.getConexion().rollback();
						return 0;
					}			
				}		
			}else {
				conexion.getConexion().rollback();
				return 0;
			}
			}else return 3;//licencia no es posible elimnarla por estar aprobada.
			
		} catch (SQLException e) {
			log.error("Error al validar: " + e);
		}

		return 0;
	}

	
	

}
