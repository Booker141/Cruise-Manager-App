package es.uca.gii.iw.crusaito.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;
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
	
	/**
	 * Método para buscar un servicio según su número de identificación
	 * 
	 * @param id - id define el número de identificación del servicio.
	 * @return devuelve el servicio cuya id haya sido pasada como parámetro.
	 */
	
	public Servicio findById(long id) {
		return this.repo.findById(id);
	}
	
	/**
	 * Método para buscar los servicios segú su tipo [Restaurante, Tienda, Excursión]
	 * 
	 * @param tipo - tipo define el tipo de los servicios a buscar.
	 * @return devuelve los servicios en forma de lista cuyo tipo haya sido pasado como parámetro .
	 */
	
	public List<Servicio> findBysTipo(ServicioTipo tipo){
		return this.repo.findBysTipo(tipo);
	}
	
	/**
	 * Método para buscar los servicios según el crucero
	 * 
	 * @param crucero - crucero define el crucero cuyos servicios queremos buscar.
	 * @return devuelve los servicios cuyo crucero haya sido pasado como parámetro.
	 */
	
	public List<Servicio> findByCruceros(Crucero crucero){
		return this.repo.findByCruceros(crucero);
	}
	

	public Servicio save(Servicio servicio) {
		return this.repo.save(servicio);
	}
	
	public void delete(Servicio servicio) {
		this.repo.delete(servicio);
	}
	
	/**
	 * Método para buscar todos los servicios
	 * 
	 * @return devuelve todos los servicios en forma de lista.
	 */
	
	public List<Servicio> findAll(){
		return this.repo.findAll();
	}
	
	/**
	 * Método para buscar todos los servicios
	 * 
	 * @return devuelve todos los servicios en forma de lista.
	 */
	
	public List<Servicio> load(){
		return this.repo.findAll();
	}
	
	/**

     * Método busca los servicios de un crucero a partir del nombre de usuario de un cliente dado.

     * @param username - username define la cadena que contiene el nombre de usuario del cliente.
     * @return Devuelve los servicios del crucero que buscamos a partir del nombre de usuario que lo ha reservado.

     */
	
	public List<Servicio> findCruceroByUsername(String username){
		Usuario usuario = this.userRepo.findByUsername(username);
		Crucero crucero = this.cruceroRepo.findByUsuarios(usuario);
		return this.repo.findByCruceros(crucero);
	}
	
	/**
	 * Métodos que asocia un servicio con el usuario que lo ha reservado
	 * 
	 * @param servicio - servicio define el servicio reservado por el usuario.
	 * @param usuario - usuario define el usuario que ha reservado el servicio.
	 * @param participantes - participantes define el número de usuarios que disfrutarán del servicio reservado.
	 */
	
	public void addServicioToUsuario(Servicio servicio, Usuario usuario, int participantes) {
		try {
			if(this.servUserRepo.findByServicioAndUsuario(servicio, usuario)==null) {
				ServicioUsuario servUser = new ServicioUsuario();
				servUser.setServicio(servicio);
				servUser.setUsuario(usuario);
				servUser.setParticipantes(participantes);
				
				//Regla de negocio número 1, descuento del 25% si la persona que reserva es mayor de 55 años
				if(Period.between(usuario.getBornDate(), LocalDate.now()).getYears() > 55) {
					servUser.setPrecio((((double)participantes) * servicio.getsPrecio()) * 0.75);
				}else {
					//Regla de negocio número 2, descuento del 20% si la reserva es para más de 2 participantes
					if(participantes>2) {
						servUser.setPrecio((((double)participantes) * servicio.getsPrecio()) * 0.80);
					}else {
						servUser.setPrecio(((double)participantes)*servicio.getsPrecio());
					}
				}
				
				servicio.getServiciosUsuarios().add(servUser);
				servicio.addAforoActual(participantes);
				usuario.getUsuariosServicios().add(servUser);
				
				this.repo.save(servicio);
				this.userRepo.save(usuario);
				this.servUserRepo.save(servUser);
        		Funciones.notificacionAcierto("Reserva realizada correctamente.");

			} else {
				throw new DataIntegrityViolationException("Ya tiene una reserva");
			}
			
		}catch(DataIntegrityViolationException error) {
    		Funciones.notificacionError(error.getMessage());
		}catch(Exception e) {
			Funciones.notificacionError(e.getMessage());
		}
	}
	
	/**
	 * Método que elimina la reserva de un servicio realizada por un usuario
	 * 
	 * @param servicio - servicio define el servicio reservado por el usuario.
	 * @param usuario - usuario define el usuario que ha reservado el servicio.
	 */
	
	public void removeServicioFromUsuario(Servicio servicio, Usuario usuario) {
		
		ServicioUsuario servUser = this.servUserRepo.findByServicioAndUsuario(servicio, usuario);
		
		servicio.getServiciosUsuarios().remove(servUser);
		servicio.removeAforoActual(servUser.getParticipantes());
		usuario.getUsuariosServicios().remove(servUser);
		
		this.userRepo.save(usuario);
		this.repo.save(servicio);
		this.servUserRepo.delete(servUser);
		
	}
	
}
