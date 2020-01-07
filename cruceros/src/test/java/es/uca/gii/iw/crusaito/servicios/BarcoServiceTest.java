package es.uca.gii.iw.crusaito.servicios;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.clases.Usuario;

public class BarcoServiceTest {
	
	private BarcoService barcoService ;
	
	@Test
	public void Test (){
		
		 Barco b = new Barco("Titania","frontend/img/barcoTest.jpg",1000, 600, 
			        4000, LocalDate.now(), "Barco para test de clase", "frontend/img/planoTest.jpg");
		 
		/**
		 * Encuentra todos los barcos
		 */
		
		List<Barco> barcos1 = barcoService.load();
		List<Barco> barcos2 = barcoService.findBybNombreLike(b.getbNombre());
		List<Barco> barcos3 = barcoService.findByCruceroIsNull();
		
		/**
		 * Carga el barco en las listas de barcos y prueba el guardado del barco
		 */
		
		barcos1.add(b);
		barcos2.add(b);
		barcoService.save(b);
		
		/**
		 * Comprobamos que funcionan los métodos del servicio
		 */
		
		assertEquals("Busca correctamente por nombre", barcoService.findBybNombre(b.getbNombre()));

		
		/**
		 * Eliminación del barco
		 */
		
		barcoService.delete(b);
	}
}
