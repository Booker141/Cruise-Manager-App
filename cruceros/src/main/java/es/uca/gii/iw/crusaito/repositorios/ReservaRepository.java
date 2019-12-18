package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.*;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	List<Reserva> findByrUsuario(Usuario usuario);
	List<Reserva> findByrCrucero(Crucero crucero);
    Reserva findById(long id);
}
