package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.common.Funciones;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.ServicioUsuarioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "MisReservas",layout = MainView.class)
@Secured("Cliente")
public class MisReservasView extends VerticalLayout implements BeforeEnterObserver{
	
	private ServicioService servicioService;
    private UsuarioService usuarioService;
    private ServicioUsuarioService servicioUsuarioService;
	
    private ServicioUsuario servicioUsuario;
    private Usuario usuario;
    
    private Grid<ServicioUsuario> grid = new Grid<>(ServicioUsuario.class);
    private List<ServicioUsuario> serviceList = new ArrayList<ServicioUsuario>();
    
    //Ventana emergente por cada click en un servicio
    private Dialog ventana = new Dialog();

    private H6 sNombre = new H6();
	private H6 sTipo = new H6();
	private Image sImagenImage = new Image();
	
    private Button deleteButton = new Button("Cancelar reserva");
        
	@Autowired
    public MisReservasView(ServicioService servicioService, UsuarioService usuarioService, 
    		ServicioUsuarioService servicioUsuarioService){
		
		this.servicioService = servicioService;
		this.servicioUsuarioService = servicioUsuarioService;
		this.usuarioService = usuarioService;

		usuario = this.usuarioService.findByUsername(SecurityUtils.currentUsername());
		
		serviceList = this.servicioUsuarioService.findByUsuario(this.usuarioService.findByUsername(SecurityUtils.currentUsername()));
		grid.setItems(serviceList);

		grid.setColumns("servicio","participantes","precio");
		grid.getColumnByKey("servicio").setHeader("Nombre");
		grid.getColumnByKey("participantes").setHeader("Participantes");
		grid.getColumnByKey("precio").setHeader("Precio");
		grid.setSelectionMode(Grid.SelectionMode.NONE);
		
		grid.setSizeFull();
		this.setSizeFull();
		
		sNombre.setTitle("Nombre");
		sTipo.setTitle("Tipo");
		
		sImagenImage.setTitle("Imagen");
		sImagenImage.setHeight("100%");
		sImagenImage.setWidth("100%");
		
		
		deleteButton.addClickListener(e -> {
			cancelarReserva();
		});

		ventana.add(sNombre,sTipo,sImagenImage,deleteButton);
		
		grid.addItemClickListener(event -> {
			
			servicioUsuario = event.getItem();

			sNombre.setText("Nombre: " + event.getItem().getServicio().getsNombre());
			sTipo.setText("Tipo: " + String.valueOf(event.getItem().getServicio().getsTipo()));
			sImagenImage.setSrc(event.getItem().getServicio().getsImagen());
			
			ventana.open();
		            
		});
		
	    add(grid);
	}
	
	public void cancelarReserva() {
		
		//Cuadro Confirmacion
		ConfirmDialog dialogConfirmable = new ConfirmDialog();
		dialogConfirmable.setHeader("Cancelar reserva");
		dialogConfirmable.setText("¿Seguro que desea cancelar esta reserva?");
				
		//Boton cancelar ventana confirmacion
		Button noButton = new Button("No", e -> {
			dialogConfirmable.close();
			ventana.close();
		});
				
		//Boton confirmar ventana confirmacion
		Button siButton = new Button("Si"); 
		siButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
		
		Servicio servicio = servicioUsuario.getServicio();
		
		siButton.addClickListener(e -> {
			try{
				this.servicioService.removeServicioFromUsuario(servicio, usuario);
				serviceList.remove(servicioUsuario);
				grid.setItems(serviceList);
				Funciones.notificacionAcierto("Reserva cancelada.");
			}catch(Exception error) {
				Funciones.notificacionError("Error al cancelar la reserva, por favor contacte con el personal del barco.");
			}
			
			dialogConfirmable.close();
			ventana.close();
		});
				
		dialogConfirmable.setConfirmButton(siButton);
		dialogConfirmable.setCancelButton(noButton);
		dialogConfirmable.open();
	}
	
	public void beforeEnter(BeforeEnterEvent event) {
		final boolean accessGranted = SecurityUtils.isAccessGranted(event.getNavigationTarget());
		if(!accessGranted) {
			if(SecurityUtils.isUserLoggedIn()) {
				event.rerouteTo(ProhibidoView.class);
			}
			else {
				event.rerouteTo(LoginView.class);
			}
		} 
	}
	
}
