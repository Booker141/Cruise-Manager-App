package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.repositorios.CruceroRepository;
import es.uca.gii.iw.crusaito.repositorios.ServicioRepository;
import es.uca.gii.iw.crusaito.repositorios.ServicioUsuarioRepository;
import es.uca.gii.iw.crusaito.repositorios.UsuarioRepository;
import es.uca.gii.iw.crusaito.common.*;

@Service
public class ServicioService {

	private ServicioRepository repo;
	private UsuarioRepository userRepo;
	private CruceroRepository cruceroRepo;
	private ServicioUsuarioRepository servUserRepo;
	
	@Autowired
	public ServicioService(ServicioRepository repo, UsuarioRepository userRepo,
			ServicioUsuarioRepository servUserRepo ,CruceroRepository cruceroRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
		this.cruceroRepo = cruceroRepo;
		this.servUserRepo = servUserRepo;
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
	
	/*public List<Servicio> findByUsername(String username){
		Usuario usuario = this.userRepo.findByUsername(username);
		return this.repo.findByUsuario(usuario);
	}*/
	
	public List<Servicio> findCruceroByUsername(String username){
		Usuario usuario = this.userRepo.findByUsername(username);
		Crucero crucero = this.cruceroRepo.findByUsuarios(usuario);
		return this.repo.findByCruceros(crucero);
	}
	public void addServicioToUsuario(Servicio servicio, Usuario usuario, int participantes) {
		try {
		ServicioUsuario servUser = new ServicioUsuario();
		servUser.setServicio(servicio);
		servUser.setUsuario(usuario);
		servUser.setParticipantes(participantes);
		
		servicio.getServiciosUsuarios().add(servUser);
		usuario.getUsuariosServicios().add(servUser);
		
		this.userRepo.save(usuario);
		this.repo.save(servicio);
		}catch(Exception e) {
			Funciones.notificacionError("Error al realizar la reserva");
		}
	}
	public void removeServicioFromUsuario(Servicio servicio, Usuario usuario) {
		try {
		ServicioUsuario servUser = this.servUserRepo.findByServicioAndUsuario(servicio, usuario);
		
		servicio.getServiciosUsuarios().remove(servUser);
		servicio.removeAforoActual(servUser.getParticipantes());
		usuario.getUsuariosServicios().remove(servUser);
		
		this.userRepo.save(usuario);
		this.repo.save(servicio);
		this.servUserRepo.delete(servUser);
		}catch(Exception e) {
			Funciones.notificacionError("Error al cancelar la reserva");
		}
	}
	
}
