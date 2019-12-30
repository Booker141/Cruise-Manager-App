package es.uca.gii.iw.crusaito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Long>{
	Servicio findById(long id);
}
