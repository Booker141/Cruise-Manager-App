package es.uca.gii.iw.crusaito.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import es.uca.gii.iw.crusaito.repositorios.BarcoRepository;

public class BarcoService {
	
	private BarcoRepository repo;
	@Autowired
	public BarcoService(BarcoRepository repo){
		this.repo=repo;
	}
}
