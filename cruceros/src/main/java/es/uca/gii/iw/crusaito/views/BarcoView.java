package es.uca.gii.iw.crusaito.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.common.Footer;
import es.uca.gii.iw.crusaito.common.Header;

@Route("BarcoView")
public class BarcoView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	HorizontalLayout infoBasicaLayout = new HorizontalLayout();
	VerticalLayout infoBasica2Layout = new VerticalLayout();
	
	public BarcoView() {
		
		this.getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
		
		Header header = new Header();
		add(header);
		
		// inicio body
		
		Image fotoBarco = new Image("frontend/img/crucero1.jpg", "fotoOferta1"); // Foto de cada barco, se
																					// debe extraer de la BD más
																					// adelante
		fotoBarco.setHeight("350px"); // Altura foto
		fotoBarco.setWidth("450px"); // Anchura foto
		
		Div title = new Div(); // Texto con la descripcion
		title.add("Barquito 1");
		
		Div routes = new Div(); // Texto con la descripcion
		routes.add("Cadiz - San Fernando - Rio San Pedro");
		
		Div price = new Div(); // Texto con la descripcion
		price.add("50000€");
		
		
		infoBasica2Layout.add(title, routes, price);
		infoBasica2Layout.setAlignItems(Alignment.CENTER);
		infoBasica2Layout.getStyle().set("border-style", "solid");
		
		
		infoBasicaLayout.add(fotoBarco, infoBasica2Layout);
		
		infoBasicaLayout.getStyle().set("margin-top", "5%");
		infoBasicaLayout.getStyle().set("margin-bottom", "5%");
		//infoBasicaLayout.getStyle().set("margin-right", "3%");
		//infoBasicaLayout.getStyle().set("margin-left", "3%");
		infoBasicaLayout.setAlignItems(Alignment.CENTER);
		infoBasicaLayout.getStyle().set("border-style", "solid");
		infoBasicaLayout.setWidthFull();
		infoBasicaLayout.getStyle().set("padding-left", "3%");
		infoBasicaLayout.getStyle().set("padding-top", "3%");
		infoBasicaLayout.getStyle().set("padding-bottom", "3%");
		infoBasicaLayout.getStyle().set("padding-right", "3%");
		this.add(infoBasicaLayout);
		
		//fin body
		
		Footer footer = new Footer();
		add(footer);
	}
}
