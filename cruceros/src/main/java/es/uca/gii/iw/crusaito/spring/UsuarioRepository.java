package es.uca.gii.iw.crusaito.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uca.gii.iw.crusaito.clases.Usuario;
//import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

//Sustituido JpaRepositoryExtension por JpaRepository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findById(int id);
	Usuario findByUsername(String username);
}
