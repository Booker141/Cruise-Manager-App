package es.uca.gii.iw.crusaito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.gii.iw.crusaito.clases.Barco;

public interface BarcoRepository extends JpaRepository<Barco,Long>{
	Barco findById(int id);
	Barco findBybNombre(String bNombre);
}
