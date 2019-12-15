package es.uca.gii.iw.crusaito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Crucero;

@Repository
public interface CruceroRepository extends JpaRepository<Barco,Long>{
	
	Crucero findById(int id);
	Crucero findByNombre(String cNombre);
	Crucero save(Crucero crucero);
	void delete(Crucero data);

}
