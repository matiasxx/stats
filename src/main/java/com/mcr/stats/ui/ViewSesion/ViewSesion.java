package com.mcr.stats.ui.ViewSesion;

import java.util.Iterator;

import com.mcr.stats.Ihandler.IhandlerViewSesion;
import com.mcr.stats.Iview.IViewSesion;
import com.mcr.stats.ViewOperaciones.NavegadorAdministrativo;
import com.mcr.stats.ViewOperaciones.ViewOperaciones;
import com.mcr.stats.model.Usuario;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class ViewSesion extends VerticalLayout implements ClickListener, IViewSesion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NAME = "";
	private TextField txtNombreDeUsuario;
	private PasswordField txtContrasenia;
	private Button btnValidar;
	private Button btnRecuperar;
	
	
	private IhandlerViewSesion handler;
	
	public ViewSesion() {
		
		
		
		addStyleName("backColoGrey");
		setSizeFull();
		Component formularioLogin = generarFormulario();
		addComponent(formularioLogin);	
		setComponentAlignment(formularioLogin, Alignment.MIDDLE_CENTER);
		
		
		
		
	}

	private Component generarFormulario() {
		
		VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.addStyleName("login-panel");
		loginPanel.setSpacing(true);
		loginPanel.addComponent(generarLabels());
		loginPanel.addComponent(generarCampos());
		loginPanel.addComponent(generarComandos());
		
		return loginPanel;
	}

	private Component generarComandos() {
		
		VerticalLayout comandos = new VerticalLayout();
		
		setBtnValidar(new Button("Ingresar",this));
		getBtnValidar().addStyleName(ValoTheme.BUTTON_PRIMARY);
		getBtnValidar().setClickShortcut(KeyCode.ENTER);
		getBtnValidar().setStyleName("friendly");
		getBtnValidar().setWidth("100%");
		
		
		setBtnRecuperar(new Button("Recuperar acceso?",this));
		getBtnRecuperar().addStyleName(ValoTheme.BUTTON_BORDERLESS);
		getBtnRecuperar().setWidth("100%");
		getBtnRecuperar().setStyleName("v-button-recupero");
		
		comandos.setSpacing(true);
		comandos.setSizeFull();
		comandos.addComponent(getBtnValidar());
		comandos.addComponent(getBtnRecuperar());
		
		
		return comandos;
	}


	
	

	private Component generarCampos() {
		
		HorizontalLayout campos = new HorizontalLayout();
		campos.setSpacing(true);
		setTxtNombreDeUsuario(new TextField("Nombre de usuario:"));		
		getTxtNombreDeUsuario().setRequired(true);
		
		Validator stringValidator = new RegexpValidator("^[a-zA-Z-0-9]+$", "Ingrese caracteres validos");
		getTxtNombreDeUsuario().addValidator(stringValidator);		
		getTxtNombreDeUsuario().setIcon((FontAwesome.USER));
		getTxtNombreDeUsuario().addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		getTxtNombreDeUsuario().focus();
		
		setTxtContrasenia(new PasswordField("Contraseña:"));
		getTxtContrasenia().setRequired(true);		
		getTxtContrasenia().setIcon(FontAwesome.LOCK);
		getTxtContrasenia().addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		
	
		
		campos.addComponent(this.getTxtNombreDeUsuario());
		campos.addComponent(this.getTxtContrasenia());
		
		
		
		
		return campos;
	}

	private Component generarLabels() {
		
		CssLayout labels = new CssLayout();
		
		Label lblBienvenida = new Label("<font color='white'>Bienvenido - Sistema de Carga Estadistica", ContentMode.HTML);
		lblBienvenida.setSizeUndefined();
		lblBienvenida.addStyleName(ValoTheme.LABEL_H4);
		lblBienvenida.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(lblBienvenida);
		
		return labels;
	}

	public TextField getTxtNombreDeUsuario() {
		return txtNombreDeUsuario;
	}

	public void setTxtNombreDeUsuario(TextField txtNombreDeUsuario) {
		this.txtNombreDeUsuario = txtNombreDeUsuario;
	}

	public PasswordField getTxtContrasenia() {
		return txtContrasenia;
	}

	public void setTxtContrasenia(PasswordField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}

	public Button getBtnValidar() {
		return btnValidar;
	}

	public void setBtnValidar(Button btnValidar) {
		this.btnValidar = btnValidar;
	}

	public Button getBtnRecuperar() {
		return btnRecuperar;
	}

	public void setBtnRecuperar(Button btnRecuperar) {
		this.btnRecuperar = btnRecuperar;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getBtnValidar()){
			if(this.getTxtContrasenia().isValid()&&this.getTxtNombreDeUsuario().isValid()){
				
				handler.login();
			}
		}
		
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	public IhandlerViewSesion getHandler() {
		return handler;
	}

	public void setHandler(IhandlerViewSesion handler) {
		this.handler = handler;
	}

	public void loginError() {
		
		Notification notification = new Notification("Error", "Usuario y/o contraseñas incorrectas ", Type.ERROR_MESSAGE);
		notification.setPosition(Position.MIDDLE_CENTER);
		notification.setDelayMsec(3000);
		notification.show(Page.getCurrent());
		
	}

	public void loginOk(final Usuario usuario) {
		
		UI.getCurrent().getSession().setAttribute("usuario", usuario); 
		//UI.getCurrent().getNavigator().navigateTo(ViewOperaciones.NAME);

		//en caso de que sea una persona que tenga acceso
		//diferenctes sectores
		if (usuario.getAccesos().size() >= 2) {

			final Window ventanaSeleccion = new Window();

			VerticalLayout lay = new VerticalLayout();
			lay.setMargin(true);
			lay.setSpacing(true);

			Iterator<String> iteradorAccesos = usuario.getAccesos().iterator();
			while (iteradorAccesos.hasNext()) {

				String accesos = iteradorAccesos.next();


				int i = 0;
				lay.addComponent(new Button(accesos, new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent event) {

						if (event.getComponent().getCaption().equalsIgnoreCase("licencia de conducir")){
							usuario.getAccesos().clear();
							usuario.getAccesos().add("licencia de conducir");
						}
						if (event.getComponent().getCaption().equalsIgnoreCase("obras publicas - proyectos")){
							usuario.getAccesos().clear();
							usuario.getAccesos().add("obras publicas - proyectos");
						}
						if (event.getComponent().getCaption().equalsIgnoreCase("obras publicas - Contrataciones")){
							usuario.getAccesos().clear();
							usuario.getAccesos().add("obras publicas - contrataciones");
						}
						if (event.getComponent().getCaption().equalsIgnoreCase("obras publicas - Certificaciones")){
							usuario.getAccesos().clear();
							usuario.getAccesos().add("obras publicas - certificaciones");
						}
						UI.getCurrent().getNavigator().navigateTo(ViewOperaciones.NAME);
						ventanaSeleccion.close();
					}
				}));
			}

			ventanaSeleccion.center();
			ventanaSeleccion.setClosable(true);
			ventanaSeleccion.setResizable(false);
			ventanaSeleccion.setCaption("Seleccione perfil");
			ventanaSeleccion.setWidth("400px");
			ventanaSeleccion.setHeight("200px");
			ventanaSeleccion.setModal(true);
			ventanaSeleccion.setContent(lay);
			UI.getCurrent().addWindow(ventanaSeleccion);

		}else{
			UI.getCurrent().getNavigator().navigateTo(ViewOperaciones.NAME);
		}
	}
	
	

}
