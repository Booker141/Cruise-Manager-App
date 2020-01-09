package es.uca.gii.iw.crusaito.servicios;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;
import es.uca.gii.iw.crusaito.clases.Usuario;

public class ServicioServiceTest {
	
	private ServicioService servicioService;
	
	@Test
	public void Test (){

		Rol r = new Rol("Cliente");
		
        Usuario u = new Usuario("Carlos","Perez", "carlos@gmail.com", "carlos", 
        "carlos", "12345678T", 435678909, LocalDate.now(), "Calle Perez", "Cadiz", r);
        
		Crucero c = new Crucero("Mar Negro","Cadiz", "Portugal", "5 dias", 
		        "Muy buen crucero", 50.0);
		
		Servicio s = new Servicio("Nadar","piscina", 10.0, ServicioTipo.Excursion, 
		        "frontend/img/golf.jpg", 30, 40, LocalDate.now());
		
		/**
		 * Encuentra todos los servicios según el usuario
		 */
		
		List<Servicio> servicios = servicioService.findCruceroByUsername(u.getUsername());
		
		/**
		 * Carga el servicio en la lista de servicios y prueba el guardado y borrado de servicio
		 */
		
		servicios.add(s);
		servicioService.save(s);
		servicioService.delete(s);
		
		/**
		 * Comprobamos que funcionan los métodos del servicio
		 */
		
		assertEquals("Busca correctamente por ID", servicioService.findById(s.getId())) ;
		assertEquals("Busca correctamente por tipo", servicioService.findBysTipo(s.getsTipo())) ;
		assertEquals("Busca correctamente por crucero", servicioService.findByCruceros(c));
		
		/**
		 * Añadido y eliminación del servicio según el usuario
		 */
		
		servicioService.addServicioToUsuario(s, u, 1);
		servicioService.removeServicioFromUsuario(s, u);
		
	}
}
