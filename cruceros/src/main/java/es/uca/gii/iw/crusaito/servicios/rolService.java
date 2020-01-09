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
	
	/**
	 * Método para buscar un rol a través de su nombre
	 * 
	 * @param name - name define el nombre del rol.
	 * @return el rol cuyo nombre haya sido pasado por parámetro.
	 */
	
	public Rol findByName(String name) {
		return repo.findByName(name);
	}
	
	/**
	 * Método para buscar todos los roles
	 * 
	 * @return todos los roles en forma de lista.
	 */
	
	public List<Rol> findAll(){
		return repo.findAll();
	}
}
