package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.*;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	List<Reserva> findByUsuario(Usuario usuario);
	List<Reserva> findByBarco(Barco barco);
    Optional<Reserva> findById(Long id);
}
