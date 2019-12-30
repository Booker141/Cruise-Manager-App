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

	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
	
	public Usuario findByUsername(String s) {
		return repo.findByUsername(s);
	}
	
	public Usuario findByEmail(String s) {
		return repo.findByEmail(s);
	}
	
	public List<Usuario> findByDni(String dni){
		return repo.findByDni(dni);
	}
	
	public Usuario save(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEnabled(true);
		return repo.save(usuario);
	}
	
	public void delete(Usuario usuario) {
		repo.delete(usuario);
	}
	public List<Usuario> load() {
		return repo.findAll();
	}

}