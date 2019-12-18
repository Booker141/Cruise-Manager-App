package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.uca.gii.iw.crusaito.clases.Tarjeta;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.repositorios.TarjetaRepository;

@Service
public class TarjetaService {
	
	private TarjetaRepository repo;
	
	@Autowired
	public TarjetaService(TarjetaRepository repo){
		this.repo=repo;
	}
	
	public Tarjeta findById(int id) {
		return repo.findById(id);
	}
	
	public Tarjeta findBytTipo(String tTipo) {
		return repo.findBytTipo(tTipo);
	}
	
	public Tarjeta findBytNumeroTarjeta(String tNumeroTarjeta) {
		return repo.findBytTipo(tNumeroTarjeta);
	}
	
	public List<Tarjeta> findByUsuario(Usuario usuario){
		return repo.findByUsuario(usuario);
	}
	
	public Tarjeta save(Tarjeta tarjeta) {
		return repo.save(tarjeta);
	}
	
	public List<Tarjeta> load(){
		return repo.findAll();
	}

	public void delete (Tarjeta tarjeta) {
        repo.delete(tarjeta);
    }
}
