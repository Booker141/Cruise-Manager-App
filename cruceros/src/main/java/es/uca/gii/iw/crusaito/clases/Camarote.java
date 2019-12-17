package es.uca.gii.iw.crusaito.clases;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Camarote {
	
	@Id
	@GeneratedValue
	private Long id;
	private String tipo;
	@ManyToOne(fetch = FetchType.LAZY)
	private Barco idBarco;
	@ManyToOne(fetch = FetchType.LAZY)
	private Crucero idCrucero;
	private boolean isReservada;
	
	
	public Camarote(Long id, String tipo, boolean isReservada) {
		this.id = id;
		this.tipo = tipo;
		this.isReservada = isReservada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isReservada() {
		return isReservada;
	}

	public void setReservada(boolean isReservada) {
		this.isReservada = isReservada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isReservada ? 1231 : 1237);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	public Barco getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(Barco idBarco) {
		this.idBarco = idBarco;
	}

	public Crucero getIdCrucero() {
		return idCrucero;
	}

	public void setIdCrucero(Crucero idCrucero) {
		this.idCrucero = idCrucero;
	}
	
	
}
