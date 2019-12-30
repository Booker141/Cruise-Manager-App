package es.uca.gii.iw.crusaito.views;
/*
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.*;
import es.uca.gii.iw.crusaito.common.Funciones;


@Route(value = "Reserva",layout = MainView.class)
public class ReservaView extends VerticalLayout{

		private static final long serialVersionUID = 1L;
		private Crucero crucero;
	    private Reserva reserva;
	    private Button volver = new Button();

	    @Autowired
	    public ReservaView() {
	        if(UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {

	            H2 titulo = new H2("Información de la reserva: ");
	            Label viaje = new Label("El crucero reservado: " + crucero.getcNombre());
	            Label codigo = new Label("El codigo de la reserva es: " + reserva.getId());
	            Label duracion = new Label("El crucero dura desde: " + reserva.getFechaInicio() + " hasta " + reserva.getFechaFin());
	            Label precio = new Label("El precio es de: " + reserva.getPrecio() + " €");
	            VerticalLayout layout = new VerticalLayout(titulo, viaje, codigo, duracion, precio, volver);
	            add(layout);
	            Funciones.clickListener(volver, "ReservasView");

	        } else
	            UI.getCurrent().navigate("MainView");
	    }
}
*/