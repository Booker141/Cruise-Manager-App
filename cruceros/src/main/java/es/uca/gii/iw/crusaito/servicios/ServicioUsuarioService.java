package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.repositorios.ServicioUsuarioRepository;

@Service
public class ServicioUsuarioService {
	
	private ServicioUsuarioRepository repo;
	
	@Autowired
	public ServicioUsuarioService(ServicioUsuarioRepository repo) {
		this.repo = repo;
	}
	
	public ServicioUsuario save(ServicioUsuario servicioUsuario) {
		return this.repo.save(servicioUsuario);
	}
	
	public void delete(ServicioUsuario servicioUsuario) {
		this.repo.delete(servicioUsuario);
	}
	
	public List<ServicioUsuario> findAll(){
		return this.repo.findAll();
	}
	
	/**
	 * Método para buscar todos los servicios de un usuario
	 * 
	 * @return todos los servicios de un usuario en forma de lista.
	 */
	
	public List<ServicioUsuario> load(){
		return this.repo.findAll();
	}
	
	/**
	 * Método para buscar los servicios reservados por un usuario
	 * 
	 * @param usuario - usuario define el usuario que ha reservado dichos servicios.
	 * @return todos los servicios reservados por dicho usuario en forma de lista.
	 */
	
	public List<ServicioUsuario> findByUsuario(Usuario usuario){
		return this.repo.findByUsuario(usuario);
	}
	
	public List<ServicioUsuario> findByServicio(Servicio servicio){
		return this.repo.findByServicio(servicio);
	}
}
