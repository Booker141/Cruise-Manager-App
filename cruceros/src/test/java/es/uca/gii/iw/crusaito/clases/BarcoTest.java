package es.uca.gii.iw.crusaito.clases;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class BarcoTest {

	@Test
    public void Test(){
		
        Barco b = new Barco("Titania","frontend/img/barcoTest.jpg",1000, 600, 
        4000, LocalDate.now(), "Barco para test de clase", "frontend/img/planoTest.jpg");

        assertEquals("Titania", b.getbNombre());
        assertEquals(1000, b.getbAforoPasajeros());
        assertEquals(600, b.getbAforoTripulantes());
        assertEquals(4000, b.getbPeso());
        assertEquals("Barco para test de clase", b.getbDescripcion());
	
 }
}