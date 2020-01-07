package es.uca.gii.iw.crusaito.servicios;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.clases.Usuario;

public class UsuarioServiceTest {
	
	private UsuarioService usuarioService ;
	
	@Test
	public void Test (){
		
		Rol r = new Rol("Cliente");
		Usuario u = new Usuario("Carlos","Perez", "carlos@gmail.com", "carlos", 
		        "carlos", "12345678T", 435678909, LocalDate.now(), "Calle Perez", "Cadiz", r);
	
		/**
		 * Encuentra todos los usuarios
		 */
		
		List<Usuario> usuarios = usuarioService.load();
		
		/**
		 * Carga el usuario en la lista de usuarios y prueba el guardado de usuario
		 */
		
		usuarios.add(u);
		usuarioService.save(u);
		
		/**
		 * Comprobamos que funcionan los métodos del servicio
		 */
		
		assertEquals("Busca correctamente por DNI", usuarioService.findByDni(u.getDni()));
		assertEquals("Busca correctamente por nombre de usuario", usuarioService.findByUsername(u.getUsername()));
		assertEquals("Busca correctamente por email", usuarioService.findByEmail(u.getEmail()));
		assertEquals("Carga correctamente el usuario", usuarioService.loadUserByUsername(u.getUsername()));
		
		/**
		 * Eliminación del usuario
		 */
		
		usuarioService.delete(u);
	}
}
