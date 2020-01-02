package es.uca.gii.iw.crusaito.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ciudad {

	@Id
	@GeneratedValue
	private long id;
	private String cNombre;
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="ciudades")
	private List<Crucero> cCruceros;
	
	public Ciudad() {}
	
	public Ciudad(String cNombre) {
		this.cNombre = cNombre;
		this.cCruceros = new ArrayList<>();
	}
	
	public Ciudad(String cNombre, List<Crucero> cCruceros) {
		this.cNombre = cNombre;
		this.cCruceros = cCruceros;
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

	public List<Crucero> getcCruceros() {
		return cCruceros;
	}

	public void setcCruceros(List<Crucero> cCruceros) {
		this.cCruceros = cCruceros;
	}
	
	public void addcCruceros(Crucero crucero) {
		this.cCruceros.add(crucero);
	}
}
