package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.CiudadCrucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.CiudadCruceroService;
import es.uca.gii.iw.crusaito.servicios.CiudadService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;
import es.uca.gii.iw.crusaito.tiempo.Weather;

@Route(value = "Ciudades", layout = MainView.class)
@Secured("Cliente")
@SuppressWarnings("serial")
public class CiudadesView extends VerticalLayout{

	private CiudadService ciudadService;
    private UsuarioService usuarioService;
    private CruceroService cruceroService;
    private CiudadCruceroService ciudadCruceroService;
    private ServicioService servicioService;
    private Weather weather;
   
	//private Grid<Servicio> grid = new Grid<>(Servicio.class);
    //private List<Servicio> serviceList = new ArrayList<Servicio>();
	
    private Grid<CiudadCrucero> grid = new Grid<>(CiudadCrucero.class);
    private List<CiudadCrucero> ciudadList = new ArrayList<CiudadCrucero>();
    
    private  Dialog ventana = new Dialog();

    private Div cNombreDiv = new Div();
	private Div cCruceroDiv = new Div();
	private Div cFechaLlegadaDiv = new Div();
	private Div cHoraLlegadaDiv = new Div();
	private Div cFechaSalidaDiv = new Div();
	private Div cHoraSalidaDiv = new Div();
	private Div cDescripcionDiv = new Div();
	private H6 cConsejo = new H6();
	private H6 cConsejo1 = new H6();
	private H6 cConsejo2 = new H6();
	private H6 cConsejo3 = new H6();
	private H6 cConsejo4 = new H6();
	private H6 cConsejo5 = new H6();
	private Div cServiciosDiv = new Div();
	private Div cserviciosDiv = new Div();
	private Div cTiempoDiv = new Div();
	
    private Notification notificacion = new Notification();
    private Button volver = new Button("Volver");

    
    //private VerticalLayout ventanaSeguro = new VerticalLayout(confirmacion, seguro);
    
