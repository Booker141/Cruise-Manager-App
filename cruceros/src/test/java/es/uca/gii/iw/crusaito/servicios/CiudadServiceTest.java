package es.uca.gii.iw.crusaito.servicios;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uca.gii.iw.crusaito.clases.Ciudad;


public class CiudadServiceTest {

	private CiudadService ciudadService;
	
	@Test
	public void Test (){

		Ciudad c = new Ciudad("Cádiz", "De Cádiz, el mar, su gente, su bahía, su historia y sus ganas de vivir.\n" + 
        		"\n" + 
        		"La provincia de Cádiz es muy diversa, con comarcas que merecen ser visitadas, desde la campiña de Jerez de la Frontera a sus pueblos del Campo de Gibraltar o un recorrido por los pueblos blancos y descansar en algún lugar de la costa entre Tarifa hasta Sanlúcar de Barrameda.\n" + 
        		"\n" + 
        		"Todo ello sin olvidar su capital, que guarda en sus murallas la cultura y las tradiciones que la hacen distinta, como a sus gentes.\n" + 
        		"\n" + 
        		"No lo dudes, Cádiz es el destino ideal para tus vacaciones.");

		/**
		 * Encuentra todos las ciudades
		 */
		
		List<Ciudad> ciudades = ciudadService.load();
		
		/**
		 * Carga la ciudad en la lista de ciudades y prueba el guardado y borrado de una ciudad
		 */
		
		ciudades.add(c);
		ciudadService.save(c);
		ciudadService.delete(c);
		
		/**
		 * Comprobamos que funcionan los métodos del servicio
		 */
		
		assertEquals("Busca correctamente por nombre", ciudadService.findBycNombre(c.getcNombre())) ;
		

		
	}
}
