package es.uca.gii.iw.crusaito.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(Lumo.class)
@PWA(name = "Project Base for Crusaito with Spring", shortName = "Crusaito")
public class MainView extends VerticalLayout {
	private static final long serialVersionUID = 1L; // para evitar el warning del serial

	public MainView(@Autowired MessageBean bean) {
		Button toggleButton = new Button("Cambiar Tema", click -> {
			ThemeList themeList = UI.getCurrent().getElement().getThemeList(); //

			if (themeList.contains(Lumo.DARK)) { //
				themeList.remove(Lumo.DARK);
			} else {
				themeList.add(Lumo.DARK);
			}
		});
		add(toggleButton);

		TextField Tcodigo = new TextField("Código"); // esto para que?
		add(Tcodigo);

		MenuBar menuBar = new MenuBar(); // Creacion del menu
		menuBar.setOpenOnHover(true); // desplegar submenu sin click

		Text selected = new Text(""); // esto para que ?
		Div message = new Div(new Text("Selected: "), selected); // y esto ?

		MenuItem info = menuBar.addItem("Info");
		MenuItem reservas = menuBar.addItem("Reservas");
		MenuItem cuenta = menuBar.addItem("Mi perfil");
		menuBar.addItem("Cerrar sesión", e -> selected.setText("Cerrar sesión"));
		add(menuBar);

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
