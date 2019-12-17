package es.uca.gii.iw.crusaito.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.*;
import es.uca.gii.iw.crusaito.repositorios.ReservaRepository;



@Service
public class ReservaService {
	
	private ReservaRepository repositorio;
	
	@Autowired
    private ReservaService(ReservaRepository repositorio) {
        this.repositorio = repositorio;
    }
	
	public Reserva findById(long id) {
        return repositorio.findById(id);
    }
	
	public List<Reserva> listByUsuario(Usuario usuario) {
        return repositorio.findByrUsuario(usuario);
    }
	
	public List<Reserva> listByCrucero(Crucero crucero) {
        return repositorio.findByrCrucero(crucero);
    }
	
	public List<Reserva> findAll() {
        return repositorio.findAll();
    }

	
	public void delete (Reserva reserva) {
        repositorio.delete(reserva);
    }
	

	public Reserva save(Reserva reserva) {
		return repositorio.save(reserva);
	}


}