	@Autowired
    public CiudadesView(CiudadService ciudadService, UsuarioService usuarioService, 
    		CruceroService cruceroService, CiudadCruceroService ciudadCruceroService, 
    		ServicioService servicioService, Weather w) throws Exception
	{
		this.ciudadService = ciudadService;
		this.ciudadCruceroService = ciudadCruceroService;
		this.cruceroService = cruceroService;
		this.usuarioService = usuarioService;
		this.servicioService = servicioService;

		ciudadList = this.ciudadCruceroService.findByCrucero(this.cruceroService.findByUsuarios(this.usuarioService.findByUsername(SecurityUtils.currentUsername())));
		grid.setItems(ciudadList);

		grid.setColumns("ciudad","crucero", "fechaLlegada", "horaLlegada", "fechaSalida", "horaSalida");
		grid.getColumnByKey("ciudad").setHeader("Nombre de la ciudad");
		grid.getColumnByKey("crucero").setHeader("Crucero que pasa por la ciudad");
		grid.getColumnByKey("fechaLlegada").setHeader("Fecha llegada");
		grid.getColumnByKey("horaLlegada").setHeader("Hora llegada");
		grid.getColumnByKey("fechaSalida").setHeader("Fecha salida");
		grid.getColumnByKey("horaSalida").setHeader("Hora salida");
		grid.setSelectionMode(Grid.SelectionMode.NONE);
		
		grid.setSizeFull();
		this.setSizeFull();
		
		cNombreDiv.setTitle("Nombre");
		cCruceroDiv.setTitle("Crucero");
		cFechaLlegadaDiv.setTitle("Fecha llegada");
		cHoraLlegadaDiv.setTitle("Hora llegada");
		cFechaSalidaDiv.setTitle("Fecha salida");
		cHoraSalidaDiv.setTitle("Hora salida");
		cDescripcionDiv.setTitle("Descripcion");
		cConsejo.setTitle("Consejos");
		cConsejo1.setTitle("Consejo 1");
		cConsejo2.setTitle("Consejo 2");
		cConsejo3.setTitle("Consejo 3");
		cConsejo4.setTitle("Consejo 4");
		cConsejo5.setTitle("Consejo 5");
		cServiciosDiv.setTitle("Servicios");
		cTiempoDiv.setTitle("Tiempo");
		
		notificacion.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

		volver.addClickListener(e -> notificacion.close());

		notificacion.add(volver);
		
		volver.getStyle().set("margin-right", "0.5rem");

		ventana.add(cNombreDiv,cCruceroDiv,cFechaLlegadaDiv, cHoraLlegadaDiv, cFechaSalidaDiv, cHoraSalidaDiv, cDescripcionDiv, cConsejo, cConsejo1, cConsejo2, cConsejo3, cConsejo4, cConsejo5,  cServiciosDiv, cserviciosDiv, cTiempoDiv, volver);
		
		grid.addItemClickListener(event -> {
			
			cNombreDiv.setText("Nombre: " + event.getItem().getCiudad().getcNombre());
			cCruceroDiv.setText("Crucero: " + event.getItem().getCrucero());
			cFechaLlegadaDiv.setText("Fecha llegada: " + event.getItem().getFechaLlegada());
			cHoraLlegadaDiv.setText("Hora llegada: " + event.getItem().getHoraLlegada());
			cFechaSalidaDiv.setText("Fecha salida: " + event.getItem().getFechaSalida());
			cHoraSalidaDiv.setText("Hora salida: " + event.getItem().getHoraSalida());
			cDescripcionDiv.setText("Descripcion: " + event.getItem().getCiudad().getcDescripcion());
			cConsejo.setText("Consejos: ");
			cConsejo1.setText("1.- Antes de bajar del barco en las escalas de cruceros, intenta tener toda la información sobre la ciudad, lugares que deseas ver o actividades que quieras realizar. "
					+ "El propio barco te facilitará un pequeño mapa de localización del barco en el puerto y las zonas mas representativas de "
					+ "la ciudad pero a veces tiene una información muy limitada o solo relacionada con compras.");
			cConsejo2.setText("2.- No intentes apurar hasta el último minuto para regresar. Calcula un tiempo de "
					+ "seguridad para regresar antes de la hora de salida para el caso que surja alguna "
					+ "complicación no prevista. ");
			cConsejo3.setText("3.- En la mayoría de las escalas de cruceros podrás encontrar oficinas de turismo locales habilitadas en lugares especiales para informar a los "
					+ "pasajeros que llegan en los diferentes barcos. Es un buen lugar donde recopilar información, mapas turísticos gratuitos, "
					+ "y preguntar sobre cómo llegar y volver al centro. "
					+ "Además suelen tener algunas ofertas de transporte turístico "
					+ "disponible en la ciudad (shuttles gratuitos, pases de 24 horas, "
					+ "autobuses panorámicos, tranvías..).");
			cConsejo4.setText("4.- Como normal general de seguridad es mejor no llamar "
					+ "la atención mostrando joyas o relojes caros, ni bolsas que sean fácilmente sustraibles. "
					+ "Los cruceros suelen llegar a puertos muy turísticos por lo general. En estos lugares es probable que haya carteristas o quienes "
					+ "aprovechan los grupos de turistas para robar de las mochilas y bolsos.");
			cConsejo5.setText("5.- Antes de contratar un taxi o tour "
					+ "independiente comprueba las credenciales del conductor y la empresa de tours" + "\n" + "6.- Aunque en la mayoría de puertos de cruceros y lugares turísticos "
					+ "aceptan tarjetas de crédito es recomendable llevar un poco "
					+ "de dinero local en efectivo para las pequeñas compras o propinas. "
					+ "Si quieres encender una vela en una catedral o tomar algo en mercados"
					+ " no podrás con la tarjeta por ejemplo");
					
			Set<Servicio> servicios = event.getItem().getCrucero().getServicios();
			cServiciosDiv.setText("Servicios de la ciudad y el crucero: ");
			cserviciosDiv.setText(servicios.toString());
			cTiempoDiv.setText("Tiempo: ");
			try {
				w.requestWeather(event.getItem().getCiudad());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
			
			ventana.open();
		            
		});
		
	    add(grid);
	}
	
}
