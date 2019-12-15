package es.uca.gii.iw.crusaito.views;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.common.Footer;
import es.uca.gii.iw.crusaito.common.Header;
import es.uca.gii.iw.crusaito.servicios.BarcoService;

@Route("CrucerosView")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class CrucerosView extends VerticalLayout {
	private static final long serialVersionUID = 1L;


	private BarcoService barcoService;
	
	@Autowired
	public CrucerosView(BarcoService barcoService) {
		this.barcoService=barcoService;
		this.getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
		
		Header header = new Header();
		add(header);
		
		// inicio body
		
		//Cada barco


		List<Barco> barcosEjemplo = barcoService.load();
		//Barco barcoEjemplo = barcosEjemplo.get(0);
		Iterator<Barco> iterador = barcosEjemplo.iterator();
		//Cuerpo de dos barcos
		HorizontalLayout subBody = new HorizontalLayout();
		while(iterador.hasNext()) {
			Div barco = new Div();
			Barco barcoEjemplo = iterador.next();
			Image fotoPrueba1 = new Image(barcoEjemplo.getbImagen(), "fotoOferta1"); // Foto de cada barco, se
			
			H1 nombre = new H1("Nombre:");
			Div textoNombre = new Div();
			textoNombre.add(barcoEjemplo.getbNombre());
			H1 aforo = new H1("Aforo:");
			Div textoAforo = new Div();
			textoAforo.add(String.valueOf(barcoEjemplo.getbAforoPasajeros()));
			barco.add(fotoPrueba1,nombre,textoNombre,aforo,textoAforo);
			barco.getStyle().set("width","500px");
			barco.getStyle().set("border-style", "solid"); // Bordes para comprobar limites

			
			subBody.add(barco);
		}

		//Cuerpo entero del body
		VerticalLayout body = new VerticalLayout();
		body.add(subBody);
		
		body.getStyle().set("width", "100%");
		body.getStyle().set("border-style", "solid");
		
		add(body);

			// TODO obtener los barcos correspondientes de la BD y mostrarlos en el "body"
		
		TextField texto = new TextField("aqui van los barcos");
		add(texto);
		
		//fin body
		
		Footer footer = new Footer();
		add(footer);
	}

}
