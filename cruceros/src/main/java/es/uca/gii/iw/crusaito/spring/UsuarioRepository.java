package es.uca.gii.iw.crusaito.spring;

import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import es.uca.gii.iw.crusaito.clases.Usuario;

public class UsuarioRepository extends JpaRepositoryExtension<Usuario, Long> {
	Usuario findById(int id) {
		
	}
	Usuario findByUsername(String username) {
		
	}
}
