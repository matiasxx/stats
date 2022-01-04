package com.mcr.stats.ViewOperaciones;

import com.lowagie.text.pdf.PdfName;
import com.mcr.stats.model.Usuario;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.*;

import java.util.Iterator;

public class NavegadorAdministrativo extends Tree{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String LICENCIA = "Licencia de conducir";
	public static final String RUIDOS = "Ruidos molestos";
	public static final String REPEVAMIENTO = "Relevamiento de precios";
	public static final String OBRAS_PROYECTOS = "Obras Publicas - Proyectos";
	public static final String OBRAS_CONTRATACIONES = "Obras Publicas - Contrataciones";
	public static final String OBRAS_CERTIFICACIONES = "Obras Publicas - Certificaciones";

	public static final String SECRETARIO_A = "Secretario de gobierno y funcion publica";
	public static final String SECRETARIO_B = "Secretario de gobierno y funcion publica";
	public static final String SECRETARIO_C = "Secretario de gobierno y funcion publica";
	public static final String SECRETARIO_D = "Secretario de gobierno y funcion publica";
	public static final String SECRETARIO_E = "Secretario de gobierno y funcion publica";
	public static final String SECRETARIO_F = "Secretario de gobierno y funcion publica";


	public NavegadorAdministrativo() {

	}

	public NavegadorAdministrativo(Usuario usuario) {

		System.out.println(usuario.getPerfil().recuperarPerfil());
		
		
		/*if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("usuario administrador") ||
				usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("usuario jun"))*/

	}

