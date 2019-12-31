package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.repositorios.ServicioRepository;

@Service
public class ServicioService {

	private ServicioRepository repo;
	
	@Autowired
	public ServicioService(ServicioRepository repo) {
		this.repo = repo;
	}
	
	public Servicio findById(long id) {
		return this.repo.findById(id);
	}
	
	public Servicio save(Servicio servicio) {
		return this.repo.save(servicio);
	}
	
	public void delete(Servicio servicio) {
		this.repo.delete(servicio);
	}
	
	public List<Servicio> load(){
		return this.repo.findAll();
	}
}
