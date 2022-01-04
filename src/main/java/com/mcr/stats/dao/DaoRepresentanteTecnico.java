package com.mcr.stats.dao;

import com.mcr.stats.Idao.IdaoRepresentanteTecnico;
import com.mcr.stats.model.contratacion.RepresentanteTecnico;
import com.mcr.stats.servicios.ServicioRepresentanteTecnico;
import com.vaadin.data.util.BeanItemContainer;
import org.apache.regexp.RE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DaoRepresentanteTecnico implements IdaoRepresentanteTecnico {

    private static DaoRepresentanteTecnico instance;

    private DaoRepresentanteTecnico(){

    }

    public static DaoRepresentanteTecnico getInstance(){

        if(instance == null){
            instance = new DaoRepresentanteTecnico();
        }
        return instance;
    }

    @Override
    public ArrayList<RepresentanteTecnico> getRepresentanteTecnico() {

        String consulta = "select * from representantes r where r.estado = 1";
        Vector<Object> datos = new Vector();
        ArrayList<RepresentanteTecnico> representantes = new ArrayList<>();
        ResultSet rs;
        try {
            Conexion conexion = new Conexion();
            rs = conexion.queryConsulta(consulta,datos);
            while (rs.next()){
                RepresentanteTecnico rt = new RepresentanteTecnico();
                rt.setIdRepresentante(rs.getInt(1));
                rt.setApellidoRepresentante(rs.getString(2));
                rt.setNombreRepresentante(rs.getString(3));
                rt.setEstado(rs.getInt(4));
                rt.setApellidoNombre(rt.getApellidoRepresentante()+", "+rt.getNombreRepresentante());
                representantes.add(rt);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return representantes;
    }
}