	public void init(Usuario usuario) {

		setSelectable(true);
		setImmediate(true);

		//System.out.println("Cantidad de accesos: " + usuario.getAccesos().size());

		if (usuario.getAccesos().get(0).equalsIgnoreCase(NavegadorAdministrativo.LICENCIA)) {
		//	System.out.println("vista generada : LICENCIA");
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) {
				configurarVistaLicenciaUno(usuario);
			}
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) {
				configurarVistaLicenciaDos(usuario);
			}
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel tres")) {
				configurarVistaLicenciaTres(usuario);
			}

		} //fin licencias


		if (usuario.getAccesos().get(0).equalsIgnoreCase(NavegadorAdministrativo.OBRAS_PROYECTOS)) {

		//	System.out.println("vista generada: ES DE OBRAS");
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) {
				configurarVistaObrasUno(usuario);
			}
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) {
				configurarVistaObrasDos(usuario);
			}
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel tres")) {
				configurarVistaObrasTres(usuario);
			}

		} //fin OBRAS

		if (usuario.getAccesos().get(0).equalsIgnoreCase(NavegadorAdministrativo.OBRAS_CONTRATACIONES)) {


			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) {
				configurarVistaObrasContratacionesUno(usuario);
			}
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel dos")) {
				//configurarVistaObrasContratacionesDos(usuario);
			}
			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel tres")) {
				//configurarVistaObrasContratacionesTres(usuario);
			}
		}

		if (usuario.getAccesos().get(0).equalsIgnoreCase(NavegadorAdministrativo.OBRAS_CERTIFICACIONES)) {

			if (usuario.getPerfil().recuperarPerfil().equalsIgnoreCase("nivel uno")) {
				configurarVistaObrasCertificaciones(usuario);
			}
		}

	}

	private void configurarVistaObrasCertificaciones(Usuario usuario) {

		//Ramas principales
		Object itemCargaDatos = addItem();
		setItemCaption(itemCargaDatos, "Carga de Datos");

		//Ramas principales
		Object itemVisualizador = addItem();
		setItemCaption(itemVisualizador, "Visualizador");

		//Ramas principales
		Object itemUsuario = addItem();
		setItemCaption(itemUsuario, "Usuario");

		//Hijo de Carga de Datos
		Object itemIdAlta = addItem();
		setItemCaption(itemIdAlta, "Alta Certificacion");
		setParent(itemIdAlta, itemCargaDatos);
		setChildrenAllowed(itemIdAlta, false);

		Object itemIdAltaCertificado = addItem();
		setItemCaption(itemIdAltaCertificado, "Alta Certificado");
		setParent(itemIdAltaCertificado, itemCargaDatos);
		setChildrenAllowed(itemIdAltaCertificado, false);


		//Hijo de Carga de Datos
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, false);



		//Hijo de Vizualizador
		Object itemIdVizualizador = addItem();
		setItemCaption(itemIdVizualizador, "Certificaciones");
		setParent(itemIdVizualizador, itemVisualizador);
		setChildrenAllowed(itemIdVizualizador, false);




		Object itemIdUsuarioSalir = addItem();
		setItemCaption(itemIdUsuarioSalir, "Salir");
		setParent(itemIdUsuarioSalir, itemUsuario);
		setChildrenAllowed(itemIdUsuarioSalir, false);


		Object itemIdUsuarioDP = addItem();
		setItemCaption(itemIdUsuarioDP, "Datos Personales");
		setParent(itemIdUsuarioDP, itemUsuario);
		setChildrenAllowed(itemIdUsuarioDP, false);





	}

	private void configurarVistaObrasContratacionesUno(Usuario usuario) {
		//Ramas principales
		Object itemCargaDatos = addItem();
		setItemCaption(itemCargaDatos, "Carga de Datos");

		//Ramas principales
		//Object itemReportes = addItem();
		//setItemCaption(itemReportes, "Reportes");

		//Ramas principales
		Object itemUsuario = addItem();
		setItemCaption(itemUsuario, "Usuario");





		//Hijo de Carga de Datos
		Object itemIdAlta = addItem();
		setItemCaption(itemIdAlta, "Alta");
		setParent(itemIdAlta, itemCargaDatos);
		setChildrenAllowed(itemIdAlta, false);

		//Ramas principales
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, false);


		Object itemIdUsuarioSalir = addItem();
		setItemCaption(itemIdUsuarioSalir, "Salir");
		setParent(itemIdUsuarioSalir, itemUsuario);
		setChildrenAllowed(itemIdUsuarioSalir, false);




		/*


//		Object itemIdProyectos = addItem();
//		setItemCaption(itemIdProyectos, "Proyectos");
//		setParent(itemIdProyectos, itemIdAlta);
//		setChildrenAllowed(itemIdProyectos, false);

		Object itemIdContrataciones = addItem();
		setItemCaption(itemIdContrataciones, "Contrataciones");
		setParent(itemIdContrataciones, itemIdAlta);
		setChildrenAllowed(itemIdContrataciones, false);

//		Object itemIdCertificaciones = addItem();
//		setItemCaption(itemIdCertificaciones, "Certificaciones");
//		setParent(itemIdCertificaciones, itemIdAlta);
//		setChildrenAllowed(itemIdCertificaciones, false);
//
//		Object itemIdInspeccion = addItem();
//		setItemCaption(itemIdInspeccion, "Inspecciones");
//		setParent(itemIdInspeccion, itemIdAlta);
//		setChildrenAllowed(itemIdInspeccion, false);


		//Ramas principales
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, true);

		Object itemIdProyectosBM = addItem();
		setItemCaption(itemIdProyectosBM, "Proyectos");
		setParent(itemIdProyectosBM, itemIdBM);
		setChildrenAllowed(itemIdProyectosBM, false);

		Object itemIdContratacionesBM = addItem();
		setItemCaption(itemIdContratacionesBM, "Contrataciones");
		setParent(itemIdContratacionesBM, itemIdBM);
		setChildrenAllowed(itemIdContratacionesBM, false);

		Object itemIdCertificacionesBM = addItem();
		setItemCaption(itemIdCertificacionesBM, "Certificaciones");
		setParent(itemIdCertificacionesBM, itemIdBM);
		setChildrenAllowed(itemIdCertificacionesBM, false);

		Object itemIdInspeccionBM = addItem();
		setItemCaption(itemIdInspeccionBM, "Inspecciones");
		setParent(itemIdInspeccionBM, itemIdBM);
		setChildrenAllowed(itemIdInspeccionBM, false);


		//
		Object itemIdUsuarioDP = addItem();
		setItemCaption(itemIdUsuarioDP, "Datos Personales");
		setParent(itemIdUsuarioDP, itemUsuario);
		setChildrenAllowed(itemIdUsuarioDP, false);

		 */






	}


	private void configurarVistaObrasTres(Usuario usuario){

		final Object[][] opciones = new Object[][]{
				//new Object[]{"Reportes","Licencia de Conducir","Transporte publico", "Relevamiento de precios", "Obras publicas"},
				//new Object[]{"Reportes","Generar Reportes"},
				new Object[]{"Reportes","Generar reportes"},
				new Object[]{"Usuario","Datos Personales","Salir"}};

		for (int i = 0; i < opciones.length; i++) {

			String opcion = (String)(opciones[i][0]);
			this.addItem(opcion);

			if(opciones[i].length == 1){
				this.setChildrenAllowed(opcion, true);
			}else{
				for(int j=1;j<opciones[i].length;j++){
					String subopcion = (String)opciones[i][j];
					this.addItem(subopcion);
					this.setParent(subopcion,opcion);
					this.setChildrenAllowed(subopcion, false);
				}
				this.collapseItemsRecursively(opcion);
			}
		}
	}

	private void configurarVistaObrasDos(Usuario usuario) {

		//Ramas principales
		Object itemCargaDatos = addItem();
		setItemCaption(itemCargaDatos, "Carga de Datos");

		//Ramas principales
		Object itemVisualizador = addItem();
		setItemCaption(itemVisualizador, "Visualizador");

		//Ramas principales
		Object itemReportes = addItem();
		setItemCaption(itemReportes, "Reportes");

		//Ramas principales
		Object itemUsuario = addItem();
		setItemCaption(itemUsuario, "Usuario");



		//Hijo de Carga de Datos
		Object itemIdAlta = addItem();
		setItemCaption(itemIdAlta, "Alta");
		setParent(itemIdAlta, itemCargaDatos);
		setChildrenAllowed(itemIdAlta, true);

		Object itemIdProyectos = addItem();
		setItemCaption(itemIdProyectos, "Proyectos");
		setParent(itemIdProyectos, itemIdAlta);
		setChildrenAllowed(itemIdProyectos, false);


		//Hijo de Carga de Datos
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, true);

		Object itemIdProyectosBM = addItem();
		setItemCaption(itemIdProyectosBM, "Proyectos");
		setParent(itemIdProyectosBM, itemIdBM);
		setChildrenAllowed(itemIdProyectosBM, false);


		//Hijo de Vizualizador
		Object itemIdVizualizador = addItem();
		setItemCaption(itemIdVizualizador, "Contrataciones");
		setParent(itemIdVizualizador, itemVisualizador);
		setChildrenAllowed(itemIdVizualizador, false);






		//Hijo de Reportes
		Object itemIdGenerarReporte = addItem();
		setItemCaption(itemIdGenerarReporte, "Generar Reporte");
		setParent(itemIdGenerarReporte, itemReportes);
		setChildrenAllowed(itemIdGenerarReporte, false);

		//
		Object itemIdUsuarioDP = addItem();
		setItemCaption(itemIdUsuarioDP, "Datos Personales");
		setParent(itemIdUsuarioDP, itemUsuario);
		setChildrenAllowed(itemIdUsuarioDP, false);

		Object itemIdUsuarioSalir = addItem();
		setItemCaption(itemIdUsuarioSalir, "Salir");
		setParent(itemIdUsuarioSalir, itemUsuario);
		setChildrenAllowed(itemIdUsuarioSalir, false);
	}

	private void configurarVistaObrasUno(Usuario usuario) {

		//Ramas principales
		Object itemCargaDatos = addItem();
		setItemCaption(itemCargaDatos, "Carga de Datos");

		//Ramas principales
		//Object itemReportes = addItem();
		//setItemCaption(itemReportes, "Reportes");

		//Ramas principales
		Object itemUsuario = addItem();
		setItemCaption(itemUsuario, "Usuario");




		//Hijo de Carga de Datos
		Object itemIdAlta = addItem();
		setItemCaption(itemIdAlta, "Alta");
		setParent(itemIdAlta, itemCargaDatos);
		setChildrenAllowed(itemIdAlta, true);

		Object itemIdProyectos = addItem();
		setItemCaption(itemIdProyectos, "Proyectos");
		setParent(itemIdProyectos, itemIdAlta);
		setChildrenAllowed(itemIdProyectos, false);

//		Object itemIdContrataciones = addItem();
//		setItemCaption(itemIdContrataciones, "Contrataciones");
//		setParent(itemIdContrataciones, itemIdAlta);
//		setChildrenAllowed(itemIdContrataciones, false);
//
//		Object itemIdCertificaciones = addItem();
//		setItemCaption(itemIdCertificaciones, "Certificaciones");
//		setParent(itemIdCertificaciones, itemIdAlta);
//		setChildrenAllowed(itemIdCertificaciones, false);
//
//		Object itemIdInspeccion = addItem();
//		setItemCaption(itemIdInspeccion, "Inspecciones");
//		setParent(itemIdInspeccion, itemIdAlta);
//		setChildrenAllowed(itemIdInspeccion, false);


		//Ramas principales
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, true);

		Object itemIdProyectosBM = addItem();
		setItemCaption(itemIdProyectosBM, "Proyectos");
		setParent(itemIdProyectosBM, itemIdBM);
		setChildrenAllowed(itemIdProyectosBM, false);

