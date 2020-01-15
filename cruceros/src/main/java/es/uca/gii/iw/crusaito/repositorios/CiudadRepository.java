package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad,Long>{

	List<Ciudad> findAll();
	Ciudad findById(int id);
	Ciudad findBycNombre(String cNombre);

}
