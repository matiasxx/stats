package com.mcr.stats.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.certificacion.Certificado;
import com.mcr.stats.model.contratacion.Contratacion;
import org.apache.log4j.Logger;

import com.mcr.stats.Idao.IdaoProyecto;
import com.mcr.stats.model.CategoriaProyecto;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.ResponsablePliego;
import com.mcr.stats.model.Sector;
import com.mcr.stats.model.TipoDeContratacion;
import com.mcr.stats.model.Usuario;

public class DaoProyecto implements IdaoProyecto{
	
	
	private static DaoProyecto instance;
	private static final Logger log = Logger.getLogger(DaoProyecto.class);
	
	private DaoProyecto() {
		
	}
	
	public static DaoProyecto getInstance() {
		
		if(instance == null) {
			instance = new DaoProyecto();
		}
		return instance;
	}

	@Override
	public int guardarProyecto(Usuario usuario, Proyecto proyecto) {
		
		String consulta = "insert into op_proyecto "+
						"(idCategoriaProyecto," +
						"subcategoria," +
						"nombreDeLaObra," +
						"idSector," +
						"subSector," +
						"latitud," +
						"longitud," +
						"detalleUbicacion," +
						"memoriaDescriptivaDeLaObra," +
						"mtsLineales," +
						"mtsCuadrados," +
						"conexiones," +
						"beneficiarios," +
						"manzanas," +
						"parcelas," +
						"idTipoContratacion," +
						"presupuestoOficial," +
						"idResponsablePlieto," +
						"estadoExistencia," +
					    "plano, " +
					    "fechaDeElevacion," +
				"pliego," +
				"planillaCotizacion," +
				"otroDocumentos) " +
						"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Vector<Object>datos = new Vector<>();
		datos.add(proyecto.getCategoriaProyecto().getIdCategoria());
		datos.add(proyecto.getSubCategoria());
		datos.add(proyecto.getNombreDeLaObra());
		datos.add(proyecto.getSector().getIdSector());
		datos.add(proyecto.getSubSector());
		datos.add(proyecto.getLatitud());
		datos.add(proyecto.getLongitud());
		datos.add(proyecto.getDetalleUbicacion());
		datos.add(proyecto.getMemoriaDescriptivaDeLaObra());
		datos.add(proyecto.getMtsLineales());
		datos.add(proyecto.getMtsCuadrados());
		datos.add(proyecto.getConexiones());
		datos.add(proyecto.getBeneficiarios());
		datos.add(proyecto.getManzanas());
		datos.add(proyecto.getParcelas());
		datos.add(proyecto.getTipoDeContratacion().getIdTipo());
		datos.add(proyecto.getPresupuestoOficial());
		datos.add(proyecto.getResponsablePliego().getIdResponsablePliego());
		datos.add(true);
		datos.add(proyecto.getPlano());
		datos.add(new Date(proyecto.getFechaDeElevacion().getTime()));
		datos.add(proyecto.getPliegoLicitatorio());
		datos.add(proyecto.getPlanillaCotizacion());
		datos.add(proyecto.getOtroDocumento());
		int idProyecto = 0;
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			idProyecto = conexion.insert(consulta, datos);
			if(idProyecto != 0) {
				
				consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
				datos.clear();
				datos.add(new Timestamp(new java.util.Date().getTime()));
				datos.add(usuario.getIdUsuario());
				datos.add("guardarProyecto() - Proyecto");
				datos.add(idProyecto);
				
				int idLog = conexion.insert(consulta, datos);
				
				if(idLog != 0) {					
					conexion.getConexion().commit();
					conexion.cerrarRecursos();
					return idLog;
				}else {
					conexion.getConexion().rollback();
				}		
								
			}return 0;
			
		} catch (SQLException e) {
			log.error("Error instancia Conexion guardarProyecto: " + e);
		
		}	
		return 0;
	}

	@Override
	public ArrayList<Proyecto> getProyectos() {
		
		//String consulta = "select * from op_proyecto op where op.estadoExistencia = 1";
		//String consulta = "SELECT * FROM op_proyecto op LEFT JOIN op_proyecto_categoria opc ON (op.idCategoriaProyecto = opc.idCategoria) " + 
		//"										LEFT JOIN op_proyecto_sector ops ON (op.idSector = ops.idSector)";
		String consulta = "SELECT * FROM op_proyecto op INNER JOIN op_proyecto_sector ops ON (op.idSector = ops.idSector)" + 
		  "INNER JOIN op_proyecto_categoria opc ON (op.idCategoriaProyecto = opc.idCategoria) " +
		  "INNER JOIN op_proyecto_tipoContratacion optc ON (op.idTipoContratacion = optc.idTipo) " +	
		  "INNER JOIN op_proyecto_responsablePliego oprp ON(op.idResponsablePlieto = oprp.id) " +								  
		  "WHERE op.estadoExistencia = 1";

		Vector<Object> datos = new Vector<Object>();
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		ResultSet rs = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			//System.out.println("fetchSize: " + rs.getF);
			while(rs.next()) {
				
			
				Proyecto p = new Proyecto();
				p.setIdProyecto(rs.getInt(1));
				p.setSubCategoria(rs.getString(3));
				p.setNombreDeLaObra(rs.getString(4));
				
				p.setCategoriaProyecto(new CategoriaProyecto(rs.getInt(29),
						rs.getString(30).toUpperCase()));

				p.setSector(new Sector(rs.getInt(26),
						rs.getString(27).toUpperCase()));

				p.setSubSector(rs.getString(6));
				p.setLatitud(rs.getString(7));
				p.setLongitud(rs.getString(8));
				
				p.setDetalleUbicacion(rs.getString(9));
				p.setMemoriaDescriptivaDeLaObra(rs.getString(10));
				p.setMtsLineales(rs.getString(11));
				p.setMtsCuadrados(rs.getString(12));
				p.setConexiones(rs.getString(13));
				p.setBeneficiarios(rs.getString(14));
				p.setManzanas(rs.getString(15));
				p.setParcelas(rs.getString(16));
				p.setPresupuestoOficial(rs.getString(18));
				p.setPlano(rs.getString(21));
				p.setFechaDeElevacion(rs.getDate(22));
				p.setPliegoLicitatorio(rs.getString(23));
				p.setPlanillaCotizacion(rs.getString(24));
				p.setOtroDocumento(rs.getString(25));

				p.setTipoDeContratacion(new TipoDeContratacion(rs.getInt(32),rs.getString(33)));
				
				p.setResponsablePliego(new ResponsablePliego(rs.getInt(35),rs.getString(36),
						rs.getString(37)));
				proyectos.add(p);
				
			}
		} catch (SQLException e) {
			log.error("Error en getProyectos:" + e);
		}		
		return proyectos;
	}

	@Override
	public ArrayList<Proyecto> getProyectosContrataciones() {

	String consulta = "SELECT op.idProyecto AS idProyecto, " +
			"op.nombreDeLaObra AS nombreDeLaObra, " +
			"op.presupuestoOficial AS presupuestoOficial FROM op_proyecto op " +
			"WHERE op.estadoExistencia = 1 ORDER BY op.nombreDeLaObra";

		Vector<Object> datos = new Vector<>();
		ArrayList<Proyecto> proyectos = new ArrayList<>();
		ResultSet rs = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {
				Proyecto p = new Proyecto();
				p.setIdProyecto(rs.getInt(1));
				p.setNombreDeLaObra(rs.getString(2));
				p.setPresupuestoOficial(rs.getString(3));
				proyectos.add(p);
			}
		} catch (SQLException e) {
			log.error("Error en getProyectosContrataciones:" + e);
		}
		return proyectos;
	}

	@Override
	public ArrayList<Proyecto> getProyectosCertificacion() {

		String consulta = "SELECT op.idProyecto AS idProyecto," +
				"op.nombreDeLaObra AS nombreDeLaObra, "+
		"opcont.idContratacion AS idContratacion, "+
		"opcont.numeroDeContratacion AS numeroContratacion " +
		"FROM op_proyecto op INNER JOIN op_contrataciones opcont ON (opcont.idProyecto = op.idProyecto) "+
		"WHERE op.estadoExistencia = 1 ORDER BY op.nombreDeLaObra ";

		Vector<Object> datos = new Vector<>();
		ArrayList<Proyecto> proyectos = new ArrayList<>();
		ResultSet rs = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {

				Proyecto p = new Proyecto();
				p.setIdProyecto(rs.getInt(1));
				p.setNombreDeLaObra(rs.getString(2));

				Contratacion contratacion = new Contratacion();
				contratacion.setIdContratacion(rs.getInt(3));
				contratacion.setNumeroDeContratacion(rs.getString(4));
				p.setContratacion(contratacion);

				proyectos.add(p);
			}
		} catch (SQLException e) {
			log.error("Error en getProyectosContrataciones:" + e);
		}
		return proyectos;
	}


	@Override
	public ArrayList<Proyecto> getProyectosCertificado() {

		String consulta = "SELECT op.idProyecto AS idProyecto, "+
		"op.nombreDeLaObra AS nombreDeLaObra, "+
		"opcont.idContratacion AS idContratacion, "+
		"opcont.numeroDeContratacion AS numeroContratacion "+
		//"opcert.idCertificacion AS idCertificacion "+
		"FROM op_proyecto op "+
		"INNER JOIN op_contrataciones opcont ON (opcont.idProyecto = op.idProyecto) "+
		//"INNER JOIN op_certificaciones opcert ON (opcert.idContratacion = opcont.idContratacion) "+
		"WHERE op.estadoExistencia = 1 ORDER BY op.nombreDeLaObra ";

		Vector<Object> datos = new Vector<>();
		ArrayList<Proyecto> proyectos = new ArrayList<>();
		ResultSet rs = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()) {

				Proyecto p = new Proyecto();
				p.setIdProyecto(rs.getInt(1));
				p.setNombreDeLaObra(rs.getString(2));

				Contratacion contratacion = new Contratacion();
				contratacion.setIdContratacion(rs.getInt(3));
				contratacion.setNumeroDeContratacion(rs.getString(4));

				Certificacion certificacion = new Certificacion();
				certificacion.setIdCertificacion(5);
				contratacion.setCertificacion(certificacion);
				p.setContratacion(contratacion);

				proyectos.add(p);
			}
		} catch (SQLException e) {
			log.error("Error en getProyectosContrataciones:" + e);
		}
		return proyectos;
	}

	@Override
	public ArrayList<Proyecto> getProyectosCompleto() {

		String consulta = "SELECT op.idProyecto AS idProyecto, "+
				"op.nombreDeLaObra AS nombreDeLaObra, "+
				"opcont.idContratacion AS idContratacion, "+
				"opcont.numeroDeContratacion AS numeroContratacion, "+
				"opcert.idCertificacion AS idCertificacion," +
				"opcont.expedidnteContable AS expedienteContable," +
				"opcont.montoContratado AS montoContratado  "+
				"FROM op_proyecto op "+
				"INNER JOIN op_contrataciones opcont ON (opcont.idProyecto = op.idProyecto) "+
				"INNER JOIN op_certificaciones opcert ON (opcert.idContratacion = opcont.idContratacion) "+
				"WHERE op.estadoExistencia = 1 ORDER BY op.nombreDeLaObra ";

		Vector<Object> datos = new Vector<>();
		ArrayList<Proyecto> proyectos = new ArrayList<>();
		ResultSet rs;
		ResultSet rsCertificados;
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rs = conexion.select(consulta, datos);
			while(rs.next()) {

				Proyecto p = new Proyecto();
				p.setIdProyecto(rs.getInt(1));
				p.setNombreDeLaObra(rs.getString(2));


				Contratacion contratacion = new Contratacion();
				contratacion.setIdContratacion(rs.getInt(3));
				contratacion.setNumeroDeContratacion(rs.getString(4));
				contratacion.setExpedidnteContable(rs.getString(6));
				contratacion.setMontoContratado(rs.getString(7));

				Certificacion certificacion = new Certificacion();
				certificacion.setIdCertificacion(5);
				certificacion.setCertificados(new ArrayList<Certificado>());

				consulta = "SELECT * FROM op_certificados opcer WHERE opcer.idCertificacion = ?";
				datos.clear();
				datos.add(rs.getInt(5));
				rsCertificados = conexion.select(consulta,datos);
				while(rsCertificados.next()) {

					Certificado certificado = new Certificado();
					certificado.setIdCertificado(rsCertificados.getInt(1));
					certificado.setMonto(rsCertificados.getString(2));
					certificado.setPeriodo(rsCertificados.getString(3));
					certificado.setFechaCertificado(rsCertificados.getDate(4));
					certificacion.getCertificados().add(certificado);



				}

				contratacion.setCertificacion(certificacion);
				p.setContratacion(contratacion);

				proyectos.add(p);
			}
		} catch (SQLException e) {
			log.error("Error en getProyectosContrataciones:" + e);
		}
		return proyectos;
	}

	@Override
	public ArrayList<Proyecto> getProyectosTodo() {

		String consulta = "SELECT * "+
				"FROM op_proyecto op "+
				"INNER JOIN op_contrataciones opcont ON (opcont.idProyecto = op.idProyecto) "+
				"INNER JOIN op_certificaciones opcert ON (opcert.idContratacion = opcont.idContratacion) "+
				"WHERE op.estadoExistencia = 1 and opcont.estadoContratacion =1 AND opcert.estadoExistencia = 1 " +
				"ORDER BY op.nombreDeLaObra";

		Vector<Object> datos = new Vector<>();
		ArrayList<Proyecto> proyectos = new ArrayList<>();
		ResultSet rs;
		ResultSet rsCertificados;
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rs = conexion.select(consulta, datos);
			while(rs.next()) {

				Proyecto p = new Proyecto();
				p.setIdProyecto(rs.getInt(1));
				p.setNombreDeLaObra(rs.getString(4));


				Contratacion contratacion = new Contratacion();
				contratacion.setIdContratacion(rs.getInt(26));
				contratacion.setNumeroDeContratacion(rs.getString(29));
				contratacion.setExpedidnteContable(rs.getString(30));
				contratacion.setMontoContratado(rs.getString(32));

				Certificacion certificacion = new Certificacion();
				certificacion.setIdCertificacion(rs.getInt(37));
				certificacion.setFechaInicio(rs.getDate(38));
				certificacion.setModificacionProyecto(rs.getString(39));
				certificacion.setCuadroModificatoriol(rs.getString(40));
				certificacion.setObservaciones(rs.getString(41));
				certificacion.setRedeterminacion(rs.getString(42));
				certificacion.setCertificados(new ArrayList<Certificado>());

				consulta = "SELECT * FROM op_certificados opcer WHERE opcer.idCertificacion = ? and opcer.estadoExistencia = 1";
				datos.clear();
				datos.add(rs.getInt(37));
				rsCertificados = conexion.select(consulta,datos);
				while(rsCertificados.next()) {

					Certificado certificado = new Certificado();
					certificado.setIdCertificado(rsCertificados.getInt(1));
					certificado.setMonto(rsCertificados.getString(2));
					certificado.setPeriodo(rsCertificados.getString(3));
					certificado.setFechaCertificado(rsCertificados.getDate(4));
					certificacion.getCertificados().add(certificado);



				}

				contratacion.setCertificacion(certificacion);
				p.setContratacion(contratacion);

				proyectos.add(p);
			}
		} catch (SQLException e) {
			log.error("Error en getProyectosContrataciones:" + e);
		}
		return proyectos;
	}




	@Override
	public int modificarProyecto(Usuario usuario, Proyecto proyecto) {
		
		String consulta = "update op_proyecto set idCategoriaProyecto = ?,"+
				"subcategoria = ?," +  
				"nombreDeLaObra = ?, " + 
				"idSector = ?, " + 
				"subSector = ?, " + 
				"latitud = ?, " + 
				"longitud = ?, " + 
				"detalleUbicacion = ?, " + 
				"memoriaDescriptivaDeLaObra = ?, " + 
				"mtsLineales = ?, " + 
				"mtsCuadrados = ?, " + 
				"conexiones = ?, " + 
				"beneficiarios = ?, " + 
				"manzanas = ?, " + 
				"parcelas = ?, " + 
				"idTipoContratacion = ?, " + 
				"presupuestoOficial = ?, " + 
				"idResponsablePlieto = ?, " + 
				"plano = ?, " + 
				"fechaDeElevacion = ?, " +
				"pliego = ?, " +
				"planillaCotizacion = ?, " +
				"otroDocumentos = ? " +
				"where idProyecto = ? and estadoExistencia = 1";
				
		Vector<Object> datos = new Vector<Object>();
		datos.add(proyecto.getCategoriaProyecto().getIdCategoria());
		datos.add(proyecto.getSubCategoria());
		datos.add(proyecto.getNombreDeLaObra());
		datos.add(proyecto.getSector().getIdSector());
		datos.add(proyecto.getSubSector());
		datos.add(proyecto.getLatitud());
		datos.add(proyecto.getLongitud());
		datos.add(proyecto.getDetalleUbicacion());
		datos.add(proyecto.getMemoriaDescriptivaDeLaObra());
		datos.add(proyecto.getMtsLineales());
		datos.add(proyecto.getMtsCuadrados());
		datos.add(proyecto.getConexiones());
		datos.add(proyecto.getBeneficiarios());
		datos.add(proyecto.getManzanas());
		datos.add(proyecto.getParcelas());
		datos.add(proyecto.getTipoDeContratacion().getIdTipo());
		datos.add(proyecto.getPresupuestoOficial());
		datos.add(proyecto.getResponsablePliego().getIdResponsablePliego());
		datos.add(proyecto.getPlano());
		datos.add(new Date(proyecto.getFechaDeElevacion().getTime()));
		datos.add(proyecto.getPliegoLicitatorio());
		datos.add(proyecto.getPlanillaCotizacion());
		datos.add(proyecto.getOtroDocumento());

		datos.add(proyecto.getIdProyecto());
		Conexion conexion = null;
		int rta = 0;
		
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			
		
			consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new java.util.Date().getTime()));
			datos.add(usuario.getIdUsuario());
			datos.add("modificarProyecto() - Proyecto");
			datos.add(proyecto.getIdProyecto());
			
			int idLog = conexion.insert(consulta, datos);
			
			if(idLog != 0) {					
				conexion.getConexion().commit();
				conexion.cerrarRecursos();
				return idLog;
			}else {
				conexion.getConexion().rollback();
				return 0;
			}		
			
			
			
			
		} catch (SQLException e) {
			log.error("Error al modificarProyecto()" + e);
		}			
		return 0;
	}

	@Override
	public int eliminarProyecto(Usuario usuario, Proyecto proyecto) {
	
		String consulta = "update op_proyecto op set op.estadoExistencia = 0 where op.idProyecto = ? and op.estadoExistencia = 1";
		Vector<Object> datos = new Vector<Object>();
		datos.add(proyecto.getIdProyecto());
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			
			consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new java.util.Date().getTime()));
			datos.add(usuario.getIdUsuario());
			datos.add("eliminarProyecto() - Proyecto");
			datos.add(proyecto.getIdProyecto());
			
			int idLog = conexion.insert(consulta, datos);
			
			if(idLog != 0) {					
				conexion.getConexion().commit();
				conexion.cerrarRecursos();
				return idLog;
			}else {
				conexion.getConexion().rollback();
				return 0;
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	

}
