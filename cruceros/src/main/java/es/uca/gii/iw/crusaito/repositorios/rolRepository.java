package es.uca.gii.iw.crusaito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Rol;

@Repository
public interface rolRepository extends JpaRepository<Rol,Long>{
	Rol findById(long id);
	Rol findByName(String name);
}
