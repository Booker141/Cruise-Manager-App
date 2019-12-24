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
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.common.Footer;
import es.uca.gii.iw.crusaito.servicios.BarcoService;

@SuppressWarnings("serial")
@Route(value = "BarcosView",layout = MainView.class)
public class BarcosView extends Div{

	private BarcoService barcoService;
	
	@Autowired
	public BarcosView(BarcoService barcoService){
	
		this.barcoService=barcoService;
		this.getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
	
		// inicio body

		List<Barco> listaBarcos = barcoService.load();

		Iterator<Barco> iterador = listaBarcos.iterator();
		VerticalLayout body = new VerticalLayout();
	
		//Cuerpo de dos barcos
		HorizontalLayout subBody = new HorizontalLayout();
		int contadorBarcos = 0;
	
		while(iterador.hasNext()) {
		
			VerticalLayout barco = new VerticalLayout();
			Barco barcoEjemplo = iterador.next();
			String rutaImagen = barcoEjemplo.getbImagen();
			Image fotoPrueba1;
			if(rutaImagen!=null) {
				fotoPrueba1 = new Image(barcoEjemplo.getbImagen(), "fotoOferta1"); // Foto de cada barco, se
			}else {
				fotoPrueba1 = new Image("frontend/img/ejemploBarcoHeader.png","fallo imagen");
			}
			fotoPrueba1.setWidthFull();
			fotoPrueba1.addClickListener(urlBarco -> {
				getUI().ifPresent(ui-> ui.navigate(BarcoView.class,barcoEjemplo.getbNombre()));
			});
			H1 nombre = new H1("Nombre:");
			Div textoNombre = new Div();
			textoNombre.add(barcoEjemplo.getbNombre());
			H1 aforo = new H1("Aforo:");
			Div textoAforo = new Div();
			textoAforo.add(String.valueOf(barcoEjemplo.getbAforoPasajeros()));
			barco.add(fotoPrueba1,nombre,textoNombre,aforo,textoAforo);
			//barco.getStyle().set("width","500px");
			barco.getStyle().set("border-style", "solid"); // Bordes para comprobar limites

			subBody.add(barco);
			contadorBarcos++;
			if(contadorBarcos==2) {
				body.add(subBody);
				subBody = new HorizontalLayout();
				contadorBarcos=0;
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
