package es.uca.gii.iw.crusaito.clases;

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
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Barco> Barco;
	
	public Ciudad(long id, String cNombre) {
		this.id = id;
		this.cNombre = cNombre;
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
}
