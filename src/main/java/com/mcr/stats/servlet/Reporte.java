package com.mcr.stats.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcr.stats.dao.Conexion;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 * Servlet implementation class Reportes
 */
@WebServlet("/Reportes")
public class Reporte extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Reporte() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String jasperFile = (String)request.getParameter("jasperFile");
		  String filter = (String)request.getParameter("filter");
		  String parameter1 = (String)request.getParameter("parameter1");
		  String parameter2 = (String)request.getParameter("parameter2");
		  String parameter3 = (String)request.getParameter("parameter3");


		System.out.println(filter);
		System.out.println(filter);
		  
		  
		  //String parameter4 = (String)request.getParameter("parameter4");
		  //String parameter5 = (String)request.getParameter("parameter5");
		  
		  //System.out.println(filter);
		 // System.out.println(jasperFile);
		 // System.out.println(parameter1);
		 // System.out.println(parameter2);
		//  System.out.println(parameter3);
		    
		   
		   byte[] bytes = null;  
		   try {    
		    Conexion conexion = new Conexion();
		   // conexion.setConexion(ConnectionPool.getInstance().getConnection());
		    Map<String, Object> parameters = new HashMap<String, Object>();
		    
		    parameters.put("filter",filter);
		    parameters.put("jasperFile",jasperFile);
		    parameters.put("parameter1",parameter1);
		    parameters.put("parameter2",parameter2);
		    parameters.put("parameter3",parameter3);

		    //System.out.println(filter);
		     
		    JasperReport reporteGenerado = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/"+jasperFile));
		    //JasperReport reporteGenerado = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/"+jasperFile));		    
		    //JasperReport reporteGenerado = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/report_licencias.jasper"));
		    //InputStream jasperIS = getClass().getResourceAsStream("/reporte_tramite.jasper");
		    bytes = JasperRunManager.runReportToPdf(reporteGenerado, parameters, conexion.getConexion());		    
		    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");   
		    Date complemento = new Date();
		    response.setContentType("application/pdf");    
		    response.setHeader("Content-disposition","attachment; filename= rep_"+formatoFecha.format(complemento)+"_.pdf");
		    
		    //Content-Disposition: inline abre en otro tab
		    //Content-Disposition: attachment permite descarga
		    //Content-Disposition: attachment; filename="filename.jpg" setea nombre
		    
		    
		    response.setContentLength(bytes.length);
		    ServletOutputStream servletOutputStream = response.getOutputStream();

		    servletOutputStream.write(bytes, 0, bytes.length);
		    servletOutputStream.flush();
		    servletOutputStream.close();
		    conexion.cerrarRecursos();
		   } catch (JRException e) {
		  
		     // display stack trace in the browser
		         StringWriter stringWriter = new StringWriter();
		         PrintWriter printWriter = new PrintWriter(stringWriter);
		         e.printStackTrace(printWriter);
		         response.setContentType("text/plain");
		         response.getOutputStream().print(stringWriter.toString());
		      
		  } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }  
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}