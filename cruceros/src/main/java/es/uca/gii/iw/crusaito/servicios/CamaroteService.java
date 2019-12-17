package es.uca.gii.iw.crusaito.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Camarote;
import es.uca.gii.iw.crusaito.repositorios.CamaroteRepository;

@Service
public class CamaroteService {

	private CamaroteRepository repo;
	
	@Autowired
	public CamaroteService(CamaroteRepository repo) {
		this.repo = repo;
	}
	
	public Camarote save(Camarote camarote) {
		return repo.save(camarote);
	}
	
	public void delete(Camarote camarote) {
		repo.delete(camarote);
	}
}
