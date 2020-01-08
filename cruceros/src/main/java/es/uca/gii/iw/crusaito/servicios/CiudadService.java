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
	public CiudadService(CiudadRepository repo) {
		this.repo = repo;
	}
	
	/**
	 * Método para buscar una ciudad a través de su número de identificación
	 * 
	 * @param id - id define el numero de identificación de la ciudad a buscar.
	 * @return la ciudad cuyo id es el pasado como parámetro.
	 */
	
	public Ciudad findById(int id) {
		return repo.findById(id);
	}
	
	/**
	 * Método para buscar una ciudad a través de su nombre
	 * 
	 * @param cNombre - cNombre define el nombre de la ciudad a buscar.
	 * @return la ciudad cuyo nombre es el pasado como parámetro.
	 */
	
	public Ciudad findBycNombre(String cNombre) {
		return repo.findBycNombre(cNombre);
	}
	
	public Ciudad save(Ciudad ciudad) {
		return repo.save(ciudad);
	}
	
	/**
	 * Método para buscar todas las ciudades
	 * 
	 * @return todas las ciudades en forma de lista.
	 */
	
	public List<Ciudad> load(){
		return repo.findAll();
	}
	
	/*public List<Ciudad> findByCruceros(Crucero crucero){
		return repo.findByCruceros(crucero);
	}*/
	
	public void delete(Ciudad ciudad) {
        repo.delete(ciudad);
  }

}
