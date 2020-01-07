package es.uca.gii.iw.crusaito.clases;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class ServicioTest {

	@SuppressWarnings("deprecation")
	@Test
    public void Test(){
		
        Servicio s = new Servicio("Nadar","piscina", 10.0, ServicioTipo.Excursion, 
        "frontend/img/golf.jpg", 30, 40, LocalDate.now());

        assertEquals("Nadar", s.getsNombre());
        assertEquals("piscina", s.getsDescripcion());
        assertEquals(10.0, s.getsPrecio());
        assertEquals(ServicioTipo.Excursion, s.getsTipo());
        assertEquals(30, s.getsAforoActual());
        assertEquals(40, s.getsAforoMaximo());
        
    }
	
}
