package es.uca.gii.iw.crusaito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.gii.iw.crusaito.clases.Tarjeta;
import es.uca.gii.iw.crusaito.clases.Usuario;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

	Tarjeta findById(int id);
	Tarjeta findBytTipo(String tTipo);
	Tarjeta findbytNumeroTarjeta(String tNumeroTarjeta);
	List<Tarjeta> findByUsuario(Usuario usuario);
	
}
