package es.uca.gii.iw.crusaito.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@Route(value = "InicioCliente", layout = MainView.class)
@Secured("Cliente")
@SuppressWarnings("serial")
public class InicioClienteView extends VerticalLayout{
	
	private UsuarioService usuarioService;
	private CruceroService cruceroService;
	private ServicioService servicioService;
	
	private Image bImagen;
	private Image b2Imagen;
	
	@Autowired
	public InicioClienteView(UsuarioService usuarioService, CruceroService cruceroService, ServicioService servicioService) {
		
		this.usuarioService = usuarioService;
		this.cruceroService = cruceroService;
		this.servicioService = servicioService;
		
		
		Crucero crucero = this.cruceroService.findByUsuarios(this.usuarioService.findByUsername(SecurityUtils.currentUsername()));
		Barco barco = crucero.getBarco();
		List<Servicio> serviceList = this.servicioService.findByCruceros(crucero);
		

		/**
		 * Información crucero
		 */
		
        H1 nombreCrucero = new H1(crucero.getcNombre());
        H2 Titulo = new H2("Informacion: ");
        Label Origen = new Label("Origen: " + crucero.getcOrigen());
        Label Destino = new Label("Destino: " + crucero.getcDestino());
        Label Duracion = new Label("Duracion: " + crucero.getcDuracion());
       
        
        /**
         * Información barco
         */
        
        H1 nombreBarco = new H1(barco.getbNombre());
        H2 Titulo2 = new H2("Informacion: ");
        bImagen = new Image(barco.getbImagen(), "Imagen no encontrada");
        bImagen.setHeight("100%");
		bImagen.setWidth("100%");
        Label Descripcion = new Label(barco.getbDescripcion());
        Label Aforo = new Label("Aforo maximo de pasajeros: " + barco.getbAforoPasajeros());
        Label Peso = new Label("Peso: " + barco.getbPeso());
        Label FchaServicio = new Label("Fecha de puesta en servicio: " + barco.getbFchPuestaServicio());
        Label Instalaciones = new Label("Instalaciones del barco: ");
        Label Instalacion = new Label(serviceList.toString());
        Label Plano = new Label("Planos del barco: ");
        b2Imagen = new Image(barco.getbPlano(), "Imagen no encontrada");
        b2Imagen.setHeight("100%");
		b2Imagen.setWidth("100%");
		
		/**
		 * Información consejos
		 */
		
		H1 Consejos = new H1("Consejos para viajeros: ");
		H3 Consejo1 = new H3("1.- No te olvides de llevar los documentos de identidad, "
				+ "como el DNI o pasaporte, así como el visado o permiso de excursiones correspondiente en "
				+ "caso de que alguno de los países en los que atraque el barco lo requieran. También deberías tener anotada la información "
				+ "del viaje (paradas, horarios, etc), el número de cuenta corriente y algunos teléfonos de emergencias o de interés. "
				+ "Lleva también contigo una tarjeta de crédito y una copia de tu póliza del seguro de viaje para cruceros.");
		H3 Consejo2 = new H3("2.- Tendrás que vestirte con ropas de baño y otras veces con prendas más cómodas o formales, además de usar algunas que te protejan del viento. No olvides protegerte con gafas de sol, un gorro y crema solar.\n" + 
				"\n" + 
				"A tener en cuenta también las medidas y límites de peso del equipaje de mano de la compañía aérea con la que vayas a "
				+ "volar a la ciudad en que se halla el puerto del que parte el barco. "
				+ "Consulta también estas cifras para los equipajes a bordo del barco.");
		H3 Consejo3 = new H3("3.- La compra de medicinas a bordo suele ser más cara de lo habitual, "
				+ "así que no te olvides de llevar contigo un botiquín personal que contenga "
				+ "antiinflamatorios, analgésicos, pomadas para golpes o dolores musculares y "
				+ "medicamentos para el mareo y los problemas digestivos. Consulta con el Ministerio "
				+ "de Sanidad si necesitas alguna vacuna para alguno de los países en los que va a "
				+ "atracar el crucero. ");
		H3 Consejo4 = new H3("4.- Al igual que en los aeropuertos, "
				+ "al adquirir los productos y servicios que se ofertan en un crucero "
				+ "no pagarás el IVA. Eso sí, cuando el barco esté atracado en algún "
				+ "puerto las tiendas permanecen cerradas. Por otro lado, lo que compres "
				+ "al entrar en tu país de origen estará exento de impuestos siempre que "
				+ "no supere los valores estipulados por la aduana. ");
		H3 Consejo5 = new H3("5.- Recuerda apagar los datos de tu teléfono móvil para evitar sorpresas en tu factura. También, regula la hora del mismo y activa la alarma despertador para viajar acorde a los cambios de huso horario. "
				+ "Procura llevar un adaptador universal para enchufes.");
		
        VerticalLayout layout = new VerticalLayout(nombreCrucero, Titulo, Origen, Destino, Duracion, 
        		nombreBarco, Titulo2, bImagen, Descripcion, Aforo, Peso, FchaServicio, Instalaciones, Instalacion, Plano, b2Imagen, 
        		Consejos, Consejo1, Consejo2, Consejo3, Consejo4, Consejo5);
        add(layout);
	}
	

}
