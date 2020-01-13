package es.uca.gii.iw.crusaito.clases;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Ciudad {

	@Id
	@GeneratedValue
	private long id;
	private String cNombre;
	@Size(max = 10000)
	private String cDescripcion;

	@OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
	private Set<CiudadCrucero> ciudadesCruceros;
	
	@OneToMany(mappedBy = "ciudad")
	private Set<Servicio> servicios;
	
	/**
	 * Constructor de clase
	 */
	public Ciudad() {}
	
	/**
	 * Constructor de la entidad Ciudad
	 * 
	 * @param cNombre - cNombre define el nombre de la ciudad.
	 */
	public Ciudad(String cNombre) {
		this.cNombre = cNombre;
		this.ciudadesCruceros = new HashSet<CiudadCrucero>();
		this.servicios = new HashSet<Servicio>();
	}
	
	/**
	 * Constructor de la entidad Ciudad
	 * 
	 * @param cNombre - cNombre define el nombre de la ciudad.
	 * @param cDescripcion - cDescripcion define una breve descripción de la historia y ubicación de la ciudad.
	 */
	
	public Ciudad(String cNombre, String cDescripcion) {
		this.cNombre = cNombre;
		this.cDescripcion = cDescripcion;
		this.servicios = new HashSet<Servicio>();
		this.ciudadesCruceros = new HashSet<CiudadCrucero>();
	}
	
	/**
	 * Constructor de la entidad Ciudad
	 * 
	 * @param cNombre - cNombre define el nombre de la ciudad.
	 * @param cCiudadCruceros - cCiudadCruceros define el conjunto de cruceros que pasarán por los puertos de la ciudad.
	 */
	
	public Ciudad(String cNombre, Set<CiudadCrucero> cCiudadCrucero) {
		this.cNombre = cNombre;
		this.servicios = new HashSet<Servicio>();
		this.ciudadesCruceros = new HashSet<CiudadCrucero>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getcNombre() {
		return cNombre;
	}

	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getcDescripcion() {
		return cDescripcion;
	}

	public void setcDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
	}

	@Override
	public String toString() {
		return this.cNombre;
	}

	public Set<CiudadCrucero> getCiudadesCruceros() {
		return ciudadesCruceros;
	}

	public void setCiudadesCruceros(Set<CiudadCrucero> ciudadesCruceros) {
		this.ciudadesCruceros = ciudadesCruceros;
	}

	public Set<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}
}
