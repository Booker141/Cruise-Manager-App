package es.uca.gii.iw.crusaito.views;

import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.security.SecurityUtils;

@SuppressWarnings("serial")
@Route(value = "AdminView",layout = MainView.class)
@Secured("Admin")
public class AdminView extends VerticalLayout{

	public AdminView() {
		
		H1 Aviso = new H1();
		Aviso.setText("¡Bienvenido " + SecurityUtils.currentUsername() + " ! Tiene acceso a sus funciones principales.");
		add(Aviso);
	}

}
