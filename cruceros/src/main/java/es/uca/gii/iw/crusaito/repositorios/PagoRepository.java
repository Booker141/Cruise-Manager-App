package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.gii.iw.crusaito.clases.Pago;
import es.uca.gii.iw.crusaito.clases.Reserva;

public interface PagoRepository extends JpaRepository<Pago, Long> {

	Pago findById(int id);
	List<Pago> findByReserva(Reserva reserva);

}
