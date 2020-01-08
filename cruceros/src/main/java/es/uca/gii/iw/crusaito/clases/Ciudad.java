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
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "ciudad_crucero",
    	joinColumns = {@JoinColumn(name = "ciudad_id", referencedColumnName = "id")},
    	inverseJoinColumns = {@JoinColumn(name = "crucero_id", referencedColumnName = "id")}
	)
	private Set<Crucero> cruceros;
	*/
	@OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
	private Set<CiudadCrucero> ciudadesCruceros;
	
	public Ciudad() {}
	
	public Ciudad(String cNombre) {
		this.cNombre = cNombre;
		//this.cruceros = new HashSet<Crucero>();
		this.ciudadesCruceros = new HashSet<CiudadCrucero>();
	}
	
	public Ciudad(String cNombre, String cDescripcion) {
		this.cNombre = cNombre;
		this.cDescripcion = cDescripcion;
		//this.cruceros = new HashSet<Crucero>();
		this.ciudadesCruceros = new HashSet<CiudadCrucero>();
	}
	
	public Ciudad(String cNombre, Set<Crucero> cCruceros) {
		this.cNombre = cNombre;
		//this.cruceros = cCruceros;
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

	
	/*public Set<Crucero> getCruceros() {
		return cruceros;
	}

	public void setCruceros(Set<Crucero> cruceros) {
		this.cruceros = cruceros;
	}*/
	
	/*public void addCruceros(Crucero crucero) {
		this.cruceros.add(crucero);
		crucero.getCiudades().add(this);
	}
	
	public void removeCrucero(Crucero crucero) {
		this.cruceros.remove(crucero);
		crucero.getCiudades().remove(this);
	}*/

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
}
