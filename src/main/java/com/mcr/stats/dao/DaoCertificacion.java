package com.mcr.stats.dao;

import com.mcr.stats.Idao.IDaoCertificacion;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.contratacion.Contratacion;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

public class DaoCertificacion implements IDaoCertificacion {

    private static DaoCertificacion instance;

    private DaoCertificacion(){

    }

    public static DaoCertificacion getInstance(){

        if(instance == null){
            instance = new DaoCertificacion();
        }
        return instance;
    }

    @Override
    public int guardarCertificacion(Usuario usuario, Contratacion contratacion) {

        String consulta = "insert into op_certificaciones (fechaInicio, modificacionDeProyecto," +
                "cuadroModificatorio, observaciones, redeterminacion, estadoExistencia," +
                "idContratacion, numeroDeContratacion) values (?,?,?,?,?,?,?,?)";
        Vector<Object> datos = new Vector<>();

        datos.add(new Date(contratacion.getCertificacion().getFechaInicio().getTime()));
        datos.add(contratacion.getCertificacion().getModificacionProyecto());
        datos.add(contratacion.getCertificacion().getCuadroModificatoriol());
        datos.add(contratacion.getCertificacion().getObservaciones());
        datos.add(contratacion.getCertificacion().getRedeterminacion());
        datos.add(contratacion.getCertificacion().isEstadoExistencia());
        datos.add(contratacion.getIdContratacion());
        datos.add(contratacion.getNumeroDeContratacion());

        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            int idCertificacion = conexion.insert(consulta,datos);
            if (idCertificacion != 0){

                consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
                datos.clear();
                datos.add(new Timestamp(new java.util.Date().getTime()));
                datos.add(usuario.getIdUsuario());
                datos.add("guardarCertificacion() - Certificacion");
                datos.add(contratacion.getIdContratacion());

                int idLog = conexion.insert(consulta, datos);

                if(idLog != 0) {
                    conexion.getConexion().commit();
                    conexion.cerrarRecursos();
                    return idLog;
                }else conexion.getConexion().rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return 0;
    }

    @Override
    public int modificarCertificacion(Usuario usuario, Contratacion contratacion) {

        String consulta = "update op_certificaciones set " +
                "fechaInicio = ?, " +
                "modificacionDeProyecto = ?, " +
                "cuadroModificatorio = ?, " +
                "observaciones = ?," +
                "redeterminacion = ?, " +
                "numeroDeContratacion = ? " +
                "where idCertificacion = ? and idContratacion = ? " +
                "and estadoExistencia = 1";
        Vector<Object>datos = new Vector<>();
        datos.add(new Date(contratacion.getCertificacion().getFechaInicio().getTime()));
        datos.add(contratacion.getCertificacion().getModificacionProyecto());
        datos.add(contratacion.getCertificacion().getCuadroModificatoriol());
        datos.add(contratacion.getCertificacion().getObservaciones());
        datos.add(contratacion.getCertificacion().getRedeterminacion());
        datos.add(contratacion.getCertificacion().getNumeroDeContratacion());
        datos.add(contratacion.getCertificacion().getIdCertificacion());
        datos.add(contratacion.getIdContratacion());
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            conexion.update(consulta,datos);

            consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
            datos.clear();
            datos.add(new Timestamp(new java.util.Date().getTime()));
            datos.add(usuario.getIdUsuario());
            datos.add("modificarCertificacion() - Certificacion");
            datos.add(contratacion.getIdContratacion());

            int idLog = conexion.insert(consulta, datos);

            if(idLog != 0) {
               conexion.getConexion().commit();
               conexion.cerrarRecursos();
               return idLog;
            }else conexion.getConexion().rollback();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return 0;
    }

    @Override
    public int eliminarCertificacion(Usuario usuario, Contratacion contratacion) {

        String consulta = "update op_certificaciones set " +
                "estadoExistencia = 0 " +
                "where idCertificacion = ? and idContratacion = ? " +
                "and estadoExistencia = 1";
        Vector<Object> datos = new Vector<>();
        datos.add(contratacion.getCertificacion().getIdCertificacion());
        datos.add(contratacion.getIdContratacion());
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            conexion.update(consulta,datos);

            consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
            datos.clear();
            datos.add(new Timestamp(new java.util.Date().getTime()));
            datos.add(usuario.getIdUsuario());
            datos.add("eliminarCertificacion() - Certificacion");
            datos.add(contratacion.getIdContratacion());

            int idLog = conexion.insert(consulta, datos);

            if(idLog != 0) {
                conexion.getConexion().commit();
                conexion.cerrarRecursos();
                return idLog;
            }else conexion.getConexion().rollback();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return 0;
    }
}
