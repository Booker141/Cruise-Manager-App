package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uca.gii.iw.crusaito.clases.Camarote;

@Repository
public interface CamaroteRepository extends JpaRepository<Camarote,Long>{

	List<Camarote> findAll();
	Camarote findById(int id);
	Camarote findBycTipo(String cTipo);
}


}

