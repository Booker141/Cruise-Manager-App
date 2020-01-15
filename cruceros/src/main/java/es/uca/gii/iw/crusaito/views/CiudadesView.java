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
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import aj.org.objectweb.asm.Label;
import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.clases.CiudadCrucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.common.Funciones;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.CiudadCruceroService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;
import es.uca.gii.iw.crusaito.tiempo.Weather;

@Route(value = "Ciudades", layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Cliente")
public class CiudadesView extends VerticalLayout implements BeforeEnterObserver{

    private UsuarioService usuarioService;
    private CruceroService cruceroService;
    private CiudadCruceroService ciudadCruceroService;
    private Weather weather;
   
    private Grid<CiudadCrucero> grid = new Grid<>(CiudadCrucero.class);
    private List<CiudadCrucero> ciudadList = new ArrayList<CiudadCrucero>();
    
    private  Dialog ventana = new Dialog();

    private VerticalLayout infoCiudad = new VerticalLayout();
    private Div cNombreDiv = new Div();
	private Div cCruceroDiv = new Div();
	private Div cFechaLlegadaDiv = new Div();
	private Div cHoraLlegadaDiv = new Div();
	private Div cFechaSalidaDiv = new Div();
	private Div cHoraSalidaDiv = new Div();
	private Div cDescripcionDiv = new Div();
	
	private VerticalLayout cConsejos = new VerticalLayout();
	private H6 cConsejo = new H6();
	private Paragraph cConsejo1 = new Paragraph();
	private Paragraph cConsejo2 = new Paragraph();
	private Paragraph cConsejo3 = new Paragraph();
	private Paragraph cConsejo4 = new Paragraph();
	private Paragraph cConsejo5 = new Paragraph();
	private Paragraph cConsejo6 = new Paragraph();
	
	private VerticalLayout serviciosTiempoLayout = new VerticalLayout();
	private Div cServiciosDiv = new Div();
	private Div cserviciosDiv = new Div();
	
	private Div cTiempoDiv = new Div();
	
	HorizontalLayout weatherLayout = new HorizontalLayout();
	private H6 temperatura = new H6();
	private H6 humedad = new H6();
	private H6 viento = new H6();
	private H6 direccion = new H6();
	private Image iconoURL = new Image();
	
    private Button volver = new Button("Volver");
    
	@Autowired
    public CiudadesView(UsuarioService usuarioService, 
    		CruceroService cruceroService, CiudadCruceroService ciudadCruceroService) throws Exception
	{
		this.ciudadCruceroService = ciudadCruceroService;
		this.cruceroService = cruceroService;
		this.usuarioService = usuarioService;

		grid.setItems(ciudadList);

		grid.setColumns("ciudad","crucero", "fechaLlegada", "horaLlegada", "fechaSalida", "horaSalida");
		grid.getColumnByKey("ciudad").setHeader("Ciudad");
		grid.getColumnByKey("crucero").setHeader("Crucero");
		grid.getColumnByKey("fechaLlegada").setHeader("Fecha llegada");
		grid.getColumnByKey("horaLlegada").setHeader("Hora llegada");
		grid.getColumnByKey("fechaSalida").setHeader("Fecha salida");
		grid.getColumnByKey("horaSalida").setHeader("Hora salida");
		
		grid.setSelectionMode(Grid.SelectionMode.NONE);
		
		grid.setSizeFull();
		this.setSizeFull();
		
		cargarVentanaDetallada();
		
		volver.addClickListener(e -> ventana.close());
		
		volver.getStyle().set("margin-right", "0.5rem");

		ventana.add(infoCiudad, cConsejos, serviciosTiempoLayout, volver);
		
		grid.addItemClickListener(event -> {
			
			cNombreDiv.setText("Nombre: " + event.getItem().getCiudad().getcNombre());
			cCruceroDiv.setText("Crucero: " + event.getItem().getCrucero());
			cFechaLlegadaDiv.setText("Fecha llegada: " + event.getItem().getFechaLlegada());
			cHoraLlegadaDiv.setText("Hora llegada: " + event.getItem().getHoraLlegada() + ":00");
			cFechaSalidaDiv.setText("Fecha salida: " + event.getItem().getFechaSalida());
			cHoraSalidaDiv.setText("Hora salida: " + event.getItem().getHoraSalida() + ":00");
			cDescripcionDiv.setText("Descripcion: " + event.getItem().getCiudad().getcDescripcion());
			
			Set<Servicio> servicios = event.getItem().getCiudad().getServicios();
			if(servicios.isEmpty()) {
				cServiciosDiv.setText("No hay excursiones organizadas en esta ciudad.");
				cserviciosDiv.removeAll();
			}else {
				cServiciosDiv.setText("Excursiones organizadas en esta ciudad:");
				cserviciosDiv.setText(servicios.toString());
			}
			
			cTiempoDiv.setText("Tiempo: ");
			try {
				rellenarInfoMeteorologica(event.getItem().getCiudad());
				weatherLayout.setVisible(true);
			} catch (Exception e1) {
				Funciones.notificacionError("No se ha podido obtener el tiempo de la ciudad, intentelo más tarde");
				weatherLayout.setVisible(false);
			}

			ventana.open();
			
		});
		
	    add(grid);
	}
	private void cargarVentanaDetallada() {
		
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
		cConsejo6.setTitle("Consejo 6");
		
		cServiciosDiv.setTitle("Servicios");
		cTiempoDiv.setTitle("Tiempo");
		
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
				+ "independiente comprueba las credenciales del conductor y la empresa de tours");
		cConsejo6.setText("6.- Aunque en la mayoría de puertos de cruceros y lugares turísticos "
				+ "aceptan tarjetas de crédito es recomendable llevar un poco "
				+ "de dinero local en efectivo para las pequeñas compras o propinas. "
				+ "Si quieres encender una vela en una catedral o tomar algo en mercados"
				+ " no podrás con la tarjeta por ejemplo");
		
		infoCiudad.add(cNombreDiv, cCruceroDiv, cFechaLlegadaDiv,cHoraLlegadaDiv, 
				cFechaSalidaDiv, cHoraSalidaDiv, cDescripcionDiv);
		
		cConsejos.add(cConsejo, cConsejo1, cConsejo2, cConsejo3, cConsejo4, cConsejo5, cConsejo6);
		
		weatherLayout.add(iconoURL, temperatura, humedad, viento, direccion);
		
		serviciosTiempoLayout.add(cServiciosDiv,cserviciosDiv, cTiempoDiv, weatherLayout);
	}
	
	private void rellenarInfoMeteorologica(Ciudad ciudad) {
		weather = new Weather(ciudad);
		
		this.temperatura.setText("Temperatura: " + weather.getTemperatura() +" °C.");
		this.humedad.setText("Humedad: " + weather.getHumedad() + " %.");
		this.viento.setText("Viento: " + weather.getViento() + " m/s.");
		this.direccion.setText("Dirección del viento: " + weather.getDireccion());
		this.iconoURL.setSrc(weather.getIconoURL());
		this.iconoURL.getStyle().set("width", "50px");
		this.iconoURL.getStyle().set("height", "50px");
	}
	
	public void beforeEnter(BeforeEnterEvent event) {
		final boolean accessGranted = SecurityUtils.isAccessGranted(event.getNavigationTarget());
		if(!accessGranted) {
			if(SecurityUtils.isUserLoggedIn()) {
				event.rerouteTo(ProhibidoView.class);
			}else {
				event.rerouteTo(LoginView.class);
			}
		}else {
			ciudadList = this.ciudadCruceroService.findByCrucero(this.cruceroService.findByUsuarios(this.usuarioService.findByUsername(SecurityUtils.currentUsername())));
			grid.setItems(ciudadList);
		}
	}
	
}
