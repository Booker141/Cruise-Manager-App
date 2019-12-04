package es.uca.gii.iw.crusaito.common;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import es.uca.gii.iw.crusaito.security.SecurityUtils;

public class Header extends HorizontalLayout{
	private static final long serialVersionUID = 1L;

	public Header() {
		
		Image logo = new Image("frontend/img/logo2.png", "logoweb");
		
		Funciones.notificacionError("Aún no está implementado");
		
		MenuBar menuBar = new MenuBar();
		MenuItem cruceros = menuBar.addItem("Cruceros"); // y esta es porque perfil tendra un menu desplegable
		MenuItem reservas = menuBar.addItem("Reservas");
		MenuItem cuenta = menuBar.addItem("Perfil");
		MenuItem sesion = menuBar.addItem("Iniciar Sesión");
		MenuItem cerrar = menuBar.addItem("Cerrar Sesión");
		MenuItem registro = menuBar.addItem("Registrarse");
		
		/*
		if(SecurityUtils.isUserLoggedIn()) {
			bienvenido.setText("Bienvenid@ " + SecurityUtils.currentUsername());
		    logout.setVisible(true);
			login.setVisible(false);
			registrar.setVisible(false);
			misReservas.setVisible(true);
			if(SecurityUtils.hasRole("Administrador") || SecurityUtils.hasRole("Gestor"))
			{
			    GesCliente.setVisible(true);
			    GesReserva.setVisible(true);
			    espacioBlancoUsu.setVisible(false);
			}
		} 
		*/
		
		//Añadir rutas
		
		Funciones.clickListener(cruceros, "CrucerosView");
		Funciones.clickListener(reservas, "CrucerosView");
		Funciones.clickListener(cuenta, "CrucerosView");
		Funciones.clickListener(sesion, "Login");
		Funciones.clickListener(cerrar, "Logout");
		Funciones.clickListener(registro, "Registrar");
	
		
		SubMenu reservaSubMenu = reservas.getSubMenu();
		reservaSubMenu.addItem("Nueva reserva", e -> Funciones.notificacionError("Aún no está implementado"));
		reservaSubMenu.addItem("Mis reservas", e -> Funciones.notificacionError("Aún no está implementado"));

		SubMenu cuentaSubMenu = cuenta.getSubMenu();
		cuentaSubMenu.addItem("Editar Perfil", e -> Funciones.notificacionError("Aún no está implementado"));
		cuentaSubMenu.addItem("Configuración de privacidad", e -> Funciones.notificacionError("Aún no está implementado"));

		HorizontalLayout horizontalHeader = new HorizontalLayout();

		horizontalHeader.add(logo, menuBar);
		horizontalHeader.setAlignItems(Alignment.CENTER); // alinear items del layout al centro (logo y menu)
		add(horizontalHeader);
		
		this.getStyle().set("border-style", "solid");
		
		/* Codigo util¿?
		menuBar.setOpenOnHover(true); // desplegar submenu sin click
		this.setWidthFull(); //Que ocupe el ancho de toda la página
		this.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); //Alineación horizontal
		this.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END); //Alineación vertical
		*/
	}
	
	
}
