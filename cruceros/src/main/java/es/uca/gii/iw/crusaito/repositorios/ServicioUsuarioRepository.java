package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;

@Repository
public interface ServicioUsuarioRepository extends JpaRepository<ServicioUsuario, Long> {
	List<ServicioUsuario> findAll();
	ServicioUsuario findByServicioAndUsuario(Servicio servicio, Usuario usuario);
	List<ServicioUsuario> findByUsuario(Usuario usuario);
	List<ServicioUsuario> findByServicio(Servicio servicio);
}
