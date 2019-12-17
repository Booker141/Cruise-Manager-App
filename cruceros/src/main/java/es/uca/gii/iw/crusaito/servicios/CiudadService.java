package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.repositorios.CiudadRepository;

@Service
public class CiudadService {

	private CiudadRepository repo;
	
	@Autowired
	public CiudadService(CiudadRepository repo) {
		this.repo = repo;
	}
	
	public Ciudad save(Ciudad ciudad) {
		return repo.save(ciudad);
	}
	
	public List<Ciudad> load(){
		return repo.findAll();
	}
	
	public void delete(Ciudad ciudad) {
        repo.delete(ciudad);
    }
}
