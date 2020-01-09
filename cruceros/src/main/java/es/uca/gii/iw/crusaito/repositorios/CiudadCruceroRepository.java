package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.clases.CiudadCrucero;
import es.uca.gii.iw.crusaito.clases.Crucero;

@Repository
public interface CiudadCruceroRepository extends JpaRepository<CiudadCrucero,Long>{

	List<CiudadCrucero> findAll();
	CiudadCrucero findByCiudadAndCrucero(Ciudad ciudad, Crucero crucero);
	List<CiudadCrucero> findByCiudad(Ciudad ciudad);
	List<CiudadCrucero> findByCrucero(Crucero crucero);

}