package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Barco 
{	
	@Id
	@GeneratedValue
	private long id;
	private String bNombre;
	private String bCodCamarote;
	private String bImagen;
	private int bAforoPasajeros;
	private int bAforoTripulantes;
	private int bPeso;
	private LocalDate bFchPuestaServicio;
	private String bOrigen;
	private String bDestino;
	private String bDescripcion;
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Ciudad> Ciudad;

	protected Barco() {}
	
	public Barco(String bNombre, String bCodCamarote,String bImagen, int bAforoPasajeros, int bAforoTripulantes,
			int bPeso, LocalDate bFchPuestaServicio, String bOrigen, String bDestino, String bDescripcion) {
		
		this.bNombre = bNombre;
		this.bCodCamarote = bCodCamarote;
		this.bImagen = bImagen;
		this.bAforoPasajeros = bAforoPasajeros;
		this.bAforoTripulantes = bAforoTripulantes;
		this.bPeso = bPeso;
		this.bFchPuestaServicio = bFchPuestaServicio;
		this.bOrigen = bOrigen;
		this.bDestino = bDestino;
		this.bDescripcion = bDescripcion;
	}
	
	public String getbImagen() {
		return bImagen;
	}
	
	public void setbImagen(String bImagen) {
		this.bImagen = bImagen;
	}
	public String getbNombre() {
		return bNombre;
	}

	public void setbNombre(String bNombre) {
		this.bNombre = bNombre;
	}

	public String getbCodCamarote() {
		return bCodCamarote;
	}

	public int getbAforoPasajeros() {
		return bAforoPasajeros;
	}

	public int getbAforoTripulantes() {
		return bAforoTripulantes;
	}

	public void setbAforoTripulantes(int bAforoTripulantes) {
		this.bAforoTripulantes = bAforoTripulantes;
	}

	public int getbPeso() {
		return bPeso;
	}

	public LocalDate getbFchPuestaServicio() {
		return bFchPuestaServicio;
	}

	public String getbOrigen() {
		return bOrigen;
	}

	public void setbOrigen(String bOrigen) {
		this.bOrigen = bOrigen;
	}

	public String getbDestino() {
		return bDestino;
	}

	public void setbDestino(String bDestino) {
		this.bDestino = bDestino;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Barco other = (Barco) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getbDescripcion() {
		return bDescripcion;
	}

	public void setbDescripcion(String bDescripcion) {
		this.bDescripcion = bDescripcion;
	}
}