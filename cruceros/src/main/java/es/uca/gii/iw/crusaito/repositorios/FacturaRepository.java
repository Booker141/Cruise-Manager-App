package es.uca.gii.iw.crusaito.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Factura;
import es.uca.gii.iw.crusaito.clases.Pago;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
	
	Factura findById(int id);
	Factura findByfFechaFactura(LocalDate fFechaFactura);
	List<Factura> findByPago(Pago pago);
	
}
