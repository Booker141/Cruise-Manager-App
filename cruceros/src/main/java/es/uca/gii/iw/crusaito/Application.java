
package es.uca.gii.iw.crusaito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    		CiudadService ciudadService, CruceroService cruceroService, BarcoService barcoService, CamaroteService camaroteService,
    		ServicioService servicioService,
    		UsuarioRepository userRepo, BarcoRepository barcoRepo, CruceroRepository cruceroRepo) {
        return (args) -> {
        	
        	// Roles ejemplo
        	rolService.save(new Rol("Cliente"));
        	rolService.save(new Rol("Gerente"));
        	rolService.save(new Rol("Admin"));
        	
            // Usuarios de ejemplo
            userService.save(new Usuario("Jack", "Bauer","cliente@gmail.com","cliente","password","12345678Y",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Chloe", "O'Brian","admin@gmail.com","admin","admin","12348678A",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Admin")));
            userService.save(new Usuario("Bill", "Harrinson","gerente@gmail.com","gerente","gerente","12365678G",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Gerente")));
          
            // Generamos ciudades de ejemplo
            Ciudad cadiz = new Ciudad("Cadiz");
            Ciudad sanFernando = new Ciudad("San Fernando");
            Ciudad chiclana = new Ciudad("Chiclana");
            
            ciudadService.save(cadiz); ciudadService.save(sanFernando); ciudadService.save(chiclana);
            
            //Camarotes
            Camarote cam1 = new Camarote("Exterior", false);
            Camarote cam2 = new Camarote("Vip", false);
            Camarote cam3 = new Camarote("Interior",true);
            
            camaroteService.save(cam1); camaroteService.save(cam2); camaroteService.save(cam3);
            
            List<Camarote> lCamarotes = new ArrayList<>();
            lCamarotes.add(cam1);
            lCamarotes.add(cam2);
            
            //Barcos de ejemplo
            barcoRepo.save(new Barco("Vaporcito","frontend/img/crucero1.jpg",1000,100,2000,LocalDate.now(),"Buen barco"));
            barcoRepo.save(new Barco("Vaporcito2","frontend/img/crucero1.jpg",1500,150,3000,LocalDate.now(),"Mal barco"));
            
            Barco barcoEjemplo1 = barcoService.findBybNombre("Vaporcito");
            //Prueba de actualizacion de datos en BD y asignacion de camarote a barco y de barco a camarote
            barcoEjemplo1.setbCamarotes(lCamarotes);
            cam1.setcBarco(barcoEjemplo1);
            cam2.setcBarco(barcoEjemplo1);
            
            barcoService.save(barcoEjemplo1);
            camaroteService.save(cam1);
            camaroteService.save(cam2);
            
            //Cruceros ejemplo
            cruceroService.save(new Crucero("Caribe","Cadiz","Cadiz","5 dias","Crucero por el Caribe",300));
            cruceroService.save(new Crucero("Caribe1","Cadiz","Chiclana","5 dias","Crucero por el Caribe",300));
            cruceroService.save(new Crucero("Caribe2","Cadiz","San Fernando","5 dias","Crucero por el Caribe",300));
            
            List<Ciudad> listaCiudades = new ArrayList<>();
            listaCiudades.add(cadiz);
            listaCiudades.add(sanFernando);
            //Relacion crucero y ciudad
            Crucero caribe = cruceroService.findBycNombre("Caribe");
            caribe.setCiudades(listaCiudades);
            cadiz.addcCruceros(caribe);
            sanFernando.addcCruceros(caribe);
            
            cruceroService.save(caribe);
            ciudadService.save(cadiz);
            ciudadService.save(sanFernando);
            
            Servicio elFaro = new Servicio("El faro", "Mariscadas a lo grande", 30, ServicioTipo.Restaurante, "frontend/img/restaurante.jpg", 0,50);
            Servicio deportiva = new Servicio("Visita el faro", "Visita guiada al faro", 40, ServicioTipo.Excursion, 0,"frontend/img/islasgriegas.jpg", 30, "Cadiz");
            servicioService.save(elFaro);
            servicioService.save(deportiva);
            
            List<Servicio> servicios = new ArrayList<Servicio>();
            servicios.add(elFaro);
            servicios.add(deportiva);
            
            Usuario usuarioPrueba = userService.findByUsername("cliente");
            usuarioPrueba.setServicios(servicios);
            List<Usuario> usuarios = new ArrayList<Usuario>();
            usuarios.add(usuarioPrueba);
            elFaro.setUsuarios(usuarios);
            deportiva.setUsuarios(usuarios);
            
            servicioService.save(elFaro);
            servicioService.save(deportiva);
            userService.save(usuarioPrueba);
            
            /*
            barcoRepo.save(new Barco("Vaporcito","14","frontend/img/crucero1.jpg",1000,100,2000,LocalDate.now(),"Buen barco"));
            barcoRepo.save(new Barco("Vaporcito2","15","frontend/img/crucero1.jpg",1510,150,3000,LocalDate.now(),"Mal barco"));
            barcoRepo.save(new Barco("Monarch","16","frontend/img/crucero1.jpg",1000,100,3100,LocalDate.now(),"Buen barco"));
            barcoRepo.save(new Barco("Antia","17",1520,150,3200,LocalDate.now(),"Mal barco"));
            barcoRepo.save(new Barco("Titanic","18",1000,100,2150,LocalDate.now(),"Buen barco"));
            barcoRepo.save(new Barco("Neptuno","19",1530,150,3400,LocalDate.now(),"Mal barco"));
            barcoRepo.save(new Barco("Poseidon","20",1000,100,2010,LocalDate.now(),"Buen barco"));
            barcoRepo.save(new Barco("Siren","21",1500,150,3200,LocalDate.now(),"Mal barco"));
            */
            /*
            //save a few cruceros
            cruceroRepo.save(new Crucero("Mediterraneo","Barcelona","Barcelona","12 dias","Disfruta de las maravillas que te ofrecen los cruceros por el Mediterráneo de Crusaito\n" + 
            		"Tres continentes, decenas de ciudades y miles de rincones por visitar. Eso es lo que te espera si te embarcas en alguno de los evocadores cruceros por el Mediterráneo de Crusaito.\n" + 
            		"\n" + 
            		"Este mar, que antaño fue habitado por muchos de los pueblos y civilizaciones más importantes de la historia como los griegos, los romanos o los fenicios, te está esperando para que disfrutes de inolvidables experiencias.\n" + 
            		"\n" + 
            		"Disfruta de esta aventura bañada por aguas cristalinas y del clima mediterráneo durante todo el año. ¡Todo lo que habías imaginado lo podrás vivir haciendo un crucero por el Mediterráneo! Además, Crusaito te ofrece la posibilidad de elegir un crucero por el Mediterráneo con todo incluido para que sólo tengas que estar pendiente de disfrutar y vivir una experiencia única.", 446, Titanic,  ));
            cruceroRepo.save(new Crucero("Caribe","Cadiz","Cadiz","5 dias",LocalDate.now(),"Mal barco"));
            cruceroRepo.save(new Crucero("Islas Canarias","Malaga","Malaga","11 dias",3100,LocalDate.now(),"Buen barco"));
            cruceroRepo.save(new Crucero("Islas Griegas","El Pireo","El Pireo","10 dias",3200,LocalDate.now(),"Mal barco"));
            cruceroRepo.save(new Crucero("Baltico","Helsinki","Helsinki","9 dias",2150,LocalDate.now(),"Buen barco"));
            cruceroRepo.save(new Crucero("Adriatico","El Pireo","El Pireo","8 dias",3400,LocalDate.now(),"Mal barco"));
            */
            
            // fetch all users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (Usuario user : userRepo.findAll()) {
                log.info(user.toString());
            }
            log.info("");
         // fetch all barcos
            log.info("Barcos found with findAll():");
            log.info("-------------------------------");
            for (Barco barco : barcoRepo.findAll()) {
                log.info(barco.toString());
            }
            log.info("");
            //fetch users by DNI
            log.info("Users found with findByDni):");
            log.info("-------------------------------");
            for (Usuario user : userRepo.findByDni("12345678Y")) {
                log.info(user.toString());
            }
            log.info("");
          //fetch users by DNI
            log.info("Users found with findByEmail):");
            log.info("-------------------------------");
            Usuario user = userRepo.findByEmail("cliente@gmail.com"); 
            log.info(user.toString());
            log.info("");

            // fetch user by dni
            log.info("Users found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            userRepo.findByDni("12345678Y").forEach(bauer -> {
                log.info(bauer.toString());
            });

            log.info("");
        };
    }
}

