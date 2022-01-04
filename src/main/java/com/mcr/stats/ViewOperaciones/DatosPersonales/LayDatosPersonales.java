package com.mcr.stats.ViewOperaciones.DatosPersonales;

import org.vaadin.dialogs.ConfirmDialog;

import com.mcr.stats.Ihandler.IhandlerLayDatosPersonales;
import com.mcr.stats.Ihandler.IhandlerLayLicencia;
import com.mcr.stats.model.Usuario;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LayDatosPersonales extends VerticalLayout implements ClickListener{
	
		
	private static final long serialVersionUID = 1L;
	private TextField txtNombre;
	private TextField txtApellido;
	private TextField txtNumeroDeDocumento;
	private PasswordField txtPassword;
	private PasswordField txtPasswordReingreso;
	private Button btnActualizar;
	
	private IhandlerLayDatosPersonales handler;
	

	public LayDatosPersonales() {
	
		setMargin(true);
		setSpacing(true);
    	setSizeFull();
    	
    	Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");
    	
    	addComponent(generarTxtNombre(usuario));
    	addComponent(generarTxtApellitdo(usuario));
    	addComponent(generarTxtNumeroDeDocumento(usuario));
		addComponent(generarPassNuevo());
		addComponent(generarPassNuevoRepetido());
		addComponent(generarBtnActualizar());
		
		
		
		
		
	}


	private Component generarBtnActualizar() {
		
		setBtnActualizar(new Button("Actualizar"));
		getBtnActualizar().addClickListener(this);
		return getBtnActualizar();
	}


	private Component generarPassNuevoRepetido() {
		
		setTxtPasswordReingreso(new PasswordField("<big><strong>Ingrese nuevo contraseña:"));
		getTxtPasswordReingreso().setCaptionAsHtml(true);
		getTxtPasswordReingreso().setWidth("250px");
		return getTxtPasswordReingreso();
	}


	private Component generarPassNuevo() {
		
		setTxtPassword(new PasswordField("<big><strong>Reingrese nuevo contraseña:"));
		getTxtPassword().setCaptionAsHtml(true);
		getTxtPassword().setWidth("250px");
		return getTxtPassword();
	}


	private Component generarTxtNumeroDeDocumento(Usuario usuario) {
		
		setTxtNumeroDeDocumento(new TextField("<big><strong>Numero de documento:"));
		getTxtNumeroDeDocumento().setCaptionAsHtml(true);
		getTxtNumeroDeDocumento().setValue(usuario.getNumeroDeDocumento());
		getTxtNumeroDeDocumento().setWidth("250px");
		return getTxtNumeroDeDocumento();
	}


	private Component generarTxtApellitdo(Usuario usuario) {
		
		setTxtApellido(new TextField("<big><strong>Apellido:"));
		getTxtApellido().setWidth("250px");
		getTxtApellido().setValue(usuario.getApellido().toUpperCase());
		getTxtApellido().setCaptionAsHtml(true);
		return getTxtApellido();
	}


	private Component generarTxtNombre(Usuario usuario) {
		
		setTxtNombre(new TextField("<big><strong>Nombre:"));
		getTxtNombre().setValue(usuario.getNombre().toUpperCase());
		getTxtNombre().setWidth("250px");
		getTxtNombre().setCaptionAsHtml(true);
		return getTxtNombre();
	}


	public TextField getTxtNombre() {
		return txtNombre;
	}


	public void setTxtNombre(TextField txtNombre) {
		this.txtNombre = txtNombre;
	}


	public TextField getTxtApellido() {
		return txtApellido;
	}


	public void setTxtApellido(TextField txtApellido) {
		this.txtApellido = txtApellido;
	}


	public TextField getTxtNumeroDeDocumento() {
		return txtNumeroDeDocumento;
	}


	public void setTxtNumeroDeDocumento(TextField txtNumeroDeDocumento) {
		this.txtNumeroDeDocumento = txtNumeroDeDocumento;
	}


	public PasswordField getTxtPassword() {
		return txtPassword;
	}


	public void setTxtPassword(PasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}


	public PasswordField getTxtPasswordReingreso() {
		return txtPasswordReingreso;
	}


	public void setTxtPasswordReingreso(PasswordField txtPasswordReingreso) {
		this.txtPasswordReingreso = txtPasswordReingreso;
	}


	public IhandlerLayDatosPersonales getHandler() {
		return handler;
	}


	public void setHandler(IhandlerLayDatosPersonales handler) {
		this.handler = handler;
	}


	public Button getBtnActualizar() {
		return btnActualizar;
	}


	public void setBtnActualizar(Button btnActualizar) {
		this.btnActualizar = btnActualizar;
	}


	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == getBtnActualizar()) {
			
			if(!getTxtApellido().isValid() ||
					!getTxtNombre().isValid() ||
					!getTxtNumeroDeDocumento().isValid()||
					!getTxtPassword().isValid()||
					!getTxtPasswordReingreso().isValid()) {
				
				Notification noti = new Notification("Atencion", "Datos mal ingresados, incompletos y/o incorrectos", Type.ERROR_MESSAGE);
				noti.setDelayMsec(3000);
				noti.setPosition(Position.MIDDLE_CENTER);
				noti.show(Page.getCurrent());
			}
			else {
				if(getTxtPassword().getValue().equals(getTxtPasswordReingreso().getValue())) {
					
					ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea actualizar datos?", "SI", "NO", new ConfirmDialog.Listener() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void onClose(ConfirmDialog rta) {

							if(rta.isConfirmed()){
								handler.actualizar();					
							}				
						}
					});			

					
	
				}
				else {
					
					Notification noti = new Notification("Atencion", "Las contraseñas ingresadas no son iguales", Type.ERROR_MESSAGE);
					noti.setDelayMsec(3000);
					noti.setPosition(Position.MIDDLE_CENTER);
					noti.show(Page.getCurrent());
				}
			}
			
		}
		
	}


	public void actualizarOK() {
		
		Notification noti = new Notification("Atencion", "Datos actualizados correctamente", Type.HUMANIZED_MESSAGE);
		noti.setDelayMsec(3000);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}


	public void actualizarError() {
		
		Notification noti = new Notification("Atencion", "Datos no actualizados", Type.ERROR_MESSAGE);
		noti.setDelayMsec(3000);
		noti.setPosition(Position.MIDDLE_CENTER);
		noti.show(Page.getCurrent());
		
	}
	
	

}
