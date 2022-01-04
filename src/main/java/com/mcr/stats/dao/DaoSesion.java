package com.mcr.stats.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.mcr.stats.Idao.IdaoSesion;
import com.mcr.stats.model.Credencial;
import com.mcr.stats.model.Perfil;
import com.mcr.stats.model.Sector;
import com.mcr.stats.model.NivelTres;
import com.mcr.stats.model.NivelUno;
import com.mcr.stats.model.NivelDos;
import com.mcr.stats.model.Usuario;


public class DaoSesion implements IdaoSesion{
	
	
	private static DaoSesion instance;
	private static Logger log = Logger.getLogger(DaoSesion.class);
	
	
	public static DaoSesion getInstance(){
		
		if(instance == null){
			instance = new DaoSesion();
		}
		return instance;
		
	}


	@Override
	public Usuario login(Credencial credencial) {
		
		String consulta = "select * from v_sesion vs where vs.nombreDeUsuario = ?";
		Vector<Object>datos = new Vector<>();
		datos.add(credencial.getNombreDeUsuario());		
		ResultSet rs = null;
		Usuario usuario = null;
		try {
		Conexion conexion = new Conexion();
		//conexion.setConexion(ConnectionPool.getInstance().getConnection());
		//conexion.getConexion().setAutoCommit(false);
		rs = conexion.queryConsulta(consulta, datos);
		
		if(rs.next()) {

			if(checkPass(credencial.getContrasenia(),rs.getString(6))){

				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt(1));
				usuario.setNombre(rs.getString(2));
				usuario.setApellido(rs.getString(3));
				usuario.setEmail(rs.getString(3));
				usuario.setPerfil(checkPerfil(rs.getString(7)));
				usuario.setCredencial(new Credencial());
				
				usuario.getCredencial().setNombreDeUsuario(credencial.getNombreDeUsuario());
				
				//usuario.setSector(new Sector(rs.getString(8)));		
				usuario.setNumeroDeDocumento(rs.getString(8));
				consulta = "select * from v_accesos va where va.idUsuario = ?";
				datos.clear();
				datos.add(usuario.getIdUsuario());
				ResultSet rsAccesos = conexion.queryConsulta(consulta, datos);
				while(rsAccesos.next()) {					
					usuario.getAccesos().add(rsAccesos.getString(2));
				}
				return usuario;
			}
			
		}
		conexion.cerrarRecursos();
		 }catch(SQLException e){
			  log.error("Error al instanciar Conexion: " + e);
		}finally {
			
		}
		return usuario;
	}

	
	private Perfil checkPerfil(String perfil) {
							
		if (BCrypt.checkpw("nivel1", perfil)) { //1
			return new NivelUno();
		}
		if (BCrypt.checkpw("nivel2", perfil)) { //2
			return new NivelDos();
		}
		if (BCrypt.checkpw("nivel3", perfil)) { //2
			return new NivelTres();
		}		
		return null;
	}


	private boolean checkPass(String plainPassword, String hashedPassword) {
		
		boolean rta = false;		
		if (BCrypt.checkpw(plainPassword, hashedPassword)) {
			rta = true;
		}
		return rta;	
	}
	
	
	
	


	@Override
	public int logout(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public byte[] stringToByte(String input) {
       
		byte[] bytes = new byte[20];
		bytes = input.getBytes();
		  System.out.println("Array " + bytes);
		
//		if (Base64.isBase64(input)) {
//            System.out.println("decodeBase64:" +Base64.decodeBase64(input));
//        	return Base64.decodeBase64(input);
//
//        } else {
//        	System.out.println("encodeBase64"+Base64.encodeBase64(input.getBytes()));
//            return Base64.encodeBase64(input.getBytes());
//        }
		  return bytes;
    }


	public int actualizar(Usuario usuario) {
		
		String consulta = "update credenciales set hash = ? where nombreDeUsuario = ? and idUsuario = ?";
		Vector<Object> datos = new Vector<>();
		datos.add(hashPassword(usuario.getCredencial().getContrasenia()));
		datos.add(usuario.getCredencial().getNombreDeUsuario());
		datos.add(usuario.getIdUsuario());
		ResultSet rs = null;
		int rta = 0;
		try {
			Conexion conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rta = conexion.update(consulta, datos);
			 
			
			consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new java.util.Date().getTime()));
			datos.add(usuario.getIdUsuario());
			datos.add("actualizar() - contraseña");
			datos.add(usuario.getIdUsuario());
			
			int idLog = conexion.insert(consulta, datos);
			
			if(idLog != 0) {					
				conexion.getConexion().commit();
				conexion.cerrarRecursos();
				return idLog;
			}else {
				conexion.getConexion().rollback();
			}		
			
			
		} catch (SQLException e) {
			log.error("Error al actualizar credencial: " + e);			
		}
		
		return rta;
	}
	

	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
}
