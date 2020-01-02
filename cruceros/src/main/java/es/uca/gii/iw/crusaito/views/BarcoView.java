package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.common.Footer;
import es.uca.gii.iw.crusaito.servicios.BarcoService;

@Route(value = "BarcoView", layout = MainView.class)
public class BarcoView extends VerticalLayout implements HasUrlParameter<String>{

	private static final long serialVersionUID = 1L;
	
	private HorizontalLayout infoBasicaLayout = new HorizontalLayout();
	private VerticalLayout infoBasica2Layout = new VerticalLayout();
	
	private BarcoService barcoService;
	private Barco barco;
	private String nombreBarco;
	
	@Autowired
	public BarcoView(BarcoService barcoService) {

		this.barcoService = barcoService;
		this.barco = barcoService.findBybNombre(nombreBarco);
		
		this.getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
		
		String rutaFoto = barco.getbImagen();
		Image fotoBarco;
		if(rutaFoto!=null)fotoBarco = new Image(barco.getbImagen(), "fotoOferta1"); 
		else fotoBarco = new Image("frontend/img/pruebaBarcoHeader.png","fallo imagen");
		/*fotoBarco.setHeight("350px"); // Altura foto
		fotoBarco.setWidth("450px"); // Anchura foto
		*/
		
		Div title = new Div(); // Texto con la descripcion
		title.add(barco.getbNombre());
		
		Div descripcion = new Div();
		descripcion.add(barco.getbDescripcion());

		Div ruta = new Div(); // Texto con la descripcion
		ruta.add("Cadiz - San Fernando - Rio San Pedro");
		
		infoBasica2Layout.add(title, descripcion, ruta);
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
	
	@Override
	public void setParameter(BeforeEvent evento, String bNombre) {
		this.nombreBarco = bNombre;
	}
}
