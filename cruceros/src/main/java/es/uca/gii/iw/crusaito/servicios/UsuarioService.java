package es.uca.gii.iw.crusaito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.repositorios.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	private UsuarioRepository repo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Método para cargar un usuario a través de nombre de usuario
	 * 
	 * @param username - username define el nombre de usuario.
	 * @return devuelve el usuario con el nombre de usuario pasado como parámetro.
	 */
	
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
	
	/**
	 * Método para buscar un usuario a través de nombre de usuario
	 * 
	 * @param s - s define el nombre de usuario.
	 * @return devuelve el usuario cuyo nombre de usuario haya sido pasado como parámetro.
	 */
	
	public Usuario findByUsername(String s) {
		return repo.findByUsername(s);
	}
	
	/**
	 * Método para buscar un usuario a través de su correo electrónico
	 * 
	 * @param s - s define el correo electrónico del usuario.
	 * @return devuelve el usuario cuyo correo electrónico haya sido pasado como parámetro.
	 */
	
	public Usuario findByEmail(String s) {
		return repo.findByEmail(s);
	}
	
	/**
	 * Método para buscar un usuario a través de su DNI
	 * 
	 * @param dni - dni define el DNI del usuario.
	 * @return devuelve el usuario cuyo DNI haya sido pasado como parámetro.
	 */
	
	public List<Usuario> findByDni(String dni){
		return repo.findByDni(dni);
	}
	
	public Usuario save(Usuario usuario) {
		if(!usuario.ispEncoded()) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuario.setpEncoded(true);
		}
		usuario.setEnabled(true);
		return repo.save(usuario);
	}
	
	public void delete(Usuario usuario) {
		repo.delete(usuario);
	}
	
	/**
	 * Método para buscar todos los usuarios
	 * 
	 * @return devuelve todos los usuarios en forma de lista.
	 */
	
	public List<Usuario> load() {
		return repo.findAll();
	}

}