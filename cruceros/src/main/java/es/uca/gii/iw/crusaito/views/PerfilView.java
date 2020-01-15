package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ServicioUsuarioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "Perfil",layout = MainView.class)
@Secured("Cliente")
public class PerfilView extends VerticalLayout implements BeforeEnterObserver{
	
	private ServicioUsuarioService servicioUsuarioService;
	private UsuarioService usuarioService;
	
	private Grid<ServicioUsuario> grid = new Grid<>(ServicioUsuario.class);
	private List<ServicioUsuario> serviceList = new ArrayList<ServicioUsuario>();
	private ListDataProvider<ServicioUsuario> dataProvider;

	private Usuario usuario;
	
	private double dTotal = 0;
	
	private VerticalLayout datos = new VerticalLayout();
	
	private H6 firstName;
	private H6 lastName;
	private H6 telefono;
	private H6 dni;
	private H6 address;
	private H6 city;
	private H6 bornDate;
	private H6 username;
	private H6 email;
	private H6 mensaje;
	
	@Autowired
	public PerfilView(UsuarioService usuarioService, ServicioUsuarioService servicioUsuarioService) {
		
		this.usuarioService = usuarioService;
		this.servicioUsuarioService = servicioUsuarioService;

		grid.removeColumnByKey("usuario"); grid.removeColumnByKey("precio");

		grid.setColumns("servicio", "participantes");

		grid.getColumnByKey("servicio").setHeader("Servicio");
		grid.getColumnByKey("participantes").setHeader("Participantes");
		grid.addColumn(new NumberRenderer<>(ServicioUsuario::getPrecio,"%(,.2f €",new Locale("es"),"0.00 €")).setHeader("Precio");
		
		grid.setSelectionMode(Grid.SelectionMode.NONE);
		
		mensaje = new H6("Para modificar los datos contacte con un administrador a través de la siguiente dirección: admin@gmail.com.");

	}
	
	/**
	 * Metodo que es llamado por el metodo beforeEnter despues de comprobar que el usuario esta
	 * logeado en el sistema y tiene el rol adecuado para acceder a esta vista
	 */
	
	public void rellenarInformacion() {
		
		usuario = this.usuarioService.findByUsername(SecurityUtils.currentUsername());
		
		serviceList = this.servicioUsuarioService.findByUsuario(usuario);
		
		dataProvider = new ListDataProvider<>(serviceList);
		grid.setDataProvider(dataProvider);
		
		serviceList.forEach(serviUsuar -> {
			dTotal += serviUsuar.getPrecio();
		});
		
		firstName = new H6("Nombre: " + usuario.getFirstName());
		lastName = new H6("Apellidos: " + usuario.getLastName());
		telefono = new H6("Telefono: " + usuario.getPhoneNumber());
		dni = new H6("DNI: " + usuario.getDni());
		address = new H6("Dirección: " + usuario.getAddress());
		city = new H6("Ciudad: " + usuario.getCity());
		bornDate = new H6("Fecha de nacimiento: " + usuario.getBornDate().toString());
	
		username = new H6("Nombre de usuario: " + usuario.getUsername());
		email = new H6("Correo electrónico: " + usuario.getEmail());

		datos.add(firstName,lastName,telefono,dni,address,city,bornDate, username,email, mensaje);

		H6 total = new H6("Factura total: " + dTotal + " €");
		
		add(datos, grid, total);
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		final boolean accessGranted =
				SecurityUtils.isAccessGranted(event.getNavigationTarget());
		if(!accessGranted) {
			if(SecurityUtils.isUserLoggedIn()) {
				event.rerouteTo(ProhibidoView.class);
			}
			else {
				event.rerouteTo(LoginView.class);
			}
		}else {
			this.rellenarInformacion();
		}
	}
	
}
