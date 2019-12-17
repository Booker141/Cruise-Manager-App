package es.uca.gii.iw.crusaito.views;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Reserva;
import es.uca.gii.iw.crusaito.common.Footer;
import es.uca.gii.iw.crusaito.common.Funciones;
import es.uca.gii.iw.crusaito.common.Header;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ReservaService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "MisReservas",layout=MainView.class)
public class MisReservasView extends VerticalLayout implements BeforeEnterObserver{
	
	private Grid<Reserva> grid = new Grid<Reserva>(Reserva.class);
    
    
    private Reserva reserva;
	
    
    private Label confirmacion = new Label("¿Seguro que desea cancelar esta reserva?");
    private Button seguro = new Button("Cancelar reserva");
    private VerticalLayout ventanaSeguro = new VerticalLayout(confirmacion, seguro);
    private  Dialog ventana = new Dialog(ventanaSeguro);
    
	@Autowired
    public MisReservasView(ReservaService ReservaService, UsuarioService UsuarioService) 
	{
		Header header = new Header();
		add(header);
		
		add(grid);
		
		grid.setItems(ReservaService.listByUsuario(UsuarioService.findByUsername(SecurityUtils.currentUsername())));

		grid.setColumns("Crucero","fechaInicio","fechaFin", "Precio", "Estado");
		grid.getColumnByKey("crucero").setHeader("Crucero");
		grid.getColumnByKey("fechaInicio").setHeader("Fecha inicial");
		grid.getColumnByKey("fechaFin").setHeader("Fecha final");
	
		grid.setSizeFull();
		grid.setColumnReorderingAllowed(true);
		this.setSizeFull();
		
		ventanaSeguro.setAlignItems(Alignment.CENTER);
		
		seguro.addClickListener(event ->{
		    reserva.setEstado("Cancelada");
		    ReservaService.save(reserva);
		    ventana.close();
		    Funciones.notificacionAcierto("Reserva cancelada con éxito");
		    grid.setItems(ReservaService.listByUsuario(UsuarioService.findByUsername(SecurityUtils.currentUsername())));
		    });
		
	    grid.addColumn(new NativeButtonRenderer<>("Cancelar", clickedItem -> {
	    	grid.asSingleSelect().clear();
	    	LocalDate now = LocalDate.now();
	    	
	    	if(clickedItem.getEstado() == "Cancelada")
	    		Funciones.notificacionAcierto("Esta reserva ya ha sido cancelada con anterioridad");
	    	else{
	    		
	    	if(clickedItem.getFechaInicio().isBefore(now.minusDays(1)))
	    		Funciones.notificacionError("Lo sentimos, no puede cancelar la reserva debido a que es demasiado tarde");
	    	else
	    	{
	    		reserva = clickedItem;
	    		clickedItem.setEstado("Cancelada");
	    	}
	        ventana.open();
	    	}
	      }));
	    
	    Footer footer = new Footer();	
		add(footer);
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		final boolean accessGranted =
				SecurityUtils.isAccessGranted(event.getNavigationTarget());
		if(!accessGranted) {
			if(SecurityUtils.isUserLoggedIn()) {
				event.rerouteToError(AccessDeniedException.class);
			}
			else {
				event.rerouteTo(LoginView.class);
			}
		} 
	}
}
