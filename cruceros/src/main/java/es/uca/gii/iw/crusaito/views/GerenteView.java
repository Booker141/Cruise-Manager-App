package es.uca.gii.iw.crusaito.views;

import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.security.SecurityUtils;

@SuppressWarnings("serial")
@Route(value = "GerenteView",layout = MainView.class)
@Secured("Gerente")
public class GerenteView extends VerticalLayout {
	
	public GerenteView() {
		
		H1 texto = new H1();
		
		if(SecurityUtils.isUserLoggedIn()) {
			texto.setText("Estoy conectado como " + SecurityUtils.currentUsername());
		}else {
			texto.setText("No estoy conectado");
		}
		
		Button botonSoloParaElGerente = new Button();
		
		if(SecurityUtils.hasRole("Gerente")) {
			botonSoloParaElGerente.setVisible(true);
		}else {
			botonSoloParaElGerente.setVisible(false);
		}
		
		add(texto,botonSoloParaElGerente);
	}


}
