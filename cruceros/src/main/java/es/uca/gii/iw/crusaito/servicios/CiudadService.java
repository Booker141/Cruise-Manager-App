package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.repositorios.CiudadRepository;

@Service
public class CiudadService {

private CiudadRepository repo;
	
	@Autowired
	public CiudadService(CiudadRepository repo){
		this.repo=repo;
	}
	
	public Ciudad findById(int id) {
		return repo.findById(id);
	}
	
	public Ciudad findBycNombre(String cNombre) {
		return repo.findBycNombre(cNombre);
	}
	
	public Ciudad save(Ciudad ciudad) {
		return repo.save(ciudad);
	}
	

	public List<Ciudad> load(){
		//List<Ciudad> listaCiudad = repo.findAll();
		return repo.findAll();
	}

	public void delete(Ciudad data) {
        repo.delete(data);
    }
}
