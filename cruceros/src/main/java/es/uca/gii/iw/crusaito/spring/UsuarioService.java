package es.uca.gii.iw.crusaito.spring;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Usuario;

@Service
public class UsuarioService implements UserDetailsService{
	
	
	private UsuarioRepository repo;
	private PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
		
		super();
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

}