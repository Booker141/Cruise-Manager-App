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
	
	public Barco findById(int id) {
		return repo.findById(id);
	}
	
	public Barco findBybNombre(String bNombre) {
		return repo.findBybNombre(bNombre);
	}
	
	public Barco save(Barco barco) {
		return repo.save(barco);
	}
	

	public List<Barco> load(){
		//List<Barco> listaBarcos = repo.findAll();
		return repo.findAll();
	}

	public void delete (Barco data) {
        repo.delete(data);
    }
}
