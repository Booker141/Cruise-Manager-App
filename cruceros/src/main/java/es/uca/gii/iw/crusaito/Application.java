
package es.uca.gii.iw.crusaito;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.uca.gii.iw.crusaito.clases.*;
import es.uca.gii.iw.crusaito.repositorios.*;
import es.uca.gii.iw.crusaito.servicios.*;
/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableJpaRepositories
public class Application extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
			
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /* Codigo de prueba que guarda usuarios y barcos en la BD
     * para comprobar funcionamiento
     */
    @Bean
    public CommandLineRunner demo(rolService rolService, UsuarioService userService,rolRepository rolRepository, 
    		CiudadService ciudadService, CruceroService cruceroService, BarcoService barcoService,
    		ServicioService servicioService, ServicioUsuarioService servicioUsuarioService,
    		UsuarioRepository userRepo, BarcoRepository barcoRepo, CruceroRepository cruceroRepo) {
        return (args) -> {
        	
        	// Roles ejemplo
        	
        	rolService.save(new Rol("Cliente"));
        	rolService.save(new Rol("Gerente"));
        	rolService.save(new Rol("Admin"));
        	
            // Usuarios de ejemplo
        	
            userService.save(new Usuario("Jack", "Bauer","cliente@gmail.com","cliente","cliente","12345678Y",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Chloe", "O'Brian","admin@gmail.com","admin","admin","12348678A",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Admin")));
            userService.save(new Usuario("Bill", "Harrinson","gerente@gmail.com","gerente","gerente","12365678G",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Gerente")));
          
            // Generamos ciudades de ejemplo
            
            Ciudad cadiz = new Ciudad("Cadiz");
            Ciudad sanFernando = new Ciudad("San Fernando");
            Ciudad chiclana = new Ciudad("Chiclana");
            Ciudad bergen = new Ciudad("Bergen");
            Ciudad southampton = new Ciudad("Southampton");
            Ciudad sanJuan = new Ciudad("San Juan");
            Ciudad kotor = new Ciudad("Kotor");
            Ciudad galapagos = new Ciudad("Galapagos");
            Ciudad miami = new Ciudad("Miami");
            Ciudad barcelona = new Ciudad("Barcelona");
            Ciudad venecia = new Ciudad("Venecia");
            Ciudad laHabana = new Ciudad("La Habana");
            Ciudad helsinki = new Ciudad("Helsinki");
            Ciudad pireo = new Ciudad("El Pireo");
            Ciudad copenhague = new Ciudad("Copenhague");
            Ciudad trondheim = new Ciudad("Trondheim");
           
            
            ciudadService.save(cadiz); ciudadService.save(sanFernando); ciudadService.save(chiclana);
            ciudadService.save(bergen); ciudadService.save(southampton); ciudadService.save(sanJuan);
            ciudadService.save(kotor); ciudadService.save(galapagos); ciudadService.save(miami);
            ciudadService.save(barcelona); ciudadService.save(venecia); ciudadService.save(laHabana);
            ciudadService.save(helsinki); ciudadService.save(pireo); ciudadService.save(copenhague);
            ciudadService.save(trondheim);
            
            //Barcos de ejemplo
            
            Barco barcoEjemplo = barcoRepo.save(new Barco("Vaporcito","frontend/img/crucero1.jpg",1000,100,2000,LocalDate.now(),"Barco de lujo con camarotes y tiendas de alta calidad"));
            barcoRepo.save(new Barco("Vaporcito2","frontend/img/crucero1.jpg",1500,150,3000,LocalDate.now(),"Barco con características de lujo y un amplio personal profesional"));
            barcoRepo.save(new Barco("Antia","frontend/img/crucero1.jpg",1520,150,3200,LocalDate.now(),"Este barco, que fue remodelado en el año 2012, ofrece la posibilidad de viajar a un total "
            		+ "de 1520 pasajeros. Cuenta con 9 cubiertas, entre las que se distribuyen agradables"
            		+ " zonas de restauración, con restaurantes variados, buffets, bares y cafés. "
            		+ "Su oferta de ocio se completa con un gran teatro y un completo casino. "
            		+ "Para el descanso y el deporte también dispone de spa, piscinas, centro "
            		+ "de belleza y gimnasio, pudiendo completar cada jornada con compras en sus tiendas duty free."));
            barcoRepo.save(new Barco("Titanic","frontend/img/titanic.jpg",1000,100,2150,LocalDate.now(),"Con una capacidad total para 1000 pasajeros, este barco es el más grande "
            		+ "de los tres que navegan con Crusaito. Cuenta con todo tipo de "
            		+ "instalaciones, repartidas a lo largo de 12 cubiertas, entre las que destacan "
            		+ "restaurantes buffet y a la carta para disfrutar de una agradable velada, bares, "
            		+ "un completo y moderno casino, salón de espectáculos, salón de juegos, gimnasio, "
            		+ "spa y un amplio salón de belleza."));
            barcoRepo.save(new Barco("Neptuno","frontend/img/neptuno.jpg",1530,150,3400,LocalDate.now(),"Este barco con capacidad para 1530 pasajeros, "
            		+ "fue remodelado en el año 2014. Cuenta con 12 cubiertas, así como multitud de "
            		+ "instalaciones y ofertas de ocio para disfrutar de una diversión plena a bordo: grandes salones, "
            		+ "bares, restaurantes, casino y zona de juegos, teatro para la representación de espectáculos, "
            		+ "solarium, biblioteca, discoteca, rocódromo, pista de pádel, spa, gimnasio y salón de belleza."));
            barcoRepo.save(new Barco("Poseidon","frontend/img/poseidon.jpg",1600,140,2010,LocalDate.now(),"Con una capacidad total para 1600 pasajeros, "
            		+ "este barco es el más grande de los tres que navegan con Crusaito. "
            		+ "Cuenta con todo tipo de instalaciones, repartidas a lo largo de 17 cubiertas, "
            		+ "entre las que destacan restaurantes buffet y a la carta para disfrutar de una agradable velada, "
            		+ "bares, un completo y moderno casino, salón de espectáculos, salón de juegos, gimnasio, "
            		+ "spa y un amplio salón de belleza."));
            barcoRepo.save(new Barco("Siren","frontend/img/siren.jpg",1700,170,3100,LocalDate.now(),"Este barco, que fue remodelado en el año 2010, "
            		+ "ofrece la posibilidad de viajar a un total de 1700 pasajeros. "
            		+ "Cuenta con 10 cubiertas, entre las que se distribuyen agradables zonas de "
            		+ "restauración, con restaurantes variados, buffets, bares y cafés. "
            		+ "Su oferta de ocio se completa con un gran teatro y un completo casino. "
            		+ "Para el descanso y el deporte también dispone de spa, piscinas, centro de belleza y gimnasio, "
            		+ "pudiendo completar cada jornada con compras en sus tiendas duty free."));
            barcoRepo.save(new Barco("Spirit","frontend/img/spirit.jpg",1440,150,3200,LocalDate.now(),"Este barco con capacidad para 1440 pasajeros, "
            		+ "fue remodelado en el año 20009. Cuenta con 13 cubiertas, así como multitud de "
            		+ "instalaciones y ofertas de ocio para disfrutar de una diversión plena a bordo: "
            		+ "grandes salones, bares, restaurantes, casino y zona de juegos, teatro para la "
            		+ "representación de espectáculos, solarium, biblioteca, discoteca, rocódromo, "
            		+ "pista de pádel, spa, gimnasio y salón de belleza."));
            
            //Cruceros ejemplo
            
            cruceroService.save(new Crucero("Caribe","Cadiz","Cadiz","5 dias","Crucero por el Caribe",300));
            cruceroService.save(new Crucero("Mediterraneo","Barcelona","Barcelona","12 dias","Disfruta de las maravillas que te ofrecen los cruceros por el Mediterráneo de Crusaito\n" + 
            		"Tres continentes, decenas de ciudades y miles de rincones por visitar. Eso es lo que te espera si te embarcas en alguno de los evocadores cruceros por el Mediterráneo de Crusaito.\n" + 
            		"\n" + 
            		"Este mar, que antaño fue habitado por muchos de los pueblos y civilizaciones más importantes de la historia como los griegos, los romanos o los fenicios, te está esperando para que disfrutes de inolvidables experiencias.\n" + 
            		"\n" + 
            		"Disfruta de esta aventura bañada por aguas cristalinas y del clima mediterráneo "
            		+ "durante todo el año. ¡Todo lo que habías imaginado lo podrás vivir haciendo un crucero por el Mediterráneo! "
            		+ "Además, Crusaito te ofrece la posibilidad de elegir un crucero por el Mediterráneo con todo incluido para que sólo tengas "
            		+ "que estar pendiente de disfrutar y vivir una experiencia única.", 446));
            cruceroService.save(new Crucero("Islas Griegas","El Pireo","El Pireo","10 dias", "Sumérgete en el maravilloso mundo de las Islas Griegas "
            		+ "con Crusaito y vive instantes de película que quedarán inmortalizados en tu "
            		+ "memoria de la mejor manera, aquella que mentalmente te haga volver a sus apasionantes destinos "
            		+ "en forma de sonrisa.", 1200));
            cruceroService.save(new Crucero("Baltico","Helsinki","Helsinki","9 dias","Conoce la riqueza y belleza cultural de las ciudades más famosas "
            		+ "y emblemáticas que viven a orillas del Báltico, "
            		+ "un mar lleno de historia donde se encuentran las mayores reservas de ámbar "
            		+ "del mundo y que comunica con el mar del Norte y el océano Atlántico.", 2150));
            cruceroService.save(new Crucero("Adriatico","El Pireo","El Pireo","8 dias", "A bordo de tu crucero Crusaito, tendrás la oportunidad de disfrutar del "
            		+ "increíble color azul turquesa de las aguas del mar Adriático y un clima "
            		+ "inmejorable que hará que te plantees quedarte a vivir en sus costas para "
            		+ "toda la vida.", 3400));
            cruceroService.save(new Crucero("Fiordos noruegos ","Copenhague","Trondheim","8 dias", "Viajar a otro mundo sin salir de Europa es completamente posible. "
            		+ "Si te aventuras a embarcar en alguno de nuestros cruceros por los impresionantes "
            		+ "Fiordos Noruegos, serás testigo de primera mano de las espectaculares montañas "
            		+ "que los rodean y disfrutarás de las singulares excursiones que cada ciudad te "
            		+ "ofrece.", 3400));
           
            
            //Relaciones
            
            Crucero caribe = cruceroService.findBycNombre("Caribe");
            
            //Ciudades con crucero
            
            cadiz.addCruceros(caribe);
            sanFernando.addCruceros(caribe);
            chiclana.addCruceros(caribe);
            
            //BARCO con crucero
            
            caribe.setBarco(barcoEjemplo);
            
            //USUARIO con crucero
            
            Usuario usuarioEjemplo = userService.findByUsername("cliente");
            usuarioEjemplo.setCrucero(caribe);
            
            userRepo.save(usuarioEjemplo);
            barcoService.save(barcoEjemplo);
            cruceroService.save(caribe);
            ciudadService.save(cadiz);
            ciudadService.save(sanFernando);
            ciudadService.save(chiclana);
            
            Servicio elFaro = new Servicio("El faro", "Mariscadas a lo grande", 30, ServicioTipo.Restaurante, "frontend/img/restaurante.jpg", 2,5, LocalDate.now() );
            Servicio buffet = new Servicio("Buffet libre", "Acceso a todo tipo de comidas", 7, ServicioTipo.Restaurante, "frontend/img/Buffet.jpg", 2,5, LocalDate.now() );
            Servicio wok = new Servicio("Wok menu", "Disfruta de la pasión argentina", 15, ServicioTipo.Restaurante, "frontend/img/wok.jpg", 2,5, LocalDate.now() );
            Servicio luxury = new Servicio("Luxury", "Para los paladares más exquisitos", 100, ServicioTipo.Restaurante, "frontend/img/Luxury.jpg", 2,5, LocalDate.now() );
            
            Servicio deportiva = new Servicio("Visita el faro", "Visita guiada al faro", 40, ServicioTipo.Excursion, 0,"frontend/img/islasgriegas.jpg", 30, LocalDate.now(), "Cadiz");
            Servicio spa = new Servicio("Spa fit", "Disfruta de todas las comodidades", 60, ServicioTipo.Excursion, "frontend/img/spa.jpg", 2,5, LocalDate.now() );
            Servicio golf = new Servicio("Golf&Cut", "Practica el golf en medio del océano", 40, ServicioTipo.Excursion, "frontend/img/golf.jpg", 2,5, LocalDate.now() );
            Servicio padel = new Servicio("Pista padel", "Practica el padel en medio del océano", 20, ServicioTipo.Excursion, "frontend/img/padel.jpg", 2,5, LocalDate.now() );
            Servicio ping = new Servicio("Ping-pong", "Practica ping-pong en medio del océano", 15, ServicioTipo.Excursion, "frontend/img/ping-pong.jpg", 2,5, LocalDate.now() );
            
            Servicio regalo = new Servicio("Tienda de regalos", "Compra regalos y recuerdos a tus seres queridos", 0, ServicioTipo.Tienda, "frontend/img/regalos.jpg", 2,5, LocalDate.now() );
            Servicio temptempie = new Servicio("Carnival cherry", "Dulces que no encontrarás en otras partes", 0, ServicioTipo.Tienda, "frontend/img/temptempie.jpg", 2,5, LocalDate.now() );
            Servicio profumeria = new Servicio("La Profumeria", "Descubre aromas que te sorprenderán", 0, ServicioTipo.Tienda, "frontend/img/profumeria.jpg", 2,5, LocalDate.now() );
            Servicio joyeria = new Servicio("Joyeria cash", "Experto en piedras preciosas", 0, ServicioTipo.Tienda, "frontend/img/joyeria.jpg", 2,5, LocalDate.now() );
         
            
            servicioService.save(elFaro);
            servicioService.save(buffet);
            servicioService.save(wok);
            servicioService.save(luxury);
    
            servicioService.save(deportiva);
            servicioService.save(spa);
            servicioService.save(golf);
            servicioService.save(padel);
            servicioService.save(ping);
            
            servicioService.save(regalo);
            servicioService.save(temptempie);
            servicioService.save(profumeria);
            servicioService.save(joyeria);
            
         
            ServicioUsuario servUsu = new ServicioUsuario();
            servUsu.setServicio(elFaro);
            servUsu.setUsuario(usuarioEjemplo);
            servUsu.setParticipantes(2);
            
            elFaro.getServiciosUsuarios().add(servUsu);
            usuarioEjemplo.getUsuariosServicios().add(servUsu);
            servicioService.save(elFaro);
            userService.save(usuarioEjemplo);
            
            //elFaro.getUsuarios().add(usuarioEjemplo);
            //usuarioEjemplo.getServicios().add(elFaro);
            
            
            //Servicio con crucero
            caribe.addServicio(elFaro);
            servicioService.save(elFaro);
            //userService.save(usuarioEjemplo);
            cruceroService.save(caribe);
            
            
            // fetch user by dni
            log.info("Users found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            userRepo.findByDni("12345678Y").forEach(bauer -> {
                log.info(bauer.toString());
            });

            log.info("Servicios con findByUsuario");
            servicioUsuarioService.findByUsuario(usuarioEjemplo).forEach(prueba -> {
            	log.info(prueba.getServicio().getsNombre());
            });      
        };
    }
}

