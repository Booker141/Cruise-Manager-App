package es.uca.gii.iw.crusaito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Barco;

@Repository
public interface BarcoRepository extends JpaRepository<Barco,Long>{
	Barco findById(int id);
	Barco findBybNombre(String bNombre);
}
