package es.uca.gii.iw.crusaito.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Usuario;

@Repository
public interface CruceroRepository extends JpaRepository<Crucero,Long>{
	
	Crucero findById(int id);
	Crucero findBycNombre(String cNombre);
	Crucero findByUsuarios(Usuario usuario);
	Crucero save(Crucero crucero);
	void delete(Crucero crucero);

}
