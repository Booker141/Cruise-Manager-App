package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Camarote;
import es.uca.gii.iw.crusaito.repositorios.CamaroteRepository;

@Service
public class CamaroteService{
	
private CamaroteRepository repo;
	
	@Autowired
	public CamaroteService(CamaroteRepository repo){
		this.repo=repo;
	}
	
	public Camarote findById(int id) {
		return repo.findById(id);
	}
	
	public Camarote findByTipo(String tipo) {
		return repo.findByTipo(tipo);
	}
	
	public Camarote save(Camarote camarote) {
		return repo.save(camarote);
	}
	

	public List<Camarote> load(){
		//List<Camarote> listaCamarotes = repo.findAll();
		return repo.findAll();
	}

	public void delete (Camarote data) {
        repo.delete(data);
    }

}
