package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Usuario;
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
	
	public Crucero findBycNombre(String cNombre) {
		return repo.findBycNombre(cNombre);
	}
	
	public Crucero findByUsuarios(Usuario usuario) {
		return repo.findByUsuarios(usuario);
	}
	
	
	public Crucero save(Crucero crucero) {
		return repo.save(crucero);
	}

	public void delete (Crucero crucero) {
        repo.delete(crucero);
	}
  
	public List<Crucero> load(){
		return repo.findAll();
	}

}
