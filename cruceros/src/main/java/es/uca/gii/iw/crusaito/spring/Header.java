package es.uca.gii.iw.crusaito.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Header extends HorizontalLayout{

	private Image barcoCabecera = new Image("frontend/img/pruebaBarcoHeader.jpg","foto"); //Imagen del barco de prueba
	private Div separador = new Div(); //Separador vacío de la imagen y el menu
	private MenuBar menuBar = new MenuBar(); // Creacion del menu
	private Text selected = new Text(""); // esto para que ?
	private Div message = new Div(new Text("Selected: "), selected); // y esto ?
	
	public Header() {
		
		menuBar.setOpenOnHover(true); // desplegar submenu sin click
		
		MenuItem info = menuBar.addItem("Info");
		MenuItem reservas = menuBar.addItem("Reservas");
		MenuItem cuenta = menuBar.addItem("Mi perfil");
		menuBar.addItem("Cerrar sesión", e -> selected.setText("Cerrar sesión"));
		
		separador.setWidthFull();	//Anchura máxima para separarlos a cada esquina
		
		this.add(barcoCabecera,separador,menuBar);	//Añadir los elementos al header
		
		this.setWidthFull(); //Que ocupe el ancho de toda la página
		this.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); //Alineación horizontal
		this.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END); //Alineación vertical
		this.getStyle().set("border-style", "solid"); //Bordes para comprobar limites.
		
		SubMenu infoSubMenu = info.getSubMenu(); // Creacion del submenu de info
		MenuItem consejos = infoSubMenu.addItem("Consejos");
		MenuItem instalaciones = infoSubMenu.addItem("Instalaciones");

		SubMenu cuentasSubMenu = cuenta.getSubMenu(); // Creacion del submenu de perfil
		cuentasSubMenu.addItem("Listar", e -> selected.setText("Listar"));
		cuentasSubMenu.addItem("Añadir", e -> selected.setText("Añadir"));
		cuenta.getSubMenu().addItem("Editar perfil", e -> selected.setText("Editar perfil"));
		cuenta.getSubMenu().addItem("Configuración de privacidad",
				e -> selected.setText("Configuración de privacidad"));
		
	}
	
	
}
