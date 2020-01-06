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
	
	public ServicioUsuario saveServicio(ServicioUsuario servicioUsuario) {
		return this.repo.save(servicioUsuario);
	}
	
	public void delete(ServicioUsuario servicioUsuario) {
		this.repo.delete(servicioUsuario);
	}
	
	public List<ServicioUsuario> findAll(){
		return this.repo.findAll();
	}
	
	public List<ServicioUsuario> load(){
		return this.repo.findAll();
	}
	
	public List<ServicioUsuario> findByUsuario(Usuario usuario){
		return this.repo.findByUsuario(usuario);
	}
	public List<ServicioUsuario> findByServicio(Servicio servicio){
		return this.repo.findByServicio(servicio);
	}
}
