package es.uca.gii.iw.crusaito.servicios;

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
	
	public Barco findById(int id) {
		return repo.findById(id);
	}
	
	public Barco findByNombre(String bNombre) {
		return repo.findByNombre(bNombre);
	}
	
	public Barco save(Barco barco) {
		return repo.save(barco);
	}
	
	public void delete (Barco data) {
        repo.delete(data);
    }
}
