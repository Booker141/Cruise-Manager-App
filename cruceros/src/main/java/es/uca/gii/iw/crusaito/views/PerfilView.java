package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.ServicioUsuarioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "Perfil",layout = MainView.class)
@Secured("Cliente")
public class PerfilView extends VerticalLayout{
	
	private ServicioUsuarioService servicioUsuarioService;
	private UsuarioService usuarioService;
	private ServicioService servicioService;
	
	private Grid<ServicioUsuario> grid = new Grid<>(ServicioUsuario.class);
	private List<ServicioUsuario> serviceList = new ArrayList<ServicioUsuario>();
	private ListDataProvider<ServicioUsuario> dataProvider;

	private double dTotal = 0;
	
	private VerticalLayout datos = new VerticalLayout();
	
	private Label firstName;
	private Label lastName;
	private Label telefono;
	private Label dni;
	private Label address;
	private Label city;
	private Label bornDate;
	private Label username;
	private Label email;
	private Label mensaje;
	@Autowired
	public PerfilView(ServicioService servicioService, UsuarioService usuarioService,
			ServicioUsuarioService servicioUsuarioService) {
		
		this.servicioService = servicioService;
		this.usuarioService = usuarioService;
		this.servicioUsuarioService = servicioUsuarioService;
		
		Usuario usuario = this.usuarioService.findByUsername(SecurityUtils.currentUsername());
		serviceList = this.servicioUsuarioService.findByUsuario(usuario);
		
		dataProvider = new ListDataProvider<>(serviceList);
		grid.setDataProvider(dataProvider);
		
		serviceList.forEach(serviUsuar -> {
			dTotal += serviUsuar.getPrecio();
		});

		grid.removeColumnByKey("usuario"); grid.removeColumnByKey("precio");

		grid.setColumns("servicio", "participantes");

		grid.getColumnByKey("servicio").setHeader("Servicio");
		grid.getColumnByKey("participantes").setHeader("Participantes");
		grid.addColumn(new NumberRenderer<>(ServicioUsuario::getPrecio,"%(,.2f €",new Locale("es"),"0.00 €")).setHeader("Precio");
		
		grid.setSelectionMode(Grid.SelectionMode.NONE);
		
		firstName = new Label("Nombre: " + usuario.getFirstName());
		lastName = new Label("Apellidos: " + usuario.getLastName());
		telefono = new Label("Telefono: " + usuario.getPhoneNumber());
		dni = new Label("DNI: " + usuario.getDni());;
		address = new Label("Dirección: " + usuario.getAddress());;
		city = new Label("Ciudad: " + usuario.getCity());;
		bornDate = new Label("Fecha de nacimiento: " + usuario.getBornDate().toString());;
		username = new Label("Nombre de usuario: " + usuario.getUsername());;
		email = new Label("Correo electrónico: " + usuario.getEmail());
		
		mensaje = new Label("Para modificar los datos contacte con un administrador.");
		
		datos.add(firstName,lastName,telefono,dni,address,city,bornDate,username,email, mensaje);
		Label total = new Label("Factura total: " + dTotal);
		
		add(datos, grid, total);
		
		
	}
	
}
