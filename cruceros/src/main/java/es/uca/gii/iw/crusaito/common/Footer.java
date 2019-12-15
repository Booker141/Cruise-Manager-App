package es.uca.gii.iw.crusaito.common;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class Footer extends HorizontalLayout{
	private static final long serialVersionUID = 1L;
	
	private Anchor linkPreguntas = new Anchor("#","Preguntas");		//Enlaces
	private Anchor linkContacto = new Anchor("#","Contacto");
	private Anchor linkAyuda = new Anchor("#","Ayuda");
	
	public Footer(){
		
		linkPreguntas.getStyle().set("margin-right", "10%");	//Separacion entre enlaces
		linkContacto.getStyle().set("margin-right", "10%");
		
		// linkAyuda.getStyle().set("color", "#3778A8"); // Cambiar color del enlac

		/*
		Tab boton1 = new Tab(VaadinIcon.BELL.create(), linkPreguntas);
		Tab boton2 = new Tab(VaadinIcon.PHONE.create(), linkContacto);
		Tab boton3 = new Tab(VaadinIcon.GROUP.create(), linkAyuda);
		Label label = new Label("© Crusaito S.L. 2019. Todos los derechos reservados");
		Tabs buttonBar = new Tabs(boton1, boton2, boton3);
		HorizontalLayout footer = new HorizontalLayout(buttonBar);
		VerticalLayout footer2 = new VerticalLayout(buttonBar, label);
		
		footer.setJustifyContentMode(JustifyContentMode.CENTER);
		footer.setWidth("100%");
		footer2.setJustifyContentMode(JustifyContentMode.CENTER);
		footer2.setWidth("100%");
		*/
		
		this.add(linkPreguntas, linkContacto, linkAyuda);
		
		this.setWidthFull(); //Que ocupe el ancho de toda la página
		this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); //Alineación horizontal
		
		this.getStyle().set("border-style", "solid"); //Bordes para comprobar limites.
	}
}
