package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Long>{
	List<Servicio> findAll();	
	Servicio findById(long id);
	List<Servicio> findByCruceros(Crucero crucero);
	List<Servicio> findBysTipo(ServicioTipo tipo);
}
