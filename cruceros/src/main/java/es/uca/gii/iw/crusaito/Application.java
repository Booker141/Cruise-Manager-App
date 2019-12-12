
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

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.repositorios.BarcoRepository;
import es.uca.gii.iw.crusaito.repositorios.UsuarioRepository;
import es.uca.gii.iw.crusaito.repositorios.rolRepository;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;
import es.uca.gii.iw.crusaito.servicios.rolService;
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
    public CommandLineRunner demo(rolService rolService, UsuarioService userService,rolRepository rolRepository, UsuarioRepository userRepo,BarcoRepository barcoRepo) {
        return (args) -> {
        	
        	// save roles
        	rolService.save(new Rol("Cliente"));
        	rolService.save(new Rol("Gerente"));
        	rolService.save(new Rol("Admin"));
            // save a few users
            userService.save(new Usuario("Jack", "Bauer","cliente@gmail.com","cliente","password","12345678Y",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Chloe", "O'Brian","admin@gmail.com","admin","admin","12345678Y",
            		123456789,LocalDate.now(),"Carranza","Cadiz",rolRepository.findByName("Admin")));

            // save a few barcos
            barcoRepo.save(new Barco("Vaporcito","15",1000,100,2000,LocalDate.now(),"Cadiz","San Fernando", "Buen barco"));
            barcoRepo.save(new Barco("Vaporcito2","14",1500,150,3000,LocalDate.now(),"San Fernando","Chiclana", "Mal barco"));

            
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
            Usuario user = userRepo.findByEmailIgnoreCase("cliente@gmail.com"); 
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

