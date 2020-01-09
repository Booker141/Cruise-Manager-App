package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.repositorios.BarcoRepository;

@Service
public class BarcoService {
	
	private BarcoRepository repo;

	@Autowired
	public BarcoService(BarcoRepository repo){
		this.repo=repo;
	}
	
	/**
	 * Método para buscar un barco a través de su id
	 * 
	 * @param id - id define el número de identificación del barco.
	 * @returnel barco con dicha id.
	 */
	
	public Barco findById(int id) {
		return repo.findById(id);
	}

	/**
	 * Método para buscar un barco a través de su nombre
	 * 
	 * @param bNombre - bNombre define el nombre del barco a buscar.
	 * @return el barco con dicho nombre.
	 */
	
	public Barco findBybNombre(String bNombre) {
		return repo.findBybNombre(bNombre);
	}
	
	public Barco save(Barco barco) {
		return repo.save(barco);
	}
	
	/**
	 * Método para buscar todos los barcos
	 * 
	 * @return todos los barcos en forma de lista.
	 */
	
	public List<Barco> load(){
		return repo.findAll();
	}
	
	public List<Barco> findBybNombreLike(String bNombre) {
		return repo.findBybNombreLike(bNombre);
	}

	public void delete (Barco barco) {
        repo.delete(barco);
    }
	
	public List<Barco> findByCruceroIsNull(){
		return repo.findByCruceroIsNull();
	}

}
