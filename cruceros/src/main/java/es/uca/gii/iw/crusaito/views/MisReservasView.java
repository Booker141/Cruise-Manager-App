package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.ServicioUsuarioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "MisReservas",layout = MainView.class)
@Secured("Cliente")
public class MisReservasView extends VerticalLayout{
	
	private ServicioService servicioService;
    private UsuarioService usuarioService;
    private ServicioUsuarioService servicioUsuarioService;
    
	//private Grid<Servicio> grid = new Grid<>(Servicio.class);
    //private List<Servicio> serviceList = new ArrayList<Servicio>();
	
    private Grid<ServicioUsuario> grid = new Grid<>(ServicioUsuario.class);
    private List<ServicioUsuario> serviceList = new ArrayList<ServicioUsuario>();
    
    private  Dialog ventana = new Dialog();

    private Div sNombreDiv = new Div();
	private Div sTipoDiv = new Div();
	private Image sImagenImage = new Image();
	
    private Notification notificacion = new Notification();
    private Label confirmacion = new Label("¿Seguro que desea cancelar esta reserva?");
    private Button cancelButton = new Button("Cancelar");
    private Button confirmButton = new Button("Confirmar");
    private Button deleteButton = new Button("Cancelar reserva");
    
    //private VerticalLayout ventanaSeguro = new VerticalLayout(confirmacion, seguro);
    
	@Autowired
    public MisReservasView(ServicioService servicioService, UsuarioService usuarioService, ServicioUsuarioService servicioUsuarioService) 
	{
		this.servicioService = servicioService;
		this.servicioUsuarioService = servicioUsuarioService;
		this.usuarioService = usuarioService;

		//Lista de servicios a partir de servUser
		/*List<ServicioUsuario> servUser = this.servicioUsuarioService.findByUsuario(this.usuarioService.findByUsername(SecurityUtils.currentUsername()));
			servUser.forEach(serviUsuar -> {
			serviceList.add(serviUsuar.getServicio());
		});*/
		serviceList = this.servicioUsuarioService.findByUsuario(this.usuarioService.findByUsername(SecurityUtils.currentUsername()));
		grid.setItems(serviceList);

		grid.setColumns("servicio","participantes","precio");
		grid.getColumnByKey("servicio").setHeader("Nombre");
		grid.getColumnByKey("participantes").setHeader("Participantes");
		grid.getColumnByKey("precio").setHeader("Precio");
		grid.setSelectionMode(Grid.SelectionMode.NONE);
		
		grid.setSizeFull();
		this.setSizeFull();
		
		sNombreDiv.setTitle("Nombre");
		sTipoDiv.setTitle("Tipo");
		
		sImagenImage.setTitle("Imagen");
		sImagenImage.setHeight("100%");
		sImagenImage.setWidth("100%");
		
		notificacion.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

		cancelButton.addClickListener(e -> notificacion.close());
		confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		notificacion.add(confirmacion,cancelButton,confirmButton);
		
		confirmacion.getStyle().set("margin-right", "0.5rem");
		cancelButton.getStyle().set("margin-right", "0.5rem");
		
		deleteButton.addClickListener(event -> notificacion.open());
		deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		ventana.add(sNombreDiv,sTipoDiv,sImagenImage,deleteButton);
		
		grid.addItemClickListener(event -> {
			
			sNombreDiv.setText("Nombre: " + event.getItem().getServicio().getsNombre());
			sTipoDiv.setText("Tipo: " + String.valueOf(event.getItem().getServicio().getsTipo()));
			sImagenImage.setSrc(event.getItem().getServicio().getsImagen());
			
			ventana.open();
		            
			confirmButton.addClickListener(e -> {
				Servicio servicio = event.getItem().getServicio();
				Usuario user = this.usuarioService.findByUsername(SecurityUtils.currentUsername());
				this.servicioService.removeServicioFromUsuario(servicio, user);
				serviceList.remove(event.getItem());
				grid.setItems(serviceList);
				
				notificacion.close();
				
			});
		});
		
		//ventanaSeguro.setAlignItems(Alignment.CENTER);
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
