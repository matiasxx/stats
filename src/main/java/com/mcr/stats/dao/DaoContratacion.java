package com.mcr.stats.dao;

import com.mcr.stats.Idao.IdaoContratacion;
import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.contratacion.Contratacion;
import com.mcr.stats.model.contratacion.Empresa;
import com.mcr.stats.model.contratacion.RepresentanteTecnico;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

public class DaoContratacion implements IdaoContratacion{

    private static DaoContratacion instance;

    private DaoContratacion(){

    }

    public static DaoContratacion getInstance(){

        if(instance == null){
            instance = new DaoContratacion();
        }
        return instance;
    }


    @Override
    public int guardarContratacion(Usuario usuario, Proyecto proyecto) {

        String consulta = "insert into op_contrataciones (" +
                "idProyecto," +
                "expedienteMunicipal, " +
                "numeroDeContratacion, " +
                "expedidnteContable, " +
                "fechaApertura," +
                "montoContratado, " +
                "ajuste, " +
                "idEmpresa, " +
                "idRepresentanteTecnico, " +
                "estadoContratacion) " +
                "values (?,?,?,?,?,?,?,?,?,?);";
        Vector<Object> datos = new Vector<>();

        datos.add(proyecto.getIdProyecto());
        datos.add(proyecto.getContratacion().getExpedienteMunicipal());
        datos.add(proyecto.getContratacion().getNumeroDeContratacion());
        datos.add(proyecto.getContratacion().getExpedidnteContable());
        datos.add(new Date(proyecto.getContratacion().getFechaApertura().getTime()));
        datos.add(proyecto.getContratacion().getMontoContratado());
        datos.add(proyecto.getContratacion().getAjuste());
        datos.add(proyecto.getContratacion().getEmpresa().getIdEmpresa());
        datos.add(proyecto.getContratacion().getRepresentanteTecnico().getIdRepresentante());
        datos.add(proyecto.getContratacion().getEstadoContratacion());



        int rta = 0;
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            rta = conexion.insert(consulta,datos);
            if(rta !=0 ){

                consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
                datos.clear();
                datos.add(new Timestamp(new java.util.Date().getTime()));
                datos.add(usuario.getIdUsuario());
                datos.add("guardarContratacion() - Contrataciones");
                datos.add(rta); //idContratacion

                int idLog = conexion.insert(consulta, datos);

                if(idLog != 0) {
                    conexion.getConexion().commit();
                    conexion.cerrarRecursos();
                    return idLog;
                }else {
                    conexion.getConexion().rollback();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int eliminarContratacion(Usuario usuario, Proyecto proyecto) {

        String consulta = "update op_contrataciones opc set opc.estadoContratacion = 0 " +
                "where opc.idProyecto = ? and opc.estadoContratacion = 1";
        Vector<Object> datos = new Vector<>();
        datos.add(proyecto.getIdProyecto());
        int idUpdate = 0;
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            idUpdate = conexion.update(consulta, datos);
            if(idUpdate != 0) {

                consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
                datos.clear();
                datos.add(new Timestamp(new java.util.Date().getTime()));
                datos.add(usuario.getIdUsuario());
                datos.add("eliminarContratacion() - Contrataciones");
                datos.add(proyecto.getContratacion().getIdContratacion());

                int idLog = conexion.insert(consulta, datos);

                if(idLog != 0) {
                    conexion.getConexion().commit();
                    conexion.cerrarRecursos();
                    return idLog;
                }else {
                    conexion.getConexion().rollback();
                }

            } else return 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public int modificarContratacion(Usuario usuario, Proyecto proyecto) {

        String consulta = "update op_contrataciones opc set opc.expedienteMunicipal = ? ," +
                "opc.numeroDeContratacion = ?, opc.expedidnteContable = ?, opc.fechaApertura = ?, " +
                "opc.montoContratado = ?, opc.ajuste = ?, opc.idEmpresa =?, opc.idRepresentanteTecnico = ? " +
                "where opc.idProyecto = ? and opc.estadoContratacion = 1";
        Vector<Object> datos = new Vector<>();

        datos.add(proyecto.getContratacion().getExpedienteMunicipal());
        datos.add(proyecto.getContratacion().getNumeroDeContratacion());
        datos.add(proyecto.getContratacion().getExpedidnteContable());
        datos.add(new Date(proyecto.getContratacion().getFechaApertura().getTime()));
        datos.add(proyecto.getContratacion().getMontoContratado());
        datos.add(proyecto.getContratacion().getAjuste());
        datos.add(proyecto.getContratacion().getEmpresa().getIdEmpresa());
        datos.add(proyecto.getContratacion().getRepresentanteTecnico().getIdRepresentante());
        datos.add(proyecto.getIdProyecto());

        int idUpdate = 0;
        try {
            Conexion conexion = new Conexion();
            conexion.getConexion().setAutoCommit(false);
            idUpdate = conexion.update(consulta, datos);
            if(idUpdate != 0) {

                consulta = "insert into log(fecha,idUsuario,metodo,idGuardado) values (?,?,?,?)";
                datos.clear();
                datos.add(new Timestamp(new java.util.Date().getTime()));
                datos.add(usuario.getIdUsuario());
                datos.add("modificarContratacion() - Contrataciones");
                datos.add(proyecto.getContratacion().getIdContratacion());

                int idLog = conexion.insert(consulta, datos);

                if(idLog != 0) {
                    conexion.getConexion().commit();
                    conexion.cerrarRecursos();
                    return idLog;
                }else {
                    conexion.getConexion().rollback();
                }

            } else return 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;

    }

    @Override
    public ArrayList<Contratacion> getContrataciones() {

        String consulta = "select * from op_contrataciones opc where opc.estadoContratacion = 1";
        Vector<Object>datos = new Vector<>();
        ResultSet rs;
        ArrayList<Contratacion> contrataciones = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            rs = conexion.queryConsulta(consulta,datos);
            while(rs.next()){

                Contratacion contratacion = new Contratacion();
                contratacion.setIdContratacion(rs.getInt(1));
                contratacion.setNumeroDeContratacion(rs.getString(4));
              //  contratacion.setProyecto(buscarProyecto(rs.getInt(2)));
                contrataciones.add(contratacion);
            }
            conexion.cerrarRecursos();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contrataciones;
    }

    private Proyecto buscarProyecto(int idProyecto) {

        String consulta = "select * from op_proyecto opp where opp.idProyecto = ? and opp.estadoExistencia = 1";
        Vector<Object> datos = new Vector<>();
        datos.add(idProyecto);
        ResultSet rs;
        Proyecto proyecto = null;
        try {
            Conexion conexion = new Conexion();
            rs = conexion.queryConsulta(consulta,datos);
            if(rs.next()){
                proyecto = new Proyecto();
                proyecto.setIdProyecto(1);
                proyecto.setNombreDeLaObra(rs.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return proyecto;
    }

    @Override
    public Contratacion buscarContratacion(Proyecto proyecto) {

        String consulta = "select * from op_contrataciones opc where opc.idProyecto = ? and opc.estadoContratacion = 1";
        Vector<Object> datos = new Vector<>();
        datos.add(proyecto.getIdProyecto());
        ResultSet rs;
        Contratacion contratacion = null;
        try {
            Conexion conexion = new Conexion();
            rs = conexion.queryConsulta(consulta,datos);
            if(rs.next()){
                contratacion = new Contratacion();
                contratacion.setIdContratacion(rs.getInt(1));
                contratacion.setExpedienteMunicipal(rs.getString(3));
                contratacion.setNumeroDeContratacion(rs.getString(4));
                contratacion.setExpedidnteContable(rs.getString(5));
                contratacion.setFechaApertura(rs.getDate(6));
                contratacion.setMontoContratado(rs.getString(7));
                contratacion.setAjuste(rs.getString(8));
                contratacion.setEmpresa(buscarEmpresa(rs.getInt(9)));
                contratacion.setRepresentanteTecnico(buscarRepresentante(rs.getInt(10)));
                contratacion.setEstadoContratacion(1);
            }
            conexion.cerrarRecursos();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contratacion;
    }

    private RepresentanteTecnico buscarRepresentante(int idRepresentante) {

    String consulta = "select * from representantes opr where opr.idRepresentante = ? and opr.estado = 1";
    Vector<Object> datos = new Vector<>();
    datos.add(idRepresentante);
    ResultSet rs;
        try {
            Conexion conexion = new Conexion();
            rs = conexion.queryConsulta(consulta,datos);
            if(rs.next()){
                RepresentanteTecnico rt = new RepresentanteTecnico();
                rt.setIdRepresentante(rs.getInt(1));
                rt.setApellidoRepresentante(rs.getString(2));
                rt.setNombreRepresentante(rs.getString(3));
                rt.setApellidoNombre(rt.getApellidoRepresentante()+", "+rt.getNombreRepresentante());
                rt.setEstado(rs.getInt(4));
                return rt;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return null;
    }

    private Empresa buscarEmpresa(int idEmpresa) {

        String consulta = "select * from empresas e where e.idEmpresa = ? and e.estado = 1";
        Vector<Object> datos = new Vector<>();
        datos.add(idEmpresa);
        ResultSet rs;
        try {
            Conexion conexion = new Conexion();
            rs = conexion.queryConsulta(consulta,datos);
            if(rs.next()){
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt(1));
                empresa.setRazonSocial(rs.getString(2));
                empresa.setCuit(rs.getString(3));
                empresa.setEstadoEmpresa(1);
                return empresa;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;


    }
}