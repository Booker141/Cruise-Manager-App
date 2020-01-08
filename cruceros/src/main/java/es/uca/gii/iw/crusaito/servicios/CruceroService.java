package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	/**
	 * Método para buscar un crucero a través de su número de identificación
	 * 
	 * @param id - id define el numero de identificación del crucero.
	 * @return el crucero cuyo id es el pasado como parámetro.
	 */
	
	public Crucero findById(int id) {
		return repo.findById(id);
	}
	
	/**
	 * Método para buscar un crucero a través de su nombre
	 * 
	 * @param cNombre - cNombre define el nombre del crucero.
	 * @return el crucero cuyo nombre es el pasado como parámetro.
	 */
	
	public Crucero findBycNombre(String cNombre) {
		return repo.findBycNombre(cNombre);
	}
	
	/**
	 * Método para buscar un crucero a través de un usuario 
	 * 
	 * @param usuario - usuario define el usuario que haya reservado dicho crucero.
	 * @return el crucero cuyo usuario pasado como parámetro haya reservado.
	 */
	
	public Crucero findByUsuarios(Usuario usuario) {
		return repo.findByUsuarios(usuario);
	}
	
	
	public Crucero save(Crucero crucero) {
		return repo.save(crucero);
	}

	public void delete (Crucero crucero) {
        repo.delete(crucero);
	}
  
	/**
	 * Método para buscar todos los cruceros
	 * 
	 * @return todos los cruceros en forma de lista.
	 */
	
	public List<Crucero> load(){
		return repo.findAll();
	}

}
