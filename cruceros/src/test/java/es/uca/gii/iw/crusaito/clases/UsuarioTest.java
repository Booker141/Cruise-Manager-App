package es.uca.gii.iw.crusaito.clases;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class UsuarioTest {

	@Test
    public void Test(){
		
		Rol r = new Rol("Cliente");
        Usuario u = new Usuario("Carlos","Perez", "carlos@gmail.com", "carlos", 
        "carlos", "12345678T", 435678909, LocalDate.now(), "Calle Perez", "Cadiz", r);
        
        assertEquals("Carlos", u.getFirstName());
        assertEquals("Perez", u.getLastName());
        assertEquals("12345678T", u.getDni());
        assertEquals("Cadiz", u.getCity());
        assertEquals("Cliente", u.getRole());
    }
}
