package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.repositorios.CruceroRepository;
import es.uca.gii.iw.crusaito.repositorios.ServicioRepository;
import es.uca.gii.iw.crusaito.repositorios.UsuarioRepository;

@Service
public class ServicioService {

	private ServicioRepository repo;
	private UsuarioRepository userRepo;
	private CruceroRepository cruceroRepo;
	
	@Autowired
	public ServicioService(ServicioRepository repo, UsuarioRepository userRepo, CruceroRepository cruceroRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
		this.cruceroRepo = cruceroRepo;
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
	
	public List<Servicio> findByUsername(String username){
		Usuario usuario = this.userRepo.findByUsername(username);
		return this.repo.findByUsuarios(usuario);
	}
	
	public List<Servicio> findCruceroByUsername(String username){
		Usuario usuario = this.userRepo.findByUsername(username);
		Crucero crucero = this.cruceroRepo.findByUsuarios(usuario);
		return this.repo.findByCruceros(crucero);
	}
	
	public void addServicioToUsuario(Servicio servicio, Usuario usuario) {
		servicio.addUsuario(usuario);
		this.repo.save(servicio);
	}
	
	public void removeServicioFromUsuario(Servicio servicio, Usuario usuario) {
		servicio.removeUsuario(usuario);
		this.repo.save(servicio);
	}
}
