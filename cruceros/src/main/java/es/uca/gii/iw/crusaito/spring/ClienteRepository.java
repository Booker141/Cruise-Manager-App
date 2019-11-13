package es.uca.gii.iw.crusaito.spring;

import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import es.uca.gii.iw.crusaito.clases.Cliente;

public class ClienteRepository extends JpaRepositoryExtension<Cliente, Long> {
	Cliente findById(int id);
	Cliente findByUsername(String username);
}
