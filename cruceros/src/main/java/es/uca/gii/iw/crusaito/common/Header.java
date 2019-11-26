package es.uca.gii.iw.crusaito.common;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Header extends HorizontalLayout{
	private static final long serialVersionUID = 1L;

	public Header() {
		
		Image logo = new Image("frontend/img/logo2.png", "logoweb");
		
		Notification notification = new Notification("Aún no está implementado", 3000);	// notificacion de NO implementado aun
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);	// tema de error(rojo) de la notificacion
		
		MenuBar menuBar = new MenuBar();
		MenuItem cruceros = menuBar.addItem("Cruceros"); // y esta es porque perfil tendra un menu desplegable
		MenuItem reservas = menuBar.addItem("Reservas");
		MenuItem cuenta = menuBar.addItem("Perfil");
		MenuItem sesion = menuBar.addItem("Iniciar Sesión");
		MenuItem registro = menuBar.addItem("Registrarse");
		
		//Añadir rutas
		
		sesion.addClickListener(e -> {
	    	sesion.getUI().ifPresent(ui-> ui.navigate("login"));
	    });
		
		logo.addClickListener(e -> {
	    	logo.getUI().ifPresent(ui-> ui.navigate("MainView"));
	    });
		
		cruceros.addClickListener(e -> {
	    	cruceros.getUI().ifPresent(ui-> ui.navigate("CrucerosView"));
	    });
		
		registro.addClickListener(e -> {
	    	registro.getUI().ifPresent(ui-> ui.navigate("Registrar"));
	    });
		
		SubMenu reservaSubMenu = reservas.getSubMenu();
		reservaSubMenu.addItem("Nueva reserva", e -> notification.open());
		reservaSubMenu.addItem("Mis reservas", e -> notification.open());

		SubMenu cuentaSubMenu = cuenta.getSubMenu();
		cuentaSubMenu.addItem("Editar Perfil", e -> notification.open());
		cuentaSubMenu.addItem("Configuración de privacidad", e -> notification.open());

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
