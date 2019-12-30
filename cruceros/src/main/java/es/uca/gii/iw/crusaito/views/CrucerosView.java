package es.uca.gii.iw.crusaito.views;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.common.Footer;
import es.uca.gii.iw.crusaito.servicios.CruceroService;

@SuppressWarnings("serial")
@Route(value = "CrucerosView",layout = MainView.class)
public class CrucerosView extends Div {
	
	private CruceroService cruceroService;
	
	@Autowired
	public CrucerosView(CruceroService cruceroService) {
		
		this.cruceroService = cruceroService;
		this.getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
		
		//Header header = new Header();
		//add(header);
		
		// inicio body
		
		//Cada barco

		List<Crucero> listaCruceros = cruceroService.load();

		Iterator<Crucero> iterador = listaCruceros.iterator();
		VerticalLayout body = new VerticalLayout();
		
		//Cuerpo de dos barcos
		HorizontalLayout subBody = new HorizontalLayout();
		int contadorCruceros = 0;
		
		
		while(iterador.hasNext()) {
			
			VerticalLayout crucero = new VerticalLayout();
			Crucero cruceroEjemplo = iterador.next();
			String rutaImagen = cruceroEjemplo.getcImagen();
			Image fotoPrueba1;
			if(rutaImagen!=null) {
				fotoPrueba1 = new Image(cruceroEjemplo.getcImagen(), "fotoOferta1"); 
			}else {
				fotoPrueba1 = new Image("frontend/img/pruebaBarcoHeader.png","Fallo imagen");
			}
			fotoPrueba1.setWidthFull();
			H1 nombre = new H1("Nombre: ");
			Div textoNombre = new Div();
			textoNombre.add(cruceroEjemplo.getcNombre());
			H1 descripcion = new H1("Descripción: ");
			Div textoDescripcion = new Div();
			descripcion.add(cruceroEjemplo.getcDescripcion());
			H1 duracion = new H1("Duracion: ");
			Div textoDuracion = new Div();
			textoDuracion.add(cruceroEjemplo.getcDuracion());
			H1 origen = new H1("Salida: ");
			Div textoOrigen = new Div();
			textoOrigen.add(cruceroEjemplo.getcOrigen());
			H1 barco = new H1("Barco: ");
			Div textoBarco = new Div(); //clase crucero idCamarotes DUDAAA!
			textoBarco.add(cruceroEjemplo.getcNombre());
			H1 precio = new H1("Precio: ");
			Div textoPrecio = new Div();
			textoPrecio.add(String.valueOf(cruceroEjemplo.getcPrecio()));
			
			/*
			H1 aforo = new H1("Aforo:");
			Div textoAforo = new Div();
			textoAforo.add(String.valueOf(barcoEjemplo.getbAforoPasajeros()));
			*/
			
			crucero.add(fotoPrueba1,nombre,textoNombre,descripcion,textoDescripcion, duracion, textoDuracion, origen, textoOrigen, barco, textoBarco, precio, textoPrecio);
			//crucero.getStyle().set("width","500px");
			crucero.getStyle().set("border-style", "solid"); // Bordes para comprobar limites

			subBody.add(crucero);
			contadorCruceros++;
			if(contadorCruceros==2) {
				body.add(subBody);
				subBody = new HorizontalLayout();
				contadorCruceros=0;
			}
		}
		
		body.getStyle().set("width", "100%");
		body.getStyle().set("border-style", "solid");
		
		add(body);
		
		//fin body
		
		Footer footer = new Footer();
		add(footer);
	}

}
