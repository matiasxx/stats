package com.mcr.stats.dao;

import com.mcr.stats.Idao.IDaoEmpresa;
import com.mcr.stats.model.contratacion.Empresa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DaoEmpresa implements IDaoEmpresa {

    private static DaoEmpresa instance;

    private DaoEmpresa(){

    }

    public static DaoEmpresa getInstance(){

        if(instance == null){
            instance = new DaoEmpresa();
        }
        return instance;
    }

    @Override
    public ArrayList<Empresa> getEmpresas() {

        String consulta = "select * from empresas e where e.estado = 1";
        Vector<Object> datos = new Vector<>();
        ResultSet rs;
        ArrayList<Empresa> empresas = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            rs = conexion.queryConsulta(consulta,datos);
            while(rs.next()){

                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt(1));
                empresa.setRazonSocial(rs.getString(2));
                empresa.setCuit(rs.getString(3));
                empresa.setEstadoEmpresa(rs.getInt(4));
                empresas.add(empresa);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empresas;
    }
}
