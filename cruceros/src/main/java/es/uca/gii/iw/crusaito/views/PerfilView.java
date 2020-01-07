package es.uca.gii.iw.crusaito.views;

import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route(value = "Perfil",layout = MainView.class)
@Secured("Cliente")
public class PerfilView extends Div{
	
}
