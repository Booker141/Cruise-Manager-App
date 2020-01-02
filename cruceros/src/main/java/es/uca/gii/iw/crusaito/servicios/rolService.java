package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.repositorios.rolRepository;

@Service
public class rolService {

	private rolRepository repo;

	@Autowired
	public rolService(rolRepository repo){
		this.repo=repo;
	}
	
	public Rol save(Rol role) {
		return repo.save(role);
	}
	
	public Rol findByName(String name) {
		return repo.findByName(name);
	}
	
	public List<Rol> findAll(){
		return repo.findAll();
	}
}
