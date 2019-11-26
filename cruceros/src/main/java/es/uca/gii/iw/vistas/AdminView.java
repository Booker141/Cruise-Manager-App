package es.uca.gii.iw.vistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.security.SecurityUtils;

@Route("AdminView")
@Secured("Admin")
public class AdminView extends VerticalLayout{
	private static final long serialVersionUID = 1L;

	@Autowired
	public AdminView() {
		H1 texto = new H1();
		
		if(SecurityUtils.isUserLoggedIn()) {
			texto.setText("Estoy conectado");
		}else {
			texto.setText("No estoy conectado");
		}
		
		Button botonSoloParaElAdmin = new Button();
		
		if(SecurityUtils.hasRole("Admin")) {
			botonSoloParaElAdmin.setVisible(true);
		}else {
			botonSoloParaElAdmin.setVisible(false);
		}
	}

}
