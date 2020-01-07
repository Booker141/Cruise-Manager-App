package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.clases.CiudadCrucero;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.repositorios.CiudadCruceroRepository;
import es.uca.gii.iw.crusaito.repositorios.ServicioUsuarioRepository;

@Service
public class CiudadCruceroService {

	private CiudadCruceroRepository repo;
	
	@Autowired
	public CiudadCruceroService(CiudadCruceroRepository repo) {
		this.repo = repo;
	}
	
	public CiudadCrucero save(CiudadCrucero ciudadCrucero) {
		return this.repo.save(ciudadCrucero);
	}
	
	public void delete(CiudadCrucero ciudadCrucero) {
		this.repo.delete(ciudadCrucero);
	}
	
	public List<CiudadCrucero> load(){
		return this.repo.findAll();
	}
	
	public CiudadCrucero findByCiudadAndCrucero(Ciudad ciudad,Crucero crucero){
		return this.repo.findByCiudadAndCrucero(ciudad, crucero);
	}
	
	public List<CiudadCrucero> findByCiudad(Ciudad ciudad){
		return this.repo.findByCiudad(ciudad);
	}
	
	public List<CiudadCrucero> findByServicio(Crucero crucero){
		return this.repo.findByCrucero(crucero);
	}
}
