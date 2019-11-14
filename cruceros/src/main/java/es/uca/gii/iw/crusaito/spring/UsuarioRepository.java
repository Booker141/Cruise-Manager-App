package es.uca.gii.iw.crusaito.spring;

import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Usuario;

<<<<<<< HEAD
@Repository
public interface UsuarioRepository extends JpaRepositoryExtension<Usuario, Long> {

	Usuario findByUsername(String username);
	//Usuario findByEmailIgnoreCase(String email);
	//List<Usuario> findByDni(String dni);

=======
public class UsuarioRepository extends JpaRepositoryExtension<Usuario, Long> {
	Usuario findById(int id) {
		
	}
	Usuario findByUsername(String username) {
		
	}
>>>>>>> 32b1064ee8f5408b7b73f246d2b780810ed4d611
}
