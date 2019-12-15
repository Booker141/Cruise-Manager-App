package es.uca.gii.iw.crusaito.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
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
		Div barco = new Div();

		List<Barco> barcosEjemplo = barcoService.load();
		Barco barcoEjemplo = barcosEjemplo.get(0);
		
		H1 nombre = new H1("Nombre:");
		Div textoNombre = new Div();
		textoNombre.add(barcoEjemplo.getbNombre());
		H1 aforo = new H1("Aforo:");
		Div textoAforo = new Div();
		textoAforo.add(String.valueOf(barcoEjemplo.getbAforoPasajeros()));
		barco.add(nombre,textoNombre,aforo,textoAforo);
		//Cuerpo de dos barcos
		HorizontalLayout subBody = new HorizontalLayout();
		subBody.add(barco);
		//Cuerpo entero del body
		VerticalLayout body = new VerticalLayout();
		body.add(subBody);
		
		
		barco.getStyle().set("border-style", "solid"); // Bordes para comprobar limites
		
		add(body);

			// TODO obtener los barcos correspondientes de la BD y mostrarlos en el "body"
		
		TextField texto = new TextField("aqui van los barcos");
		add(texto);
		
		//fin body
		
		Footer footer = new Footer();
		add(footer);
	}

}
