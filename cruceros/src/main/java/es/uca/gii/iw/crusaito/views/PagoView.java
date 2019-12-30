package es.uca.gii.iw.crusaito.views;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Reserva;
import es.uca.gii.iw.crusaito.forms.ReservaForm;
import es.uca.gii.iw.crusaito.servicios.PagoService;
import es.uca.gii.iw.crusaito.servicios.ReservaService;
import es.uca.gii.iw.crusaito.servicios.TarjetaService;

//Modificar

@SuppressWarnings("serial")
@Route(value= "Pago", layout=MainView.class)
@Secured("Cliente")
public class PagoView extends PrincipalView{
	
	/*
    private PagoService pagoService;
    private TarjetaService tarjetaService;
    private ReservaService reservaService;
    private ReservaForm reservaForm;

    @Autowired
    public PagoView(PagoService pagoService, TarjetaService tarjetaService, ReservaService reservaService) {
        if(UI.getCurrent().getSession().getAttribute(Reserva.class) != null) {
        	
            this.pagoService = pagoService;
            this.tarjetaService = tarjetaService;
            this.reservaForm = new ReservaForm(this, pagoService, tarjetaService, reservaService);

            HorizontalLayout contenido = new HorizontalLayout();
            
            H1 titulo = new H1("Datos de la reserva");
            H2 fecha = new H2("Para la fecha " +
                    UI.getCurrent().getSession().getAttribute(Reserva.class).getFechaInicio().toString() +
                    " hasta la fecha " + UI.getCurrent().getSession().getAttribute(Reserva.class).getFechaFin().toString());
            H2 vehiculo = new H2("El crucero " + UI.getCurrent().getSession().getAttribute(Vehiculo.class).getMarca().getMarca() +
                    " " + UI.getCurrent().getSession().getAttribute(Vehiculo.class).getTipo().getTipo());
            contenido.add(titulo, fecha, vehiculo);
            VerticalLayout mainContent = new VerticalLayout(contenido, reservaForm); //metemos en un objeto el grid y formulario
            mainContent.setSizeFull();

            add(mainContent);

            setSizeFull();
        } else {
            UI.getCurrent().navigate("");
            UI.getCurrent().getPage().reload();
        }
    }

}
*/