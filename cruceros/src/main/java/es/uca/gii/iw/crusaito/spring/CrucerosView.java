package es.uca.gii.iw.crusaito.spring;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("CrucerosView")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class CrucerosView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public CrucerosView() {
		
		getElement().setAttribute("theme", "dark"); // aplicar tema oscuro
		
		Header header = new Header();
		add(header);
		
		// inicio body
		
			// TODO obtener los barcos correspondientes de la BD y mostrarlos en el "body"
		
		TextField texto = new TextField("aqui van los barcos");
		add(texto);
		
		//fin body
		
		Footer footer = new Footer();
		add(footer);
	}

}
