package es.uca.gii.iw.crusaito.clases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CruceroTest {

	@SuppressWarnings("deprecation")
	@Test
    public void Test(){
		
        Crucero c = new Crucero("Mar Negro","Cadiz", "Portugal", "5 dias", 
        "Muy buen crucero", 50.0);

        assertEquals("Mar Negro", c.getcNombre());
        assertEquals("Cadiz", c.getcOrigen());
        assertEquals("Portugal", c.getcDestino());
        assertEquals("5 dias", c.getcDuracion());
        assertEquals("Muy buen crucero", c.getcDescripcion());
        assert(50.0 == c.getcPrecio());
    }
}
