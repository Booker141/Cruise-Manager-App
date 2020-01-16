
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

    /**
     * Codigo de prueba que guarda usuarios y barcos en la BD
     * @param rolService - rolService define el servicio del rol del usuario.
     * @param userService - userService define el servicio del usuario.
     * @param rolRepository - rolRepository define el repositorio del usuario.
     * @param ciudadService - ciudadService define el servicio de la ciudad.
     * @param cruceroService - cruceroService define el servicio del crucero.
     * @param barcoService - barcoService define el servicio del barco.
     * @param servicioService - servicioService define el servicio de los servicios disponibles.
     * @param servicioUsuarioService - servicioUsuarioService define el servicio de los servicios reservados por el usuario.
     * @param userRepo - userRepo define el repositorio del usuario.
     * @param barcoRepo - barcoRepo define el repositorio del barco.
     * @param cruceroRepo - cruceroRepo define el repositorio del crucero.
     * @return args.
     */
    
    @Bean
    public CommandLineRunner demo(rolService rolService, UsuarioService userService,rolRepository rolRepository, 
    		CiudadService ciudadService, CruceroService cruceroService, BarcoService barcoService,
    		ServicioService servicioService, ServicioUsuarioService servicioUsuarioService,
    		UsuarioRepository userRepo, BarcoRepository barcoRepo, CruceroRepository cruceroRepo) {
        return (args) -> {
        	
        	/**
        	 * Roles de ejemplo
        	 */
        	
        	rolService.save(new Rol("Cliente"));
        	rolService.save(new Rol("Gerente"));
        	rolService.save(new Rol("Admin"));
        	
            /**
             * Usuarios de ejemplo
             */
        	
            userService.save(new Usuario("Jack", "Bauer","cliente@gmail.com","cliente","cliente","12345678Y",
            		123456789,LocalDate.of(1950, 10, 11),"Calle Sagasta","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Ben", "Smith","cliente2@gmail.com","cliente2","cliente2","12345638B",
            		123456789,LocalDate.of(1983, 5, 6),"Calle Tolosa Latour","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Harry", "Smith","cliente3@gmail.com","cliente3","cliente3","12345638B",
            		123456789,LocalDate.of(1980, 3, 10),"Calle Santa María de la Cabeza","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Chloe", "O'Brian","admin@gmail.com","admin","admin","12348678A",
            		123456789,LocalDate.of(1979, 9, 7),"Avenida Andalucía","Cadiz",rolRepository.findByName("Admin")));
            userService.save(new Usuario("Bill", "Harrinson","gerente@gmail.com","gerente","gerente","12365678G",
            		123456789,LocalDate.of(1964, 1, 4),"Calle Brasil","Cadiz",rolRepository.findByName("Gerente")));
          
            /**
             * Generamos ciudades de ejemplo
             */
            
            /**
             * Ciudades Caribe
             */
            
            Ciudad cadiz = new Ciudad("Cadiz", "De Cádiz, el mar, su gente, su bahía, su historia y sus ganas de vivir.\n" + 
            		"\n" + 
            		"La provincia de Cádiz es muy diversa, con comarcas que merecen ser visitadas, desde la campiña de Jerez de la Frontera a sus pueblos del Campo de Gibraltar o un recorrido por los pueblos blancos y descansar en algún lugar de la costa entre Tarifa hasta Sanlúcar de Barrameda.\n" + 
            		"\n" + 
            		"Todo ello sin olvidar su capital, que guarda en sus murallas la cultura y las tradiciones que la hacen distinta, como a sus gentes.\n" + 
            		"\n" + 
            		"No lo dudes, Cádiz es el destino ideal para tus vacaciones.");
            Ciudad sanFernando = new Ciudad("San Fernando", "Rodeada por el Parque Natural Bahía de Cádiz, "
            		+ "la antigua Isla de León, que toma su nombre actual tras la Guerra "
            		+ "de Independencia (al serle concedido por su valor en la defensa "
            		+ "contra los franceses), ofrece un interesante paisaje de marismas, "
            		+ "salinas y dunas. La presencia de fenicios y romanos queda "
            		+ "atestiguada por los restos conservados en su Museo Histórico. "
            		+ "El Castillo de San Romualdo (un ribat islámico) defendía el único "
            		+ "paso terrestre desde el continente hasta Cádiz.");
            Ciudad chiclana = new Ciudad("Chiclana", "Fundada en el año 1303 por Alfonso Pérez de Guzmán, en el s.XVIII experimentará un "
            		+ "gran desarrollo económico contagiándose así de la intensa actividad comercial y mercantil de toda la Bahía. Se construyen "
            		+ "en esta época casas señoriales como la del Conde del Pinar. La industria vinícola que ha sustentado tradicionalmente la economía "
            		+ "de la población (con excelentes vinos que pertenecen a las denominaciones de origen de Jerez-Xeres- Sherry y Manzanilla de Sanlúcar) ha ido paulatinamente dejando "
            		+ "paso al sector turístico con un auge espectacular en los últimos años.");
            Ciudad colon = new Ciudad("Colon", "La provincia de Colón cubre un tramo casi virgen de la ribera caribeña en el norte de Panamá. "
            		+ "Las localidades costeras de la provincia fueron importantes puertos comerciales para España durante la época colonial, "
            		+ "y las reliquias de esta época siguen siendo atracciones turísticas para los "
            		+ "visitantes interesados ​​en la historia. Colón igualmente atrae a los apasionados "
            		+ "de la naturaleza con sus vastas extensiones de selvas vírgenes, domicilio de "
            		+ "monos aulladores y cientos de géneros de aves. Las playas cálidas y las islas "
            		+ "a lo largo de la costa del Caribe completan el atractivo de esta parte de Panamá.");
            
            /**
             * Ciudades Mediterráneo
             */
            
            Ciudad barcelona = new Ciudad("Barcelona", "Barcelona posee una "
            		+ "impresionante oferta cultural, contando con algunos interesantes museos "
            		+ "como la Fundación Joan Miró o el Museo Picasso, aunque donde se aprecia "
            		+ "realmente el arte que envuelve la ciudad es paseando por sus calles "
            		+ "cargadas de encanto.\n" + 
            		"\n" + 
            		"La conocidísima Sagrada Familia o el sorprendente Parque Güell son algunas "
            		+ "de las marcas con las que Antonio Gaudí decoró la ciudad de un modo "
            		+ "espectacular.\n" + 
            		"\n" + 
            		"Pero Barcelona no es sólo arte, sino que también posee soleadas playas "
            		+ "combinadas con una sugerente oferta gastronómica.");
            Ciudad napoles = new Ciudad("Napoles", "Capital de la región Campania posee más de un millón de habitantes siendo la tercera ciudad más grande de Italia. Linda con el Golfo de Nápoles y como vistas tiene el colosal volcan Vesubio.\n" + 
            		"\n" + 
            		"Nápoles hay que vivirla: hay que estar dentro de ella para saber "
            		+ "cómo es: vivaz, desorganizada, con imagen caótica, pero dentro de "
            		+ "ese caos mantiene un cierto orden, ya que sus propios habitantes se "
            		+ "crean sus propias reglas: sólo hay que conducir por las calles de Nápoles "
            		+ "para comprender lo que digo..."
            		+ "aunque al principio te crea confusión, poco a poco te habitúas.");
            Ciudad roma = new Ciudad("Roma", "Con una larga e interesante historia a sus espaldas, Roma es una ciudad que atrae visitantes de todo el "
            		+ "mundo gracias a sus impresionantes monumentos y restos "
            		+ "arqueológicos procedentes de la Antigüedad. Existen infinidad de razones "
            		+ "para visitar Roma, enamorarse de la ciudad y desear volver a ella. La gastronomía"
            		+ " y su animado ambiente son algunas de ellas.\n" + 
            		"\n" + 
            		"Pasear por Roma no es solo recorrer una antigua ciudad repleta de restos arqueológicos; Roma es el recuerdo de los "
            		+ "Gladiadores luchando a vida o muerte en el Coliseo, las cuadrigas emprendiendo veloces carreras en el Circo Máximo, y también la visión de los sabios romanos paseando por el foro mientras conversaban sobre la democracia.");
            Ciudad marsella = new Ciudad("Marsella", "Marseille es ciudad más antigua de Francia, ya que fue fundada por los "
            		+ "foceos en el año 600 a. C. Después de que los romanos dejaron su huella, "
            		+ "la ciudad siguió desarrollándose mediante una verdadera mezcolanza de culturas, "
            		+ "alrededor del puerto Viejo, protegido por sus dos fuertes, Saint-Nicolas y "
            		+ "Saint-Jean. El Puerto Viejo es el centro neurálgico de la ciudad, y constituye "
            		+ "una etapa ineludible para impregnarse del ambiente del mercado de pescado y de "
            		+ "sus animados muelles, sobre todo en las noches de partido "
            		+ "de fútbol del equipo de la ciudad, el Olympique de Marsella.");
            
            /**
             * Ciudades Islas Griegas
             */
            
            Ciudad pireo = new Ciudad("El Pireo", "¿Quién dijo que el puerto del "
            		+ "Pireo es un lugar sin encanto y sin nada interesante a descubrir en él? "
            		+ "Lejos del alboroto y el bullicio de los embarcaderos, el mayor puerto de "
            		+ "Grecia es un destino cargado de historia lleno de sitios y monumentos "
            		+ "encantadores, los más importantes de los cuales procuraremos  descubrir a "
            		+ "continuación sin pretensión alguna, por supuesto, de agotarlos todos. El Pireo dispone de tres puertos menores: Kantharos (el puerto central) de "
            		+ "donde salen los ferries, Zea y Mikrolímano. Situado a 12 km al suroeste del centro de Atenas, el Pireo es fácilmente "
            		+ "accesible en autobús o metro. La estación del metro ha sido recientemente "
            		+ "reformada por lo que se podría aprovechar "
            		+ "de este medio para poder visitarla.");
            Ciudad mikonos = new Ciudad("Mikonos", "La isla de Míkonos se encuentra en el centro "
            		+ "del archipiélago de las Cícladas, entre las islas de Tinos, Delos, Siros y Naxos. "
            		+ "Míkonos, cuyo nombre se escribe de diversa maneras (Mykonos, Míconos) es una "
            		+ "isla pequeña, con menos de 90 km 2, pero mundialmente conocida gracias a sus "
            		+ "playas y su diversión. Las discotecas, junto a la playa y la música electrónica, "
            		+ "son sinónimos del verano y del invierno en Míkonos, no en vano se ha llamado a "
            		+ "Mykonos la Ibiza de Grecia. Junto a la también pequeña Santorini, a la gran isla "
            		+ "Creta y a Atenas, Mykonos es una de las escalas más visitadas por los "
            		+ "turistas que se acercan a Grecia y al mar Egeo en un viaje en crucero.");
            Ciudad marmaris = new Ciudad("Marmaris", "La localıdad destaca por la abundancıa de calas en su larga costa, "
            		+ "su puerto natural, su cercanía a antıguas cıudades, oportunıdad de realızar el “vıaje azul”, sus modernas marınas y "
            		+ "ocasıón de la práctıca de deportes acuátıcos en su golfo. "
            		+ "La regıón uno de los prıncıpales destınos turıstıcos de Turquía, "
            		+ "tıene numerosos lugares para vısıtar. En Marmar s la poblacıón se "
            		+ "ıncrementa en los meses de verano con la llegada de turıstas tanto nacıonales "
            		+ "como extranjeros y la convıerten en un gran centro turístıco, exısten calas "
            		+ "tranquılas y limpıas que se puede acceder a ellas tanto en barcos como por "
            		+ "carretera, además de la "
            		+ "posıbılıdad de bañarse en las playas sıtuadas en la mısma cıudad.");
            Ciudad creta = new Ciudad("Creta", "Creta, la isla más grande de Grecia, es famosa por su diverso terreno, "
            		+ "que va desde las playas de arena fina en Elafonisi hasta las Montañas Blancas. "
            		+ "En el monte Ida, el más alto de la cadena, se encuentra la cueva de Ideon, "
            		+ "donde nació Zeus, según la mitología griega. La capital, Heraclión, "
            		+ "cuenta con el famoso Museo Arqueológico de Heraclión, con artefactos minoicos,"
            		+ " y Cnosos, un asentamiento de la Edad de Bronce.");
            
            /**
             * Ciudades Báltico
             */
            
            Ciudad tallin = new Ciudad("Tallin", "Tallin, la capital de Estonia sobre el mar Báltico, es el centro "
            		+ "cultural del país. Conserva su Ciudad Antigua amurallada y con adoquines, "
            		+ "que alberga cafés y tiendas, junto con Kiek in de Kök, una torre defensiva "
            		+ "del siglo XV. El edificio gótico del Ayuntamiento, construido en el siglo XIII y con una torre de 64 m de altura, "
            		+ "se encuentra en la histórica plaza principal de Tallin.");
            Ciudad rostock = new Ciudad("Rostock", "Rostock es una ciudad alemana, situada en el norte del país, que es famosa por su puerto marítimo, al que llegan gran cantidad de cruceros turísticos. "
            		+ "Con apenas doscientos mil habitantes y localizada en el estado de "
            		+ "Mecklemburgo-Pomerania Occidental, de donde procede Angela Merkel, "
            		+ "a orillas del mar Báltico, es conocida como la ciudad de la concordia y "
            		+ "la felicidad pública por el buen ambiente y el alto nivel de satisfacción "
            		+ "que proporciona a sus nativos y turistas.");
            Ciudad helsinki = new Ciudad("Helsinki", "Helsinki es una ciudad que se asoma al mar Báltico y posee un litoral sinuoso hecho "
            		+ "de bahías, playas e islas como Suomenlinna y Pihlajasaari, "
            		+ "a las que se llega rápidamente en ferry. Quizás algunos viajeros esperan "
            		+ "una ciudad con un casco antiguo similar a los de Europa del sur y "
            		+ "una arquitectura centenaria, pero la capital de Finlandia posee una historia corta, "
            		+ "es quizás la más joven de las capitales de los países nórdicos. "
            		+ "Aunque está claramente marcada por una vanguardia y una tradición"
            		+ " en diseño destacable. Su arquitectura Art Nouveau y funcionalista dibuja "
            		+ "una ciudad atípica. Es una ciudad de talla humana que invita al visitante "
            		+ "a descubrirla a ritmo lento, parándose en sus barrios de diseño, arquitectura, "
            		+ "compras, cultura, restaurantes, saunas…");
            Ciudad petersburgo = new Ciudad("Petersburgo", "San Petersburgo es una ciudad "
            		+ "considerada como uno de los más grandes centros económicos, "
            		+ "culturales y científicos de Rusia, Europa y el mundo entero. "
            		+ "Los majestuosos conjuntos arquitectónicos, palacios magníficos, "
            		+ "parques espléndidos y museos únicos llaman la atención y atraen a los "
            		+ "turistas de todas partes del mundo para sentir el ambiente de la Venecia "
            		+ "del Norte. La ciudad de San Pedro fue fundada por el emperador Pedro el "
            		+ "Grande en 1703 con la intención de convertirla en la \"ventana de Rusia hacia "
            		+ "el mundo occidental\". La ciudad es una combinación de culturas y "
            		+ "tradiciones de Rusia y Europa. ");
           
            /**
             * Ciudades Adriático
             */
            
            Ciudad dubrovnik = new Ciudad("Dubrovnik", "En Dubrovnik no solo encontraremos preciosas playas de piedra"
            		+ " con un encanto inusual, si no también una oferta cultural para aquellos que "
            		+ "quieran disfrutar de un lugar plagado de elegancia, edificios que nos trasladaran "
            		+ "a una época en la que el lujo y la ostentación eran los protagonistas.");
            Ciudad split = new Ciudad("Split", " Nos trasladamos a la región de Dalmacia, "
            		+ "más concretamente a Split, el centro neurálgico de la costa dálmata,"
            		+ " una ciudad con unos 200,000 habitantes, y sin duda otro lugar clave en nuestro itinerario." + "\n" + "En la historia de Split no podemos pasar por alto algunos datos que dan carácter a este "
            				+ "lugar, así que es de ley saber que el centro antiguo de esta ciudad está "
            				+ "unida al Imperio Romano, fue fundada en el S. IV, ya que el emperador Diocleciano,"
            				+ " natural de esta zona, hizo construir su palacio para habitarlo una vez se "
            				+ "retiró de la vida política, hoy en día es uno de los palacios romanos que mejor "
            				+ "se conservan.");
            Ciudad venecia = new Ciudad("Venecia", "Venecia es un conjunto de 120 islas unidas a través de puentes. "
            		+ "Desde Mestre se puede llegar a Venecia utilizando el Puente de la Libertad, "
            		+ "que lleva hasta la Piazzale Roma.\n" + 
            		"\n" + 
            		"Como es de imaginar, la ciudad ha sufrido inundaciones periódicas desde sus "
            		+ "inicios y, a día de hoy, la ciudad continúa sufriendo las amenazas de las "
            		+ "repetidas inundaciones que provocan el fenómeno conocido como Acqua Alta.\n" + 
            		"\n" + 
            		"En primavera y otoño suele subir el nivel del agua, por lo que es frecuente "
            		+ "que la Plaza de San Marcos se inunde hasta tal punto que las autoridades "
            		+ "tienen que colocar pasarelas para que caminen los peatones.");
            Ciudad zadar = new Ciudad("Zadar", "Este es uno de los mejores lugares para "
            		+ "disfrutar de unas vacaciones activas. Los paisajes de esta región geográfica, "
            		+ "desde lo alto de sus montañas hasta los valles y las islas, ofrecen buenas "
            		+ "oportunidades para disfrutar de vacaciones activas;"
            		+ " aprovechando numerosas veredas al borde del mar y rutas "
            		+ "para ciclistas de montaña, practicando senderismo, parapente o espeleología." +"\n" + "Es la ciudad con el casco histórico más grande de Dalmacia. "
            				+ "La ciudad destaca por su paseo marítimo que ofrece al visitante una visión "
            				+ "inolvidable de las islas y de sus puestas de sol acompañadas del sonido del "
            				+ "“Órgano de Mar” y del monumento arquitectónico «Oda al sol». Toda una experiencia. "
            				+ "Ambas del arquitecto Nikola Basic. "
            				+ "Además, destaca el Museo del Oro y de la Plata y el Museo de Cristal Antiguo.");
           
            /**
             * Ciudades Fiordos Noruegos
             */
            
            Ciudad bergen = new Ciudad("Bergen", "A nivel de Noruega, Bergen es una ciudad grande, pero ofrece el ambiente y "
            		+ "encanto de una ciudad pequeña. Sus habitantes apasionadamente patrióticos "
            		+ "están orgullosos de las múltiples facetas, la historia y las tradiciones "
            		+ "culturales de su ciudad.  Muchos lugareños están encantados de dirigir a los "
            		+ "visitantes a su atracción turística, cafetería o restaurante favoritos.");
            Ciudad molde = new Ciudad("Molde", "Molde es una ciudad y municipio costero del de la provincia de Møre og Romsdal, Noruega."
            		+ " Debido a la accidentada topografía de la zona, el acceso terrestre con el "
            		+ "resto del país es complicado, dependiendo en buena medida del transporte marítimo."
            		+ " Cuenta con una población de 26 392 habitantes según el censo de 2015.1​\n" + 
            		"\n" + 
            		"El municipio está situado en la península de Romsdal, rodeado por islas y por "
            		+ "los fiordos Fannefjord y Moldefjord. La ciudad está situada en la orilla norte "
            		+ "del Romsdalsfjord.");
            Ciudad alesund = new Ciudad("Alesund", "A nivel de Noruega, Bergen es una ciudad grande, pero ofrece el ambiente y encanto de una ciudad pequeña."
            		+ " Sus habitantes apasionadamente patrióticos están orgullosos de las múltiples "
            		+ "facetas, la historia y las tradiciones culturales de su ciudad.  "
            		+ "Muchos lugareños están encantados de dirigir a los visitantes a su atracción "
            		+ "turística, cafetería o restaurante favoritos.");
            Ciudad trondheim = new Ciudad("Trondheim", "Con una población de 193.000 habitantes, "
            		+ "Trondheim no es una gran ciudad en términos europeos. "
            		+ "Sin embargo, es la tercera ciudad más grande de Noruega. "
            		+ "La amplia variedad de actividades que puedes hacer aquí se debe en parte a "
            		+ "los estudiantes, que son más de 30.000 en esta ciudad. Los estudiantes dejan "
            		+ "su huella porque organizan muchos eventos, además de asistir a otras ofertas "
            		+ "culturales de la ciudad.\n" + 
            		"\n" + 
            		"Trondheim tiene un número de lugares de interés turístico que atraen anualmente"
            		+ " a una parte importante de los visitantes de Trøndelag. La catedral de"
            		+ " Nidarosdomen ofrece unas vistas espectaculares. La catedral es el santuario "
            		+ "nacional de Noruega, construido sobre la tumba de San Olav. Las obras "
            		+ "comenzaron en 1070, pero los restos más antiguos que aún existen datan de "
            		+ "mediados del siglo XII.");
            
            ciudadService.save(cadiz); ciudadService.save(sanFernando); ciudadService.save(chiclana);
            ciudadService.save(bergen); ciudadService.save(split); ciudadService.save(creta);
            ciudadService.save(molde); ciudadService.save(alesund); ciudadService.save(zadar);
            ciudadService.save(barcelona); ciudadService.save(venecia); ciudadService.save(dubrovnik);
            ciudadService.save(helsinki); ciudadService.save(pireo); ciudadService.save(petersburgo);
            ciudadService.save(trondheim); ciudadService.save(rostock); ciudadService.save(tallin);
            ciudadService.save(colon); ciudadService.save(napoles); ciudadService.save(roma);
            ciudadService.save(marsella); ciudadService.save(mikonos); ciudadService.save(marmaris);
        
            
            /**
             * Barcos de ejemplo
             */
            
         
            Barco antia =barcoRepo.save(new Barco("Antia","/img/crucero1.jpg",1520,150,3200,LocalDate.now(),"Este barco, que fue remodelado en el año 2012, ofrece la posibilidad de viajar a un total "
            		+ "de 1520 pasajeros. Cuenta con 9 cubiertas, entre las que se distribuyen agradables"
            		+ " zonas de restauración, con restaurantes variados, buffets, bares y cafés. "
            		+ "Su oferta de ocio se completa con un gran teatro y un completo casino. "
            		+ "Para el descanso y el deporte también dispone de spa, piscinas, centro "
            		+ "de belleza y gimnasio, pudiendo completar cada jornada con compras en sus tiendas duty free.", "/img/plano1.jpg"));
            Barco titanic =barcoRepo.save(new Barco("Titanic","/img/titanic.jpg",1000,100,2150,LocalDate.now(),"Con una capacidad total para 1000 pasajeros, este barco es el más grande "
            		+ "de los tres que navegan con Crusaito. Cuenta con todo tipo de "
            		+ "instalaciones, repartidas a lo largo de 12 cubiertas, entre las que destacan "
            		+ "restaurantes buffet y a la carta para disfrutar de una agradable velada, bares, "
            		+ "un completo y moderno casino, salón de espectáculos, salón de juegos, gimnasio, "
            		+ "spa y un amplio salón de belleza.", "/img/plano2.jpg"));
            Barco neptuno = barcoRepo.save(new Barco("Neptuno","/img/neptuno.jpg",1530,150,3400,LocalDate.now(),"Este barco con capacidad para 1530 pasajeros, "
            		+ "fue remodelado en el año 2014. Cuenta con 12 cubiertas, así como multitud de "
            		+ "instalaciones y ofertas de ocio para disfrutar de una diversión plena a bordo: grandes salones, "
            		+ "bares, restaurantes, casino y zona de juegos, teatro para la representación de espectáculos, "
            		+ "solarium, biblioteca, discoteca, rocódromo, pista de pádel, spa, gimnasio y salón de belleza.", "/img/plano3.jpg"));
            Barco poseidon =barcoRepo.save(new Barco("Poseidón","/img/poseidon.jpg",1600,140,2010,LocalDate.now(),"Con una capacidad total para 1600 pasajeros, "
            		+ "este barco es el más grande de los tres que navegan con Crusaito. "
            		+ "Cuenta con todo tipo de instalaciones, repartidas a lo largo de 17 cubiertas, "
            		+ "entre las que destacan restaurantes buffet y a la carta para disfrutar de una agradable velada, "
            		+ "bares, un completo y moderno casino, salón de espectáculos, salón de juegos, gimnasio, "
            		+ "spa y un amplio salón de belleza.", "/img/plano4.jpg"));
            Barco siren =barcoRepo.save(new Barco("Siren","/img/siren.jpg",1700,170,3100,LocalDate.now(),"Este barco, que fue remodelado en el año 2010, "
            		+ "ofrece la posibilidad de viajar a un total de 1700 pasajeros. "
            		+ "Cuenta con 10 cubiertas, entre las que se distribuyen agradables zonas de "
            		+ "restauración, con restaurantes variados, buffets, bares y cafés. "
            		+ "Su oferta de ocio se completa con un gran teatro y un completo casino. "
            		+ "Para el descanso y el deporte también dispone de spa, piscinas, centro de belleza y gimnasio, "
            		+ "pudiendo completar cada jornada con compras en sus tiendas duty free.", "/img/plano5.jpg"));
            Barco spirit =barcoRepo.save(new Barco("Spirit","/img/spirit.jpg",1440,150,3200,LocalDate.now(),"Este barco con capacidad para 1440 pasajeros, "
            		+ "fue remodelado en el año 2009. Cuenta con 13 cubiertas, así como multitud de "
            		+ "instalaciones y ofertas de ocio para disfrutar de una diversión plena a bordo: "
            		+ "grandes salones, bares, restaurantes, casino y zona de juegos, teatro para la "
            		+ "representación de espectáculos, solarium, biblioteca, discoteca, rocódromo, "
            		+ "pista de pádel, spa, gimnasio y salón de belleza.", "/img/plano6.jpg"));
            
            /**
             * Cruceros ejemplo
             */
            
            cruceroService.save(new Crucero("Caribe","Cádiz","Cádiz","5 días","Crucero por el Caribe",300));
            cruceroService.save(new Crucero("Mediterráneo","Barcelona","Barcelona","12 días","Disfruta de las maravillas que te ofrecen los cruceros por el Mediterráneo de Crusaito\n" + 
            		"Tres continentes, decenas de ciudades y miles de rincones por visitar. Eso es lo que te espera si te embarcas en alguno de los evocadores cruceros por el Mediterráneo de Crusaito.\n" + 
            		"\n" + 
            		"Este mar, que antaño fue habitado por muchos de los pueblos y civilizaciones más importantes de la historia como los griegos, los romanos o los fenicios, te está esperando para que disfrutes de inolvidables experiencias.\n" + 
            		"\n" + 
            		"Disfruta de esta aventura bañada por aguas cristalinas y del clima mediterráneo "
            		+ "durante todo el año. ¡Todo lo que habías imaginado lo podrás vivir haciendo un crucero por el Mediterráneo! "
            		+ "Además, Crusaito te ofrece la posibilidad de elegir un crucero por el Mediterráneo con todo incluido para que sólo tengas "
            		+ "que estar pendiente de disfrutar y vivir una experiencia única.", 446));
            cruceroService.save(new Crucero("Islas Griegas","El Pireo","El Pireo","10 días", "Sumérgete en el maravilloso mundo de las Islas Griegas "
            		+ "con Crusaito y vive instantes de película que quedarán inmortalizados en tu "
            		+ "memoria de la mejor manera, aquella que mentalmente te haga volver a sus apasionantes destinos "
            		+ "en forma de sonrisa.", 1200));
            cruceroService.save(new Crucero("Báltico","Helsinki","Helsinki","9 días","Conoce la riqueza y belleza cultural de las ciudades más famosas "
            		+ "y emblemáticas que viven a orillas del Báltico, "
            		+ "un mar lleno de historia donde se encuentran las mayores reservas de ámbar "
            		+ "del mundo y que comunica con el mar del Norte y el océano Atlántico.", 2150));
            cruceroService.save(new Crucero("Adriático","Dubrovnik","Dubrovnik","8 días", "A bordo de tu crucero Crusaito, tendrás la oportunidad de disfrutar del "
            		+ "increíble color azul turquesa de las aguas del mar Adriático y un clima "
            		+ "inmejorable que hará que te plantees quedarte a vivir en sus costas para "
            		+ "toda la vida.", 3400));
            cruceroService.save(new Crucero("Fiordos noruegos","Trondheim","Trondheim","8 días", "Viajar a otro mundo sin salir de Europa es completamente posible. "
            		+ "Si te aventuras a embarcar en alguno de nuestros cruceros por los impresionantes "
            		+ "Fiordos Noruegos, serás testigo de primera mano de las espectaculares montañas "
            		+ "que los rodean y disfrutarás de las singulares excursiones que cada ciudad te "
            		+ "ofrece.", 3400));
           
            
            /**
             * Relaciones
             */
            
            Crucero caribe = cruceroService.findBycNombre("Caribe");
            Crucero mediterraneo = cruceroService.findBycNombre("Mediterráneo");
            Crucero griego = cruceroService.findBycNombre("Islas Griegas");
            Crucero baltico = cruceroService.findBycNombre("Báltico");
            Crucero adriatico = cruceroService.findBycNombre("Adriático");
            Crucero noruego = cruceroService.findBycNombre("Fiordos noruegos");
            
            /**
             * Ciudades con crucero
             */
            
            /**
             * Caribe
             */
            
            CiudadCrucero ciudadCrucero1 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero2 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero3 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero4 = new CiudadCrucero();
            
            ciudadCrucero1.setCiudad(cadiz);
            ciudadCrucero2.setCiudad(sanFernando);
            ciudadCrucero3.setCiudad(chiclana);
            ciudadCrucero4.setCiudad(colon);
         
            ciudadCrucero1.setFechaLlegada(LocalDate.now());
            ciudadCrucero1.setFechaSalida(LocalDate.now());
            ciudadCrucero1.setHoraLlegada(11);
            ciudadCrucero1.setHoraSalida(15);
            
            ciudadCrucero2.setFechaLlegada(LocalDate.now());
            ciudadCrucero2.setFechaSalida(LocalDate.now());
            ciudadCrucero2.setHoraLlegada(13);
            ciudadCrucero2.setHoraSalida(14);
            
            ciudadCrucero3.setFechaLlegada(LocalDate.now());
            ciudadCrucero3.setFechaSalida(LocalDate.now());
            ciudadCrucero3.setHoraLlegada(15);
            ciudadCrucero3.setHoraSalida(17);
            
            ciudadCrucero4.setFechaLlegada(LocalDate.now());
            ciudadCrucero4.setFechaSalida(LocalDate.now());
            ciudadCrucero4.setHoraLlegada(14);
            ciudadCrucero4.setHoraSalida(16);
            
            ciudadCrucero1.setCrucero(caribe);
            ciudadCrucero2.setCrucero(caribe);
            ciudadCrucero3.setCrucero(caribe);
            ciudadCrucero4.setCrucero(caribe);
            
            cadiz.getCiudadesCruceros().add(ciudadCrucero1);
            sanFernando.getCiudadesCruceros().add(ciudadCrucero2);
            chiclana.getCiudadesCruceros().add(ciudadCrucero3);
            colon.getCiudadesCruceros().add(ciudadCrucero4);
            
            caribe.getCrucerosCiudades().add(ciudadCrucero1);
            caribe.getCrucerosCiudades().add(ciudadCrucero2);
            caribe.getCrucerosCiudades().add(ciudadCrucero3);
            caribe.getCrucerosCiudades().add(ciudadCrucero4);
            
            ciudadService.save(cadiz);
            ciudadService.save(sanFernando);
            ciudadService.save(chiclana);
            ciudadService.save(colon);
            
            cruceroService.save(caribe);
            
            /**
             * Mediterráneo
             */
            
            CiudadCrucero ciudadCrucero5 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero6 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero7 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero8 = new CiudadCrucero();
            
            ciudadCrucero5.setCiudad(barcelona);
            ciudadCrucero6.setCiudad(napoles);
            ciudadCrucero7.setCiudad(roma);
            ciudadCrucero8.setCiudad(marsella);
         
            ciudadCrucero5.setFechaLlegada(LocalDate.now());
            ciudadCrucero5.setFechaSalida(LocalDate.now());
            ciudadCrucero5.setHoraLlegada(10);
            ciudadCrucero5.setHoraSalida(16);
            
            ciudadCrucero6.setFechaLlegada(LocalDate.now());
            ciudadCrucero6.setFechaSalida(LocalDate.now());
            ciudadCrucero6.setHoraLlegada(13);
            ciudadCrucero6.setHoraSalida(14);
            
            ciudadCrucero7.setFechaLlegada(LocalDate.now());
            ciudadCrucero7.setFechaSalida(LocalDate.now());
            ciudadCrucero7.setHoraLlegada(15);
            ciudadCrucero7.setHoraSalida(19);
            
            ciudadCrucero8.setFechaLlegada(LocalDate.now());
            ciudadCrucero8.setFechaSalida(LocalDate.now());
            ciudadCrucero8.setHoraLlegada(14);
            ciudadCrucero8.setHoraSalida(20);
            
            ciudadCrucero5.setCrucero(mediterraneo);
            ciudadCrucero6.setCrucero(mediterraneo);
            ciudadCrucero7.setCrucero(mediterraneo);
            ciudadCrucero8.setCrucero(mediterraneo);
            
            barcelona.getCiudadesCruceros().add(ciudadCrucero5);
            napoles.getCiudadesCruceros().add(ciudadCrucero6);
            roma.getCiudadesCruceros().add(ciudadCrucero7);
            marsella.getCiudadesCruceros().add(ciudadCrucero8);
            
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero5);
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero6);
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero7);
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero8);
            
            ciudadService.save(barcelona);
            ciudadService.save(napoles);
            ciudadService.save(roma);
            ciudadService.save(marsella);
            
            cruceroService.save(mediterraneo);
            
            /**
             * Islas Griegas
             */
            
            CiudadCrucero ciudadCrucero9 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero10 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero11 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero12 = new CiudadCrucero();
            
            ciudadCrucero9.setCiudad(pireo);
            ciudadCrucero10.setCiudad(mikonos);
            ciudadCrucero11.setCiudad(marmaris);
            ciudadCrucero12.setCiudad(creta);
         
            ciudadCrucero9.setFechaLlegada(LocalDate.now());
            ciudadCrucero9.setFechaSalida(LocalDate.now());
            ciudadCrucero9.setHoraLlegada(7);
            ciudadCrucero9.setHoraSalida(12);
            
            ciudadCrucero10.setFechaLlegada(LocalDate.now());
            ciudadCrucero10.setFechaSalida(LocalDate.now());
            ciudadCrucero10.setHoraLlegada(9);
            ciudadCrucero10.setHoraSalida(14);
            
            ciudadCrucero11.setFechaLlegada(LocalDate.now());
            ciudadCrucero11.setFechaSalida(LocalDate.now());
            ciudadCrucero11.setHoraLlegada(12);
            ciudadCrucero11.setHoraSalida(17);
            
            ciudadCrucero12.setFechaLlegada(LocalDate.now());
            ciudadCrucero12.setFechaSalida(LocalDate.now());
            ciudadCrucero12.setHoraLlegada(13);
            ciudadCrucero12.setHoraSalida(16);
            
            ciudadCrucero9.setCrucero(griego);
            ciudadCrucero10.setCrucero(griego);
            ciudadCrucero11.setCrucero(griego);
            ciudadCrucero12.setCrucero(griego);
            
            pireo.getCiudadesCruceros().add(ciudadCrucero9);
            mikonos.getCiudadesCruceros().add(ciudadCrucero10);
            marmaris.getCiudadesCruceros().add(ciudadCrucero11);
            creta.getCiudadesCruceros().add(ciudadCrucero12);
            
            griego.getCrucerosCiudades().add(ciudadCrucero9);
            griego.getCrucerosCiudades().add(ciudadCrucero10);
            griego.getCrucerosCiudades().add(ciudadCrucero11);
            griego.getCrucerosCiudades().add(ciudadCrucero12);
            
            ciudadService.save(pireo);
            ciudadService.save(mikonos);
            ciudadService.save(marmaris);
            ciudadService.save(creta);
            
            cruceroService.save(griego);
            
            /**
             * Báltico
             */
     
            CiudadCrucero ciudadCrucero13 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero14 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero15 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero16 = new CiudadCrucero();
            
            ciudadCrucero13.setCiudad(tallin);
            ciudadCrucero14.setCiudad(rostock);
            ciudadCrucero15.setCiudad(helsinki);
            ciudadCrucero16.setCiudad(petersburgo);
         
            ciudadCrucero13.setFechaLlegada(LocalDate.now());
            ciudadCrucero13.setFechaSalida(LocalDate.now());
            ciudadCrucero13.setHoraLlegada(11);
            ciudadCrucero13.setHoraSalida(12);
            
            ciudadCrucero14.setFechaLlegada(LocalDate.now());
            ciudadCrucero14.setFechaSalida(LocalDate.now());
            ciudadCrucero14.setHoraLlegada(13);
            ciudadCrucero14.setHoraSalida(14);
            
            ciudadCrucero15.setFechaLlegada(LocalDate.now());
            ciudadCrucero15.setFechaSalida(LocalDate.now());
            ciudadCrucero15.setHoraLlegada(15);
            ciudadCrucero15.setHoraSalida(17);
            
            ciudadCrucero16.setFechaLlegada(LocalDate.now());
            ciudadCrucero16.setFechaSalida(LocalDate.now());
            ciudadCrucero16.setHoraLlegada(14);
            ciudadCrucero16.setHoraSalida(16);
            
            ciudadCrucero13.setCrucero(baltico);
            ciudadCrucero14.setCrucero(baltico);
            ciudadCrucero15.setCrucero(baltico);
            ciudadCrucero16.setCrucero(baltico);
            
            tallin.getCiudadesCruceros().add(ciudadCrucero13);
            rostock.getCiudadesCruceros().add(ciudadCrucero14);
            helsinki.getCiudadesCruceros().add(ciudadCrucero15);
            petersburgo.getCiudadesCruceros().add(ciudadCrucero16);
            
            baltico.getCrucerosCiudades().add(ciudadCrucero13);
            baltico.getCrucerosCiudades().add(ciudadCrucero14);
            baltico.getCrucerosCiudades().add(ciudadCrucero15);
            baltico.getCrucerosCiudades().add(ciudadCrucero16);
            
            ciudadService.save(tallin);
            ciudadService.save(rostock);
            ciudadService.save(helsinki);
            ciudadService.save(petersburgo);
            
            cruceroService.save(baltico);
            
            /**
             * Adriático
             */
            
            CiudadCrucero ciudadCrucero17 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero18 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero19 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero20 = new CiudadCrucero();
            
            ciudadCrucero17.setCiudad(dubrovnik);
            ciudadCrucero18.setCiudad(split);
            ciudadCrucero19.setCiudad(venecia);
            ciudadCrucero20.setCiudad(zadar);
         
            ciudadCrucero17.setFechaLlegada(LocalDate.now());
            ciudadCrucero17.setFechaSalida(LocalDate.now());
            ciudadCrucero17.setHoraLlegada(4);
            ciudadCrucero17.setHoraSalida(12);
            
            ciudadCrucero18.setFechaLlegada(LocalDate.now());
            ciudadCrucero18.setFechaSalida(LocalDate.now());
            ciudadCrucero18.setHoraLlegada(5);
            ciudadCrucero18.setHoraSalida(14);
            
            ciudadCrucero19.setFechaLlegada(LocalDate.now());
            ciudadCrucero19.setFechaSalida(LocalDate.now());
            ciudadCrucero19.setHoraLlegada(6);
            ciudadCrucero19.setHoraSalida(21);
            
            ciudadCrucero20.setFechaLlegada(LocalDate.now());
            ciudadCrucero20.setFechaSalida(LocalDate.now());
            ciudadCrucero20.setHoraLlegada(18);
            ciudadCrucero20.setHoraSalida(19);
            
            ciudadCrucero17.setCrucero(adriatico);
            ciudadCrucero18.setCrucero(adriatico);
            ciudadCrucero19.setCrucero(adriatico);
            ciudadCrucero20.setCrucero(adriatico);
            
            dubrovnik.getCiudadesCruceros().add(ciudadCrucero17);
            split.getCiudadesCruceros().add(ciudadCrucero18);
            venecia.getCiudadesCruceros().add(ciudadCrucero19);
            zadar.getCiudadesCruceros().add(ciudadCrucero20);
            
            adriatico.getCrucerosCiudades().add(ciudadCrucero17);
            adriatico.getCrucerosCiudades().add(ciudadCrucero18);
            adriatico.getCrucerosCiudades().add(ciudadCrucero19);
            adriatico.getCrucerosCiudades().add(ciudadCrucero20);
            
            ciudadService.save(dubrovnik);
            ciudadService.save(split);
            ciudadService.save(venecia);
            ciudadService.save(zadar);
            
            cruceroService.save(adriatico);
            
            /**
             * Fiordos Noruegos
             */
            
            CiudadCrucero ciudadCrucero21 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero22 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero23 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero24 = new CiudadCrucero();
            
            ciudadCrucero21.setCiudad(bergen);
            ciudadCrucero22.setCiudad(molde);
            ciudadCrucero23.setCiudad(alesund);
            ciudadCrucero24.setCiudad(trondheim);
         
            ciudadCrucero21.setFechaLlegada(LocalDate.now());
            ciudadCrucero21.setFechaSalida(LocalDate.now());
            ciudadCrucero21.setHoraLlegada(10);
            ciudadCrucero21.setHoraSalida(17);
            
            ciudadCrucero22.setFechaLlegada(LocalDate.now());
            ciudadCrucero22.setFechaSalida(LocalDate.now());
            ciudadCrucero22.setHoraLlegada(10);
            ciudadCrucero22.setHoraSalida(14);
            
            ciudadCrucero23.setFechaLlegada(LocalDate.now());
            ciudadCrucero23.setFechaSalida(LocalDate.now());
            ciudadCrucero23.setHoraLlegada(11);
            ciudadCrucero23.setHoraSalida(17);
            
            ciudadCrucero24.setFechaLlegada(LocalDate.now());
            ciudadCrucero24.setFechaSalida(LocalDate.now());
            ciudadCrucero24.setHoraLlegada(12);
            ciudadCrucero24.setHoraSalida(22);
            
            ciudadCrucero21.setCrucero(noruego);
            ciudadCrucero22.setCrucero(noruego);
            ciudadCrucero23.setCrucero(noruego);
            ciudadCrucero24.setCrucero(noruego);
            
            bergen.getCiudadesCruceros().add(ciudadCrucero21);
            molde.getCiudadesCruceros().add(ciudadCrucero22);
            alesund.getCiudadesCruceros().add(ciudadCrucero23);
            trondheim.getCiudadesCruceros().add(ciudadCrucero24);
            
            noruego.getCrucerosCiudades().add(ciudadCrucero21);
            noruego.getCrucerosCiudades().add(ciudadCrucero22);
            noruego.getCrucerosCiudades().add(ciudadCrucero23);
            noruego.getCrucerosCiudades().add(ciudadCrucero24);
            
            ciudadService.save(bergen);
            ciudadService.save(molde);
            ciudadService.save(alesund);
            ciudadService.save(trondheim);
            
            cruceroService.save(noruego);
            
            /**
             * Barco con crucero
             */
            
            caribe.setBarco(spirit);
            mediterraneo.setBarco(antia);
            griego.setBarco(titanic);
            baltico.setBarco(neptuno);
            adriatico.setBarco(poseidon);
            noruego.setBarco(siren);
            
            
            /**
             * Usuario 1 con crucero en el caribe
             */
            
            Usuario usuarioEjemplo = userService.findByUsername("cliente");
            usuarioEjemplo.setCrucero(caribe);
            
            userRepo.save(usuarioEjemplo);
            barcoService.save(spirit);
            cruceroService.save(caribe);
            ciudadService.save(cadiz);
            ciudadService.save(sanFernando);
            ciudadService.save(chiclana);
            ciudadService.save(colon);
            
            /**
             * Usuario2 con crucero en el adriatico
             */
            
            Usuario usuarioEjemplo2 = userService.findByUsername("cliente2");
            usuarioEjemplo2.setCrucero(adriatico);
            
            userRepo.save(usuarioEjemplo2);
            barcoService.save(poseidon);
            cruceroService.save(adriatico);
            ciudadService.save(dubrovnik);
            ciudadService.save(split);
            ciudadService.save(venecia);
            ciudadService.save(zadar);
            
            /**
             * Usuario2 con crucero en el baltico
             */
            
            Usuario usuarioEjemplo3 = userService.findByUsername("cliente3");
            usuarioEjemplo3.setCrucero(baltico);
            
            userRepo.save(usuarioEjemplo3);
            barcoService.save(neptuno);
            cruceroService.save(baltico);
            ciudadService.save(tallin);
            ciudadService.save(rostock);
            ciudadService.save(helsinki);
            ciudadService.save(petersburgo);
            
            /**
             * Definición de los servicios
             */
            
            Servicio elFaro = new Servicio("El faro", "Mariscadas a lo grande", 30, ServicioTipo.Restaurante, "/img/restaurante.jpg", 0,70, LocalDate.now() );
            Servicio buffet = new Servicio("Buffet libre", "Acceso a todo tipo de comidas", 7, ServicioTipo.Restaurante, "/img/Buffet.jpg", 0,50, LocalDate.now() );
            Servicio wok = new Servicio("Wok menu", "Disfruta de la pasión argentina", 15, ServicioTipo.Restaurante, "/img/wok.jpg", 0,65, LocalDate.now() );
            Servicio luxury = new Servicio("Luxury", "Para los paladares más exquisitos", 100, ServicioTipo.Restaurante, "/img/Luxury.jpg", 0,50, LocalDate.now() );
            
            Servicio excursion1 = new Servicio("Visita al faro", "Visita guiada al faro. Comienzo de la excursión: 12:00", 40, ServicioTipo.Excursion, 2,"/img/islasgriegas.jpg", 30, LocalDate.now());
            Servicio excursion2 = new Servicio("Visita al Museo Municipal", "Visita guiada al Museo Municipal. Comienzo de la excursión: 15:30", 20, ServicioTipo.Excursion, 1,"/img/museo.jpg", 30, LocalDate.now());
            Servicio excursion3 = new Servicio("Visita al templo griego", "Visita guiada al templo griego. Comienzo de la excursión: 9:00", 30, ServicioTipo.Excursion, 0,"/img/templo.jpg", 30, LocalDate.now());
            Servicio excursion4 = new Servicio("Visita a la Iglesia de Santa María", "Visita guiada a la Iglesia de Santa María. Comienzo de la excursión: 12:30", 55, ServicioTipo.Excursion, 0,"/img/iglesia.jpg", 30, LocalDate.now());
            Servicio excursion5 = new Servicio("Visita El Florian", "Visita guiada a El Florian. Comienzo de la excursión: 17:00", 40, ServicioTipo.Excursion, 3,"/img/cafe.jpg", 30, LocalDate.now());
            Servicio excursion6 = new Servicio("Visita Sagrada Familia", "Visita guiada al La Sagrada Familia. Comienzo de la excursión: 13:00", 40, ServicioTipo.Excursion, 0,"/img/sagrada.jpg", 30, LocalDate.now());
            
            excursion1.setCiudad(cadiz);
            cadiz.getServicios().add(excursion1);
            servicioService.save(excursion1);
            
            excursion2.setCiudad(helsinki);
            helsinki.getServicios().add(excursion2);
            servicioService.save(excursion2);
            
            excursion3.setCiudad(pireo);
            pireo.getServicios().add(excursion3);
            servicioService.save(excursion3);
            
            excursion4.setCiudad(bergen);
            bergen.getServicios().add(excursion4);
            servicioService.save(excursion4);
            
            excursion5.setCiudad(venecia);
            venecia.getServicios().add(excursion5);
            servicioService.save(excursion5);
            
            excursion6.setCiudad(barcelona);
            barcelona.getServicios().add(excursion6);
            servicioService.save(excursion6);
            
            
            Servicio spa = new Servicio("Spa fit", "Disfruta de todas las comodidades", 60, ServicioTipo.Otro, "/img/spa.jpg", 0,25, LocalDate.now() );
            Servicio golf = new Servicio("Golf&Cut", "Practica el golf en medio del océano", 40, ServicioTipo.Otro, "/img/golf.jpg", 0,35, LocalDate.now() );
            Servicio padel = new Servicio("Pista padel", "Practica el padel en medio del océano", 20, ServicioTipo.Otro, "/img/padel.jpg", 0,40, LocalDate.now() );
            Servicio ping = new Servicio("Ping-pong", "Practica ping-pong en medio del océano", 15, ServicioTipo.Otro, "/img/ping-pong.jpg", 1,30, LocalDate.now() );
            
            Servicio regalo = new Servicio("Tienda de regalos", "Compra regalos y recuerdos a tus seres queridos", 0, ServicioTipo.Tienda, "/img/regalos.jpg", 0,20, LocalDate.now() );
            Servicio temptempie = new Servicio("Carnival cherry", "Dulces que no encontrarás en otras partes", 0, ServicioTipo.Tienda, "/img/temptempie.jpg", 0,25, LocalDate.now() );
            Servicio profumeria = new Servicio("La Profumería", "Descubre aromas que te sorprenderán", 0, ServicioTipo.Tienda, "/img/profumeria.jpg", 0,25, LocalDate.now() );
            Servicio joyeria = new Servicio("Joyería cash", "Experto en piedras preciosas", 0, ServicioTipo.Tienda, "/img/joyeria.jpg", 0,30, LocalDate.now() );
         
            
            servicioService.save(elFaro);
            servicioService.save(buffet);
            servicioService.save(wok);
            servicioService.save(luxury);
    
            servicioService.save(excursion1);
            servicioService.save(excursion2);
            servicioService.save(excursion3);
            servicioService.save(excursion4);
            servicioService.save(excursion5);
            servicioService.save(excursion6);
            
            servicioService.save(spa);
            servicioService.save(golf);
            servicioService.save(padel);
            servicioService.save(ping);
            
            servicioService.save(regalo);
            servicioService.save(temptempie);
            servicioService.save(profumeria);
            servicioService.save(joyeria);
            
            /**
             * Usuario 1 con servicio
             */
         
            ServicioUsuario servUsu = new ServicioUsuario();
            servUsu.setServicio(excursion1);
            servUsu.setUsuario(usuarioEjemplo);
            servUsu.setParticipantes(2);
            servUsu.setPrecio(60);
            
            excursion1.getServiciosUsuarios().add(servUsu);
            usuarioEjemplo.getUsuariosServicios().add(servUsu);
            servicioService.save(excursion1);
            userService.save(usuarioEjemplo);
            servicioUsuarioService.save(servUsu);
            
            /**
             * Usuario 2 con servicio
             */
         
            ServicioUsuario servUsu2 = new ServicioUsuario();
            servUsu2.setServicio(excursion5);
            servUsu2.setUsuario(usuarioEjemplo2);
            servUsu2.setParticipantes(3);
            servUsu2.setPrecio(120);
            
            excursion5.getServiciosUsuarios().add(servUsu2);
            usuarioEjemplo2.getUsuariosServicios().add(servUsu2);
            servicioService.save(excursion5);
            userService.save(usuarioEjemplo2);
            
            /**
             * Usuario 3 con servicio
             */
         
            ServicioUsuario servUsu3 = new ServicioUsuario();
            servUsu3.setServicio(ping);
            servUsu3.setUsuario(usuarioEjemplo3);
            servUsu3.setParticipantes(1);
            servUsu3.setPrecio(15);
            
            ServicioUsuario servUsu4 = new ServicioUsuario();
            servUsu4.setServicio(excursion2);
            servUsu4.setUsuario(usuarioEjemplo3);
            servUsu4.setParticipantes(1);
            servUsu4.setPrecio(20);
            
            ping.getServiciosUsuarios().add(servUsu3);
            usuarioEjemplo3.getUsuariosServicios().add(servUsu3);
            servicioService.save(ping);
            userService.save(usuarioEjemplo3);
            
            excursion2.getServiciosUsuarios().add(servUsu4);
            usuarioEjemplo3.getUsuariosServicios().add(servUsu4);
            servicioService.save(excursion2);
            userService.save(usuarioEjemplo3);
            
            /**
             * Servicio con crucero
             */
            
            caribe.addServicio(excursion1);
            servicioService.save(excursion1);
            caribe.addServicio(golf);
            servicioService.save(golf);
            caribe.addServicio(profumeria);
            servicioService.save(profumeria);
            caribe.addServicio(spa);
            servicioService.save(spa);
            //userService.save(usuarioEjemplo);
            cruceroService.save(caribe);
            
            mediterraneo.addServicio(buffet);
            servicioService.save(buffet);
            mediterraneo.addServicio(golf);
            servicioService.save(golf);
            mediterraneo.addServicio(spa);
            servicioService.save(spa);
            mediterraneo.addServicio(excursion6);
            servicioService.save(excursion6);
            //userService.save(usuarioEjemplo);
            cruceroService.save(mediterraneo);
            
            griego.addServicio(wok);
            servicioService.save(wok);
            griego.addServicio(excursion3);
            servicioService.save(excursion3);
            griego.addServicio(padel);
            servicioService.save(padel);
            griego.addServicio(spa);
            servicioService.save(spa);
            //userService.save(usuarioEjemplo);
            cruceroService.save(griego);
            
            baltico.addServicio(luxury);
            servicioService.save(luxury);
            baltico.addServicio(ping);
            servicioService.save(ping);
            baltico.addServicio(excursion2);
            servicioService.save(excursion2);
            baltico.addServicio(regalo);
            servicioService.save(regalo);
            //userService.save(usuarioEjemplo);
            cruceroService.save(baltico);
            
            adriatico.addServicio(regalo);
            servicioService.save(regalo);
            adriatico.addServicio(golf);
            servicioService.save(golf);
            adriatico.addServicio(profumeria);
            servicioService.save(profumeria);
            adriatico.addServicio(excursion5);
            servicioService.save(excursion5);
            cruceroService.save(adriatico);
            
            noruego.addServicio(elFaro);
            servicioService.save(elFaro);
            noruego.addServicio(golf);
            servicioService.save(golf);
            noruego.addServicio(excursion4);
            servicioService.save(excursion4);
            noruego.addServicio(spa);
            servicioService.save(spa);
            cruceroService.save(noruego);
            
            log.info("Servicios con findByUsuario");
            Usuario usucliente = userService.findByUsername("cliente");
            	log.info(usucliente.getFirstName());
              
        };
    }
}

