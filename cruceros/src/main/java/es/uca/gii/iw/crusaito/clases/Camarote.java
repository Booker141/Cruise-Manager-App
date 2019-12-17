package es.uca.gii.iw.crusaito.clases;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Camarote {

	@Id
	@GeneratedValue
	private Long id;
	private String cTipo;
	private String caImagen;
	@ManyToOne(fetch = FetchType.LAZY)
	private Barco cBarco;
	@ManyToOne(fetch = FetchType.LAZY)
	private Crucero cCrucero;
	private boolean isReservada;

	
	public Camarote(String cTipo, boolean isReservada) {
		this.cTipo = cTipo;
		this.isReservada = isReservada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcTipo() {
		return cTipo;
	}

	public void setcTipo(String cTipo) {
		this.cTipo = cTipo;
	}

	public boolean isReservada() {
		return isReservada;
	}

	public void setReservada(boolean isReservada) {
		this.isReservada = isReservada;
	}

	public String getCaImagen() {
		return caImagen;
	}

	public void setCaImagen(String caImagen) {
		this.caImagen = caImagen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isReservada ? 1231 : 1237);
		result = prime * result + ((cTipo == null) ? 0 : cTipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camarote other = (Camarote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isReservada != other.isReservada)
			return false;
		if (cTipo == null) {
			if (other.cTipo != null)
				return false;
		} else if (!cTipo.equals(other.cTipo))
			return false;
		return true;
	}

	public Barco getcBarco() {
		return cBarco;
	}

	public void setcBarco(Barco cBarco) {
		this.cBarco = cBarco;
	}

	public Crucero getcCrucero() {
		return cCrucero;
	}

	public void setcCrucero(Crucero cCrucero) {
		this.cCrucero = cCrucero;
	}
	
	
}
