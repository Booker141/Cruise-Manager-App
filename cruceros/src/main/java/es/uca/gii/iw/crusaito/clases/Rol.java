package es.uca.gii.iw.crusaito.clases;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rol {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@OneToMany
	private List<Usuario> idUsuarios;
	
	public Rol() {}
	
	public Rol(String name) {
		this.name=name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}