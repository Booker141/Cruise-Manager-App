package es.uca.gii.iw.crusaito.spring;

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
		
		getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
		
		Image logo = new Image("frontend/img/logo.png", "logoweb");
		
		Notification notification = new Notification("Not implemented yet...", 3000);	// notificacion de NO implementado aun
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);	// tema de error(rojo) de la notificacion
		
		MenuBar menuBar = new MenuBar();
		menuBar.addItem("Home", e -> notification.open()); // Esta asignacion es porque es un boton directamente, no tiene submenu desplegable
		MenuItem info = menuBar.addItem("Información"); // y esta es porque perfil tendra un menu desplegable
		MenuItem reservas = menuBar.addItem("Reservas");
		MenuItem cuenta = menuBar.addItem("Perfil");
		menuBar.addItem("Iniciar Sesion", e -> notification.open());
		
		SubMenu infoSubMenu = info.getSubMenu();
		MenuItem barcos = infoSubMenu.addItem("Cruceros");
		infoSubMenu.addItem("Destinos", e -> notification.open());

		SubMenu crucerosSubMenu = barcos.getSubMenu();
		crucerosSubMenu.addItem("Servicios", e -> notification.open());
		crucerosSubMenu.addItem("Instalaciones", e -> notification.open());

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
		
		/* Codigo util¿?
		menuBar.setOpenOnHover(true); // desplegar submenu sin click
		this.setWidthFull(); //Que ocupe el ancho de toda la página
		this.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); //Alineación horizontal
		this.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END); //Alineación vertical
		*/
	}
	
	
}
