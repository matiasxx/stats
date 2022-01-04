package com.mcr.stats.Idao;

import java.util.ArrayList;
import java.util.Date;

import com.mcr.stats.model.Usuario;
import com.mcr.stats.model.cargas.LicenciaConducir;

public interface IdaoLicencia {
	
	
	public int guardarLicencia(Usuario usuario, LicenciaConducir licencia);
	public int modificarLicencia(Usuario usuario, LicenciaConducir licencia);
	public int eliminarLicencia(Usuario usuario, LicenciaConducir licencia);
	public ArrayList<LicenciaConducir> buscarLicencias(Date fechaDesde, Date fechaHasta);
	
	public int validar (Usuario usuario, Date fechaDesde, Date fechaHasta, String centro);
}
