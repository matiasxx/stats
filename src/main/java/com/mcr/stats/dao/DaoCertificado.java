package com.mcr.stats.dao;

import com.mcr.stats.Idao.IDaoCertificado;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.certificacion.Certificacion;
import com.mcr.stats.model.certificacion.Certificado;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

public class DaoCertificado implements IDaoCertificado {

    private static DaoCertificado instance;

    private DaoCertificado(){

    }

    public static DaoCertificado getInstance(){

        if(instance == null){
            instance = new DaoCertificado();
        }
        return instance;
    }


    @Override
    public int guardarCertificado(Usuario usuario, Certificacion certificacion) {

        String consulta = "insert into op_certificados (monto, periodo, fechaCertificado, " +
                "estadoExistencia, idCertificacion) values (?,?,?,?,?)";
        Vector<Object>datos = new Vector<>();
        datos.add(certificacion.getCertificados().get(0).getMonto());
        datos.add(certificacion.getCertificados().get(0).getPeriodo());
        datos.add(new Date(certificacion.getCertificados().get(0).getFechaCertificado().getTime()));
        datos.add(true);
        datos.add(certificacion.getIdCertificacion());
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            int idCertificado = conexion.insert(consulta,datos);
            if (idCertificado != 0){

                consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
                datos.clear();
                datos.add(new Timestamp(new java.util.Date().getTime()));
                datos.add(usuario.getIdUsuario());
                datos.add("guardarCertificado() - Certificado");
                datos.add(idCertificado);

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
    public int modificarCertificado(Usuario usuario, Certificacion certificacion, Certificado certificado) {

        String consulta = "update op_certificados set monto = ?, periodo = ?, fechaCertificado = ? where " +
                "idCertificado = ? and idCertificacion = ? and estadoExistencia = 1";
        Vector<Object> datos = new Vector<>();
        datos.add(certificado.getMonto());
        datos.add(certificado.getPeriodo());
        datos.add(new Date(certificado.getFechaCertificado().getTime()));
        datos.add(certificado.getIdCertificado());
        datos.add(certificacion.getIdCertificacion());
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            conexion.update(consulta,datos);

            consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
                datos.clear();
                datos.add(new Timestamp(new java.util.Date().getTime()));
                datos.add(usuario.getIdUsuario());
                datos.add("modificarCertificado() - Certificado");
                datos.add(certificado.getIdCertificado());

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
    public int eliminarCertificado(Usuario usuario, Certificacion certificacion, Certificado certificado) {

        String consulta = "update op_certificados set  estadoExistencia = 0 where " +
                "idCertificado = ? and idCertificacion = ? and estadoExistencia = 1";
        Vector<Object> datos = new Vector<>();
        datos.add(certificado.getIdCertificado());
        datos.add(certificacion.getIdCertificacion());
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            conexion.update(consulta,datos);

            consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
            datos.clear();
            datos.add(new Timestamp(new java.util.Date().getTime()));
            datos.add(usuario.getIdUsuario());
            datos.add("eliminarCertificado() - Certificado");
            datos.add(certificado.getIdCertificado());

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
