package com.mcr.stats.Iservicios;

import com.mcr.stats.model.Proyecto;
import com.mcr.stats.model.contratacion.Empresa;
import com.vaadin.data.util.BeanItemContainer;

import java.util.ArrayList;

public interface IservicioEmpresa {

    BeanItemContainer<Empresa> getEmpresas();
}
