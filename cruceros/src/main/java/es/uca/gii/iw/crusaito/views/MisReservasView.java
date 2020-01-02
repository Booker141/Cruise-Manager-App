package es.uca.gii.iw.crusaito.views;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.common.Funciones;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "MisReservas",layout = MainView.class)
@Secured("Cliente")
public class MisReservasView extends PrincipalView{
	
	private Grid<Servicio> grid = new Grid<>(Servicio.class);
    
	private ServicioService servicioService;
    private UsuarioService usuarioService;
    
    private Label confirmacion = new Label("¿Seguro que desea cancelar esta reserva?");
    private Button seguro = new Button("Cancelar reserva");
    private VerticalLayout ventanaSeguro = new VerticalLayout(confirmacion, seguro);
    private  Dialog ventana = new Dialog(ventanaSeguro);
    
	@Autowired
    public MisReservasView(ServicioService servicioService, UsuarioService usuarioService) 
	{
		this.servicioService = servicioService;
		this.usuarioService = usuarioService;
		
		grid.setItems(servicioService.findByUsername(SecurityUtils.currentUsername()));

		grid.setColumns("sNombre","sDescripcion","sPrecio");
		grid.getColumnByKey("sNombre").setHeader("Nombre");
		grid.getColumnByKey("sDescripcion").setHeader("Descripcion");
		grid.getColumnByKey("sPrecio").setHeader("Precio");
	
		grid.setSizeFull();
		grid.setColumnReorderingAllowed(true);
		this.setSizeFull();
		
		ventanaSeguro.setAlignItems(Alignment.CENTER);
		/*
		seguro.addClickListener(event ->{
		    reserva.setEstado(ReservaEstado.Cancelada);
		    ReservaService.save(reserva);
		    ventana.close();
		    Funciones.notificacionAcierto("Reserva cancelada con éxito");
		    grid.setItems(ReservaService.listByUsuario(UsuarioService.findByUsername(SecurityUtils.currentUsername())));
		    });
		*/
		/*
	    grid.addColumn(new NativeButtonRenderer<>("Cancelar", clickedItem -> {
	    	grid.asSingleSelect().clear();
	    	LocalDate now = LocalDate.now();
	    	
	    	if(clickedItem.getEstado() == ReservaEstado.Cancelada)
	    		Funciones.notificacionAcierto("Esta reserva ya ha sido cancelada con anterioridad");
	    	else{
	    		
	    	if(clickedItem.getFechaInicio().isBefore(now.minusDays(1)))
	    		Funciones.notificacionError("Lo sentimos, no puede cancelar la reserva debido a que es demasiado tarde");
	    	else
	    	{
	    		reserva = clickedItem;
	    		clickedItem.setEstado(ReservaEstado.Cancelada);
	    	}
	        ventana.open();
	    	}
	      }));
	*/
	    add(grid);
	}
	
}
