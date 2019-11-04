package es.uca.gii.iw.crusaito.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(Lumo.class)
@PWA(name = "Project Base for Crusaito with Spring", shortName = "Crusaito")
public class MainView extends VerticalLayout {
	private static final long serialVersionUID = 1L; // para evitar el warning del serial

	public MainView(@Autowired MessageBean bean) {
		
		/* CODIGO RANDOM
		TextField Tcodigo = new TextField("Código"); // esto para que?
		add(Tcodigo);
		*/
		
	//Inicio cabecera
		
		Image logo = new Image("frontend/img/logo.png","logoweb");
		
		Button toggleButton = changeTheme();
		
		//TODO barra de navegacion y añadirla al header
		
		MenuBar menuBar = new MenuBar();
		MenuItem project = menuBar.addItem("Reservas");
		MenuItem account = menuBar.addItem("Perfil");
		menuBar.addItem("Administrar");
		menuBar.addItem("Cerrar Sesión");
		
		Header cabecera = new Header();
		cabecera.add(logo, menuBar, toggleButton);
		
		HorizontalLayout horizontalHeader = new HorizontalLayout();
		
		horizontalHeader.add(cabecera);
		add(horizontalHeader);
		
	//Fin cabecera
		
	//Inicio body
		
		H1 titulo1 = new H1("Ofertas");	//Titulo
		titulo1.getStyle().set("margin-left", "40%");	//Titulo de la seccion izquierda
		
		Image fotoPrueba1 = new Image("frontend/img/pruebaBarcoHeader.png","fotoOferta1"); //Foto de cada barco, se debe extraer de la BD más adelante
		fotoPrueba1.setMaxHeight("350px");	//Altura foto
		fotoPrueba1.setMaxWidth("450px");	//Anchura foto
		
		HorizontalLayout infoBarco1 = new HorizontalLayout(); //"Ficha" de cada oferta que consta de Foto, descripcion y precio.
		Div texto1 = new Div();	//Texto con la descripcion
		texto1.add("Este barco parte desde Cádiz hasta Italia. "
				+ "Precio: 650€");
		infoBarco1.add(fotoPrueba1,texto1);
		
		H1 titulo2 = new H1("Reservas");
		titulo2.getStyle().set("margin-left", "40%");	//Titulo de la seccion derecha
		
		Image fotoPrueba2 = new Image("frontend/img/pruebaBarcoHeader.png","fotoOferta2");
		fotoPrueba2.setMaxHeight("350px");	//Altura foto
		fotoPrueba2.setMaxWidth("450px");	//Anchura foto
		
		HorizontalLayout infoBarco2 = new HorizontalLayout();
		Div texto2 = new Div();
		texto2.add("Descripción de la reserva.");
		infoBarco2.add(fotoPrueba2,texto2);

		//Bloque izquierdo centro de la web
		VerticalLayout leftVerticalBody = new VerticalLayout(titulo1, infoBarco1);
		leftVerticalBody.getStyle().set("border-style", "solid");	//Bordes para comprobar limites
		
		//Bloque derecho centro de la web
		VerticalLayout rightVerticalBody = new VerticalLayout(titulo2, infoBarco2);
		rightVerticalBody.getStyle().set("border-style", "solid");	//Bordes para comprobar limites
		
		
		//Bloque del cuerpo de la web que contiene los dos bloques verticales.
		HorizontalLayout bodyHorizontal = new HorizontalLayout(leftVerticalBody, rightVerticalBody);
		
		bodyHorizontal.setWidthFull();
		bodyHorizontal.getStyle().set("border-style", "solid"); //Bordes para comprobar limites.
		
		add(bodyHorizontal);
		
	//Fin body
		
	//Inicio Footer
		
		Footer footer = new Footer();
		add(footer);
		
	//Fin Footer
	}
	
	public Button changeTheme() {
		
		Button toggleButton = new Button("Cambiar Tema", click -> {
			ThemeList themeList = UI.getCurrent().getElement().getThemeList(); //

			if (themeList.contains(Lumo.DARK)) { //
				themeList.remove(Lumo.DARK);
			} else {
				themeList.add(Lumo.DARK);
			}
		});
		return toggleButton;
	}
	
}
