package es.uca.gii.iw.crusaito.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
		
		Button toggleButton = new Button("Cambiar Tema", click -> {
			ThemeList themeList = UI.getCurrent().getElement().getThemeList(); //

			if (themeList.contains(Lumo.DARK)) { //
				themeList.remove(Lumo.DARK);
			} else {
				themeList.add(Lumo.DARK);
			}
		});
		add(toggleButton);

		TextField Tcodigo = new TextField("Código"); // esto para que?
		add(Tcodigo);
		
		//Inicio cabecera
		
		Header cabecera = new Header();
		add(cabecera);
		//Fin cabecera
		
		//Inicio body
		
		H1 titulo = new H1("Ofertas");	//Titulo
		titulo.getStyle().set("margin-left", "40%");	//Titulo de la seccion izquierda
		
		Image fotoPrueba = new Image("frontend/img/pruebaBarcoHeader.png","fotoOferta"); //Foto de cada barco, se debe extraer de la BD más adelante
		fotoPrueba.setMaxHeight("350px");	//Altura foto
		fotoPrueba.setMaxWidth("450px");	//Anchura foto
		
		HorizontalLayout infoBarco1 = new HorizontalLayout(); //"Ficha" de cada oferta que consta de Foto, descripcion y precio.
		Div texto = new Div();	//Texto con la descripcion
		texto.add("Este barco parte desde Cádiz hasta Italia. "
				+ "Precio: 650€");
		infoBarco1.add(fotoPrueba,texto);
		
		HorizontalLayout infoBarco2 = new HorizontalLayout();	//Este infoBarco2 no aparece en la web
		
		infoBarco2.add(fotoPrueba,texto);

		//Bloque izquierdo centro de la web
		VerticalLayout leftVerticalBody = new VerticalLayout(titulo,infoBarco1,infoBarco2);
		leftVerticalBody.getStyle().set("border-style", "solid");	//Bordes para comprobar limites
		//Bloque derecho centro de la web
		VerticalLayout rightVerticalBody = new VerticalLayout();
		rightVerticalBody.getStyle().set("border-style", "solid");	//Bordes para comprobar limites
		//Bloque del cuerpo de la web que contiene los dos bloques verticales.
		HorizontalLayout bodyHorizontal = new HorizontalLayout(leftVerticalBody,rightVerticalBody);
		
		bodyHorizontal.setWidthFull();
		bodyHorizontal.getStyle().set("border-style", "solid"); //Bordes para comprobar limites.
		add(bodyHorizontal);
		//Fin body
		
		//Inicio Footer
		Footer footer = new Footer();
		add(footer);
		
		//Fin Footer
	}
}
