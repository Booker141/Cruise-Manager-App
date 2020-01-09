package es.uca.gii.iw.crusaito.servicios;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.clases.Usuario;

public class CruceroServiceTest {
	
	private CruceroService cruceroService ;
	
	@Test
	public void Test (){
		
		Rol r = new Rol("Cliente");
        Usuario u = new Usuario("Carlos","Perez", "carlos@gmail.com", "carlos", 
        "carlos", "12345678T", 435678909, LocalDate.now(), "Calle Perez", "Cadiz", r);
        
		Crucero c = new Crucero("Mar Negro","Cadiz", "Portugal", "5 dias", "Muy buen crucero", 50.0);
		
		/**
		 * Encuentra todos los cruceros
		 */
		
		List<Crucero> cruceros = cruceroService.load();
		
		/**
		 * Carga el crucero en la lista de cruceros y prueba el guardado del crucero
		 */
		
		cruceros.add(c);
		cruceroService.save(c);
		
		/**
		 * Comprobamos que funcionan los métodos del servicio
		 */
		
		assertEquals("Busca correctamente por nombre", cruceroService.findBycNombre(c.getcNombre()));
		assertEquals("Busca correctamente por usuario", cruceroService.findByUsuarios(u));

		
		/**
		 * Eliminación del crucero
		 */
		
		cruceroService.delete(c);
	}
}
