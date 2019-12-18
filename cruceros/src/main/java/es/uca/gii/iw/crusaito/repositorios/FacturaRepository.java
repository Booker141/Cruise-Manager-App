package es.uca.gii.iw.crusaito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
