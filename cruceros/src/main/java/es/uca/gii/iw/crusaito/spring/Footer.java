package es.uca.gii.iw.crusaito.spring;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Footer extends HorizontalLayout{
	private static final long serialVersionUID = 1L;
	
	private Anchor linkPreguntas = new Anchor("#","Preguntas");		//Enlace
	private Anchor linkContacto = new Anchor("#","Contacto");		//Enlace
	private Anchor linkAyuda = new Anchor("#","Ayuda");				//Enlace
	
	public Footer() {
		
		getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
		
		linkPreguntas.getStyle().set("margin-right", "10%");	//Separacion entre enlaces
		linkContacto.getStyle().set("margin-right", "10%");		//Separacion entre enlaces
		
		this.add(linkPreguntas,linkContacto,linkAyuda);
		
		this.setWidthFull(); //Que ocupe el ancho de toda la página
		this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); //Alineación horizontal
		
		this.getStyle().set("border-style", "solid"); //Bordes para comprobar limites.
	}
}
