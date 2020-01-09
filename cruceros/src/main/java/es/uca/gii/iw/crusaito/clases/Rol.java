package es.uca.gii.iw.crusaito.clases;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<Usuario> idUsuarios;
	
	public Rol() {}
	
	/**
	 * Constructor de la entidad Rol
	 * 
	 * @param name - name define el nombre del rol [Cliente, Admin, Gerente]
	 */
	
	public Rol(String name) {
		this.name=name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
}