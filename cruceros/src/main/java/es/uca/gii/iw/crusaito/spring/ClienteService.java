package es.uca.gii.iw.crusaito.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Cliente;
import es.uca.gii.iw.crusaito.spring.ClienteRepository;

@Service
public class ClienteService implements UserDetailsService{
	
	
	private ClienteRepository repo;
	private PasswordEncoder passwordEncoder;

	public ClienteService(ClienteRepository repo, PasswordEncoder passwordEncoder) {
		
		super();
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}

	
	public Cliente loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

}