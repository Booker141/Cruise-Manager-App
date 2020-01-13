package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@Route(value = "InicioCliente", layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Cliente")
public class InicioClienteView extends VerticalLayout implements BeforeEnterObserver{
	
	private UsuarioService usuarioService;
	private CruceroService cruceroService;
	private ServicioService servicioService;
	
	private Crucero crucero;
	private Barco barco;
	private List<Servicio> serviceList = new ArrayList<Servicio>();
	private Image bImagen;
	private Image b2Imagen;
	
	private H1 nombreCrucero;
	private H2 Titulo = new H2("Información: ");
	private Label Origen;
    private Label Destino;
    private Label Duracion;
    
    private H1 nombreBarco;
    private H2 Titulo2 = new H2("Información: ");
	private Label Descripcion;
	private Label Aforo;
	private Label Peso;
	private Label FchaServicio;
	private Label Servicios = new Label("Servicios que ofrece el barco: ");
	private Label Servicio;
	private Label Plano = new Label("Planos del barco: ");
    
	/**
	 * Información consejos
	 */
	
	private H3 Consejos = new H3("Consejos para viajeros: ");
	private H6 Consejo1 = new H6("1.- No te olvides de llevar los documentos de identidad, "
			+ "como el DNI o pasaporte, así como el visado o permiso de excursiones correspondiente en "
			+ "caso de que alguno de los países en los que atraque el barco lo requieran. También deberías tener anotada la información "
			+ "del viaje (paradas, horarios, etc), el número de cuenta corriente y algunos teléfonos de emergencias o de interés. "
			+ "Lleva también contigo una tarjeta de crédito y una copia de tu póliza del seguro de viaje para cruceros.");
	private H6 Consejo2 = new H6("2.- Tendrás que vestirte con ropas de baño y otras veces con prendas más cómodas o formales, además de usar algunas que te protejan del viento. No olvides protegerte con gafas de sol, un gorro y crema solar.\n" + 
			"\n" + 
			"A tener en cuenta también las medidas y límites de peso del equipaje de mano de la compañía aérea con la que vayas a "
			+ "volar a la ciudad en que se halla el puerto del que parte el barco. "
			+ "Consulta también estas cifras para los equipajes a bordo del barco.");
	private H6 Consejo3 = new H6("3.- La compra de medicinas a bordo suele ser más cara de lo habitual, "
			+ "así que no te olvides de llevar contigo un botiquín personal que contenga "
			+ "antiinflamatorios, analgésicos, pomadas para golpes o dolores musculares y "
			+ "medicamentos para el mareo y los problemas digestivos. Consulta con el Ministerio "
			+ "de Sanidad si necesitas alguna vacuna para alguno de los países en los que va a "
			+ "atracar el crucero. ");
	private H6 Consejo4 = new H6("4.- Al igual que en los aeropuertos, "
			+ "al adquirir los productos y servicios que se ofertan en un crucero "
			+ "no pagarás el IVA. Eso sí, cuando el barco esté atracado en algún "
			+ "puerto las tiendas permanecen cerradas. Por otro lado, lo que compres "
			+ "al entrar en tu país de origen estará exento de impuestos siempre que "
			+ "no supere los valores estipulados por la aduana. ");
	private H6 Consejo5 = new H6("5.- Recuerda apagar los datos de tu teléfono móvil para evitar sorpresas en tu factura. También, regula la hora del mismo y activa la alarma despertador para viajar acorde a los cambios de huso horario. "
			+ "Procura llevar un adaptador universal para enchufes.");
    
	private VerticalLayout layout;
	@Autowired
	public InicioClienteView(UsuarioService usuarioService, CruceroService cruceroService, ServicioService servicioService) {
		
		this.usuarioService = usuarioService;
		this.cruceroService = cruceroService;
		this.servicioService = servicioService;
		
	}
	
	public void rellenarInformacion() {
		/**
		 * Informacion del usuario
		 */
		crucero = this.cruceroService.findByUsuarios(this.usuarioService.findByUsername(SecurityUtils.currentUsername()));
		barco = crucero.getBarco();
		serviceList = this.servicioService.findByCruceros(crucero);
		
		/**
		 * Información crucero
		 */
		
		nombreCrucero = new H1(crucero.getcNombre());
		Origen = new Label("Origen: " + crucero.getcOrigen());
		Destino = new Label("Destino: " + crucero.getcDestino());
	    Duracion = new Label("Duración: " + crucero.getcDuracion());
	    
	    /**
         * Información barco
         */
	    
	    nombreBarco = new H1(barco.getbNombre());
	    Descripcion = new Label(barco.getbDescripcion());
	    Aforo = new Label("Aforo máximo de pasajeros: " + barco.getbAforoPasajeros());
        Peso = new Label("Peso: " + barco.getbPeso());
        FchaServicio = new Label("Fecha de puesta en servicio: " + barco.getbFchPuestaServicio());
        Servicio = new Label(serviceList.toString());
        bImagen = new Image(barco.getbImagen(), "Imagen no encontrada");
        bImagen.setHeight("100%");
		bImagen.setWidth("100%");
        b2Imagen = new Image(barco.getbPlano(), "Imagen no encontrada");
        b2Imagen.setHeight("100%");
		b2Imagen.setWidth("100%");

		layout = new VerticalLayout(nombreCrucero, Titulo, Origen, Destino, Duracion, 
        		nombreBarco, Titulo2, bImagen, Descripcion, Aforo, Peso, FchaServicio, Servicios, Servicio, Plano, b2Imagen, 
        		Consejos, Consejo1, Consejo2, Consejo3, Consejo4, Consejo5);
        add(layout);
        
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		final boolean accessGranted = SecurityUtils.isAccessGranted(event.getNavigationTarget());
		if(!accessGranted) {
			if(SecurityUtils.isUserLoggedIn()) {
				event.rerouteTo(ProhibidoView.class);
			}
			else {
				event.rerouteTo(LoginView.class);
			}
		}else{
			this.rellenarInformacion();
		}
	}

}