//		Object itemIdContratacionesBM = addItem();
//		setItemCaption(itemIdContratacionesBM, "Contrataciones");
//		setParent(itemIdContratacionesBM, itemIdBM);
//		setChildrenAllowed(itemIdContratacionesBM, false);
//
//		Object itemIdCertificacionesBM = addItem();
//		setItemCaption(itemIdCertificacionesBM, "Certificaciones");
//		setParent(itemIdCertificacionesBM, itemIdBM);
//		setChildrenAllowed(itemIdCertificacionesBM, false);
//
//		Object itemIdInspeccionBM = addItem();
//		setItemCaption(itemIdInspeccionBM, "Inspecciones");
//		setParent(itemIdInspeccionBM, itemIdBM);
//		setChildrenAllowed(itemIdInspeccionBM, false);


		//
		Object itemIdUsuarioDP = addItem();
		setItemCaption(itemIdUsuarioDP, "Datos Personales");
		setParent(itemIdUsuarioDP, itemUsuario);
		setChildrenAllowed(itemIdUsuarioDP, false);

		Object itemIdUsuarioSalir = addItem();
		setItemCaption(itemIdUsuarioSalir, "Salir");
		setParent(itemIdUsuarioSalir, itemUsuario);
		setChildrenAllowed(itemIdUsuarioSalir, false);

	}

	private void configurarVistaLicenciaTres(Usuario usuario) {

		final Object[][] opciones = new Object[][]{
				//new Object[]{"Reportes","Licencia de Conducir","Transporte publico", "Relevamiento de precios", "Obras publicas"},
				//new Object[]{"Reportes","Generar Reportes"},
				new Object[]{"Reportes", "Generar reportes"},
				new Object[]{"Usuario", "Datos Personales", "Salir"}};

		for (int i = 0; i < opciones.length; i++) {

			String opcion = (String) (opciones[i][0]);
			this.addItem(opcion);

			if (opciones[i].length == 1) {
				this.setChildrenAllowed(opcion, true);
			} else {
				for (int j = 1; j < opciones[i].length; j++) {
					String subopcion = (String) opciones[i][j];
					this.addItem(subopcion);
					this.setParent(subopcion, opcion);
					this.setChildrenAllowed(subopcion, false);
				}
				this.collapseItemsRecursively(opcion);
			}
		}
	}

	private void configurarVistaLicenciaDos(Usuario usuario) {

//		final Object[][] opciones = new Object[][]{
//				new Object[]{"Carga de datos","Altas","Bajas/Modificaciones"},
//				//new Object[]{"Validación","Validar datos"},
//				new Object[]{"Reportes","Generar reporte"},
//				new Object[]{"Usuario","Datos Personales","Salir"}};
//
//		for (int i = 0; i < opciones.length; i++) {
//
//			String opcion = (String)(opciones[i][0]);
//			this.addItem(opcion);
//
//			if(opciones[i].length == 1){
//				this.setChildrenAllowed(opcion, true);
//			}else{
//				for(int j=1;j<opciones[i].length;j++){
//					String subopcion = (String)opciones[i][j];
//					this.addItem(subopcion);
//					this.setParent(subopcion,opcion);
//					this.setChildrenAllowed(subopcion, false);
//				}
//				this.collapseItemsRecursively(opcion);
//			}
//		}

		//Ramas principales
		Object itemCargaDatos = addItem();
		setItemCaption(itemCargaDatos, "Carga de Datos");

		//Ramas principales
		Object itemReportes = addItem();
		setItemCaption(itemReportes, "Reportes");

		//Ramas principales
		Object itemUsuario = addItem();
		setItemCaption(itemUsuario, "Usuario");


		//Hijo de Carga de Datos
		Object itemIdAlta = addItem();
		setItemCaption(itemIdAlta, "Alta");
		setParent(itemIdAlta, itemCargaDatos);
		setChildrenAllowed(itemIdAlta, false);
		//setChildrenAllowed(itemIdAlta, true);

		//Hijo de Carga de Datos
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, false);


		//Hijo de Carga de Datos
		Object itemIdReportes = addItem();
		setItemCaption(itemIdReportes,"Generar Reportes");
		setParent(itemIdReportes,itemReportes);
		setChildrenAllowed(itemIdReportes,false);

		//Hijo de Usuarios
		Object itemIdUsuarioDP = addItem();
		setItemCaption(itemIdUsuarioDP, "Datos Personales");
		setParent(itemIdUsuarioDP, itemUsuario);
		setChildrenAllowed(itemIdUsuarioDP, false);

		//Hijo de Usuarios
		Object itemIdUsuarioSalir = addItem();
		setItemCaption(itemIdUsuarioSalir, "Salir");
		setParent(itemIdUsuarioSalir, itemUsuario);
		setChildrenAllowed(itemIdUsuarioSalir, false);







		/*

		//Ramas principales
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, true);

		Object itemIdProyectosBM = addItem();
		setItemCaption(itemIdProyectosBM, "Proyectos");
		setParent(itemIdProyectosBM, itemIdBM);
		setChildrenAllowed(itemIdProyectosBM, false);

		Object itemIdContratacionesBM = addItem();
		setItemCaption(itemIdContratacionesBM, "Contrataciones");
		setParent(itemIdContratacionesBM, itemIdBM);
		setChildrenAllowed(itemIdContratacionesBM, false);

		Object itemIdCertificacionesBM = addItem();
		setItemCaption(itemIdCertificacionesBM, "Certificaciones");
		setParent(itemIdCertificacionesBM, itemIdBM);
		setChildrenAllowed(itemIdCertificacionesBM, false);

		Object itemIdInspeccionBM = addItem();
		setItemCaption(itemIdInspeccionBM, "Inspecciones");
		setParent(itemIdInspeccionBM, itemIdBM);
		setChildrenAllowed(itemIdInspeccionBM, false);


		//
		Object itemIdUsuarioDP = addItem();
		setItemCaption(itemIdUsuarioDP, "Datos Personales");
		setParent(itemIdUsuarioDP, itemUsuario);
		setChildrenAllowed(itemIdUsuarioDP, false);

		Object itemIdUsuarioSalir = addItem();
		setItemCaption(itemIdUsuarioSalir, "Salir");
		setParent(itemIdUsuarioSalir, itemUsuario);
		setChildrenAllowed(itemIdUsuarioSalir, false);



		 */
	}

	private void configurarVistaLicenciaUno(Usuario usuario) {

//		System.out.println("nivel 1 licencia");
//		final Object[][] opciones = new Object[][]{
//				new Object[]{"Carga de datos", "Altas", "Bajas/Modificaciones"},
//				new Object[]{"Usuario", "Datos Personales", "Salir"}};
//
//		for (int i = 0; i < opciones.length; i++) {
//
//			String opcion = (String) (opciones[i][0]);
//			this.addItem(opcion);
//			if (opciones[i].length == 1) {
//				this.setChildrenAllowed(opcion, true);
//			} else {
//				for (int j = 1; j < opciones[i].length; j++) {
//					String subopcion = (String) opciones[i][j];
//					this.addItem(subopcion);
//					this.setParent(subopcion, opcion);
//					this.setChildrenAllowed(subopcion, false);
//				}
//				this.collapseItemsRecursively(opcion);
//			}
//		}

		//Ramas principales
		Object itemCargaDatos = addItem();
		setItemCaption(itemCargaDatos, "Carga de Datos");

		//Ramas principales
		Object itemUsuario = addItem();
		setItemCaption(itemUsuario, "Usuario");


		//Hijo de Carga de Datos
		Object itemIdAlta = addItem();
		setItemCaption(itemIdAlta, "Alta");
		setParent(itemIdAlta, itemCargaDatos);
		setChildrenAllowed(itemIdAlta, false);
		//setChildrenAllowed(itemIdAlta, true);

		//Hijo de Carga de Datos
		Object itemIdBM = addItem();
		setItemCaption(itemIdBM, "Baja/Modificaciones");
		setParent(itemIdBM, itemCargaDatos);
		setChildrenAllowed(itemIdBM, false);


//		//Hijo de Carga de Datos
//		Object itemIdReportes = addItem();
//		setItemCaption(itemIdReportes,"Generar Reeportes");
//		setParent(itemIdReportes,itemReportes);
//		setChildrenAllowed(itemIdReportes,false);

		//Hijo de Usuarios
		Object itemIdUsuarioDP = addItem();
		setItemCaption(itemIdUsuarioDP, "Datos Personales");
		setParent(itemIdUsuarioDP, itemUsuario);
		setChildrenAllowed(itemIdUsuarioDP, false);

		//Hijo de Usuarios
		Object itemIdUsuarioSalir = addItem();
		setItemCaption(itemIdUsuarioSalir, "Salir");
		setParent(itemIdUsuarioSalir, itemUsuario);
		setChildrenAllowed(itemIdUsuarioSalir, false);


	}

}

	


