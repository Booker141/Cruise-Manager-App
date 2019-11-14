package es.uca.gii.iw.crusaito.spring;

import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepositoryExtension<Usuario, Long> {

	Usuario findByUsername(String username);
	//Usuario findByEmailIgnoreCase(String email);
	//List<Usuario> findByDni(String dni);

}
