package es.uca.gii.iw.crusaito.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.uca.gii.iw.crusaito.common.Footer;
import es.uca.gii.iw.crusaito.common.Header;

@Route("MainView")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Project Base for Crusaito with Spring", shortName = "Crusaito")

public class MainView extends VerticalLayout {
	private static final long serialVersionUID = 1L; // para evitar el warning del serial

	public MainView(@Autowired MessageBean bean) {
		
		getElement().setAttribute("theme", "dark"); // aplicar tema oscuro

	// Inicio cabecera
		
		Header header = new Header();
		add(header);

	// Fin cabecera
		
	// Inicio body

		H1 titulo1 = new H1("Oferta del día"); // Titulo
		// como cambiamos el color al H1 ?????
		titulo1.getStyle().set("margin-left", "30%"); // Titulo de la seccion izquierda

		Image fotoPrueba1 = new Image("frontend/img/crucero1.jpg", "fotoOferta1"); // Foto de cada barco, se
																					// debe extraer de la BD más
																					// adelante
		fotoPrueba1.setMaxHeight("350px"); // Altura foto
		fotoPrueba1.setMaxWidth("450px"); // Anchura foto

		HorizontalLayout infoBarco1 = new HorizontalLayout(); // "Ficha" de cada oferta que consta de Foto, descripcion
																// y precio.
		Div texto1 = new Div(); // Texto con la descripcion
		texto1.add("Transmediterráneo con 4 destinos elegibles, camarote 2 personas, 7 días. " + "\nPrecio: 550€/p");
		infoBarco1.add(fotoPrueba1, texto1);

		H1 titulo2 = new H1("Más vendido");
		titulo2.getStyle().set("margin-left", "30%"); // Titulo de la seccion derecha

		Image fotoPrueba2 = new Image("frontend/img/crucero2.jpg", "fotoOferta2");
		fotoPrueba2.setMaxHeight("350px"); // Altura foto
		fotoPrueba2.setMaxWidth("450px"); // Anchura foto

		HorizontalLayout infoBarco2 = new HorizontalLayout();
		Div texto2 = new Div();
		texto2.add("Transatlántico con 3 destinos elegibles e intercambiables, camarote 3 personas, 5 días. "
				+ "\nPrecio: 450€/p");
		infoBarco2.add(fotoPrueba2, texto2);

		// Bloque izquierdo centro de la web
		VerticalLayout leftVerticalBody = new VerticalLayout(titulo1, infoBarco1);
		leftVerticalBody.getStyle().set("border-style", "solid"); // Bordes para comprobar limites

		// Bloque derecho centro de la web
		VerticalLayout rightVerticalBody = new VerticalLayout(titulo2, infoBarco2);
		rightVerticalBody.getStyle().set("border-style", "solid"); // Bordes para comprobar limites

		// Bloque del cuerpo de la web que contiene los dos bloques verticales.
		HorizontalLayout bodyHorizontal = new HorizontalLayout(leftVerticalBody, rightVerticalBody);

		bodyHorizontal.setWidthFull();
		bodyHorizontal.getStyle().set("border-style", "solid"); // Bordes para comprobar limites.
		//bodyHorizontal.getStyle().set("color", "green"); //para cambiar de color las lineas (y texto interior ¿?)

		add(bodyHorizontal);

	// Fin body

	// Inicio Footer
		
		Footer footer = new Footer();	//no funciona en el mainview
		add(footer);

	// Fin Footer
		
	} // MainView

	/**
	 * changeTheme Crea un boton para intercambiar entre tema claro y oscuro
	 */
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
