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
		
		linkPreguntas.getStyle().set("margin-right", "10%");	//Separacion entre enlaces
		linkContacto.getStyle().set("margin-right", "10%");		//Separacion entre enlaces
		
		linkAyuda.getStyle().set("color", "#3778A8"); // Cambiar color del link
		
		this.add(linkPreguntas,linkContacto,linkAyuda);
		
		this.setWidthFull(); //Que ocupe el ancho de toda la página
		this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); //Alineación horizontal
		
		this.getStyle().set("border-style", "solid"); //Bordes para comprobar limites.
	}
}
