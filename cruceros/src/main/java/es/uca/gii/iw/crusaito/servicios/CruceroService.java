package es.uca.gii.iw.crusaito.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.repositorios.CruceroRepository;

@Service
public class CruceroService {
	
private CruceroRepository repo;
	
	@Autowired
	public CruceroService(CruceroRepository repo){
		this.repo=repo;
	}
	
	public Crucero findById(int id) {
		return repo.findById(id);
	}
	
	public Crucero findByNombre(String cNombre) {
		return repo.findByNombre(cNombre);
	}
	
	public Crucero save(Crucero crucero) {
		return repo.save(crucero);
	}
	
	public void delete (Crucero data) {
        repo.delete(data);
    }

}
